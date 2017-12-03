package compression;

import javafx.util.Pair;

/**
 * Classe que implementa o algoritmo de compressão LZ77.
 */
public class LZ77CompressionAlgorithm implements CompressionAlgorithm {

    /**
     * Tamanho da janela de procura. Nesta implementação,
     * pode ter tamanho máximo 63 (2^6 - 1), para que a
     * distância até o maior padrão casado possa ser
     * representada com 6 bits.
     */
    private static final int WINDOW_SIZE = 63;

    /**
     * Tamanho do buffer de look-ahead. Nesta implementação,
     * pode ter tamanho máximo 3 (2^2 - 1), para que o comprimento
     * do maior padrão casado possa ser representado com 2 bits.
     *
     * Como a aplicação, neste caso, é para textos com linguagem
     * natural, tamanho 3 é suficiente, pois raramente padrões
     * com tamanho maior que 3 se repetem nos textos processados.
     */
    private static final int BUFFER_SIZE = 3;

    /**
     * Método que comprime o conteúdo recebido em forma de String
     * e o escreve através do BinaryOutputStream.
     *
     * O formato da saída das tuplas <a, b, C> onde a e b são inteiros
     * e representam, respectivamente, a distância até o maior padrão
     * e seu comprimento e C o último caracter consumido, é o seguinte:
     *
     * Caso não seja encontrado nenhum padrão, a tupla <0, 0, C> é
     * representada com um flag de um bit (0), indicando que nenhum
     * padrão foi encontrado, seguido de um byte representando o
     * caracter C.
     *
     * Caso um padrão seja encontrado, a tupla <a, b, C>, com a e b
     * diferentes de 0, é representada com um flag de um bit (1),
     * indicando que um padrão foi encontrado, seguido de um byte
     * composto pelos 6 primeiros bits representando a distância
     * até o maior padrão casado e os dois últimos bits representando
     * o tamanho de tal padrão.
     *
     * @param content Conteúdo a ser comprimido
     * @param out BinaryOutputStream que escreve no arquivo
     */
    @Override
    public void compress(String content, BinaryOutputStream out) {
        int i = 0;
        while (i < content.length()) {
            // Obtém a distância até e o comprimento do maior padrão casado
            Pair<Integer, Integer> match = findLongestMatch(content, i);

            if (match != null) { // Se um padrão for encontrado
                int bestMatchDistance = match.getKey();
                int bestMatchLength = match.getValue();

                out.write(true); // Escreve a flag 1
                out.write(bestMatchDistance << 2, 6); // Escreve os 6 últimos bits da distância
                out.write(bestMatchLength << 6, 2); // Escreve os 2 últimos bits do comprimento

                i += bestMatchLength;
            } else {
                out.write(false); // Escreve a flag 0
                out.write(content.charAt(i)); // Escreve o caracter consumido

                i++;
            }
        }
    }

    /**
     * Função que encontra o maior padrão na janela de procura a partir
     * da posição recebida do conteúdo.
     *
     * @param content Conteúdo a ser comprimido
     * @param position Posição do início do padrão a ser procurado
     * @return Um par de inteiros contendo a distância até o maior padrão
     * e seu tamanho.
     */
    private Pair<Integer, Integer> findLongestMatch(String content, int position) {
        int endOfBuffer = Math.min(position + BUFFER_SIZE, content.length() + 1);

        int bestMatchDistance = -1;
        int bestMatchLength = -1;

        for (int i = position + 2; i < endOfBuffer; i++) {
            int startIndex = Math.max(0, position - WINDOW_SIZE);
            String substring = content.substring(position, i);

            for (int j = startIndex; j < position; j++) {
                int repetitions = substring.length() / (position - j);

                int last = substring.length() % (position - j);

                String matchedString = new String(new char[repetitions]).replace("\0", content.substring(j, position)) + content.substring(j, j + last);

                if (matchedString.equals(substring) && substring.length() > bestMatchLength) {
                    bestMatchDistance = position - j;
                    bestMatchLength = substring.length();
                }
            }
        }

        if (bestMatchDistance > 0 && bestMatchLength > 0)
            return new Pair<>(bestMatchDistance, bestMatchLength);

        return null;
    }
}

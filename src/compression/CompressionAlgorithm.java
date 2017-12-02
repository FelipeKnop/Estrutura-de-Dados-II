package compression;

/**
 * Interface que define o comportamento dos algoritmos de compressão.
 */
public interface CompressionAlgorithm {

    /**
     * Função abstrata (deve ser implementada por cada classe) que
     * recebe uma String contendo um conteúdo qualquer e
     * retorna uma nova array de bytes com o conteúdo comprimido.
     * @param content Conteúdo a ser comprimido
     * @return Conteúdo comprimido
     */
    byte[] compress(String content);
}

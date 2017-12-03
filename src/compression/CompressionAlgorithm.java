package compression;

/**
 * Interface que define o comportamento dos algoritmos de compressão.
 */
public interface CompressionAlgorithm {

    /**
     * Função abstrata (deve ser implementada por cada classe) que
     * recebe uma String contendo um conteúdo qualquer e um
     * {@link BinaryOutputStream} que receberá o resultado da
     * compressão.
     * @param content Conteúdo a ser comprimido
     * @param out BinaryOutputStream que escreve no arquivo
     */
    void compress(String content, BinaryOutputStream out);
}

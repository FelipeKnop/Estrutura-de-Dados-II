package compression;

import java.io.File;

/**
 * Interface que define o comportamento dos algoritmos de compressão.
 */
public interface CompressionAlgorithm {

    /**
     * Função abstrata (deve ser implementada por cada classe) que
     * recebe um arquivo com um conteúdo qualquer e sobrescreve o
     * arquivo com o conteúdo comprimido.
     * @param file Arquivo que contém o que deve ser
     *                    comprimido e recebe o novo conteúdo
     */
    void compress(File file);
}

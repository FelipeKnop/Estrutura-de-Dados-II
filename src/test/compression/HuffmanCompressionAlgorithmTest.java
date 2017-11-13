package test.compression;

import compression.CompressionAlgorithm;
import compression.HuffmanCompressionAlgorithm;

/**
 * Classe de testes para o algoritmo de compressão Huffman.
 */
public class HuffmanCompressionAlgorithmTest extends CompressionAlgorithmTest {

    @Override
    protected CompressionAlgorithm getCompressionAlgorithm() {
        return new HuffmanCompressionAlgorithm();
    }

    // TODO
    @Override
    protected String getResponseBytesString() {
        return "";
    }
}

package test.compression;

import compression.CompressionAlgorithm;
import compression.LZWCompressionAlgorithm;

/**
 * Classe de testes para o algoritmo de compressão LZW.
 */
public class LZWCompressionAlgorithmTest extends CompressionAlgorithmTest {

    @Override
    protected CompressionAlgorithm getCompressionAlgorithm() {
        return new LZWCompressionAlgorithm();
    }

    // TODO
    @Override
    protected String getResponseBytesString() {
        return "";
    }
}

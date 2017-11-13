package test.compression;

import compression.CompressionAlgorithm;
import compression.LZ78CompressionAlgorithm;

/**
 * Classe de testes para o algoritmo de compressão LZ78.
 */
public class LZ78CompressionAlgorithmTest extends CompressionAlgorithmTest {

    @Override
    protected CompressionAlgorithm getCompressionAlgorithm() {
        return new LZ78CompressionAlgorithm();
    }

    // TODO
    @Override
    protected String getResponseBytesString() {
        return "";
    }
}

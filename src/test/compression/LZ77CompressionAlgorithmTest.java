package test.compression;

import compression.CompressionAlgorithm;
import compression.LZ77CompressionAlgorithm;

/**
 * Classe de testes para o algoritmo de compressão LZ77.
 */
public class LZ77CompressionAlgorithmTest extends CompressionAlgorithmTest {

    @Override
    protected CompressionAlgorithm getCompressionAlgorithm() {
        return new LZ77CompressionAlgorithm();
    }

    // TODO
    @Override
    protected String getResponseBytesString() {
        return "";
    }
}

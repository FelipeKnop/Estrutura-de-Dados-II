package compression;

public class LZWCompressionAlgorithm implements CompressionAlgorithm {

    @Override
    public byte[] compress(byte[] content) {
        return content;
    }
}

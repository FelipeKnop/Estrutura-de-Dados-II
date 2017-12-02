package compression;

public class LZWCompressionAlgorithm implements CompressionAlgorithm {

    // TODO
    @Override
    public byte[] compress(String content) {
        return content.getBytes();
    }
}

package compression;

public class LZ78CompressionAlgorithm implements CompressionAlgorithm {

    // TODO
    @Override
    public byte[] compress(String content) {
        return content.getBytes();
    }
}

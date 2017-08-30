package hash;

public class DJB2Hash implements HashingAlgorithm {

    @Override
    public int hashFunction(int value, int mod) {
        int hash = 5381;
        return (((hash << 5) + hash) + value) % mod;
    }
}

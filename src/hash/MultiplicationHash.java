package hash;

public class MultiplicationHash implements HashingAlgorithm {

    private static final Double phi = 0.6180339887;

    @Override
    public int hashFunction(int value, int mod) {
        return (int) Math.floor(mod * (value * phi - Math.floor(value * phi)));
    }
}

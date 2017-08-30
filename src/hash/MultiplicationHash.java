package hash;

public class MultiplicationHash implements HashingAlgorithm {

    private static final Double phi = 0.6180339887;

    @Override
    public Integer hash(Integer value, Integer tSize) {
        return (int) Math.floor(tSize * (value * phi - Math.floor(value * phi)));
    }
}

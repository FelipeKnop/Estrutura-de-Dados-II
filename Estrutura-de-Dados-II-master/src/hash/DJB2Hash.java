package hash;

public class DJB2Hash implements HashingAlgorithm {

    @Override
    public Integer hash(Integer value, Integer tSize) {
        Integer hash = 5381;
        return (((hash << 5) + hash) + value) % tSize;
    }
}

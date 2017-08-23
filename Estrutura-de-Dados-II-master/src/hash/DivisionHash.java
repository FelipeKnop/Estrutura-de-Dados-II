package hash;

import java.math.BigInteger;

public class DivisionHash implements HashingAlgorithm {

    @Override
    public Integer hash(Integer value, Integer tSize) {
        Integer nextPrime = BigInteger.valueOf(tSize).nextProbablePrime().intValue();
        return (value % nextPrime) % tSize;
    }
}

package hash;

import java.math.BigInteger;

public class KnuthDivisionHash implements HashingAlgorithm {

    @Override
    public Integer hash(Integer value, Integer tSize) {
        Integer nextPrime = BigInteger.valueOf(tSize).nextProbablePrime().intValue();
        return ((value * (value + 3)) % nextPrime) % tSize;
    }
}

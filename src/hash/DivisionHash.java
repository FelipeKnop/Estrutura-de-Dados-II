package hash;

import java.math.BigInteger;

public class DivisionHash implements HashingAlgorithm {

    @Override
    public int hashFunction(int value, int mod) {
        int nextPrime = BigInteger.valueOf(mod).nextProbablePrime().intValue();
        return (value % nextPrime) % mod;
    }
}

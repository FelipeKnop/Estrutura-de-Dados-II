package hash;

import java.math.BigInteger;

public class KnuthDivisionHash implements HashingAlgorithm {

    @Override
    public int hashFunction(int value, int mod) {
        int nextPrime = BigInteger.valueOf(mod).nextProbablePrime().intValue();
        return ((value * (value + 3)) % nextPrime) % mod;
    }
}

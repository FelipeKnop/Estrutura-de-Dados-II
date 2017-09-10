package test.hash;

import hash.DJB2Hash;
import hash.HashingAlgorithm;

public class DJB2HashTest extends HashingAlgorithmTest {

    @Override
    protected int getAmountOfValues() {
        return 100;
    }

    @Override
    protected HashingAlgorithm getHashingAlgorithm() {
        return new DJB2Hash(200);
    }
}

package test.hash;

import hash.HashingAlgorithm;
import hash.MultiplicationHash;

public class MultiplicationHashTest extends HashingAlgorithmTest {

    @Override
    protected int getAmountOfValues() {
        return 100;
    }

    @Override
    protected HashingAlgorithm getHashingAlgorithm() {
        return new MultiplicationHash(200);
    }
}

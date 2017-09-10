package test.hash;

import hash.HashingAlgorithm;
import hash.KnuthDivisionHash;

public class KnuthDivisionHashTest extends HashingAlgorithmTest {

    @Override
    protected int getAmountOfValues() {
        return 100;
    }

    @Override
    protected HashingAlgorithm getHashingAlgorithm() {
        return new KnuthDivisionHash(200);
    }
}

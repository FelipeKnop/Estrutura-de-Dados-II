package test.hash;

import hash.HashingAlgorithm;
import hash.MultiplicationHash;

/**
 * Classe de testes para os métodos da classe {@link MultiplicationHash}
 */
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

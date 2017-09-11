package test.hash;

import hash.DivisionHash;
import hash.HashingAlgorithm;

/**
 * Classe de testes para os métodos da classe {@link DivisionHash}
 */
public class DivisionHashTest extends HashingAlgorithmTest {

    @Override
    protected int getAmountOfValues() {
        return 100;
    }

    @Override
    protected HashingAlgorithm getHashingAlgorithm() {
        return new DivisionHash(200);
    }
}

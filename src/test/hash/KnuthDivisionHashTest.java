package test.hash;

import hash.HashingAlgorithm;
import hash.KnuthDivisionHash;

/**
 * Classe de testes para os métodos da classe {@link KnuthDivisionHash}
 */
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

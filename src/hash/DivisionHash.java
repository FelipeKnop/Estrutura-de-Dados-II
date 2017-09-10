package hash;

import java.math.BigInteger;

/**
 * Classe que extende a classe abstrata {@link HashingAlgorithm HashingAlgorithm} utilizando o algoritmo
 * da divisão visto em aula.
 */
public class DivisionHash extends HashingAlgorithm {

    public DivisionHash(int tableSize) {
        super(tableSize);
    }

    /**
     * Implementação padrão do algoritmo da divisão visto em aula.

     * @param value Valor a ser gerado o hash
     * @return Hash gerado pela função de hashing a partir do valor recebido
     */
    @Override
    int hashingFunction(Long value) {
        int nextPrime = BigInteger.valueOf(tableSize).nextProbablePrime().intValue();
        return (int) ((value % nextPrime) % tableSize);
    }
}

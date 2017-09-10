package hash;

import java.math.BigInteger;

/**
 * Classe que extende a classe abstrata {@link HashingAlgorithm HashingAlgorithm} utilizando o algoritmo
 * da divisão de Knuth.
 */
public class KnuthDivisionHash extends HashingAlgorithm {

    public KnuthDivisionHash(int tableSize) {
        super(tableSize);
    }

    /**
     * Implementação padrão do algoritmo da divisão de Knuth, que é uma variação
     * do algoritmo da divisão visto em aula.
     *
     * Enquanto que no algoritmo da divisão padrão é feito (k % p) % t, sendo
     * k o valor a ser inserido, t o tamanho da tabela hash e p o próximo primo
     * maior que t, nessa variação é feito (k * (k + 3) % p) % t.
     *
     * @param value Valor a ser gerado o hash
     * @return Hash gerado pela função de hashing a partir do valor recebido
     */
    @Override
    int hashingFunction(Long value) {
        int nextPrime = BigInteger.valueOf(tableSize).nextProbablePrime().intValue();
        return (int) (((value * (value + 3)) % nextPrime) % tableSize);
    }
}

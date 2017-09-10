package hash;

/**
 * Classe que extende a classe abstrata {@link HashingAlgorithm HashingAlgorithm} utilizando o algoritmo
 * da multiplicação visto em aula.
 */
public class MultiplicationHash extends HashingAlgorithm {

    /**
     * Aproximação do valor de phi.
     */
    private static final Double phi = 0.6180339887;

    public MultiplicationHash(int tableSize) {
        super(tableSize);
    }

    /**
     * Implementação padrão do algoritmo da multiplicação visto em aula.

     * @param value Valor a ser gerado o hash
     * @return Hash gerado pela função de hashing a partir do valor recebido
     */
    @Override
    int hashingFunction(Long value) {
        return (int) Math.floor(tableSize * (value * phi - Math.floor(value * phi)));
    }
}

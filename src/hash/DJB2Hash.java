package hash;

/**
 * Classe que extende a classe abstrata {@link HashingAlgorithm HashingAlgorithm} utilizando o algoritmo
 * DJB2.
 */
public class DJB2Hash extends HashingAlgorithm {

    public DJB2Hash(int tableSize) {
        super(tableSize);
    }

    /**
     * Implementação do algoritmo DJB2 para números.
     *
     * @param value Valor a ser gerado o hash
     * @return Hash gerado pela função de hashing a partir do valor recebido
     */
    @Override
    public int hashingFunction(Long value) {
        int hash = 5381;
        return (int) ((((hash << 5) + hash) + value) % tableSize);
    }
}

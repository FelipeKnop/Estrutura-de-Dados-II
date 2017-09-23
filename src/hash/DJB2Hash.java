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

    /**
     * Implementação padrão do algoritmo DJB2.
     *
     * @param word Palavra a ser gerado o hash
     * @return Hash gerado pela função de hashing a partir da palavra recebida
     */
    public int hashingFunction(String word) {
        long hash = 5831;
        for (int i = 0; i < word.length(); i++)
            hash = ((hash << 5) + hash) + (int) word.charAt(i);
        return (int) Math.abs(hash % tableSize);
    }
}

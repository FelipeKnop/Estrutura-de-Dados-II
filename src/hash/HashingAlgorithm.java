package hash;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe abstrata que contém os métodos de inserção para os algoritmos de hashing.
 */
public abstract class HashingAlgorithm {

    /**
     * Número de colisões durante a inserção de elementos.
     */
    private long collisions = 0;

    /**
     * Número de comparações de chaves durante a inserção de elementos.
     */
    private long comparisons = 0;

    /**
     * Tamanho da tabela.
     */
    int tableSize;

    /**
     * Tabela hash.
     */
    private final ArrayList<Long> hashTable;

    public HashingAlgorithm(int tableSize) {
        this.tableSize = tableSize;
        this.hashTable = new ArrayList<>(Collections.nCopies(tableSize, null));
    }

    /**
     * Método de inserção de valores na tabela hash. Qualquer valor inserido
     * deve ser um número capaz de ser representado em forma de Long.
     * @param value Valor a ser inserido na tabela hash.
     */
    public void insert(Long value) {
        int hash = hashingFunction(value);
        if (hashTable.get(hash) != null) collisions++;
        comparisons++;
        hashTable.set(hash, value);
    }

    /**
     * Método abstrato (deve ser implementado por cada classe) que retorna
     * um hash inteiro a partir de um valor Long recebido. É usado para a
     * inserção do valor na tabela hash na posição gerada por essa função.
     * @param value Valor a ser gerado o hash
     * @return Hash gerado pela função de hashing a partir do valor recebido
     */
    public abstract int hashingFunction(Long value);

    public final long getCollisions() {
        return collisions;
    }

    public final long getComparisons() {
        return comparisons;
    }
}

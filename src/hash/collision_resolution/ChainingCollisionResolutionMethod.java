package hash.collision_resolution;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe abstrata que contém os métodos de inserção para os algoritmos de hashing
 * com tratamento de colisão do tipo Encadeamento.
 */
public abstract class ChainingCollisionResolutionMethod extends CollisionResolutionMethod {

    /**
     * Tamanho da tabela.
     */
    int tableSize;

    /**
     * Tabela hash.
     */
    ArrayList<ArrayList<Long>> hashTable;

    ChainingCollisionResolutionMethod() {
        this(1);
    }

    ChainingCollisionResolutionMethod(int tableSize) {
        this.tableSize = tableSize;
        hashTable = new ArrayList<>(Collections.nCopies(tableSize, null));
    }

    /**
     * Método de inserção de valores na tabela hash. Qualquer valor inserido
     * deve ser um número capaz de ser representado em forma de Long.
     *
     * Se o hash gerado pela função de hash já possuir um valor associado na
     * tabela hash, a função de tratamento de colisão é chamada para lidar
     * com a situação.
     *
     * @param value Valor a ser inserido na tabela hash.
     */
    @Override
    public void insert(Long value) {
        int hash = hashingFunction(value);
        if (hashTable.get(hash) == null) {
            ArrayList<Long> newList = new ArrayList<>();
            newList.add(value);
            hashTable.set(hash, newList);
        } else {
            resolutionFunction(value, hash, 0);
        }
        comparisons++;
    }

    /**
     * Calcula a quantidade de memória gasta pela tabela hash.
     *
     * Cada Long ocupa 16 bytes de memória, então o gasto total é a quantidade
     * de elementos da tabela hash * 16 * tableSize.
     *
     * @return Quantidade total de memória gasta
     */
    @Override
    public long getMemorySpent() {
        long totalMemory = 0;
        for (int i = 0; i < tableSize; i++) {
            if (hashTable.get(i) != null)
                totalMemory += hashTable.get(i).size() * 16;
        }
        return totalMemory;
    }

    /**
     * Limpa a tabela hash e o contador de comparações.
     */
    @Override
    public void clear() {
        comparisons = 0;
        hashTable = new ArrayList<>(Collections.nCopies(tableSize, null));
    }

    @Override
    public void setTableSize(int tableSize) {
        this.tableSize = tableSize;
        hashTable = new ArrayList<>(Collections.nCopies(tableSize, null));
    }
}

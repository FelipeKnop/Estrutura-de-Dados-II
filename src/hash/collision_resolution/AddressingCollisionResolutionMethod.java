package hash.collision_resolution;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe abstrata que contém os métodos de inserção para os algoritmos de hashing
 * com tratamento de colisão do tipo Endereçamento.
 */
public abstract class AddressingCollisionResolutionMethod extends CollisionResolutionMethod {

    /**
     * Tamanho da tabela.
     */
    int tableSize;

    /**
     * Tabela hash.
     */
    private ArrayList<Long> hashTable;

    AddressingCollisionResolutionMethod() {
        this(1);
    }

    AddressingCollisionResolutionMethod(int tableSize) {
        this.tableSize = tableSize;
        this.hashTable = new ArrayList<>(Collections.nCopies(tableSize, null));
    }

    /**
     * Método de inserção de valores na tabela hash. Qualquer valor inserido
     * deve ser um número capaz de ser representado em forma de Long.
     *
     * Se o hash gerado pela função de hash já possuir um valor associado na
     * tabela hash, a função de tratamento de colisão é chamada para a geração
     * de um novo hash.
     *
     * @param value Valor a ser inserido na tabela hash.
     */
    @Override
    public void insert(Long value) {
        int hash = hashingFunction(value);
        int iteration = 1;
        while (hashTable.get(hash) != null) {
            hash = resolutionFunction(value, hash, iteration++);
        }
        comparisons++;
        hashTable.set(hash, value);
    }

    /**
     * Calcula a quantidade de memória gasta pela tabela hash.
     *
     * Cada Long ocupa 16 bytes de memória, então o gasto total é a quantidade
     * de elementos da tabela hash * 16.
     *
     * @return Quantidade total de memória gasta
     */
    @Override
    public long getMemorySpent() {
        return hashTable.size() * 16;
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
    public final void setTableSize(int tableSize) {
        this.tableSize = tableSize;
        this.hashTable = new ArrayList<>(Collections.nCopies(tableSize, null));
    }
}

package hash.collision_resolution;

/**
 * Classe abstrata que contém os métodos de inserção para os algoritmos de hashing
 * com tratamento de colisão.
 */
public abstract class CollisionResolutionMethod {

    /**
     * Número de comparações de chaves durante a inserção de elementos.
     */
    long comparisons = 0;

    /**
     * Método abstrato de inserção de valores na tabela hash. Qualquer valor inserido
     * deve ser um número capaz de ser representado em forma de Long.
     * @param value Valor a ser inserido na tabela hash.
     */
    public abstract void insert(Long value);

    /**
     * Método abstrato (deve ser implementado por cada classe) que retorna
     * um hash inteiro a partir de um valor Long recebido. É usado para a
     * inserção do valor na tabela hash na posição gerada por essa função.
     * @param value Valor a ser gerado o hash
     * @return Hash gerado pela função de hashing a partir do valor recebido
     */
    public abstract int hashingFunction(Long value);

    /**
     * Método abstrato (deve ser implementado por cada classe) que retorna
     * um novo hash inteiro a partir de um valor Long recebido, o último
     * hash gerado e a iteração do tratamento de colisão.
     * @param value Valor a ser gerado o hash
     * @param lastTry Hash gerado na última tentativa, que ocasionou em colisão
     * @param iteration Iteração do tratamento de colisão
     * @return Hash gerado pela função de tratamento
     */
    public abstract int resolutionFunction(Long value, int lastTry, int iteration);

    public final long getComparisons() {
        return comparisons;
    }

    public abstract long getMemorySpent();
}

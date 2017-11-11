package tree;

/**
 * Classe abstrata que define métodos para análise das estruturas
 * de árvore.
 * @param <Key> Tipo das chaves que identificam os dados guardados
 * @param <Value> Tipo dos dados a serem guardados pelos nós da árvore
 */
public abstract class BenchmarkableTree<Key, Value> implements ITree<Key, Value> {

    /**
     * Soma do número de comparações feitas na árvore.
     */
    protected long comparisons = 0;

    /**
     * Soma do número de cópias de registros feitas na árvore.
     */
    protected long copies = 0;

    /**
     * Hora em que o método {@link #start()} foi chamado.
     */
    private long timeStarted = 0;

    public final long getComparisons() {
        return comparisons;
    }

    public final long getCopies() {
        return copies;
    }

    public final long getTimeSpent() {
        return System.currentTimeMillis() - timeStarted;
    }

    /**
     * Inicia as variáveis para análise da árvore.
     */
    public final void start() {
        this.comparisons = 0;
        this.copies = 0;
        this.timeStarted = System.currentTimeMillis();
    }
}

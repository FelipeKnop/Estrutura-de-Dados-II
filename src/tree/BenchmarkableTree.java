package tree;

public abstract class BenchmarkableTree<T extends Comparable<? super T>> implements ITree<T> {

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

    public final void start() {
        this.comparisons = 0;
        this.copies = 0;
        this.timeStarted = System.currentTimeMillis();
    }
}

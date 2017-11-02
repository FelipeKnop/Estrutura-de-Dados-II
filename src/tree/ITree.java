package tree;

/**
 * Interface que deve ser implementada por todas as classes que tem o
 * comportamento de uma árvore (inserção, busca e remoção de elementos).
 * @param <T>
 */
public interface ITree<T> {

    /**
     * Insere um elemento na árvore.
     * @param value Elemento a ser inserido
     */
    void insert(T value);

    /**
     * Busca um elemento na árvore.
     * @param value Elemento a ser buscado
     * @return Retorna o próprio elemento se for encontrado,
     * null caso contrário
     */
    T search(T value);

    /**
     * Remove um elemento da árvore.
     * @param value Elemento a ser removido
     * @return Retorna true se o elemento existir na árvore
     * e for removido, false caso contrário
     */
    boolean remove(T value);
}

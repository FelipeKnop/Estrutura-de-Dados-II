package tree;

/**
 * Interface que deve ser implementada por todas as classes que tem o
 * comportamento de uma árvore (inserção, busca e remoção de elementos),
 * assim como um método que limpa a árvore e um que valida seus nós
 * de acordo com suas regras.
 * @param <Key> Tipo das chaves que identificam os dados guardados
 * @param <Value> Tipo dos dados a serem guardados pelos nós da árvore
 */
public interface ITree<Key, Value> {

    /**
     * Insere um dado na árvore com a chave informada.
     * @param key Chave do dado a ser inserido
     * @param value Dado a ser inserido
     */
    void insert(Key key, Value value);

    /**
     * Busca um dado na árvore a partir de sua chave.
     * @param key Chave do dado a ser buscado
     * @return Retorna o dado se for encontrado,
     * null caso contrário
     */
    Value search(Key key);


    /**
     * Remove um dado da árvore a partir de sua chave.
     * @param key Chave do dado a ser removido
     * @return Retorna true se o dado existir na árvore
     * e for removido, false caso contrário
     */
    boolean remove(Key key);

    /**
     * Limpa os dados da árvore.
     */
    void clear();

    /**
     * Valida a árvore de acordo com suas regras.
     * @return True se todos os nós seguem as devidas
     * regras, False caso contrário
     */
    boolean validate();
}

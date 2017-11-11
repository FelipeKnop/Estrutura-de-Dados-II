package test.tree;

import tree.BTree;
import tree.BenchmarkableTree;

/**
 * Classe de testes para a classe {@link tree.BTree}
 */
public class BTreeTest extends SearchTreeTest {

    /**
     * Grau da árvore B a ser testada.
     */
    private static final int DEGREE = 5;

    @Override
    protected int getAmountOfElements() {
        return 100000;
    }

    @Override
    protected BenchmarkableTree<Integer, Integer> getNewSearchTree() {
        return new BTree<>(DEGREE);
    }
}

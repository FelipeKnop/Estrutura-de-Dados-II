package test.tree;

import tree.BenchmarkableTree;
import tree.SplayTree;

/**
 * Classe de teste para as operações da classe {@link SplayTree}
 */
public class SplayTreeTest extends SearchTreeTest {

    @Override
    protected int getAmountOfElements() {
        return 10000;
    }

    @Override
    protected BenchmarkableTree<Integer, Integer> getNewSearchTree() {
        return new SplayTree<>();
    }
}

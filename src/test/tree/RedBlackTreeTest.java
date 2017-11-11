package test.tree;

import tree.BenchmarkableTree;
import tree.RedBlackTree;

/**
 * Classe de teste para as operações da classe {@link RedBlackTree}.
 */
public class RedBlackTreeTest extends SearchTreeTest {

    @Override
    protected int getAmountOfElements() {
        return 10000;
    }

    @Override
    protected BenchmarkableTree<Integer, Integer> getNewSearchTree() {
        return new RedBlackTree<>();
    }
}

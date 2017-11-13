package test.tree;

import tree.AVLTree;
import tree.BenchmarkableTree;

/**
 * Classe de teste para as operações da classe {@link AVLTree}.
 */
public class AVLTreeTest extends SearchTreeTest {

    @Override
    protected int getAmountOfElements() {
        return 1000;
    }

    @Override
    protected BenchmarkableTree<Integer, Integer> getNewSearchTree() {
        return new AVLTree<>();
    }
}

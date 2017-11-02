package test.tree;

import tree.AVLTree;
import tree.BinarySearchTree;

/**
 * Classe de teste para as operações da classe {@link AVLTree}.
 */
public class AVLTreeTest extends BinarySearchTreeTest {

    @Override
    protected Integer[] getLevelOrderArray() {
        return new Integer[] {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};
    }

    @Override
    protected BinarySearchTree<Integer> getNewBinarySearchTree() {
        return new AVLTree<>();
    }

    @Override
    protected Integer[][] getRemoveLevelOrderArrays() {
        return new Integer[][] {
                {8, 4, 12, 2, 6, 10, 14, 1, 3, 7, 9, 11, 13, 15},
                {8, 4, 12, 1, 6, 10, 14, 3, 7, 9, 11, 13, 15},
                {8, 4, 11, 1, 6, 10, 14, 3, 7, 9, 13, 15},
                {8, 4, 11, 1, 7, 10, 14, 3, 9, 13, 15},
                {8, 4, 11, 1, 7, 9, 14, 3, 13, 15},
                {8, 3, 11, 1, 7, 9, 14, 13, 15},
                {8, 3, 14, 1, 7, 9, 15, 13},
                {7, 3, 14, 1, 9, 15, 13},
                {7, 3, 13, 1, 9, 15},
                {7, 3, 9, 1, 15},
                {3, 1, 9, 15},
                {3, 1, 9},
                {1, 9},
                {9},
                {}
        };
    }
}

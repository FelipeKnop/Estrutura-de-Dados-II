package test.tree;

import tree.BinarySearchTree;
import tree.SplayTree;

/**
 * Classe de teste para as operações da classe {@link SplayTree}
 */
public class SplayTreeTest extends BinarySearchTreeTest {

    @Override
    protected Integer[] getLevelOrderArray() {
        return new Integer[] {15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    }

    @Override
    protected BinarySearchTree<Integer> getNewBinarySearchTree() {
        return new SplayTree<>();
    }

    @Override
    protected Integer[][] getRemoveLevelOrderArrays() {
        return new Integer[][] {
                {4, 3, 14, 2, 12, 15, 1, 10, 13, 8, 11, 6, 9, 7},
                {1, 3, 4, 14, 12, 15, 10, 13, 8, 11, 6, 9, 7},
                {11, 3, 14, 1, 10, 13, 15, 4, 8, 6, 9, 7},
                {4, 3, 11, 1, 10, 14, 8, 13, 15, 7, 9},
                {9, 8, 11, 4, 14, 3, 7, 13, 15, 1},
                {3, 1, 8, 7, 9, 11, 14, 13, 15},
                {9, 3, 14, 1, 8, 13, 15, 7},
                {7, 3, 9, 1, 14, 13, 15},
                {13, 9, 15, 7, 3, 1},
                {9, 7, 15, 3, 1},
                {3, 1, 9, 15},
                {9, 3, 1},
                {1, 9},
                {9},
                {}
        };
    }
}

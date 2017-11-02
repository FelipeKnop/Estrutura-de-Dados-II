package test.tree;

import tree.BinarySearchTree;
import tree.RedBlackTree;

/**
 * Classe de teste para as operações da classe {@link RedBlackTree}.
 */
public class RedBlackTreeTest extends BinarySearchTreeTest {

    @Override
    protected Integer[] getLevelOrderArray() {
        return new Integer[] {4, 2, 8, 1, 3, 6, 10, 5, 7, 9, 12, 11, 14, 13, 15};
    }

    @Override
    protected BinarySearchTree<Integer> getNewBinarySearchTree() {
        return new RedBlackTree<>();
    }

    @Override
    protected Integer[][] getRemoveLevelOrderArrays() {
        return new Integer[][] {
                {4, 2, 10, 1, 3, 8, 12, 6, 9, 11, 14, 7, 13, 15},
                {10, 4, 12, 1, 8, 11, 14, 3, 6, 9, 13, 15, 7},
                {10, 4, 14, 1, 8, 11, 15, 3, 6, 9, 13, 7},
                {10, 4, 14, 1, 8, 11, 15, 3, 7, 9, 13},
                {9, 4, 14, 1, 8, 11, 15, 3, 7, 13},
                {9, 3, 14, 1, 8, 11, 15, 7, 13},
                {9, 3, 14, 1, 8, 13, 15, 7},
                {9, 3, 14, 1, 7, 13, 15},
                {9, 3, 13, 1, 7, 15},
                {9, 3, 15, 1, 7},
                {9, 3, 15, 1},
                {3, 1, 9},
                {1, 9},
                {9},
                {}
        };
    }
}

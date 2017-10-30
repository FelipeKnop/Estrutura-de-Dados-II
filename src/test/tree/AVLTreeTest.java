package test.tree;

import org.junit.Test;
import tree.AVLTree;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AVLTreeTest {

    private static final Integer[] TEST_ARRAY = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    private static final Integer[] LEVEL_ORDER_ARRAY = new Integer[] {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};

    @Test
    public void insertTest() {
        AVLTree<Integer> avlTree = new AVLTree<>();

        for (Integer number : TEST_ARRAY)
            avlTree.insert(number);

        assertArrayEquals(getLevelOrderElements(avlTree), LEVEL_ORDER_ARRAY);
    }

    @Test
    public void searchTest() {
        AVLTree<Integer> avlTree = new AVLTree<>();

        for (Integer number : TEST_ARRAY)
            assertEquals(avlTree.search(number), null);

        for (Integer number : TEST_ARRAY)
            avlTree.insert(number);

        for (Integer number : TEST_ARRAY)
            assertEquals(avlTree.search(number), number);

        assertArrayEquals(getLevelOrderElements(avlTree), LEVEL_ORDER_ARRAY);
    }

    @Test
    public void removeTest() {
        AVLTree<Integer> avlTree = new AVLTree<>();

        for (Integer number : TEST_ARRAY)
            assertEquals(avlTree.remove(number), false);

        for (Integer number : TEST_ARRAY)
            avlTree.insert(number);

        avlTree.remove(5);
        assertArrayEquals(getLevelOrderElements(avlTree), new Integer[] {8, 4, 12, 2, 6, 10, 14, 1, 3, 7, 9, 11, 13, 15});

        avlTree.remove(2);
        assertArrayEquals(getLevelOrderElements(avlTree), new Integer[] {8, 4, 12, 1, 6, 10, 14, 3, 7, 9, 11, 13, 15});

        avlTree.remove(12);
        assertArrayEquals(getLevelOrderElements(avlTree), new Integer[] {8, 4, 11, 1, 6, 10, 14, 3, 7, 9, 13, 15});

        avlTree.remove(6);
        assertArrayEquals(getLevelOrderElements(avlTree), new Integer[] {8, 4, 11, 1, 7, 10, 14, 3, 9, 13, 15});

        avlTree.remove(10);
        assertArrayEquals(getLevelOrderElements(avlTree), new Integer[] {8, 4, 11, 1, 7, 9, 14, 3, 13, 15});

        avlTree.remove(4);
        assertArrayEquals(getLevelOrderElements(avlTree), new Integer[] {8, 3, 11, 1, 7, 9, 14, 13, 15});

        avlTree.remove(11);
        assertArrayEquals(getLevelOrderElements(avlTree), new Integer[] {8, 3, 14, 1, 7, 9, 15, 13});

        avlTree.remove(8);
        assertArrayEquals(getLevelOrderElements(avlTree), new Integer[] {7, 3, 14, 1, 9, 15, 13});

        avlTree.remove(14);
        assertArrayEquals(getLevelOrderElements(avlTree), new Integer[] {7, 3, 13, 1, 9, 15});

        avlTree.remove(13);
        assertArrayEquals(getLevelOrderElements(avlTree), new Integer[] {7, 3, 9, 1, 15});

        avlTree.remove(7);
        assertArrayEquals(getLevelOrderElements(avlTree), new Integer[] {3, 1, 9, 15});

        avlTree.remove(15);
        assertArrayEquals(getLevelOrderElements(avlTree), new Integer[] {3, 1, 9});

        avlTree.remove(3);
        assertArrayEquals(getLevelOrderElements(avlTree), new Integer[] {1, 9});

        avlTree.remove(1);
        assertArrayEquals(getLevelOrderElements(avlTree), new Integer[] {9});

        avlTree.remove(9);
        assertArrayEquals(getLevelOrderElements(avlTree), new Integer[] {});
    }

    private Integer[] getLevelOrderElements(AVLTree<Integer> avlTree) {
        List<Integer> elements = avlTree.levelOrderTraversal();
        return elements.toArray(new Integer[elements.size()]);
    }
}

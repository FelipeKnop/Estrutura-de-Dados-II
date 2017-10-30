package test.tree;

import org.junit.Test;
import tree.RedBlackTree;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RedBlackTreeTest {

    private static final Integer[] TEST_ARRAY = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    private static final Integer[] LEVEL_ORDER_ARRAY = new Integer[] {4, 2, 8, 1, 3, 6, 10, 5, 7, 9, 12, 11, 14, 13, 15};

    @Test
    public void insertTest() {
        RedBlackTree<Integer> redBlackTree = new RedBlackTree<>();

        for (Integer number : TEST_ARRAY)
            redBlackTree.insert(number);

        assertArrayEquals(getLevelOrderElements(redBlackTree), LEVEL_ORDER_ARRAY);
    }

    @Test
    public void searchTest() {
        RedBlackTree<Integer> redBlackTree = new RedBlackTree<>();

        for (Integer number : TEST_ARRAY)
            assertEquals(redBlackTree.search(number), null);

        for (Integer number : TEST_ARRAY)
            redBlackTree.insert(number);

        for (Integer number : TEST_ARRAY)
            assertEquals(redBlackTree.search(number), number);

        assertArrayEquals(getLevelOrderElements(redBlackTree), LEVEL_ORDER_ARRAY);
    }

    @Test
    public void removeTest() {
        RedBlackTree<Integer> redBlackTree = new RedBlackTree<>();

        for (Integer number : TEST_ARRAY)
            assertEquals(redBlackTree.remove(number), false);

        for (Integer number : TEST_ARRAY)
            redBlackTree.insert(number);

        redBlackTree.remove(5);
        assertArrayEquals(getLevelOrderElements(redBlackTree), new Integer[] {4, 2, 10, 1, 3, 8, 12, 6, 9, 11, 14, 7, 13, 15});

        redBlackTree.remove(2);
        assertArrayEquals(getLevelOrderElements(redBlackTree), new Integer[] {10, 4, 12, 1, 8, 11, 14, 3, 6, 9, 13, 15, 7});

        redBlackTree.remove(12);
        assertArrayEquals(getLevelOrderElements(redBlackTree), new Integer[] {10, 4, 14, 1, 8, 11, 15, 3, 6, 9, 13, 7});

        redBlackTree.remove(6);
        assertArrayEquals(getLevelOrderElements(redBlackTree), new Integer[] {10, 4, 14, 1, 8, 11, 15, 3, 7, 9, 13});

        redBlackTree.remove(10);
        assertArrayEquals(getLevelOrderElements(redBlackTree), new Integer[] {9, 4, 14, 1, 8, 11, 15, 3, 7, 13});

        redBlackTree.remove(4);
        assertArrayEquals(getLevelOrderElements(redBlackTree), new Integer[] {9, 3, 14, 1, 8, 11, 15, 7, 13});

        redBlackTree.remove(11);
        assertArrayEquals(getLevelOrderElements(redBlackTree), new Integer[] {9, 3, 14, 1, 8, 13, 15, 7});

        redBlackTree.remove(8);
        assertArrayEquals(getLevelOrderElements(redBlackTree), new Integer[] {9, 3, 14, 1, 7, 13, 15});

        redBlackTree.remove(14);
        assertArrayEquals(getLevelOrderElements(redBlackTree), new Integer[] {9, 3, 13, 1, 7, 15});

        redBlackTree.remove(13);
        assertArrayEquals(getLevelOrderElements(redBlackTree), new Integer[] {9, 3, 15, 1, 7});

        redBlackTree.remove(7);
        assertArrayEquals(getLevelOrderElements(redBlackTree), new Integer[] {9, 3, 15, 1});

        redBlackTree.remove(15);
        assertArrayEquals(getLevelOrderElements(redBlackTree), new Integer[] {3, 1, 9});

        redBlackTree.remove(3);
        assertArrayEquals(getLevelOrderElements(redBlackTree), new Integer[] {1, 9});

        redBlackTree.remove(1);
        assertArrayEquals(getLevelOrderElements(redBlackTree), new Integer[] {9});

        redBlackTree.remove(9);
        assertArrayEquals(getLevelOrderElements(redBlackTree), new Integer[] {});
    }

    private Integer[] getLevelOrderElements(RedBlackTree<Integer> redBlackTree) {
        List<Integer> elements = redBlackTree.levelOrderTraversal();
        return elements.toArray(new Integer[elements.size()]);
    }
}

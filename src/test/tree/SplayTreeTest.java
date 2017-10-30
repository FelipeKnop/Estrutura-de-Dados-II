package test.tree;

import org.junit.Test;
import tree.SplayTree;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SplayTreeTest {

    private static final Integer[] TEST_ARRAY = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    private static final Integer[] LEVEL_ORDER_ARRAY = new Integer[] {15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

    @Test
    public void insertTest() {
        SplayTree<Integer> splayTree = new SplayTree<>();

        for (Integer number : TEST_ARRAY)
            splayTree.insert(number);

        assertArrayEquals(getLevelOrderElements(splayTree), LEVEL_ORDER_ARRAY);
    }

    @Test
    public void searchTest() {
        SplayTree<Integer> splayTree = new SplayTree<>();

        for (Integer number : TEST_ARRAY)
            assertEquals(splayTree.search(number), null);

        for (Integer number : TEST_ARRAY)
            splayTree.insert(number);

        for (Integer number : TEST_ARRAY)
            assertEquals(splayTree.search(number), number);

        assertArrayEquals(getLevelOrderElements(splayTree), LEVEL_ORDER_ARRAY);
    }

    @Test
    public void removeTest() {
        SplayTree<Integer> splayTree = new SplayTree<>();

        for (Integer number : TEST_ARRAY)
            assertEquals(splayTree.remove(number), false);

        for (Integer number : TEST_ARRAY)
            splayTree.insert(number);

        splayTree.remove(5);
        assertArrayEquals(getLevelOrderElements(splayTree), new Integer[] {4, 3, 14, 2, 12, 15, 1, 10, 13, 8, 11, 6, 9, 7});

        splayTree.remove(2);
        assertArrayEquals(getLevelOrderElements(splayTree), new Integer[] {1, 3, 4, 14, 12, 15, 10, 13, 8, 11, 6, 9, 7});

        splayTree.remove(12);
        assertArrayEquals(getLevelOrderElements(splayTree), new Integer[] {11, 3, 14, 1, 10, 13, 15, 4, 8, 6, 9, 7});

        splayTree.remove(6);
        assertArrayEquals(getLevelOrderElements(splayTree), new Integer[] {4, 3, 11, 1, 10, 14, 8, 13, 15, 7, 9});

        splayTree.remove(10);
        assertArrayEquals(getLevelOrderElements(splayTree), new Integer[] {9, 8, 11, 4, 14, 3, 7, 13, 15, 1});

        splayTree.remove(4);
        assertArrayEquals(getLevelOrderElements(splayTree), new Integer[] {3, 1, 8, 7, 9, 11, 14, 13, 15});

        splayTree.remove(11);
        assertArrayEquals(getLevelOrderElements(splayTree), new Integer[] {9, 3, 14, 1, 8, 13, 15, 7});

        splayTree.remove(8);
        assertArrayEquals(getLevelOrderElements(splayTree), new Integer[] {7, 3, 9, 1, 14, 13, 15});

        splayTree.remove(14);
        assertArrayEquals(getLevelOrderElements(splayTree), new Integer[] {13, 9, 15, 7, 3, 1});

        splayTree.remove(13);
        assertArrayEquals(getLevelOrderElements(splayTree), new Integer[] {9, 7, 15, 3, 1});

        splayTree.remove(7);
        assertArrayEquals(getLevelOrderElements(splayTree), new Integer[] {3, 1, 9, 15});

        splayTree.remove(15);
        assertArrayEquals(getLevelOrderElements(splayTree), new Integer[] {9, 3, 1});

        splayTree.remove(3);
        assertArrayEquals(getLevelOrderElements(splayTree), new Integer[] {1, 9});

        splayTree.remove(1);
        assertArrayEquals(getLevelOrderElements(splayTree), new Integer[] {9});

        splayTree.remove(9);
        assertArrayEquals(getLevelOrderElements(splayTree), new Integer[] {});
    }

    private Integer[] getLevelOrderElements(SplayTree<Integer> splayTree) {
        List<Integer> elements = splayTree.levelOrderTraversal();
        return elements.toArray(new Integer[elements.size()]);
    }
}

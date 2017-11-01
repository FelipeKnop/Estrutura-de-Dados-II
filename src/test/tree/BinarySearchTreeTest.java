package test.tree;

import org.junit.Test;
import tree.BinarySearchTree;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public abstract class BinarySearchTreeTest {

    private static final Integer[] TEST_ARRAY = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    protected abstract Integer[] getLevelOrderArray();

    private final Integer[] LEVEL_ORDER_ARRAY = this.getLevelOrderArray();

    protected abstract BinarySearchTree<Integer> getNewBinarySearchTree();

    protected abstract Integer[][] getRemoveLevelOrderArrays();

    @Test
    public void insertTest() {
        BinarySearchTree<Integer> binarySearchTree = getNewBinarySearchTree();

        for (Integer number : TEST_ARRAY)
            binarySearchTree.insert(number);

        assertArrayEquals(getLevelOrderElements(binarySearchTree), LEVEL_ORDER_ARRAY);
    }

    @Test
    public void searchTest() {
        BinarySearchTree<Integer> binarySearchTree = getNewBinarySearchTree();

        for (Integer number : TEST_ARRAY)
            assertEquals(binarySearchTree.search(number), null);

        for (Integer number : TEST_ARRAY)
            binarySearchTree.insert(number);

        for (Integer number : TEST_ARRAY)
            assertEquals(binarySearchTree.search(number), number);

        assertArrayEquals(getLevelOrderElements(binarySearchTree), LEVEL_ORDER_ARRAY);
    }

    @Test
    public void removeTest() {
        BinarySearchTree<Integer> binarySearchTree = getNewBinarySearchTree();

        for (Integer number : TEST_ARRAY)
            assertEquals(binarySearchTree.remove(number), false);

        for (Integer number : TEST_ARRAY)
            binarySearchTree.insert(number);

        Integer[][] levelOrderArrays = this.getRemoveLevelOrderArrays();
        Integer[] removeOrder = new Integer[] {5, 2, 12, 6, 10, 4, 11, 8, 14, 13, 7, 15, 3, 1, 9};

        for (int i = 0; i < removeOrder.length; i++) {
            binarySearchTree.remove(removeOrder[i]);
            assertArrayEquals(getLevelOrderElements(binarySearchTree), levelOrderArrays[i]);
        }
    }

    private Integer[] getLevelOrderElements(BinarySearchTree<Integer> binarySearchTree) {
        List<Integer> elements = binarySearchTree.levelOrderTraversal();
        return elements.toArray(new Integer[elements.size()]);
    }
}

package tree;

public class RedBlackTree<T extends Comparable<? super T>> extends BinarySearchTree<T> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    @Override
    protected Node createNode(T key) {
        return new RedBlackNode(key, BLACK);
    }

    // TODO: Implementar a inserção e remoção de elementos

    private class RedBlackNode extends Node {

        private boolean color;

        RedBlackNode(T key, boolean color) {
            super(key);
            this.color = color;
        }

        RedBlackNode getGrandParent() {
            return parent == null || parent.parent == null ? null : (RedBlackNode) parent.parent;
        }

        RedBlackNode getUncle() {
            RedBlackNode grandParent = getGrandParent();
            if (grandParent == null) return null;
            if (grandParent.leftChild != null && grandParent.leftChild == parent)
                return (RedBlackNode) grandParent.rightChild;
            else if (grandParent.rightChild != null && grandParent.rightChild == parent)
                return (RedBlackNode) grandParent.leftChild;
            return null;
        }
    }
}

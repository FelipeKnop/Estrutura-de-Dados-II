package tree;

public class SplayTree<T extends Comparable<? super T>> extends BinarySearchTree<T> {

    @Override
    protected Node createNode(T key) {
        return new Node(key);
    }

    @Override
    protected Node addValue(T value) {
        Node node = super.addValue(value);
        if (node != null)
            while (node.parent != null)
                splay(node);
        return node;
    }

    @Override
    public T search(T key) {
        Node node = getNode(key);
        if (node != null) {
            while (node.parent != null)
                splay(node);
            return node.key;
        }
        return null;
    }

    @Override
    protected Node removeValue(Node nodeToRemove) {
        nodeToRemove = super.removeValue(nodeToRemove);
        if (nodeToRemove != null && nodeToRemove.parent != null) {
            Node parent = nodeToRemove.parent;
            while (parent.parent != null)
                splay(parent);
        }
        return nodeToRemove;
    }

    // TODO: Arrumar splay para a remoção
    private void splay(Node node) {
        Node parent = node.parent;
        Node grandParent = parent != null ? parent.parent : null;

        if (parent != null && parent == root) {
            // Zig
            root = node;
            node.parent = null;

            if (node == parent.leftChild) {
                parent.leftChild = node.rightChild;
                if (node.rightChild != null)
                    node.rightChild.parent = parent;
                node.rightChild = parent;
                parent.parent = node;
            } else {
                parent.rightChild = node.leftChild;
                if (node.leftChild != null)
                    node.leftChild.parent = parent;
                node.leftChild = parent;
                parent.parent = node;
            }

            return;
        }

        if (parent != null && grandParent != null) {
            Node greatGrandParent = grandParent.parent;
            if (greatGrandParent != null && greatGrandParent.leftChild == grandParent) {
                greatGrandParent.leftChild = node;
                node.parent = greatGrandParent;
            } else if (greatGrandParent != null && greatGrandParent.rightChild == grandParent) {
                greatGrandParent.rightChild = node;
                node.parent = greatGrandParent;
            } else {
                root = node;
                node.parent = null;
            }

            if ((node == parent.leftChild && parent == grandParent.leftChild)
                    || (node == parent.rightChild && parent == grandParent.rightChild)) {
                // Zig-Zig
                if (node == parent.leftChild) {
                    Node rightChild = node.rightChild;
                    node.rightChild = parent;
                    parent.parent = node;

                    parent.leftChild = rightChild;
                    if (rightChild != null)
                        rightChild.parent = parent;

                    Node parentRightChild = parent.rightChild;
                    parent.rightChild = grandParent;
                    grandParent.parent = parent;

                    grandParent.leftChild = parentRightChild;
                    if (parentRightChild != null)
                        parentRightChild.parent = grandParent;
                } else {
                    Node leftChild = node.leftChild;
                    node.leftChild = parent;
                    parent.parent = node;

                    parent.rightChild = leftChild;
                    if (leftChild != null)
                        leftChild.parent = parent;

                    Node parentLeftChild = parent.leftChild;
                    parent.leftChild = grandParent;
                    grandParent.parent = parent;

                    grandParent.rightChild = parentLeftChild;
                    if (parentLeftChild != null)
                        parentLeftChild.parent = grandParent;
                }
                return;
            }

            // Zig-Zag
            if (node == parent.leftChild) {
                Node leftChild = node.rightChild;
                Node rightChild = node.leftChild;

                node.rightChild = parent;
                parent.parent = node;

                node.leftChild = grandParent;
                grandParent.parent = node;

                parent.leftChild = leftChild;
                if (leftChild != null)
                    leftChild.parent = parent;

                grandParent.rightChild = rightChild;
                if (rightChild != null)
                    rightChild.parent = grandParent;

                return;
            }

            Node leftChild = node.leftChild;
            Node rightChild = node.rightChild;

            node.leftChild = parent;
            parent.parent = node;

            node.rightChild = grandParent;
            grandParent.parent = node;

            parent.rightChild = leftChild;
            if (leftChild != null)
                leftChild.parent = parent;

            grandParent.leftChild = rightChild;
            if (rightChild != null)
                rightChild.parent = grandParent;
        }
    }
}

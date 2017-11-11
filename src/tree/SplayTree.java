package tree;

public class SplayTree<Key extends Comparable<? super Key>, Value> extends BinarySearchTree<Key, Value> {

    @Override
    protected Node createNode(Key key, Value value) {
        return new Node(key, value);
    }

    /**
     * Função que sobrescreve a função {@link BinarySearchTree#addValue(Comparable, Object)}
     * para definir as regras de inserção de um dado na árvore Splay.
     * @param key Chave do dado a ser inserido
     * @param value Dado a ser inserido
     * @return Nó inserido
     */
    @Override
    protected Node addValue(Key key, Value value) {
        Node node = super.addValue(key, value);
        if (node != null)
            while (node.parent != null)
                splay(node);
        return node;
    }

    @Override
    public Value search(Key key) {
        Node node = getNode(key);
        if (node != null) {
            while (node.parent != null)
                splay(node);
            return node.value;
        }
        return null;
    }



    /**
     * Função que sobrescreve a função {@link BinarySearchTree#removeNode(Node)}
     * para definir as regras de remoção de um nó na árvore Splay.
     * @param nodeToRemove Nó a ser removido
     * @return Nó que foi removido
     */
    @Override
    protected Node removeNode(Node nodeToRemove) {
        nodeToRemove = super.removeNode(nodeToRemove);
        if (nodeToRemove != null && nodeToRemove.parent != null) {
            Node parent = nodeToRemove.parent;
            while (parent.parent != null)
                splay(parent);
        }
        return nodeToRemove;
    }

    /**
     * Método que faz o "splay" de um nó da árvore, que consiste em
     * mover um nó acima na árvore realizando rotações do tipo Zig,
     * Zig-Zig e Zig-Zag.
     * @param node Nó a ser movido acima na árvore
     */
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
                copies++;
            } else {
                parent.rightChild = node.leftChild;
                if (node.leftChild != null)
                    node.leftChild.parent = parent;
                node.leftChild = parent;
                parent.parent = node;
                copies++;
            }

            return;
        }

        if (parent != null && grandParent != null) {
            Node greatGrandParent = grandParent.parent;
            if (greatGrandParent != null && greatGrandParent.leftChild == grandParent) {
                greatGrandParent.leftChild = node;
                node.parent = greatGrandParent;
                copies++;
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
                    copies++;
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
                    copies++;
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
                copies++;
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
            copies++;
            parent.rightChild = leftChild;
            if (leftChild != null)
                leftChild.parent = parent;

            grandParent.leftChild = rightChild;
            if (rightChild != null)
                rightChild.parent = grandParent;
        }
    }
}

package tree;

public class RedBlackTree<T extends Comparable<? super T>> extends BinarySearchTree<T> {

    /**
     * Cores possíveis para os nós da árvore.
     */
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
            // Se o nó não tem pai, não tem avô
            return parent == null || parent.parent == null ? null : (RedBlackNode) parent.parent;
        }

        RedBlackNode getUncle() {
            RedBlackNode grandParent = getGrandParent();
            if (grandParent == null) return null; // Se o nó não tem avô, não tem tio
            if (grandParent.leftChild != null && grandParent.leftChild == parent) // Avô tem filho à esquerda, que é o pai do nó
                return (RedBlackNode) grandParent.rightChild;
            else if (grandParent.rightChild != null && grandParent.rightChild == parent) // Avô tem filho à direita, que é o pai do nó
                return (RedBlackNode) grandParent.leftChild;
            return null;
        }
    }
}

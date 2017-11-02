package tree;

public class RedBlackTree<T extends Comparable<? super T>> extends BinarySearchTree<T> {

    /**
     * Cores possíveis para os nós da árvore.
     */
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    @Override
    protected Node createNode(T key) {
        return new RedBlackNode(key, RED);
    }


    private void addHelper(RedBlackNode node)
    {
        if(node.parent==null)
        {
            node.color = BLACK;
            return;
        }
        RedBlackNode uncle =  node.getUncle();
        RedBlackNode grand = node.getGrandParent();
        RedBlackNode parent = (RedBlackNode) node.parent;
        if (node.color == RED && parent.color == RED) {
            if (uncle != null && uncle.color == RED)
            {
                parent.color= !parent.color;
                uncle.color = !uncle.color;
                grand.color = !grand.color;
            }
            else
            {
                if(node==parent.leftChild && parent == grand.leftChild)
                {
                    parent.color = !parent.color;
                    grand.color = !parent.color;
                    rotateRight(grand);
                }

                else if(node==parent.rightChild && parent == grand.rightChild)
                {
                    parent.color = !parent.color;
                    grand.color = !parent.color;
                    rotateLeft(grand);
                }

                else if (node==parent.leftChild && parent == grand.rightChild)
                {
                    node.color =!node.color;
                    grand.color =!grand.color;
                    rotateRight(parent);
                    rotateLeft(grand);

                }

                else if (node==parent.rightChild && parent == grand.leftChild)
                {
                    node.color =!node.color;
                    grand.color =!grand.color;
                    rotateLeft(parent);
                    rotateRight(grand);
                }
            }



        }
        addHelper(parent);
    }
    @Override
    protected Node addValue (T value)
    {
        Node node = super.addValue(value);
        RedBlackNode newnode = (RedBlackNode) node;

        if (newnode.parent == null) {
            newnode.color = BLACK;
            return newnode;
        }
        else {
           addHelper(newnode);
           int i =0;
           return newnode;
        }

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

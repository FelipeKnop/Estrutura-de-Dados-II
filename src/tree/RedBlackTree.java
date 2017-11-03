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

    /**
     * Função chamada após a inserção do nó para verificar recursivamente, da base até o topo, possíveis regras da
     * RedBlackTree que tenham sido quebradas e então corrigílas.
     * @param node Nó de verificação.
     */

    private void addHelper(RedBlackNode node) {
        //Correção da raiz para cor preta
        if (node.parent == null) {
            node.color = BLACK;
            return;
        }

        RedBlackNode uncle =  node.getUncle();
        RedBlackNode grand = node.getGrandParent();
        RedBlackNode parent = (RedBlackNode) node.parent;

        // Verificação do erro pai e filho vermelhos
        if (node.color == RED && parent.color == RED) {
            //Correção caso o tio seja vermelho
            if (uncle != null && uncle.color == RED) {
                parent.color = BLACK;
                uncle.color = BLACK;
                grand.color = !grand.color;

            }
            //Correção caso o tio seja preto
            else {
                comparisons += 2; // Por causa do &&
                //Filho a esquerda de um pai a esquerda
                if(node == parent.leftChild && parent == grand.leftChild) {
                    parent.color = BLACK;
                    grand.color = RED;
                    rotateRight(grand);
                } else{
                    comparisons += 2; // Por causa do &&
                    //Filho a direita de um pai a direita
                    if(node == parent.rightChild && parent == grand.rightChild) {
                        parent.color = BLACK;
                        grand.color = RED;
                        rotateLeft(grand);
                    } else {
                        comparisons += 2; // Por causa do &&
                        //Filho a esquerda de um pai a direita
                        if (node == parent.leftChild && parent == grand.rightChild) {
                            node.color = BLACK;
                            grand.color = !grand.color;
                            rotateRight(parent);
                            rotateLeft(grand);
                        } else {
                            comparisons += 2; // Por causa do &&
                            //Filho a direita de um pai a esquerda
                            if (node == parent.rightChild && parent == grand.leftChild) {
                                node.color = BLACK;
                                grand.color = !grand.color;
                                rotateLeft(parent);
                                rotateRight(grand);
                            }
                        }
                    }
                }
            }
        }
        addHelper(parent);
    }

    @Override
    protected Node addValue (T value) {
        Node node = super.addValue(value);
        RedBlackNode newnode = (RedBlackNode) node;

        if (newnode.parent == null) {
            newnode.color = BLACK;
            return newnode;
        } else {
           addHelper(newnode);
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
    /**Função que retorna o avô do nó caso ele exista ou null caso contrário*/
        RedBlackNode getGrandParent() {
            // Se o nó não tem pai, não tem avô
            return parent == null || parent.parent == null ? null : (RedBlackNode) parent.parent;
        }
        /**Função que retorna o tio do nó caso ele exista ou null caso contrário*/
        RedBlackNode getUncle() {
            RedBlackNode grandParent = getGrandParent();
            if (grandParent == null) return null; // Se o nó não tem avô, não tem tio
            comparisons++;
            if (grandParent.leftChild != null && grandParent.leftChild == parent) // Avô tem filho à esquerda, que é o pai do nó
                return (RedBlackNode) grandParent.rightChild;
            else {
                comparisons ++;
                if (grandParent.rightChild != null && grandParent.rightChild == parent) // Avô tem filho à direita, que é o pai do nó
                    return (RedBlackNode) grandParent.leftChild;
            }
            return null;
        }
    }
}

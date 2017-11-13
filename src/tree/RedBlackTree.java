package tree;

public class RedBlackTree<Key extends Comparable<? super Key>, Value> extends BinarySearchTree<Key, Value> {

    /**
     * Cores possíveis para os nós da árvore.
     */
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    @Override
    protected Node createNode(Key key, Value value) {
        return new RedBlackNode(key, value);
    }

    /**
     * Função chamada após a inserção do nó para verificar recursivamente, da base até o topo,
     * possíveis regras da RedBlackTree que tenham sido quebradas e então corrigi-las.
     * @param node Nó de verificação.
     */
    private void addHelper(RedBlackNode node) {
        //Correção da raiz para cor preta
        if (node.parent == null) {
            node.color = BLACK;
            return;
        }

        // Guarda variáveis para uso posterior
        RedBlackNode uncle = node.getUncle();
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
                //Filho à esquerda de um pai à esquerda
                if(node == parent.leftChild && parent == grand.leftChild) {
                    parent.color = BLACK;
                    grand.color = RED;
                    rotateRight(grand);
                } else{
                    comparisons += 2; // Por causa do &&
                    //Filho à direita de um pai à direita
                    if(node == parent.rightChild && parent == grand.rightChild) {
                        parent.color = BLACK;
                        grand.color = RED;
                        rotateLeft(grand);
                    } else {
                        comparisons += 2; // Por causa do &&
                        //Filho à esquerda de um pai à direita
                        if (node == parent.leftChild && parent == grand.rightChild) {
                            node.color = BLACK;
                            grand.color = !grand.color;
                            rotateRight(parent);
                            rotateLeft(grand);
                        } else {
                            comparisons += 2; // Por causa do &&
                            //Filho à direita de um pai à esquerda
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

    /**
     * Função que sobrescreve a função {@link BinarySearchTree#addValue(Comparable, Object)}
     * para definir as regras de inserção de um dado na árvore Vermelho e Preto.
     * @param key Chave do dado a ser inserido
     * @param value Dado a ser inserido
     * @return Nó inserido
     */
    @Override
    protected Node addValue(Key key, Value value) {
        Node node = super.addValue(key, value);
        RedBlackNode newNode = (RedBlackNode) node;

        if (newNode.parent == null) {
            newNode.color = BLACK;
            return newNode;
        } else {
           addHelper(newNode);
           return newNode;
        }
    }

    @Override
    public boolean validate() {
        if (root == null)
            return true;

        // Raiz deve ser preta
        return ((RedBlackNode) root).color != RED && this.validateNode(root);
    }

    @Override
    protected boolean validateNode(Node node) {
        RedBlackNode rbNode = (RedBlackNode) node;
        RedBlackNode leftChild = (RedBlackNode) node.leftChild;
        RedBlackNode rightChild = (RedBlackNode) node.rightChild;

        if ((rbNode.leftChild == null && rbNode.rightChild == null) // Nó é folha
                && rbNode.color == RED) // Folhas devem ser pretas
            return false;

        if (rbNode.color == RED) {
            // Não podem haver dois nós vermelhos em seguida
            if (leftChild != null && leftChild.color == RED) return false;
            if (rightChild != null && rightChild.color == RED) return false;
        }

        if (leftChild != null && !(leftChild.leftChild == null && leftChild.rightChild == null)) { // Não é folha
            boolean leftCheck = leftChild.key.compareTo(rbNode.key) <= 0;
            if (!leftCheck) return false;

            leftCheck = this.validateNode(leftChild);
            if (!leftCheck) return false;
        }

        if (rightChild != null && !(rightChild.leftChild == null && rightChild.rightChild == null)) { // Não é folha
            boolean rightCheck = rightChild.key.compareTo(rbNode.key) > 0;
            if (!rightCheck) return false;

            rightCheck = this.validateNode(rightChild);
            if (!rightCheck) return false;
        }

        return true;
    }

    private class RedBlackNode extends Node {

        private boolean color;

        private RedBlackNode(Key key, Value value) {
            super(key, value);
            this.color = RED;
        }

        /**
         * Função que retorna o avô do nó caso ele exista ou null caso contrário.
         */
        private RedBlackNode getGrandParent() {
            // Se o nó não tem pai, não tem avô
            return parent == null || parent.parent == null ? null : (RedBlackNode) parent.parent;
        }

        /**
         * Função que retorna o tio do nó caso ele exista ou null caso contrário.
         */
        private RedBlackNode getUncle() {
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

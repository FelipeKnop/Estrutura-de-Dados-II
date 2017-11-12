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
    /**
     * Função que chama a função {@link #getNode(Comparable)} e, caso o seu retorno não seja nulo, inicia as verificações para
     * a remoção do nó. Caso o nó removido ou seu filho sejam vermelhos ela realiza o remoção. Caso ambos sejam pretos, ela inicia
     * as verificações necessárias para correção da altura negra
     * */
    @Override
    public boolean remove(Key key){
        RedBlackNode nodeToRemove = (RedBlackNode) getNode(key);
        if(nodeToRemove==null)
            return false;
        RedBlackNode nodeToSub = (RedBlackNode) getReplacementNode(nodeToRemove);
        if(nodeToSub==null)
            nodeToSub = nodeToRemove;
        RedBlackNode nodeToSubChild;
        if(nodeToSub.leftChild!=null){
            nodeToSubChild = (RedBlackNode) nodeToSub.leftChild;
        }else if(nodeToSub.rightChild!=null){
            nodeToSubChild = (RedBlackNode) nodeToSub.rightChild;
        }else{
            nodeToSubChild = new RedBlackNode(null, null);
            nodeToSubChild.color = BLACK;
        }
        nodeToRemove.key = nodeToSub.key;
        nodeToRemove.value = nodeToSub.value;
        replaceNode(nodeToSub, nodeToSubChild);
        if(nodeToSub.color == BLACK){
            if(nodeToSubChild.color==RED){
                nodeToSubChild.color = BLACK;
            }else{
                removeCase1(nodeToSubChild);
            }
        }
        if(nodeToSubChild.key==null)
            replaceNode(nodeToSubChild, null);
        return true;
    }

    private RedBlackNode getBrother(RedBlackNode n){
        if(n.parent.leftChild == n)
            return (RedBlackNode)n.parent.rightChild;
        else
            return (RedBlackNode)n.parent.leftChild;
    }
    /**
     * Caso o nó seja a nova raiz, nada deve ser feito. Caso contrário, segue para o caso 2.
     * */
    private void removeCase1(RedBlackNode doubleBlack){
        if(doubleBlack.parent!=null){
            removeCase2(doubleBlack);
        }
    }
    /**
     * Caso o irmão do nó seja vermelho, troca a cor do pai e do irmão e faz a rotação adequada. Segue para o caso 3.
     * */
    private void removeCase2(RedBlackNode doubleBlack){
        RedBlackNode brother = getBrother(doubleBlack);
        if(brother.color==RED){
            ((RedBlackNode)doubleBlack.parent).color = RED;
            brother.color = BLACK;
            if(doubleBlack == doubleBlack.parent.leftChild){
                rotateLeft(doubleBlack.parent);
            }else{
                rotateRight(doubleBlack.parent);
            }
        }
        removeCase3(doubleBlack);
    }
    /**
     * Caso o irmão, o pai e os 2 filhos do irmão sejam pretos, troca a cor do irmão e recomeça do caso 1 passando o pai como parâmetro.
     * Caso contrário segue para o passo 4.
     * */
    private void removeCase3(RedBlackNode doubleBlack){
        RedBlackNode brother = getBrother(doubleBlack);
        if(((RedBlackNode)doubleBlack.parent).color==BLACK && brother.color==BLACK && (brother.leftChild==null || ((RedBlackNode)brother.leftChild).color==BLACK) && (brother.rightChild==null || ((RedBlackNode)brother.rightChild).color==BLACK)){
            brother.color = RED;
            removeCase1((RedBlackNode) doubleBlack.parent);
        }else
            removeCase4(doubleBlack);
    }
    /**
     * Caso o pai seja vermelho e o irmão e seus 2 filhos sejam pretos, troca a cor do irmão e do pai.
     * Caso contrário, segue para o caso 5
     * */
    private void removeCase4(RedBlackNode doubleBlack){
        RedBlackNode brother = getBrother(doubleBlack);
        if(((RedBlackNode)doubleBlack.parent).color==RED && brother.color==BLACK && (brother.leftChild==null || ((RedBlackNode)brother.leftChild).color==BLACK) && (brother.rightChild==null || ((RedBlackNode)brother.rightChild).color==BLACK)){
            brother.color = RED;
            ((RedBlackNode)doubleBlack.parent).color = BLACK;
        }else
            removeCase5(doubleBlack);
    }
    /**
     * Caso o irmão seja preto e tenha um filho vermelho e um filho preto, sendo o filho preto o que está do mesmo lado que o irmão,
     * ou seja, o filho direito caso o irmão seja filho direito, ou filho esquerdo caso o irmão seja filçho esquerdo, troca a cor do irmão
     * e faz a rotação adequada. Segue para o caso 6
     * */
    private void removeCase5(RedBlackNode doubleBlack){
        RedBlackNode brother = getBrother(doubleBlack);
        if(brother.color==BLACK){
            if(doubleBlack==doubleBlack.parent.leftChild && (brother.rightChild==null || ((RedBlackNode)brother.rightChild).color==BLACK) && (brother.leftChild!=null && ((RedBlackNode)brother.leftChild).color==RED)){
                brother.color = RED;
                ((RedBlackNode)brother.leftChild).color = BLACK;
                rotateRight(brother);
            }else if(doubleBlack==doubleBlack.parent.rightChild && (brother.leftChild==null || ((RedBlackNode)brother.leftChild).color==BLACK) && (brother.rightChild!=null && ((RedBlackNode)brother.rightChild).color==RED)){
                brother.color = RED;
                ((RedBlackNode)brother.rightChild).color = BLACK;
                rotateLeft(brother);
            }
        }
        removeCase6(doubleBlack);
    }
    /**
     * Nesse caso, o irmão é preto e possui 1 filho preto e 1 vermelho, sendo o filho vermelho o que está do mesmo lado que o irmão,
     * ou seja, o filho direito caso o irmão seja filho direito, ou filho esquerdo caso o irmão seja filçho esquerdo.
     * Muda a cor do irmão para a cor do pai, e muda a cor do pai para preto. Em seguida, troca a cor do filho vermelho para preto
     * e faz a rotação adequada
     * */
    private void removeCase6(RedBlackNode doubleBlack){
        RedBlackNode brother = getBrother(doubleBlack);
        brother.color = ((RedBlackNode)doubleBlack.parent).color;
        ((RedBlackNode)doubleBlack.parent).color = BLACK;
        if(doubleBlack == doubleBlack.parent.leftChild){
            ((RedBlackNode)brother.rightChild).color = BLACK;
            rotateLeft(doubleBlack.parent);
        }else{
            ((RedBlackNode)brother.leftChild).color = BLACK;
            rotateRight(doubleBlack.parent);
        }
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

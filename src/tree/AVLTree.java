package tree;

public class AVLTree<Key extends Comparable<? super Key>, Value> extends BinarySearchTree<Key, Value> {

    private int height(Node node) {
        if (node == null) return 0; // Nós que não existem têm altura 0
        return 1 + Math.max(height(node.leftChild), height(node.rightChild));
    }

    private int getBalanceFactor(AVLNode node) {
        if (node == null) return 0; // Nó folha tem altura 0
        return height(node.rightChild) - height(node.leftChild);
    }

    @Override
    protected Node createNode(Key key, Value value) {
        return new AVLNode(key, value);
    }

    /**
     * Função que sobrescreve a função {@link BinarySearchTree#addValue(Comparable, Object)}
     * para definir as regras de inserção de um dado na árvore AVL.
     * @param key Chave do dado a ser inserido
     * @param value Dado a ser inserido
     * @return Nó inserido
     */
    @Override
    protected Node addValue(Key key, Value value) {
        Node node = super.addValue(key, value); // Utiliza o método normal de Árvore Binária de Busca para inserir o dado
        AVLNode newNode = (AVLNode) node;
        rebalance(newNode); // Rebalanceia a árvore a partir do novo nó inserido

        newNode = (AVLNode) newNode.parent;
        while (newNode != null) {
            int h1 = height(newNode);
            rebalance(newNode); // Sobe todos os níveis da árvore até a raiz rebalanceando para cada nó pai
            if (height(newNode) != h1) break; // Se a altura antes e depois de rebalancear for igual, para
            newNode = (AVLNode) newNode.parent;
        }

        return node;
    }

    /**
     * Função que faz o rebalanceamento de uma árvore ou subárvore a partir
     * de seu nó raiz. Para saber se o rebalanceamente deve ser efetuado e
     * como, é calculado o fator de balanceamento do nó recebido, assim
     * enquadrando a situação em um dos casos de rebalanceamento da AVL;
     * @param node Nó raiz da árvore ou subárvore
     */
    private void rebalance(AVLNode node) {
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor == -2) { // Altura da subárvore à esquerda é maior
            AVLNode leftLeft = (AVLNode) node.leftChild.leftChild;
            int left = height(leftLeft);
            AVLNode leftRight = (AVLNode) node.leftChild.rightChild;
            int right = height(leftRight);
            if (left >= right) { // Altura da subárvore à esquerda do filho à esquerda é maior
                rotateRight(node);
            } else { // Altura da subárvore à direita do filho à esquerda é maior
                rotateLeft(node.leftChild);
                rotateRight(node);
            }
        } else if (balanceFactor == 2) { // Altura da subárvore à direita é maior
            AVLNode rightRight = (AVLNode) node.rightChild.rightChild;
            int right = height(rightRight);
            AVLNode rightLeft = (AVLNode) node.rightChild.leftChild;
            int left = height(rightLeft);
            if (right >= left) { // Altura da subárvore à direita do filho à direita é maior
                rotateLeft(node);
            } else { // Altura da subárvore à esquerda do filho à direita é maior
                rotateRight(node.rightChild);
                rotateLeft(node);
            }
        }
    }

    @Override
    protected Node removeNode(Node nodeToRemove) {
        Node replacementNode = getReplacementNode(nodeToRemove); // Obtém nó que substituirá o nó a ser removido

        // Achar o pai do nó de substituição
        AVLNode node = null;
        if (replacementNode != null)
            node = (AVLNode) replacementNode.parent;
        if (node == null)
            node = (AVLNode) nodeToRemove.parent;
        comparisons++;
        if (node != null && node == nodeToRemove)
            node = (AVLNode) replacementNode;

        replaceNode(nodeToRemove, replacementNode); // Substitui o nó

        while (node != null) {
            int h1 = height(node);
            rebalance(node); // Sobe todos os níveis da árvore até a raiz rebalanceando para cada nó pai
            if (height(node) == h1) break; // Se a altura antes e depois de rebalancear for igual, para
            node = (AVLNode) node.parent;
        }

        return nodeToRemove;
    }

    @Override
    protected boolean validateNode(Node node) {
        boolean bst = super.validateNode(node);
        if (!bst) // Não segue as regras da árvore binária de busca
            return false;

        AVLNode avlNode = (AVLNode) node;
        int balanceFactor = getBalanceFactor(avlNode);
        if (balanceFactor > 1 || balanceFactor < -1) // Fator de balanceamento não é -1, 0 ou 1
            return false;

        if (avlNode.leftChild == null && avlNode.rightChild == null) {// Nó é folha
            if (height(avlNode) != 1)
                return false;
        } else {
            int height = height(avlNode);

            AVLNode leftChild = (AVLNode) avlNode.leftChild;
            int leftHeight = 1;
            if (leftChild != null)
                leftHeight = height(leftChild);

            AVLNode rightChild = (AVLNode) avlNode.rightChild;
            int rightHeight = 1;
            if (rightChild != null)
                rightHeight = height(rightChild);

            return height == leftHeight + 1 || height == rightHeight + 1;
        }

        return true;
    }

    private class AVLNode extends Node {

        private AVLNode(Key key, Value value) {
            super(key, value);
        }
    }
}

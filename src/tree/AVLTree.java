package tree;

public class AVLTree<Key extends Comparable<? super Key>, Value> extends BinarySearchTree<Key, Value> {

    /**
     * Casos de balanceamento dos nós da árvore.
     */
    private enum Balance {
        LL, // Left-Left, nó é filho à esquerda de um filho à esquerda
        LR, // Left-Right, nó é filho à direita de um filho à esquerda
        RL, // Right-Left, nó é filho à esquerda de um filho à direita
        RR // Right-Right, nó é filho à direita de um filho à direita
    }

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
            rebalance(newNode); // Sobe todos os níveis da árvore até a raiz rebalanceando para cada nó pai
            newNode = (AVLNode) newNode.parent;
        }

        return node;
    }

    /**
     * Função que faz o rebalanceamento de uma árvore ou subárvore a partir
     * de seu nó raiz. Para saber se o rebalanceamente deve ser efetuado e
     * como, é calculado o fator de balanceamento do nó recebido, assim
     * enquadrando a situação em um dos casos de rebalanceamento da AVL;
     * @param head Nó raiz da árvore ou subárvore
     */
    private void rebalance(AVLNode head) {
        int balanceFactor = getBalanceFactor(head);
        if (balanceFactor > 1 || balanceFactor < -1) { // Nó não está balanceado
            AVLNode child;
            Balance balance;
            if (balanceFactor < 0) { // Altura da subárvore à esquerda é maior
                child = (AVLNode) head.leftChild;
                balanceFactor = getBalanceFactor(child);
                balance = balanceFactor < 0 ? Balance.LL : Balance.LR; // Altura da subárvore à esquerda do filho à esquerda é maior
            } else { // Altura da subárvore à direita é maior
                child = (AVLNode) head.rightChild;
                balanceFactor = getBalanceFactor(child);
                balance = balanceFactor < 0 ? Balance.RL : Balance.RR; // Altura da subárvore à esquerda do filho à esquerda é maior
            }

            switch (balance) {
                case LR: // Left-Right: faz rotação para a esquerda e depois para a direita
                    rotateLeft(head.leftChild); // Falta de "break" para que caia no próximo case, com a rotação à direita
                case LL: // Left-Left: faz rotação para a direita
                    rotateRight(head);
                    break;
                case RL: // Right-Left: faz rotação para a direita e depois para a esquerda
                    rotateRight(head.rightChild); // Falta de "break" para que caia no próximo case, com a rotação à esquerda
                case RR: // Right-Right: faz rotação para a esquerda
                    rotateLeft(head);
                    break;
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
            rebalance(node); // Sobe todos os níveis da árvore até a raiz rebalanceando para cada nó pai
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

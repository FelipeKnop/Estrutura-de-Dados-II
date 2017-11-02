package tree;

public class AVLTree<T extends Comparable<? super T>> extends BinarySearchTree<T> {

    /**
     * Casos de balanceamento dos nós da árvore.
     */
    private enum Balance {
        LL, // Left-Left, nó é filho à esquerda de um filho à esquerda
        LR, // Left-Right, nó é filho à direita de um filho à esquerda
        RL, // Right-Left, nó é filho à esquerda de um filho à direita
        RR // Right-Right, nó é filho à direita de um filho à direita
    }

    private int getBalanceFactor(AVLNode node) {
        if (node == null) return 0; // Nó folha tem altura 0
        return height(node.rightChild) - height(node.leftChild);
    }

    @Override
    protected Node createNode(T key) {
        return new AVLNode(key);
    }

    @Override
    protected Node addValue(T value) {
        Node node = super.addValue(value); // Utiliza o método normal de Árvore Binária de Busca para inserir o elemento
        AVLNode newNode = (AVLNode) node;
        rebalance(newNode); // Rebalanceia a árvore a partir do novo nó inserido

        newNode = (AVLNode) newNode.parent;
        while (newNode != null) {
            rebalance(newNode); // Sobe todos os níveis da árvore até a raiz rebalanceando para cada nó pai
            newNode = (AVLNode) newNode.parent;
        }

        return node;
    }

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
                    rotateLeft(head); // Falta de "break" para que caia no próximo case, com a rotação à direita
                case LL: // Left-Left: faz rotação para a direita
                    rotateRight(head);
                    break;
                case RL: // Right-Left: faz rotação para a direita e depois para a esquerda
                    rotateRight(head); // Falta de "break" para que caia no próximo case, com a rotação à esquerda
                case RR: // Right-Right: faz rotação para a esquerda
                    rotateLeft(head);
                    break;
            }
        }
    }

    @Override
    protected Node removeValue(Node nodeToRemove) {
        Node replacementNode = getReplacementNode(nodeToRemove); // Obtém nó que substituirá o nó a ser removido

        // Achar o pai do nó de substituição
        AVLNode node = null;
        if (replacementNode != null)
            node = (AVLNode) replacementNode.parent;
        if (node == null)
            node = (AVLNode) nodeToRemove.parent;
        if (node != null && node == nodeToRemove)
            node = (AVLNode) replacementNode;

        replaceNode(nodeToRemove, replacementNode); // Substitui o nó

        while (node != null) {
            rebalance(node); // Sobe todos os níveis da árvore até a raiz rebalanceando para cada nó pai
            node = (AVLNode) node.parent;
        }

        return nodeToRemove;
    }

    private class AVLNode extends Node {

        AVLNode(T key) {
            super(key);
        }
    }
}

package tree;

public class AVLTree<T extends Comparable<? super T>> extends BinarySearchTree<T> {

    private enum Balance {
        LL, LR, RL, RR
    }

    private int getBalanceFactor(AVLNode node) {
        if (node == null) return 0;
        return height(node.rightChild) - height(node.leftChild);
    }

    @Override
    protected Node createNode(T key) {
        return new AVLNode(key);
    }

    @Override
    protected Node addValue(T value) {
        Node node = super.addValue(value);
        AVLNode newNode = (AVLNode) node;
        rebalance(newNode);

        newNode = (AVLNode) newNode.parent;
        while (newNode != null) {
            rebalance(newNode);
            newNode = (AVLNode) newNode.parent;
        }

        return node;
    }

    private void rebalance(AVLNode head) {
        int balanceFactor = getBalanceFactor(head);
        if (balanceFactor > 1 || balanceFactor < -1) {
            AVLNode child;
            Balance balance;
            if (balanceFactor < 0) {
                child = (AVLNode) head.leftChild;
                balanceFactor = getBalanceFactor(child);
                balance = balanceFactor < 0 ? Balance.LL : Balance.LR;
            } else {
                child = (AVLNode) head.rightChild;
                balanceFactor = getBalanceFactor(child);
                balance = balanceFactor < 0 ? Balance.RL : Balance.RR;
            }

            switch (balance) {
                case LR:
                    rotateLeft(head);
                case LL:
                    rotateRight(head);
                    break;
                case RL:
                    rotateRight(head);
                case RR:
                    rotateLeft(head);
                    break;
            }
        }
    }

    @Override
    protected Node removeValue(Node nodeToRemove) {
        Node replacementNode = getReplacementNode(nodeToRemove);

        AVLNode node = null;

        if (replacementNode != null)
            node = (AVLNode) replacementNode.parent;

        if (node == null)
            node = (AVLNode) nodeToRemove.parent;

        if (node != null && node == nodeToRemove)
            node = (AVLNode) replacementNode;

        replaceNode(nodeToRemove, replacementNode);

        while (node != null) {
            rebalance(node);
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

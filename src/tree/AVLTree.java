package tree;

public class AVLTree<T extends Comparable<? super T>> {

    public static void main(String[] args) {
        AVLTree<Integer> avlTree = new AVLTree<>();
        avlTree.insert(7);
        avlTree.insert(14);
        avlTree.insert(4);
        avlTree.insert(12);
        avlTree.insert(15);
        avlTree.insert(2);
        avlTree.insert(6);
        avlTree.insert(11);
        avlTree.insert(13);
        avlTree.insert(1);
        avlTree.insert(3);
        avlTree.insert(5);
        Integer result = avlTree.search(4);
        avlTree.remove(4);
        result = avlTree.search(4);
        int i = 0;
    }

    private Node root;

    private int height(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.leftChild), height(node.rightChild));
    }

    private void rotationLeft(Node head) {
        if (!head.equals(root)) {
            if (head.equals(head.parent.leftChild)) {
                head.parent.leftChild = head.rightChild;
            } else {
                head.parent.rightChild = head.rightChild;
            }
        }

        head.rightChild.parent = head.parent;
        head.parent = head.rightChild;
        Node aux = head.rightChild;
        head.rightChild = head.rightChild.leftChild;
        aux.leftChild = head;

        if (head.equals(root)) root = head.parent;
    }

    private void rotationRight(Node head) {
        if (!head.equals(root)) {
            if (head.equals(head.parent.leftChild)) {
                head.parent.leftChild = head.leftChild;
            } else {
                head.parent.rightChild = head.leftChild;
            }
        }

        head.leftChild.parent = head.parent;
        head.parent = head.leftChild;
        Node aux = head.leftChild;
        head.leftChild = head.leftChild.rightChild;
        aux.rightChild = head;

        if (head.equals(root)) root = head.parent;
    }

    private void rotationLeftRight(Node head) {
        rotationLeft(head.leftChild);
        rotationRight(head);
    }

    private void rotationRightLeft(Node head) {
        rotationRight(head.rightChild);
        rotationLeft(head);
    }

    private void rebalance(Node head) {
        int balanceFactor = height(head.rightChild) - height(head.leftChild);
        if (balanceFactor == 2) {
            int rightBalanceFactor = height(head.rightChild.rightChild) - height(head.rightChild.leftChild);
            if (rightBalanceFactor == 1)
                rotationLeft(head);
            else
                rotationRightLeft(head);
        } else if (balanceFactor == -2) {
            int leftBalanceFactor = height(head.leftChild.rightChild) - height(head.leftChild.leftChild);
            if (leftBalanceFactor == 1)
                rotationLeftRight(head);
            else
                rotationRight(head);
        }
    }

    public void insert(T value) {
        if (root == null) {
            root = new Node(value);
            return;
        }

        Node aux = root;
        while (true) {
            if (value.compareTo(aux.key) < 0)
                if (aux.leftChild == null) {
                    aux.leftChild = new Node(value);
                    aux.leftChild.parent = aux;
                    break;
                } else
                    aux = aux.leftChild;
            else
                if (aux.rightChild == null) {
                    aux.rightChild = new Node(value);
                    aux.rightChild.parent = aux;
                    break;
                } else
                    aux = aux.rightChild;
        }

        if (aux.parent != null)
            rebalance(aux.parent);
    }

    public T search (T key) {
        Node aux = root;
        while (true) {
            if (aux == null)
                return null;

            if (key.compareTo(aux.key) < 0) {
                aux = aux.leftChild;
            } else if (key.compareTo(aux.key) > 0) {
                aux = aux.rightChild;
            } else
                return aux.key;
        }
    }

    public boolean remove (T key) {
        Node aux = root;
        while (true) {
            if (aux == null)
                return false;

            if (key.compareTo(aux.key) < 0) {
                aux = aux.leftChild;
            } else if (key.compareTo(aux.key) > 0) {
                aux = aux.rightChild;
            } else {
                Node parent = aux.parent;
                if (aux.leftChild == null && aux.rightChild == null) {
                    if (parent.leftChild.equals(aux)) parent.leftChild = null;
                    else parent.rightChild = null;
                } else if (aux.leftChild != null ^ aux.rightChild != null) {
                    if (parent.leftChild.equals(aux))
                        if (aux.leftChild != null) {
                            parent.leftChild = aux.leftChild;
                            aux.leftChild.parent = parent;
                        } else {
                            parent.leftChild = aux.rightChild;
                            aux.rightChild.parent = parent;
                        }
                    else
                        if (aux.leftChild != null) {
                            parent.rightChild = aux.leftChild;
                            aux.leftChild.parent = parent;
                        } else {
                            parent.rightChild = aux.rightChild;
                            aux.rightChild.parent = parent;
                        }
                } else {
                    Node aux2 = aux.leftChild;
                    while (aux2.rightChild != null)
                        aux2 = aux2.rightChild;
                    if (parent.leftChild.equals(aux)) aux.parent.leftChild = aux2;
                    else aux.parent.rightChild = aux2;
                    parent = aux2.parent;
                    aux2.parent = aux.parent;
                    if (!aux.leftChild.equals(aux2)) {
                        aux2.leftChild = aux.leftChild;
                        aux2.leftChild.parent = aux2;
                        parent.rightChild = null;
                    }
                    aux2.rightChild = aux.rightChild;
                    aux2.rightChild.parent = aux2;
                }
                while (parent != null) {
                    rebalance(parent);
                    parent = parent.parent;
                }
                return true;
            }
        }
    }

    private class Node {

        private T key;
        private Node parent;
        private Node leftChild;
        private Node rightChild;

        private Node(T key) {
            this.key = key;
        }
    }
}

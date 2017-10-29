package tree;

public class RedBlackTree<T extends Comparable<? super T>> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public static void main(String[] args) {
        RedBlackTree<Integer> redBlackTree = new RedBlackTree<>();
        redBlackTree.insert(1);
        redBlackTree.insert(2);
        redBlackTree.insert(3);
        redBlackTree.insert(4);
        redBlackTree.insert(5);
        redBlackTree.insert(6);
        redBlackTree.insert(7);
        int i = 0;
    }

    private Node root;

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

    private void rebalance(Node node) {
        if (node.parent.parent == null)
            return;

        Node grandparent = node.parent.parent;
        Node parent = node.parent;
        Node uncle;

        if (parent.equals(grandparent.leftChild))
            uncle = grandparent.rightChild;
        else
            uncle = grandparent.leftChild;

        if (uncle != null && uncle.color == RED) {
            uncle.color = BLACK;
            parent.recolor();
            grandparent.recolor();
        } else {
            if (parent.equals(grandparent.leftChild)) {
                if (node.equals(parent.leftChild)) {
                    rotationRight(grandparent);
                    parent.recolor();
                    grandparent.recolor();
                } else {
                    rotationLeftRight(grandparent);
                    node.recolor();
                    grandparent.recolor();
                }
            } else {
                if (node.equals(parent.leftChild)) {
                    rotationRightLeft(grandparent);
                    node.recolor();
                    grandparent.recolor();
                } else {
                    rotationLeft(grandparent);
                    parent.recolor();
                    grandparent.recolor();
                }
            }
        }
    }

    public void insert(T value) {
        if (root == null) {
            root = new Node(value);
            root.color = BLACK;
            return;
        }

        Node aux = root;
        while (true) {
            if (value.compareTo(aux.key) < 0)
                if (aux.leftChild == null) {
                    aux.leftChild = new Node(value);
                    aux.leftChild.parent = aux;
                    rebalance(aux.leftChild);
                    break;
                } else
                    aux = aux.leftChild;
            else if (aux.rightChild == null) {
                aux.rightChild = new Node(value);
                aux.rightChild.parent = aux;
                rebalance(aux.rightChild);
                break;
            } else
                aux = aux.rightChild;
        }

        root.color = BLACK;
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

    //TODO
    public boolean remove (T key) {
//        Node aux = root;
//        while (true) {
//            if (aux == null)
//                return false;
//
//            if (key.compareTo(aux.key) < 0) {
//                aux = aux.leftChild;
//            } else if (key.compareTo(aux.key) > 0) {
//                aux = aux.rightChild;
//            } else {
//                Node parent = aux.parent;
//                if (aux.leftChild == null && aux.rightChild == null) {
//                    if (parent.leftChild.equals(aux)) parent.leftChild = null;
//                    else parent.rightChild = null;
//                } else if (aux.leftChild != null ^ aux.rightChild != null) {
//                    if (parent.leftChild.equals(aux))
//                        if (aux.leftChild != null) {
//                            parent.leftChild = aux.leftChild;
//                            aux.leftChild.parent = parent;
//                        } else {
//                            parent.leftChild = aux.rightChild;
//                            aux.rightChild.parent = parent;
//                        }
//                    else
//                    if (aux.leftChild != null) {
//                        parent.rightChild = aux.leftChild;
//                        aux.leftChild.parent = parent;
//                    } else {
//                        parent.rightChild = aux.rightChild;
//                        aux.rightChild.parent = parent;
//                    }
//                } else {
//                    Node aux2 = aux.leftChild;
//                    while (aux2.rightChild != null)
//                        aux2 = aux2.rightChild;
//                    if (parent.leftChild.equals(aux)) aux.parent.leftChild = aux2;
//                    else aux.parent.rightChild = aux2;
//                    parent = aux2.parent;
//                    aux2.parent = aux.parent;
//                    if (!aux.leftChild.equals(aux2)) {
//                        aux2.leftChild = aux.leftChild;
//                        aux2.leftChild.parent = aux2;
//                        parent.rightChild = null;
//                    }
//                    aux2.rightChild = aux.rightChild;
//                    aux2.rightChild.parent = aux2;
//                }
//                while (parent != null) {
//                    rebalance(parent);
//                    parent = parent.parent;
//                }
//                return true;
//            }
//        }
        return false;
    }

    private class Node {

        private T key;
        private Node parent;
        private Node leftChild;
        private Node rightChild;
        private boolean color;

        private Node(T key) {
            this.key = key;
            this.color = RED;
        }

        private void recolor() {
            this.color = !this.color;
        }
    }
}
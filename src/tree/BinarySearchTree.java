package tree;

import java.util.ArrayList;
import java.util.List;

public abstract class BinarySearchTree<T extends Comparable<? super T>> implements ITree<T> {

    protected abstract Node createNode(T key);

    private Node root;

    final int height(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.leftChild), height(node.rightChild));
    }

    private Node getGreatestNode(Node head) {
        if (head == null)
            return null;

        Node greater = head.rightChild;
        while (greater != null) {
            Node aux = greater.rightChild;
            if (aux != null)
                greater = aux;
            else break;
        }

        return greater;
    }

    @Override
    public final void insert(T value) {
        addValue(value);
    }

    protected Node addValue(T value) {
        Node newNode = createNode(value);

        if (root == null) {
            root = newNode;
            return newNode;
        }

        Node aux = root;
        while (true) {
            if (newNode.key.compareTo(aux.key) <= 0) {
                if (aux.leftChild == null) {
                    aux.leftChild = newNode;
                    newNode.parent = aux;
                    return newNode;
                }
                aux = aux.leftChild;
            } else {
                if (aux.rightChild == null) {
                    aux.rightChild = newNode;
                    newNode.parent = aux;
                    return newNode;
                }
                aux = aux.rightChild;
            }
        }
    }

    @Override
    public final T search(T key) {
        Node foundNode = getNode(key);
        return foundNode != null ? foundNode.key : null;
    }

    private Node getNode(T value) {
        Node aux = root;
        while (aux != null) {
            if (value.compareTo(aux.key) < 0)
                aux = aux.leftChild;
            else if (value.compareTo(aux.key) > 0)
                aux = aux.rightChild;
            else
                return aux;
        }
        return null;
    }

    final void rotateLeft(Node head) {
        Node parent = head.parent;
        Node rightChild = head.rightChild;
        Node leftChild = rightChild.leftChild;

        rightChild.leftChild = head;
        head.parent = rightChild;
        head.rightChild = leftChild;

        if (leftChild != null)
            leftChild.parent = head;

        if (parent != null) {
            if (head == parent.leftChild)
                parent.leftChild = rightChild;
            else
                parent.rightChild = rightChild;
            rightChild.parent = parent;
        } else {
            root = rightChild;
            root.parent = null;
        }
    }

    final void rotateRight(Node head) {
        Node parent = head.parent;
        Node leftChild = head.leftChild;
        Node rightChild = leftChild.rightChild;

        leftChild.rightChild = head;
        head.parent = leftChild;
        head.leftChild = rightChild;

        if (rightChild != null)
            rightChild.parent = head;

        if (parent != null) {
            if (head == parent.leftChild)
                parent.leftChild = leftChild;
            else
                parent.rightChild = leftChild;
            leftChild.parent = parent;
        } else {
            root = leftChild;
            root.parent = null;
        }
    }

    @Override
    public final boolean remove(T value) {
        Node nodeToRemove = getNode(value);
        return nodeToRemove != null && removeValue(nodeToRemove) != null;
    }

    protected Node removeValue(Node nodeToRemove) {
        Node replacementNode = getReplacementNode(nodeToRemove);
        replaceNode(nodeToRemove, replacementNode);
        return nodeToRemove;
    }

    final Node getReplacementNode(Node nodeToRemove) {
        Node replacementNode;
        if (nodeToRemove.rightChild != null && nodeToRemove.leftChild != null) {
            replacementNode = getGreatestNode(nodeToRemove.leftChild);
            if (replacementNode == null)
                replacementNode = nodeToRemove.leftChild;
        } else if (nodeToRemove.leftChild != null)
            replacementNode = nodeToRemove.leftChild;
        else
            replacementNode = nodeToRemove.rightChild;
        return replacementNode;
    }

    final void replaceNode(Node nodeToRemove, Node replacementNode) {
        if (replacementNode != null) {
            Node replacementLeftChild = replacementNode.leftChild;
            Node replacementRightChild = replacementNode.rightChild;

            Node nodeToRemoveLeftChild = nodeToRemove.leftChild;
            if (nodeToRemoveLeftChild != null && nodeToRemoveLeftChild != replacementNode) {
                replacementNode.leftChild = nodeToRemoveLeftChild;
                nodeToRemoveLeftChild.parent = replacementNode;
            }

            Node nodeToRemoveRightChild = nodeToRemove.rightChild;
            if (nodeToRemoveRightChild != null && nodeToRemoveRightChild != replacementNode) {
                replacementNode.rightChild = nodeToRemoveRightChild;
                nodeToRemoveRightChild.parent = replacementNode;
            }

            Node replacementParent = replacementNode.parent;
            if (replacementParent != null && replacementParent != nodeToRemove) {
                Node replacementParentLeftChild = replacementParent.leftChild;
                Node replacementParentRightChild = replacementParent.rightChild;
                if (replacementParentLeftChild != null && replacementParentLeftChild == replacementNode) {
                    replacementParent.leftChild = replacementRightChild;
                    if (replacementRightChild != null)
                        replacementRightChild.parent = replacementParent;
                } else if (replacementParentRightChild != null && replacementParentRightChild == replacementNode) {
                    replacementParent.rightChild = replacementLeftChild;
                    if (replacementLeftChild != null)
                        replacementLeftChild.parent = replacementParent;
                }
            }
        }

        Node parent = nodeToRemove.parent;
        if (parent == null) {
            root = replacementNode;
            if (root != null)
                root.parent = null;
        } else if (parent.leftChild != null && (parent.leftChild.key.compareTo(nodeToRemove.key) == 0)) {
            parent.leftChild = replacementNode;
            if (replacementNode != null)
                replacementNode.parent = parent;
        } else if (parent.rightChild != null && (parent.rightChild.key.compareTo(nodeToRemove.key) == 0)) {
            parent.rightChild = replacementNode;
            if (replacementNode != null)
                replacementNode.parent = parent;
        }
    }

    public final List<T> levelOrderTraversal() {
        List<T> elements = new ArrayList<>();
        if (root == null) return elements;
        int height = height(root);
        for (int i = 1; i <= height; i++)
            traverseLevel(root, i, elements);
        return elements;
    }

    private void traverseLevel(Node node, int level, List<T> elements) {
        if (node != null) {
            if (level == 1) elements.add(node.key);
            else if (level > 1) {
                traverseLevel(node.leftChild, level - 1, elements);
                traverseLevel(node.rightChild, level - 1, elements);
            }
        }
    }

    class Node {

        T key;
        Node parent;
        Node leftChild;
        Node rightChild;

        Node(T key) {
            this.key = key;
        }
    }
}

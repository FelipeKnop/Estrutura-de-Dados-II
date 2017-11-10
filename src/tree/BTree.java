package tree;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ConstantConditions")
public class BTree<T extends Comparable<? super T>> extends BenchmarkableTree<T> {

    /**
     * Número máximo de filhos que um nó pode ter (grau da árvore).
     */
    private final int maxChildrenSize;

    /**
     * Número máximo de chaves que um nó pode ter (grau - 1).
     */
    private final int maxKeySize;

    /**
     * Número mínimo de filhos que um nó pode ter ((grau - 1) / 2);
     */
    private final int minChildrenSize;

    /**
     * Número mínimo de chaves que um nó pode ter (((grau - 1) / 2) + 1).
     */
    private final int minKeySize;

    /**
     * Nó raiz da árvore.
     */
    private BNode root;

    public BTree(int degree) {
        this.maxChildrenSize = degree;
        this.maxKeySize = degree - 1;
        this.minKeySize = (int) Math.ceil((degree - 1) / 2);
        this.minChildrenSize = minKeySize + 1;
    }

    @Override
    public void insert(T value) {
        if (root == null) {
            root = new BNode();
            root.addKey(value);
            return;
        }

        BNode node = root;
        while (true) {
            if (node.children.size() == 0) {
                node.addKey(value);
                if (node.keys.size() <= maxKeySize) break;
                split(node);
                break;
            }

            comparisons++;
            if (value.compareTo(node.getKey(0)) <= 0) {
                node = node.getChild(0);
                continue;
            }

            int last = node.keys.size() - 1;
            comparisons++;
            if (value.compareTo(node.getKey(last)) > 0) {
                node = node.getChild(last + 1);
                continue;
            }

            for (int i = 1; i < node.keys.size(); i++)
                if (value.compareTo(node.getKey(i - 1)) > 0
                        && value.compareTo(node.getKey(i)) <= 0) {
                    comparisons += 2; // Por causa do &&
                    node = node.getChild(i);
                    break;
                }
        }
    }

    private void split(BNode nodeToSplit) {
        BNode node = nodeToSplit;
        int keys = node.keys.size();
        int medianIndex = keys / 2;
        T medianValue = node.getKey(medianIndex);

        BNode left = new BNode();
        for (int i = 0; i < medianIndex; i++)
            left.addKey(node.getKey(i));
        if (node.children.size() > 0)
            for (int i = 0; i <= medianIndex; i++)
                left.addChild(node.getChild(i));

        BNode right = new BNode();
        for (int i = medianIndex + 1; i < keys; i++)
            right.addKey(node.getKey(i));
        if (node.children.size() > 0)
            for (int i = medianIndex + 1; i <= node.children.size(); i++)
                right.addChild(node.getChild(i));

        if (node.parent == null) {
            BNode newRoot = new BNode();
            newRoot.addKey(medianValue);
            node.parent = newRoot;
            root = newRoot;
            node = root;
            node.addChild(left);
            node.addChild(right);
        } else {
            BNode parent = node.parent;
            parent.addKey(medianValue);
            parent.removeChild(node);
            parent.addChild(left);
            parent.addChild(right);

            if (parent.keys.size() > maxKeySize)
                split(parent);
        }
    }

    @Override
    public T search(T value) {
        BNode foundNode = getNode(value);
        return foundNode != null ? value : null;
    }

    private BNode getNode(T value) {
        BNode node = root;
        while (node != null) {
            comparisons++;
            if (value.compareTo(node.getKey(0)) < 0) {
                node = node.children.size() > 0 ? node.getChild(0) : null;
                continue;
            }

            int keys = node.keys.size();
            comparisons++;
            if (value.compareTo(node.getKey(keys - 1)) > 0) {
                node = node.children.size() > keys ? node.getChild(keys) : null;
                continue;
            }

            for (int i = 0; i < keys; i++) {
                T currentValue = node.getKey(i);
                comparisons++;
                if (currentValue.compareTo(value) == 0)
                    return node;

                int next = i + 1;
                if (next <= keys - 1) {
                    comparisons += 2; // Por causa do &&
                    if (currentValue.compareTo(value) < 0
                            && node.getKey(next).compareTo(value) > 0) {
                        if (next < node.children.size()) {
                            node = node.getChild(next);
                            break;
                        }

                        return null;
                    }
                }
            }
        }

        return null;
    }

    @Override
    public boolean remove(T value) {
        BNode node = getNode(value);
        return node != null && remove(value, node);
    }

    private boolean remove(T value, BNode node) {
        int index = node.keys.indexOf(value);
        T removed = node.removeKey(value);
        if (node.children.size() == 0) {
            if (node.parent != null && node.keys.size() < minKeySize)
                combine(node);
            else if (node.parent == null && node.keys.size() == 0)
                root = null;
        } else {
            BNode lesser = node.getChild(index);
            BNode greatest = getGreatestNode(lesser);
            T replaceValue = removeGreatestValue(greatest);
            node.addKey(replaceValue);
            if (greatest.parent != null && greatest.keys.size() < minKeySize)
                combine(greatest);
            if (greatest.children.size() > maxChildrenSize)
                split(greatest);
        }

        return removed != null;
    }

    private void combine(BNode node) {
        BNode parent = node.parent;
        int index = parent.children.indexOf(node);

        BNode leftNeighbor = null;
        int leftNeighborSize = -minChildrenSize;
        if (index - 1 >= 0) {
            leftNeighbor = parent.getChild(index - 1);
            leftNeighborSize = leftNeighbor.keys.size();
        }

        if (leftNeighbor != null && leftNeighborSize > minKeySize) {
            T removeValue = leftNeighbor.getKey(leftNeighbor.keys.size() - 1);
            int prev = getIndexOfNextValue(parent, removeValue);
            T parentValue = parent.removeKey(prev);
            T neighborValue = leftNeighbor.removeKey(leftNeighbor.keys.size() - 1);
            node.addKey(parentValue);
            parent.addKey(neighborValue);
            if (leftNeighbor.children.size() > 0)
                node.addChild(leftNeighbor.removeChild(leftNeighbor.children.size() - 1));
        } else {
            BNode rightNeighbor = null;
            int rightNeighborSize = -minChildrenSize;
            if (index + 1 < parent.children.size()) {
                rightNeighbor = parent.getChild(index + 1);
                rightNeighborSize = rightNeighbor.keys.size();
            }
            if (rightNeighbor != null && rightNeighborSize > minKeySize) {
                T removeValue = rightNeighbor.getKey(0);
                int prev = getIndexOfPreviousValue(parent, removeValue);
                T parentValue = parent.removeKey(prev);
                T neighborValue = rightNeighbor.removeKey(0);
                node.addKey(parentValue);
                parent.addKey(neighborValue);
                if (rightNeighbor.children.size() > 0)
                    node.addChild(rightNeighbor.removeChild(0));

            } else if (leftNeighbor != null && parent.keys.size() > 0) {
                T removeValue = leftNeighbor.getKey(leftNeighbor.keys.size() - 1);
                int prev = getIndexOfNextValue(parent, removeValue);
                T parentValue = parent.removeKey(prev);
                parent.removeChild(leftNeighbor);
                node.addKey(parentValue);
                for (int i = 0; i < leftNeighbor.keys.size(); i++) {
                    T value = leftNeighbor.getKey(i);
                    node.addKey(value);
                }
                for (int i = 0; i < leftNeighbor.children.size(); i++) {
                    BNode child = leftNeighbor.getChild(i);
                    node.addChild(child);
                }
                if (parent.parent != null && parent.keys.size() < minKeySize)
                    combine(parent);
                else if (parent.keys.size() == 0) {
                    node.parent = null;
                    root = node;
                }
            } else if (rightNeighbor != null && parent.keys.size() > 0) {
                T removeValue = rightNeighbor.getKey(0);
                int prev = getIndexOfPreviousValue(parent, removeValue);
                T parentValue = parent.removeKey(prev);
                parent.removeChild(rightNeighbor);
                node.addKey(parentValue);
                for (int i = 0; i < rightNeighbor.keys.size(); i++) {
                    T value = rightNeighbor.getKey(i);
                    node.addKey(value);
                }
                for (int i = 0; i < rightNeighbor.children.size(); i++) {
                    BNode child = rightNeighbor.getChild(i);
                    node.addChild(child);
                }
                if (parent.parent != null && parent.keys.size() < minKeySize)
                    combine(parent);
                else if (parent.keys.size() == 0) {
                    node.parent = null;
                    root = node;
                }

            }
        }
    }

    private int getIndexOfPreviousValue(BNode node, T value) {
        for (int i = 1; i < node.keys.size(); i++) {
            comparisons++;
            if (node.getKey(i).compareTo(value) >= 0)
                return i - 1;
        }
        return node.keys.size() - 1;
    }

    private int getIndexOfNextValue(BNode node, T value) {
        for (int i = 0; i < node.keys.size(); i++) {
            comparisons++;
            if (node.getKey(i).compareTo(value) >= 0)
                return i;
        }
        return node.keys.size() - 1;
    }

    private BNode getGreatestNode(BNode node) {
        BNode aux = node;
        while (aux.children.size() > 0)
            aux = aux.getChild(aux.children.size() - 1);
        return aux;
    }

    private T removeGreatestValue(BNode node) {
        return node.keys.size() > 0 ? node.removeKey(node.keys.size() - 1) : null;
    }

    @Override
    public void clear() {

    }

    private int height(BNode node) {
        if (node == null) return 0;
        if (node.getChild(0) == null) return 1;
        return 1 + height(node.getChild(0));
    }

    public List<T> levelOrderTraversal() {
        List<T> elements = new ArrayList<>();
        if (root == null) return elements; // Árvore vazia, lista deve retornar vazia
        int height = height(root); // Obtém altura da árvore calculando altura da raiz
        for (int i = 1; i <= height; i++)
            traverseLevel(root, i, elements); // Chama a função traverseLevel para cada nível da árvore
        return elements;
    }

    private void traverseLevel(BNode node, int level, List<T> elements) {
        if (node != null) {
            if (level == 1)
                elements.addAll(node.keys);
            else if (level > 1) {
                for (BNode child : node.children)
                    traverseLevel(child, level - 1, elements);
            }
        }
    }

    private class BNode implements Comparable<BNode> {

        private ArrayList<T> keys = new ArrayList<>();
        private ArrayList<BNode> children = new ArrayList<>();
        private BNode parent;

        private T getKey(int index) {
            return index < keys.size() ? keys.get(index) : null;
        }

        private void addKey(T value) {
            keys.add(value);
            keys.sort(T::compareTo);
        }

        private T removeKey(T value) {
            int index = keys.indexOf(value);
            return index >= 0 ? keys.remove(index) : null;
        }

        private T removeKey(int index) {
            return index < keys.size() ? keys.remove(index) : null;
        }

        private BNode getChild(int index) {
            return index < children.size() ? children.get(index) : null;
        }

        private boolean addChild(BNode child) {
            if (child == null) return false;
            child.parent = this;
            children.add(child);
            children.sort(BNode::compareTo);
            return true;
        }

        private boolean removeChild(BNode child) {
            return children.remove(child);
        }

        private BNode removeChild(int index) {
            return index < children.size() ? children.remove(index) : null;
        }

        @Override
        public int compareTo(BNode o) {
            return keys.get(0).compareTo(o.keys.get(0));
        }
    }
}

package tree;

import java.util.ArrayList;

@SuppressWarnings("ConstantConditions")
public class BTree<Key extends Comparable<? super Key>, Value> extends BenchmarkableTree<Key, Value> {

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
    public void insert(Key key, Value value) {
        if (root == null) {
            root = new BNode();
            root.addEntry(key, value);
            return;
        }

        BNode node = root;
        while (true) {
            if (node.children.size() == 0) {
                node.addEntry(key, value);
                if (node.entries.size() <= maxKeySize) break;
                split(node);
                break;
            }

            comparisons++;
            if (key.compareTo(node.getKey(0)) <= 0) {
                node = node.getChild(0);
                continue;
            }

            int last = node.entries.size() - 1;
            comparisons++;
            if (key.compareTo(node.getKey(last)) > 0) {
                node = node.getChild(last + 1);
                continue;
            }

            for (int i = 1; i < node.entries.size(); i++)
                if (key.compareTo(node.getKey(i - 1)) > 0
                        && key.compareTo(node.getKey(i)) <= 0) {
                    comparisons += 2; // Por causa do &&
                    node = node.getChild(i);
                    break;
                }
        }
    }

    private void split(BNode nodeToSplit) {
        BNode node = nodeToSplit;
        int keys = node.entries.size();
        int medianIndex = keys / 2;
        BNode.Entry medianValue = node.getEntry(medianIndex);
        copies++;
        BNode left = new BNode();
        for (int i = 0; i < medianIndex; i++)
            left.addEntry(node.getEntry(i));
        if (node.children.size() > 0)
            for (int i = 0; i <= medianIndex; i++)
                left.addChild(node.getChild(i));

        BNode right = new BNode();
        for (int i = medianIndex + 1; i < keys; i++)
            right.addEntry(node.getEntry(i));
        if (node.children.size() > 0)
            for (int i = medianIndex + 1; i <= node.children.size(); i++)
                right.addChild(node.getChild(i));

        if (node.parent == null) {
            BNode newRoot = new BNode();
            newRoot.addEntry(medianValue);
            node.parent = newRoot;
            root = newRoot;
            node = root;
            node.addChild(left);
            node.addChild(right);
        } else {
            copies++;
            BNode parent = node.parent;
            parent.addEntry(medianValue);
            parent.removeChild(node);
            parent.addChild(left);
            parent.addChild(right);

            if (parent.entries.size() > maxKeySize)
                split(parent);
        }
    }

    @Override
    public Value search(Key key) {
        BNode foundNode = getNode(key);
        return foundNode != null ? foundNode.getEntry(foundNode.indexOfEntry(key)).value : null;
    }

    private BNode getNode(Key key) {
        BNode node = root;
        while (node != null) {
            comparisons++;
            if (key.compareTo(node.getKey(0)) < 0) {
                node = node.children.size() > 0 ? node.getChild(0) : null;
                continue;
            }

            int keys = node.entries.size();
            comparisons++;
            if (key.compareTo(node.getKey(keys - 1)) > 0) {
                node = node.children.size() > keys ? node.getChild(keys) : null;
                continue;
            }

            for (int i = 0; i < keys; i++) {
                Key currentValue = node.getKey(i);
                comparisons++;
                if (currentValue.compareTo(key) == 0)
                    return node;

                int next = i + 1;
                if (next <= keys - 1) {
                    comparisons += 2; // Por causa do &&
                    if (currentValue.compareTo(key) < 0
                            && node.getKey(next).compareTo(key) > 0) {
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
    public boolean remove(Key key) {
        BNode node = getNode(key);
        return node != null && remove(key, node);
    }

    private boolean remove(Key key, BNode node) {
        int index = node.indexOfEntry(key);
        BNode.Entry removed = node.removeEntry(key);
        if (node.children.size() == 0) {
            if (node.parent != null && node.entries.size() < minKeySize)
                combine(node);
            else if (node.parent == null && node.entries.size() == 0)
                root = null;
        } else {
            BNode lesser = node.getChild(index);
            BNode greatest = getGreatestNode(lesser);
            BNode.Entry replaceValue = removeGreatestValue(greatest);
            copies++;
            node.addEntry(replaceValue);
            if (greatest.parent != null && greatest.entries.size() < minKeySize)
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
            leftNeighborSize = leftNeighbor.entries.size();
        }
        copies++;
        if (leftNeighbor != null && leftNeighborSize > minKeySize) {
            BNode.Entry removeValue = leftNeighbor.getEntry(leftNeighbor.entries.size() - 1);
            int prev = getIndexOfNextValue(parent, removeValue.key);
            BNode.Entry parentValue = parent.removeEntry(prev);
            BNode.Entry neighborValue = leftNeighbor.removeEntry(leftNeighbor.entries.size() - 1);
            node.addEntry(parentValue);
            parent.addEntry(neighborValue);
            if (leftNeighbor.children.size() > 0)
                node.addChild(leftNeighbor.removeChild(leftNeighbor.children.size() - 1));
        } else {
            BNode rightNeighbor = null;
            int rightNeighborSize = -minChildrenSize;
            if (index + 1 < parent.children.size()) {
                rightNeighbor = parent.getChild(index + 1);
                rightNeighborSize = rightNeighbor.entries.size();
            }
            if (rightNeighbor != null && rightNeighborSize > minKeySize) {
                BNode.Entry removeValue = rightNeighbor.getEntry(0);
                int prev = getIndexOfPreviousValue(parent, removeValue.key);
                BNode.Entry parentValue = parent.removeEntry(prev);
                BNode.Entry neighborValue = rightNeighbor.removeEntry(0);
                node.addEntry(parentValue);
                parent.addEntry(neighborValue);
                if (rightNeighbor.children.size() > 0)
                    node.addChild(rightNeighbor.removeChild(0));

            } else if (leftNeighbor != null && parent.entries.size() > 0) {
                BNode.Entry removeValue = leftNeighbor.getEntry(leftNeighbor.entries.size() - 1);
                int prev = getIndexOfNextValue(parent, removeValue.key);
                BNode.Entry parentValue = parent.removeEntry(prev);
                parent.removeChild(leftNeighbor);
                node.addEntry(parentValue);
                for (int i = 0; i < leftNeighbor.entries.size(); i++) {
                    BNode.Entry value = leftNeighbor.getEntry(i);
                    node.addEntry(value);
                }
                for (int i = 0; i < leftNeighbor.children.size(); i++) {
                    BNode child = leftNeighbor.getChild(i);
                    node.addChild(child);
                }
                if (parent.parent != null && parent.entries.size() < minKeySize)
                    combine(parent);
                else if (parent.entries.size() == 0) {
                    node.parent = null;
                    root = node;
                }
            } else if (rightNeighbor != null && parent.entries.size() > 0) {
                BNode.Entry removeValue = rightNeighbor.getEntry(0);
                int prev = getIndexOfPreviousValue(parent, removeValue.key);
                BNode.Entry parentValue = parent.removeEntry(prev);
                parent.removeChild(rightNeighbor);
                node.addEntry(parentValue);
                for (int i = 0; i < rightNeighbor.entries.size(); i++) {
                    BNode.Entry value = rightNeighbor.getEntry(i);
                    node.addEntry(value);
                }
                for (int i = 0; i < rightNeighbor.children.size(); i++) {
                    BNode child = rightNeighbor.getChild(i);
                    node.addChild(child);
                }
                if (parent.parent != null && parent.entries.size() < minKeySize)
                    combine(parent);
                else if (parent.entries.size() == 0) {
                    node.parent = null;
                    root = node;
                }

            }
        }
    }

    private int getIndexOfPreviousValue(BNode node, Key key) {
        for (int i = 1; i < node.entries.size(); i++) {
            comparisons++;
            if (node.getKey(i).compareTo(key) >= 0)
                return i - 1;
        }
        return node.entries.size() - 1;
    }

    private int getIndexOfNextValue(BNode node, Key key) {
        for (int i = 0; i < node.entries.size(); i++) {
            comparisons++;
            if (node.getKey(i).compareTo(key) >= 0)
                return i;
        }
        return node.entries.size() - 1;
    }

    private BNode getGreatestNode(BNode node) {
        BNode aux = node;
        while (aux.children.size() > 0)
            aux = aux.getChild(aux.children.size() - 1);
        return aux;
    }

    private BNode.Entry removeGreatestValue(BNode node) {
        return node.entries.size() > 0 ? node.removeEntry(node.entries.size() - 1) : null;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean validate() {
        return root == null || validateNode(root);
    }

    private boolean validateNode(BNode node) {
        int entries = node.entries.size();
        if (entries > 1) {
            for (int i = 1; i < entries; i++) { // Chaves tem que estar ordenadas
                Key previous = node.getKey(i - 1);
                Key next = node.getKey(i);
                if (previous.compareTo(next) > 0)
                    return false;
            }
        }

        int children = node.children.size();
        if (node.parent == null) { // Nó é raiz
            if (entries > maxKeySize)
                return false;
            else if (children == 0)
                return true;
            else if (children < 2)
                return false;
            else if (children > maxChildrenSize)
                return false;
        } else { // Nó não é raiz
            if (entries < minKeySize)
                return false;
            else if (entries > maxKeySize)
                return false;
            else if (children == 0)
                return true;
            else if (entries != children - 1)
                return false;
            else if (children < minChildrenSize)
                return false;
            else if (children > maxChildrenSize)
                return false;
        }

        BNode first = node.getChild(0);
        // A última chave do primeiro filho deve ser menor que a primeira chave do nó
        if (first.getKey(first.entries.size() - 1).compareTo(node.getKey(0)) > 0)
            return false;

        BNode last = node.getChild(node.children.size() - 1);
        // A primeira chave do último filho deve ser maior que a última chave do nó
        if (last.getKey(0).compareTo(node.getKey(node.entries.size() - 1)) < 0)
            return false;

        for (int i = 1; i < node.entries.size(); i++) {
            Key previous = node.getKey(i - 1);
            Key next = node.getKey(i);
            BNode child = node.getChild(i);
            if (previous.compareTo(child.getKey(0)) > 0)
                return false;
            if (next.compareTo(child.getKey(child.entries.size() - 1)) < 0)
                return false;
        }

        for (int i = 0; i < node.children.size(); i++) {
            BNode child = node.getChild(i);
            boolean valid = validateNode(child);
            if (!valid)
                return false;
        }

        return true;
    }

    private int height(BNode node) {
        if (node == null) return 0;
        if (node.getChild(0) == null) return 1;
        return 1 + height(node.getChild(0));
    }

    private class BNode implements Comparable<BNode> {

        private ArrayList<Entry> entries = new ArrayList<>();
        private ArrayList<BNode> children = new ArrayList<>();
        private BNode parent;

        private int indexOfEntry(Key key) {
            int index = -1;
            for (int i = 0; i < entries.size(); i++)
                if (entries.get(i).key.equals(key))
                    index = i;
            return index;
        }

        private Entry getEntry(int index) {
            return index < entries.size() ? entries.get(index) : null;
        }

        private Key getKey(int index) {
            return getEntry(index).key;
        }

        private void addEntry(Entry entry) {
            addEntry(entry.key, entry.value);
        }

        private void addEntry(Key key, Value value) {
            entries.add(new Entry(key, value));
            entries.sort(Entry::compareTo);
        }

        private Entry removeEntry(Key key) {
            int index = indexOfEntry(key);
            return index >= 0 ? entries.remove(index) : null;
        }

        private Entry removeEntry(int index) {
            return index < entries.size() ? entries.remove(index) : null;
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
            return entries.get(0).compareTo(o.entries.get(0));
        }

        private class Entry implements Comparable<Entry> {
            private Key key;
            private Value value;

            private Entry(Key key, Value value) {
                this.key = key;
                this.value = value;
            }

            @Override
            public int compareTo(Entry o) {
                return key.compareTo(o.key);
            }
        }
    }
}

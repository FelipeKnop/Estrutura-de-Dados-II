package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstrata que contém os métodos principais para Árvores Binárias de Busca.
 * Implementa a interface {@link ITree} pois possui os comportamentos de uma árvore.
 * @param <T> Tipo dos elementos que serão guardados nos nós da árvore. Como as
 *           árvores binárias de busca têm como princípio a inserção dos elementos
 *           em ordem crescente da esquerda para a direita dos nós, os elementos
 *           devem ser comparáveis, implementando a interface {@link Comparable}
 */
public abstract class BinarySearchTree<T extends Comparable<? super T>> implements ITree<T> {

    /**
     * Método abstrato (deve ser implementado por cada classe) que retorna
     * uma nova instância de nó da árvore à partir do dado a ser inserido.
     *
     * Garante a usabilidade do polimorfismo da classe {@link Node}.
     *
     * @param key Dado a ser inserido na árvore
     * @return Instância de {@link Node} criada
     */
    protected abstract Node createNode(T key);

    /**
     * Nó raiz da árvore.
     */
    Node root;

    final int height(Node node) {
        if (node == null) return 0; // Nós que não existem têm altura 0
        return 1 + Math.max(height(node.leftChild), height(node.rightChild));
    }

    /**
     * Função que retorna o nó com maior dado chave menor que a própria
     * que é filho de um nó recebido como parâmetro, ou seja, seu nó filho
     * mais à direita da subárvore à esquerda.
     * @param head Nó pai da subárvore
     * @return Nó com maior dado chave dentro da subárvore à esquerda
     */
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

    /**
     * Chama a função {@link #addValue(Comparable)}, que define o comportamento
     * de inserção de um elemento na árvore, passando o valor como parâmetro.
     * @param value Elemento a ser inserido
     */
    @Override
    public final void insert(T value) {
        addValue(value);
    }

    /**
     * Função que define o comportamento de inserção de um elemento na árvore.
     * Pode ser sobreescrita para definir um comportamento diferente do padrão.
     * @param value Valor a ser inserido na árvore
     * @return Nó inserido
     */
    protected Node addValue(T value) {
        Node newNode = createNode(value);

        if (root == null) { // Árvore vazia, raiz vira o novo nó
            root = newNode;
            return newNode;
        }

        // Percorre a árvore procurando onde o nó deve ser inserido
        Node aux = root;
        while (true) {
            if (newNode.key.compareTo(aux.key) <= 0) { // Se chave é menor ou igual à do nó atual, continua à esquerda
                if (aux.leftChild == null) { // Nó nulo foi encontrado, valor deve ser inserido nessa posição
                    aux.leftChild = newNode;
                    newNode.parent = aux;
                    return newNode;
                }
                aux = aux.leftChild;
            } else { // Se chave é maior que a do nó atual, continua à esquerda
                if (aux.rightChild == null) { // Nó nulo foi encontrado, valor deve ser inserido nessa posição
                    aux.rightChild = newNode;
                    newNode.parent = aux;
                    return newNode;
                }
                aux = aux.rightChild;
            }
        }
    }

    /**
     * Chama a função {@link #getNode(Comparable)} passando o valor a ser buscado
     * como parâmetro. Se a função {@link #getNode(Comparable)} retornar um nó não
     * nulo, o elemento foi encontrado é retornado, caso contrário o retorno é null.
     * @param key Elemento a ser buscado
     * @return Elemento a ser buscado caso encontrado ou null caso contrário
     */
    @Override
    public T search(T key) {
        Node foundNode = getNode(key); // Procura o nó que contém o valor recebido
        return foundNode != null ? foundNode.key : null;
    }

    /**
     * Função que encontra um nó em uma árvore a partir de sua chave.
     * Utiliza a propriedade da Árvore Binária de Busca para fazer
     * essa busca em O(logN).
     * @param value Chave do nó a ser encontrado
     * @return Nó que possui chave recebida
     */
    final Node getNode(T value) {
        // Percorre a árvore procurando o nó
        Node aux = root;
        while (aux != null) {
            if (value.compareTo(aux.key) < 0) // Se o valor é menor que a chave do nó atual, continua à esquerda
                aux = aux.leftChild;
            else if (value.compareTo(aux.key) > 0) // Se o valor é maior que a chave do nó atual, continua à esquerda
                aux = aux.rightChild;
            else
                return aux; // Nó encontrado
        }
        return null;
    }

    /**
     * Realiza rotação para a esquerda a partir do nó recebido.
     * @param head Nó a partir da qual será feita a rotação
     */
    final void rotateLeft(Node head) {
        // Guarda variáveis para uso posterior
        Node parent = head.parent;
        Node rightChild = head.rightChild;
        Node leftChild = rightChild.leftChild;

        // Troca o nó com seu filho à direita
        rightChild.leftChild = head;
        head.parent = rightChild;
        head.rightChild = leftChild;

        // Se filho à direita possui filho à esquerda, troca o ponteiro do pai
        if (leftChild != null)
            leftChild.parent = head;

        if (parent != null) { // Nó não é raiz
            if (head == parent.leftChild) // Nó é o filho à esquerda do pai
                parent.leftChild = rightChild; // Atualiza ponteiro de filho do pai do nó
            else // Nó é o filho à direita do pai
                parent.rightChild = rightChild; // Atualiza ponteiro de filho do pai do nó
            rightChild.parent = parent;
        } else { // Nó é raiz
            root = rightChild; // Atualiza a raiz
            root.parent = null;
        }
    }

    /**
     * Realiza rotação para a direita a partir do nó recebido.
     * @param head Nó a partir da qual será feita a rotação
     */
    final void rotateRight(Node head) {
        // Guarda variáveis para uso posterior
        Node parent = head.parent;
        Node leftChild = head.leftChild;
        Node rightChild = leftChild.rightChild;

        // Troca o nó com seu filho à esquerda
        leftChild.rightChild = head;
        head.parent = leftChild;
        head.leftChild = rightChild;

        // Se filho à esquerda possui filho à direita, troca o ponteiro do pai
        if (rightChild != null)
            rightChild.parent = head;

        if (parent != null) { // Nó não é raiz
            if (head == parent.leftChild) // Nó é o filho à esquerda do pai
                parent.leftChild = leftChild; // Atualiza ponteiro de filho do pai do nó
            else // Nó é o filho à direita do pai
                parent.rightChild = leftChild; // Atualiza ponteiro de filho do pai do nó
            leftChild.parent = parent;
        } else { // Nó é raiz
            root = leftChild; // Atualiza a raiz
            root.parent = null;
        }
    }

    /**
     * Chama a função {@link #getNode(Comparable)} para obter o nó que possui
     * a chave recebida. Se o nó retornado for não nulo, chama a função
     * {@link #removeValue(Node)} para removê-lo da árvore.
     * @param value Elemento a ser removido
     * @return Boolean que indica se o elemento foi encontrado e removido ou não
     */
    @Override
    public final boolean remove(T value) {
        Node nodeToRemove = getNode(value); // Procura o nó que contém o valor recebido
        return nodeToRemove != null && removeValue(nodeToRemove) != null; // Se for encontrado, chama função que o remove
    }

    /**
     * Função que remove um nó recebido como parâmetro da árvore.
     * Obtém o nó que substituirá o nó a ser removido através da função
     * {@link #getReplacementNode(Node)} e chama a função {@link #replaceNode(Node, Node)}
     * para realizar a remoção.
     * @param nodeToRemove Nó a ser removido
     * @return Nó que foi removido
     */
    protected Node removeValue(Node nodeToRemove) {
        Node replacementNode = getReplacementNode(nodeToRemove); // Obtém o nó que substituirá o removido
        replaceNode(nodeToRemove, replacementNode); // Chama a função que substitui os nós
        return nodeToRemove;
    }

    /**
     * Função que encontra e retorna o nó que deve substituir o nó a ser removido,
     * de acordo com as regras da Árvore Binária de Busca.
     * @param nodeToRemove Nó a ser removido
     * @return Nó substituto
     */
    final Node getReplacementNode(Node nodeToRemove) {
        Node replacementNode;
        if (nodeToRemove.rightChild != null && nodeToRemove.leftChild != null) { // Nó a ser removido possui os dois filhos
            replacementNode = getGreatestNode(nodeToRemove.leftChild); // Nesse caso, o nó que substitui é o filho com maior chave à esquerda
            if (replacementNode == null)
                replacementNode = nodeToRemove.leftChild;
        } else if (nodeToRemove.leftChild != null)  // Nó a ser removido só possui filho à esquerda
            replacementNode = nodeToRemove.leftChild; // Nó que substitui é o filho à esquerda
        else // Nó a ser removido só possui filho à direita
            replacementNode = nodeToRemove.rightChild; // Nó que substitui é o filho à direita
        return replacementNode;
    }

    /**
     * Substitui um nó da árvore por outro.
     * @param nodeToRemove Nó a ser removido
     * @param replacementNode Nó que tomará seu lugar
     */
    final void replaceNode(Node nodeToRemove, Node replacementNode) {
        if (replacementNode != null) {
            // Guarda as variáveis para uso posterior
            Node replacementLeftChild = replacementNode.leftChild;
            Node replacementRightChild = replacementNode.rightChild;

            // Troca a subárvore à esquerda de um nó para o outro
            Node nodeToRemoveLeftChild = nodeToRemove.leftChild;
            if (nodeToRemoveLeftChild != null && nodeToRemoveLeftChild != replacementNode) {
                replacementNode.leftChild = nodeToRemoveLeftChild;
                nodeToRemoveLeftChild.parent = replacementNode;
            }

            // Troca a subárvore à direita de um nó para o outro
            Node nodeToRemoveRightChild = nodeToRemove.rightChild;
            if (nodeToRemoveRightChild != null && nodeToRemoveRightChild != replacementNode) {
                replacementNode.rightChild = nodeToRemoveRightChild;
                nodeToRemoveRightChild.parent = replacementNode;
            }

            // Remove a ligação entre o nó a ser removido e seu pai
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

        // Atualiza as ligações na árvore do nó a ser removido para aquele que o substitui
        Node parent = nodeToRemove.parent;
        if (parent == null) {
            root = replacementNode;
            if (root != null)
                root.parent = null; // Atualiza o nó raiz
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

    /**
     * Retorna uma lista de elementos que corresponde ao percurso da árvore
     * em ordem de nível, utilizando o método {@link #traverseLevel(Node, int, List)}
     * para realizar tal percurso.
     * @return Lista de elementos
     */
    public final List<T> levelOrderTraversal() {
        List<T> elements = new ArrayList<>();
        if (root == null) return elements; // Árvore vazia, lista deve retornar vazia
        int height = height(root); // Obtém altura da árvore calculando altura da raiz
        for (int i = 1; i <= height; i++)
            traverseLevel(root, i, elements); // Chama a função traverseLevel para cada nível da árvore
        return elements;
    }

    /**
     * Função recursiva que percorre a árvore por nível, adicionando os elementos
     * de cada nível na lista que é repassada em cada chamada.
     * @param node Nó raiz da subárvore a ser percorrida
     * @param level Nível a ser analisado
     * @param elements Lista de elementos encontrados
     */
    private void traverseLevel(Node node, int level, List<T> elements) {
        if (node != null) {
            if (level == 1) elements.add(node.key);
            else if (level > 1) {
                traverseLevel(node.leftChild, level - 1, elements);
                traverseLevel(node.rightChild, level - 1, elements);
            }
        }
    }

    /**
     * Classe que representa um nó de uma Árvore Binária de Busca
     */
    class Node {

        T key; // Elemento a ser guardado pelo nó
        Node parent; // Pai do nó
        Node leftChild; // Filho à esquerda do nó (possui chave menor)
        Node rightChild; // Filho à direita do nó (possui chave maior)

        Node(T key) {
            this.key = key;
        }
    }
}

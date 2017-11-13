package tree;

/**
 * Classe abstrata que contém os métodos principais para Árvores Binárias de Busca.
 * Extende a classe {@link BenchmarkableTree} pois possui os comportamentos de uma
 * árvore, e será analisada posteriormente quanto a suas operações.
 * @param <Key>   Tipo das chaves que identificam os dados guardados. Como as
 *                árvores binárias de busca têm como princípio a inserção dos elementos
 *                em ordem crescente da esquerda para a direita dos nós, os elementos
 *                devem ser comparáveis, implementando a interface {@link Comparable}
 * @param <Value> Tipo dos dados a serem guardados pelos nós da árvore
 */
public abstract class BinarySearchTree<Key extends Comparable<? super Key>, Value> extends BenchmarkableTree<Key, Value> {

    /**
     * Método abstrato (deve ser implementado por cada classe) que retorna
     * uma nova instância de nó da árvore à partir do dado a ser inserido.
     * <p>
     * Garante a usabilidade do polimorfismo da classe {@link Node}.
     * @param key   Chave do dado a ser inserido na árvore
     * @param value Dado a ser inserido na árvore
     * @return Instância de {@link Node} criada
     */
    protected abstract Node createNode(Key key, Value value);

    /**
     * Nó raiz da árvore.
     */
    protected Node root;

    /**
     * Função que retorna o nó com maior chave menor que a própria
     * que é filho de um nó recebido como parâmetro, ou seja, seu nó filho
     * mais à direita da subárvore à esquerda.
     * @param head Nó pai da subárvore
     * @return Nó com maior chave dentro da subárvore à esquerda
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
     * Chama a função {@link #addValue(Comparable, Object)}, que define o comportamento
     * de inserção de um dado na árvore, passando a chave e o dado como parâmetros.
     * @param key   Chave do dado a ser inserido
     * @param value Dado a ser inserido
     */
    @Override
    public final void insert(Key key, Value value) {
        addValue(key, value);
    }

    /**
     * Função que define o comportamento de inserção de um dado na árvore.
     * Pode ser sobrescrita para definir um comportamento diferente do padrão.
     * @param key Chave do dado a ser inserido
     * @param value Dado a ser inserido
     * @return Nó inserido
     */
    protected Node addValue(Key key, Value value) {
        Node newNode = createNode(key, value);

        if (root == null) { // Árvore vazia, raiz vira o novo nó
            root = newNode;
            return newNode;
        }

        // Percorre a árvore procurando onde o nó deve ser inserido
        Node aux = root;
        while (true) {
            comparisons++;
            if (newNode.key.compareTo(aux.key) <= 0) { // Se chave é menor ou igual à do nó atual, continua à esquerda
                if (aux.leftChild == null) { // Nó nulo foi encontrado, novo nó deve ser inserido nessa posição
                    aux.leftChild = newNode;
                    newNode.parent = aux;
                    return newNode;
                }
                aux = aux.leftChild; // Continua à esquerda
            } else { // Se chave é maior que a do nó atual, continua à esquerda
                if (aux.rightChild == null) { // Nó nulo foi encontrado, novo nó deve ser inserido nessa posição
                    aux.rightChild = newNode;
                    newNode.parent = aux;
                    return newNode;
                }
                aux = aux.rightChild; // Continua à direita
            }
        }
    }

    /**
     * Chama a função {@link #getNode(Comparable)} passando a chave do dado a ser buscado
     * como parâmetro. Se a função {@link #getNode(Comparable)} retornar um nó não
     * nulo, o dado foi encontrado e é retornado, caso contrário o retorno é null.
     * @param key Chave do dado a ser buscado
     * @return Retorna o dado se for encontrado, null caso contrário
     */
    @Override
    public Value search(Key key) {
        Node foundNode = getNode(key); // Procura o nó que contém a chave recebida
        return foundNode != null ? foundNode.value : null;
    }

    /**
     * Função que encontra um nó em uma árvore a partir de sua chave.
     * Utiliza a propriedade da Árvore Binária de Busca para fazer
     * essa busca em O(logN).
     * @param key Chave do nó a ser encontrado
     * @return Nó que possui chave recebida
     */
    final Node getNode(Key key) {
        // Percorre a árvore procurando o nó
        Node aux = root;
        while (aux != null) {
            comparisons++;
            if (key.compareTo(aux.key) < 0) // Se o valor é menor que a chave do nó atual, continua à esquerda
                aux = aux.leftChild;
            else {
                comparisons++;
                if (key.compareTo(aux.key) > 0) // Se o valor é maior que a chave do nó atual, continua à esquerda
                    aux = aux.rightChild;
                else {
                    copies++;
                    return aux; // Nó encontrado
                }
            }
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
        copies++;
        // Se filho à direita possui filho à esquerda, troca o ponteiro do pai
        if (leftChild != null)
            leftChild.parent = head;

        if (parent != null) { // Nó não é raiz
            comparisons++;
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
        copies++;
        // Se filho à esquerda possui filho à direita, troca o ponteiro do pai
        if (rightChild != null)
            rightChild.parent = head;

        if (parent != null) { // Nó não é raiz
            comparisons++;
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
     * {@link #removeNode(Node)} para removê-lo da árvore.
     * @param key Chave do dado a ser removido
     * @return Retorna true se o dado existir na árvore e for removido,
     * false caso contrário
     */
    @Override
    public boolean remove(Key key) {
        Node nodeToRemove = getNode(key); // Procura o nó que contém o valor recebido
        return nodeToRemove != null && removeNode(nodeToRemove) != null; // Se for encontrado, chama função que o remove
    }

    /**
     * Função que remove um nó recebido como parâmetro da árvore.
     * Obtém o nó que substituirá o nó a ser removido através da função
     * {@link #getReplacementNode(Node)} e chama a função {@link #replaceNode(Node, Node)}
     * para realizar a remoção.
     * @param nodeToRemove Nó a ser removido
     * @return Nó que foi removido
     */
    protected Node removeNode(Node nodeToRemove) {
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
        } else {
            if (nodeToRemove.leftChild != null)  // Nó a ser removido só possui filho à esquerda
                replacementNode = nodeToRemove.leftChild; // Nó que substitui é o filho à esquerda
            else // Nó a ser removido só possui filho à direita
                replacementNode = nodeToRemove.rightChild; // Nó que substitui é o filho à direita
        }
        copies++;
        return replacementNode;
    }

    /**
     * Substitui um nó da árvore por outro.
     * @param nodeToRemove    Nó a ser removido
     * @param replacementNode Nó que tomará seu lugar
     */
    final void replaceNode(Node nodeToRemove, Node replacementNode) {
        if (replacementNode != null) {
            // Guarda as variáveis para uso posterior
            Node replacementLeftChild = replacementNode.leftChild;
            Node replacementRightChild = replacementNode.rightChild;

            // Troca a subárvore à esquerda de um nó para o outro
            Node nodeToRemoveLeftChild = nodeToRemove.leftChild;
            comparisons++;
            if (nodeToRemoveLeftChild != null && nodeToRemoveLeftChild != replacementNode) {
                replacementNode.leftChild = nodeToRemoveLeftChild;
                nodeToRemoveLeftChild.parent = replacementNode;
                copies++;
            }

            // Troca a subárvore à direita de um nó para o outro
            Node nodeToRemoveRightChild = nodeToRemove.rightChild;
            comparisons++;
            if (nodeToRemoveRightChild != null && nodeToRemoveRightChild != replacementNode) {
                replacementNode.rightChild = nodeToRemoveRightChild;
                nodeToRemoveRightChild.parent = replacementNode;
                copies++;
            }

            // Remove a ligação entre o nó a ser removido e seu pai
            Node replacementParent = replacementNode.parent;
            comparisons++;
            if (replacementParent != null && replacementParent != nodeToRemove) {
                Node replacementParentLeftChild = replacementParent.leftChild;
                Node replacementParentRightChild = replacementParent.rightChild;
                copies++;
                comparisons++;
                if (replacementParentLeftChild != null && replacementParentLeftChild == replacementNode) {
                    replacementParent.leftChild = replacementRightChild;
                    if (replacementRightChild != null)
                        replacementRightChild.parent = replacementParent;
                } else {
                    comparisons++;
                    if (replacementParentRightChild != null && replacementParentRightChild == replacementNode) {
                        replacementParent.rightChild = replacementLeftChild;
                        if (replacementLeftChild != null)
                            replacementLeftChild.parent = replacementParent;
                    }
                }
            }
        }

        // Atualiza as ligações na árvore do nó a ser removido para aquele que o substitui
        Node parent = nodeToRemove.parent;
        if (parent == null) {
            root = replacementNode;
            if (root != null)
                root.parent = null; // Atualiza o nó raiz
        } else {
            comparisons++;
            if (parent.leftChild != null && ((parent.leftChild.key==null && nodeToRemove.key==null)||(parent.leftChild.key!=null && nodeToRemove.key!=null && parent.leftChild.key.compareTo(nodeToRemove.key) == 0))) {
                parent.leftChild = replacementNode;
                if (replacementNode != null)
                    replacementNode.parent = parent;
            } else {
                comparisons++;
                if (parent.rightChild != null && ((parent.rightChild.key==null && nodeToRemove.key==null)||(parent.rightChild.key!=null && nodeToRemove.key!=null && parent.rightChild.key.compareTo(nodeToRemove.key) == 0))) {
                    parent.rightChild = replacementNode;
                    if (replacementNode != null)
                        replacementNode.parent = parent;
                }
            }
        }
    }

    /**
     * Limpa os dados da árvore.
     */
    @Override
    public void clear() {
        root = null;
    }

    /**
     * Valida a árvore de acordo com suas regras.
     * @return True se todos os nós seguem as devidas
     * regras, False caso contrário
     */
    @Override
    public boolean validate() {
        return root == null || validateNode(root);
    }

    /**
     * Função que valida um nó da árvore de acordo
     * com suas regras.
     * @param node Nó a ser validado
     * @return True se o nó segue as regras da árvore,
     * False caso contrário
     */
    protected boolean validateNode(Node node) {
        Node leftChild = node.leftChild;
        Node rightChild = node.rightChild;

        boolean leftCheck = true;
        if (leftChild != null) {
            leftCheck = leftChild.key.compareTo(node.key) <= 0;
            if (leftCheck)
                leftCheck = validateNode(leftChild);
        }
        if (!leftCheck)
            return false;

        boolean rightCheck = true;
        if (rightChild != null) {
            rightCheck = rightChild.key.compareTo(node.key) > 0;
            if (rightCheck)
                rightCheck = validateNode(rightChild);
        }
        return rightCheck;
    }

    /**
     * Classe que representa um nó de uma Árvore Binária de Busca
     */
    protected class Node {

        protected Key key; // Chave do dado a ser guardado pelo nó
        protected Value value; // Dado a ser guardado pelo nó
        protected Node parent; // Pai do nó
        protected Node leftChild; // Filho à esquerda do nó (possui chave menor)
        protected Node rightChild; // Filho à direita do nó (possui chave maior)

        protected Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }
}

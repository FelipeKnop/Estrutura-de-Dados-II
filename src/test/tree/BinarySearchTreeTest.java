package test.tree;

import org.junit.Test;
import tree.BinarySearchTree;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Classe abstrata que implementa os métodos de teste de árvores binárias de busca.
 */
public abstract class BinarySearchTreeTest {

    /**
     * Array de teste para as operações da árvore. Todas as operações serão testadas com
     * estes elementos nesta ordem.
     */
    private static final Integer[] TEST_ARRAY = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    /**
     * Variável que guarda o resultado da função {@link #getLevelOrderArray()}.
     */
    private final Integer[] LEVEL_ORDER_ARRAY = this.getLevelOrderArray();

    /**
     * Método abstrato (deve ser implementado por cada classe) que retorna uma array de elementos
     * indicando o resultado do percurso em ordem de nível dos elementos da árvore após a inserção
     * de todos os elementos da {@link #TEST_ARRAY}.
     * @return Array de inteiros de tamanho igual ao da {@link #TEST_ARRAY}
     */
    protected abstract Integer[] getLevelOrderArray();

    /**
     * Método abstrato (deve ser implementado por cada classe) que retorna uma instância de uma
     * subclasse de {@link BinarySearchTree<Integer>}.
     * @return Instância de subclasse relevante para a classe de teste que extende esta
     */
    protected abstract BinarySearchTree<Integer> getNewBinarySearchTree();

    /**
     * Método abstrato (deve ser implementado por cada classe) que retorna uma array de arrays
     * de elementos que indicam o resultado do percurso em ordem de nível dos elementos da árvore
     * após a remoção de cada elemento já inserido na árvore, na ordem indicada na documentação
     * do método {@link #removeTest()}.
     * @return Array de arrays de inteiros de tamanho igual ao da {@link #TEST_ARRAY}, onde cada
     * array interna tem tamanho igual a <code>TEST_ARRAY.length - i</code>, onde i é o indice
     * da array interna
     */
    protected abstract Integer[][] getRemoveLevelOrderArrays();

    /**
     * Teste de inserção de elementos na árvore binária de busca.
     *
     * Insere todos os elementos da array {@link #TEST_ARRAY} e verifica
     * se a array de elementos retornada pela função {@link #getLevelOrderElements(BinarySearchTree)}
     * equivale à array guardada na variável {@link #LEVEL_ORDER_ARRAY}.
     */
    @Test
    public final void insertTest() {
        BinarySearchTree<Integer> binarySearchTree = getNewBinarySearchTree();

        for (Integer number : TEST_ARRAY)
            binarySearchTree.insert(number);

        assertArrayEquals(getLevelOrderElements(binarySearchTree), LEVEL_ORDER_ARRAY);
    }

    /**
     * Teste de busca de elementos na árvore binária de busca.
     *
     * Primeiramente, realiza a busca de todos os elementos da array
     * {@link #TEST_ARRAY} na árvore e verifica se o resultado foi null
     * para todos os casos, já que a função {@link BinarySearchTree#search(Comparable)}
     * retorna null quando o elemento não é encontrado, que é o resultado esperado
     * já que a árvore nesse momento está vazia.
     *
     * Após isso, insere todos os elementos da array {@link #TEST_ARRAY} na árvore
     * e verifica, novamente, o resultado da função {@link BinarySearchTree#search(Comparable)},
     * que agora deve retornar o próprio número buscado em todos os casos, já que
     * a árvore possui todos esses números.
     */
    @Test
    public final void searchTest() {
        BinarySearchTree<Integer> binarySearchTree = getNewBinarySearchTree();

        for (Integer number : TEST_ARRAY)
            assertEquals(binarySearchTree.search(number), null);

        for (Integer number : TEST_ARRAY)
            binarySearchTree.insert(number);

        for (Integer number : TEST_ARRAY)
            assertEquals(binarySearchTree.search(number), number);
    }

    /**
     * Teste de remoção de elementos na árvore binária de busca.
     *
     * Primeiramente, realiza a remoção de todos os elementos da array
     * {@link #TEST_ARRAY} na árvore e verifica se o resultado foi false
     * para todos os casos, já que a função {@link BinarySearchTree#remove(Comparable)}
     * retorna null quando o elemento não é encontrado, que é o resultado esperado
     * já que a árvore nesse momento está vazia.
     *
     * Após isso, insere todos os elementos da array {@link #TEST_ARRAY} na árvore
     * e verifica, novamente, o resultado da função {@link BinarySearchTree#remove(Comparable)},
     * que deve estar na posição adequada da array retornada pela função
     * {@link #getRemoveLevelOrderArrays()}.
     *
     * Os elementos são removidos da árvore na seguinte ordem:
     * 5, 2, 12, 6, 10, 4, 11, 8, 14, 13, 7, 15, 3, 1, 9
     *
     * A resposta da função {@link #getRemoveLevelOrderArrays()} deve conter em sua
     * primeira posição o estado da árvore após a remoção do elemento 5, a segunda
     * posição deve conter o estado da árvore após a remoção do eleento 2, e assim
     * por diante.
     */
    @Test
    public final void removeTest() {
        BinarySearchTree<Integer> binarySearchTree = getNewBinarySearchTree();

        for (Integer number : TEST_ARRAY)
            assertEquals(binarySearchTree.remove(number), false);

        for (Integer number : TEST_ARRAY)
            binarySearchTree.insert(number);

        Integer[][] levelOrderArrays = this.getRemoveLevelOrderArrays();
        Integer[] removeOrder = new Integer[] {5, 2, 12, 6, 10, 4, 11, 8, 14, 13, 7, 15, 3, 1, 9};

        for (int i = 0; i < removeOrder.length; i++) {
            binarySearchTree.remove(removeOrder[i]);
            assertArrayEquals(getLevelOrderElements(binarySearchTree), levelOrderArrays[i]);
        }
    }

    /**
     * Retorna uma array de elementos indicando o resultado do percurso em ordem de nível dos
     * elementos da árvore recebida.
     * @param binarySearchTree Árvore binária de busca que deve ser percorrida
     * @return Array de inteiros que representam os elementos da árvore
     */
    private Integer[] getLevelOrderElements(BinarySearchTree<Integer> binarySearchTree) {
        List<Integer> elements = binarySearchTree.levelOrderTraversal();
        return elements.toArray(new Integer[elements.size()]);
    }
}

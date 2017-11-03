package test.tree;

import org.junit.Test;
import tree.BTree;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Classe de testes para a classe {@link tree.BTree}
 */
public class BTreeTest {

    /**
     * Grau da árvore B a ser testada.
     */
    private static final int DEGREE = 5;

    /**
     * Array de teste para as operações da árvore. Todas as operações serão testadas com
     * estes elementos nesta ordem.
     */
    private static final Integer[] TEST_ARRAY = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    /**
     * Array com o resultado do percurso em ordem de nível dos elementos da árvore após a inserção
     * de todos os elementos da {@link #TEST_ARRAY}.
     */
    private static final Integer[] LEVEL_ORDER_ARRAY = new Integer[] {3, 6, 9, 12, 1, 2, 4, 5, 7, 8, 10, 11, 13, 14, 15};

    /**
     * Array de arrays de elementos que indicam o resultado do percurso em ordem de nível dos elementos
     * da árvore após a remoção de cada elemento já inserido na árvore, na seguinte ordem:
     * 5, 2, 12, 6, 10, 4, 11, 8, 14, 13, 7, 15, 3, 1, 9
     */
    private static final Integer[][] REMOVE_LEVEL_ORDER_ARRAYS = new Integer[][] {
            {6, 9, 12, 1, 2, 3, 4, 7, 8, 10, 11, 13, 14, 15},
            {6, 9, 12, 1, 3, 4, 7, 8, 10, 11, 13, 14, 15},
            {6, 9, 13, 1, 3, 4, 7, 8, 10, 11, 14, 15},
            {4, 9, 13, 1, 3, 7, 8, 10, 11, 14, 15},
            {4, 13, 1, 3, 7, 8, 9, 11, 14, 15},
            {7, 13, 1, 3, 8, 9, 11, 14, 15},
            {7, 13, 1, 3, 8, 9, 14, 15},
            {13, 1, 3, 7, 9, 14, 15},
            {9, 1, 3, 7, 13, 15},
            {7, 1, 3, 9, 15},
            {1, 3, 9, 15},
            {1, 3, 9},
            {1, 9},
            {9},
            {},
    };

    /**
     * Teste de inserção de elementos na árvore B.
     *
     * Insere todos os elementos da array {@link #TEST_ARRAY} e verifica
     * se a array de elementos retornada pela função {@link #getLevelOrderElements(BTree)}
     * equivale à array guardada na variável {@link #LEVEL_ORDER_ARRAY}.
     */
    @Test
    public final void insertTest() {
        BTree<Integer> bTree = new BTree<>(DEGREE);

        for (Integer number : TEST_ARRAY)
            bTree.insert(number);

        assertArrayEquals(getLevelOrderElements(bTree), LEVEL_ORDER_ARRAY);
    }

    /**
     * Teste de busca de elementos na árvore B.
     *
     * Primeiramente, realiza a busca de todos os elementos da array
     * {@link #TEST_ARRAY} na árvore e verifica se o resultado foi null
     * para todos os casos, já que a função {@link BTree#search(Comparable)}
     * retorna null quando o elemento não é encontrado, que é o resultado esperado
     * já que a árvore nesse momento está vazia.
     *
     * Após isso, insere todos os elementos da array {@link #TEST_ARRAY} na árvore
     * e verifica, novamente, o resultado da função {@link BTree#search(Comparable)},
     * que agora deve retornar o próprio número buscado em todos os casos, já que
     * a árvore possui todos esses números.
     */
    @Test
    public final void searchTest() {
        BTree<Integer> bTree = new BTree<>(DEGREE);

        for (Integer number : TEST_ARRAY)
            assertEquals(bTree.search(number), null);

        for (Integer number : TEST_ARRAY)
            bTree.insert(number);

        for (Integer number : TEST_ARRAY)
            assertEquals(bTree.search(number), number);
    }

    /**
     * Teste de remoção de elementos na árvore B.
     *
     * Primeiramente, realiza a remoção de todos os elementos da array
     * {@link #TEST_ARRAY} na árvore e verifica se o resultado foi false
     * para todos os casos, já que a função {@link BTree#remove(Comparable)}
     * retorna null quando o elemento não é encontrado, que é o resultado esperado
     * já que a árvore nesse momento está vazia.
     *
     * Após isso, insere todos os elementos da array {@link #TEST_ARRAY} na árvore
     * e verifica, novamente, o resultado da função {@link BTree#remove(Comparable)},
     * que deve estar na posição adequada da array {@link #REMOVE_LEVEL_ORDER_ARRAYS}.
     *
     * Os elementos são removidos da árvore na seguinte ordem:
     * 5, 2, 12, 6, 10, 4, 11, 8, 14, 13, 7, 15, 3, 1, 9
     *
     * A resposta da função {@link #REMOVE_LEVEL_ORDER_ARRAYS} deve conter em sua
     * primeira posição o estado da árvore após a remoção do elemento 5, a segunda
     * posição deve conter o estado da árvore após a remoção do eleento 2, e assim
     * por diante.
     */
    @Test
    public final void removeTest() {
        BTree<Integer> bTree = new BTree<>(DEGREE);

        for (Integer number : TEST_ARRAY)
            assertEquals(bTree.remove(number), false);

        for (Integer number : TEST_ARRAY)
            bTree.insert(number);

        Integer[] removeOrder = new Integer[] {5, 2, 12, 6, 10, 4, 11, 8, 14, 13, 7, 15, 3, 1, 9};

        for (int i = 0; i < removeOrder.length; i++) {
            bTree.remove(removeOrder[i]);
            assertArrayEquals(getLevelOrderElements(bTree), REMOVE_LEVEL_ORDER_ARRAYS[i]);
        }
    }

    /**
     * Retorna uma array de elementos indicando o resultado do percurso em ordem de nível dos
     * elementos da árvore recebida.
     * @param bTree Árvore B que deve ser percorrida
     * @return Array de inteiros que representam os elementos da árvore
     */
    private Integer[] getLevelOrderElements(BTree<Integer> bTree) {
        List<Integer> elements = bTree.levelOrderTraversal();
        return elements.toArray(new Integer[elements.size()]);
    }
}

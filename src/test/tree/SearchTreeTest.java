package test.tree;

import org.junit.Test;
import tree.BenchmarkableTree;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Classe abstrata que implementa os métodos de teste de árvores de busca.
 */
public abstract class SearchTreeTest {

    /**
     * Variável que guarda uma lista de tamanho definido pela função {@link #getAmountOfElements()},
     * contendo todos os inteiros de 0 a esse tamanho em ordem aleatória.
     */
    private final List<Integer> ELEMENTS = getIntegerList();

    /**
     * Função abstrata que retorna a quantidade de elementos que deverão
     * ser inseridos na árvore para efetuar os testes.
     * @return Inteiro representando o tamanho da lista {@link #ELEMENTS}
     */
    protected abstract int getAmountOfElements();

    /**
     * Método abstrato (deve ser implementado por cada classe) que retorna uma instância de uma
     * subclasse de {@link tree.BenchmarkableTree<Integer>}.
     * @return Instância de subclasse relevante para a classe de teste que extende esta
     */
    protected abstract BenchmarkableTree<Integer, Integer> getNewSearchTree();

    /**
     * Teste de inserção de elementos na árvore de busca.
     *
     * Insere todos os elementos da array {@link #ELEMENTS} e verifica
     * se o retorno da função {@link tree.ITree#validate()} é True.
     */
    @Test
    public final void insertTest() {
        BenchmarkableTree<Integer, Integer> searchTree = getNewSearchTree();

        for (Integer number : ELEMENTS)
            searchTree.insert(number, number);

        assertTrue(searchTree.validate());
    }

    /**
     * Teste de busca de elementos na árvore de busca.
     *
     * Primeiramente, realiza a busca de todos os elementos da array
     * {@link #ELEMENTS} na árvore e verifica se o resultado foi null
     * para todos os casos, já que a função {@link tree.ITree#search(Object)}
     * retorna null quando o elemento não é encontrado, que é o resultado esperado
     * já que a árvore nesse momento está vazia.
     *
     * Após isso, insere todos os elementos da array {@link #ELEMENTS} na árvore
     * e verifica, novamente, o resultado da função {@link tree.ITree#search(Object)},
     * que agora deve retornar o próprio número buscado em todos os casos, já que
     * a árvore possui todos esses números, junto com o resultado da função
     * {@link tree.ITree#validate()}, que deve ser True, garantindo que a busca
     * não afetou nas regras da árvore.
     */
    @Test
    public final void searchTest() {
        BenchmarkableTree<Integer, Integer> searchTree = getNewSearchTree();

        for (Integer number : ELEMENTS)
            assertEquals(searchTree.search(number), null);

        for (Integer number : ELEMENTS)
            searchTree.insert(number, number);

        for (Integer number : ELEMENTS) {
            assertEquals(searchTree.search(number), number);
            assertTrue(searchTree.validate());
        }
    }

    /**
     * Teste de remoção de elementos na árvore de busca.
     *
     * Primeiramente, realiza a remoção de todos os elementos da array
     * {@link #ELEMENTS} na árvore e verifica se o resultado foi false
     * para todos os casos, já que a função {@link tree.ITree#remove(Object)}
     * retorna null quando o elemento não é encontrado, que é o resultado esperado
     * já que a árvore nesse momento está vazia.
     *
     * Após isso, insere todos os elementos da array {@link #ELEMENTS} na árvore
     * e verifica, novamente, o resultado da função {@link tree.ITree#remove(Object)},
     * que deve ser true, já que os elementos estão agora presentes na árvore, juntamente com
     * o resultado da função {@link tree.ITree#validate()}, que também deve ser true,
     * garantindo que a árvore continua seguindo suas regras.
     */
    @Test
    public final void removeTest() {
        BenchmarkableTree<Integer, Integer> searchTree = getNewSearchTree();

        for (Integer number : ELEMENTS)
            assertEquals(searchTree.remove(number), false);

        for (Integer number : ELEMENTS)
            searchTree.insert(number, number);

        for (Integer number : ELEMENTS) {
            assertEquals(searchTree.remove(number), true);
            assertTrue(searchTree.validate());
        }
    }

    /**
     * Função que cria uma lista de inteiros com todos os valores de 0 ao valor
     * retornado pela função {@link #getAmountOfElements()} em ordem aleatória.
     * @return Lista de inteiros
     */
    private final List<Integer> getIntegerList() {
        List<Integer> list =  IntStream.range(0, getAmountOfElements()).boxed().collect(Collectors.toList());
        Collections.shuffle(list, new Random(42));
        return list;
    }
}

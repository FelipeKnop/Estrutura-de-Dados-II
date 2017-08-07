package test.sort;

import org.junit.Test;
import sort.InsertionSort;
import test.TestUtils;

import java.util.List;

import static org.junit.Assert.fail;

/**
 * Classe de testes para os métodos da classe {@link sort.InsertionSort}
 */
public class InsertionSortTest {

    /**
     * Tamanho padrão das arrays/listas de teste.
     */
    private static final int DEFAULT_SIZE = 10000;

    /**
     * Testa a ordenação de uma array de inteiros aleatórios gerada pela classe
     * {@link TestUtils}. Caso algum elemento da array seja maior que o elemento
     * anterior, o teste falha, imprimindo quais são os elementos que falharam.
     * Caso isso não aconteça, a array está ordenada e essa informção é impressa.
     */
    @Test
    public void sortRandomIntegerArray() {
        Integer[] array = TestUtils.getRandomIntegerArray(DEFAULT_SIZE);
        InsertionSort insertionSort = new InsertionSort();
        Integer[] sorted = insertionSort.sort(array);
        for (int i = 1; i < sorted.length - 1; i++)
            if (sorted[i - 1].compareTo(sorted[i]) > 0)
                fail(String.format("A array não está ordenada corretamente. Índice [%d]: %d, índice [%d]: %d", i - 1, sorted[i - 1], i, sorted[i]));
        System.out.println("Array de inteiros aleatórios ordenada corretamente");
    }

    /**
     * Testa a ordenação de uma lista de inteiros aleatórios gerada pela classe
     * {@link TestUtils}. Caso algum elemento da lista seja maior que o elemento
     * anterior, o teste falha, imprimindo quais são os elementos que falharam.
     * Caso isso não aconteça, a lista está ordenada e essa informção é impressa.
     */
    @Test
    public void sortRandomIntegerList() {
        List<Integer> list = TestUtils.getRandomIntegerList(DEFAULT_SIZE);
        InsertionSort insertionSort = new InsertionSort();
        List<Integer> sorted = insertionSort.sort(list);
        for (int i = 1; i < sorted.size() - 1; i++)
            if (sorted.get(i - 1).compareTo(sorted.get(i)) > 0)
                fail(String.format("A lista não está ordenada corretamente. Índice [%d]: %d, índice [%d]: %d", i - 1, sorted.get(i - 1), i, sorted.get(i)));
        System.out.println("Lista de inteiros aleatórios ordenada corretamente");
    }

    /**
     * Testa a ordenação de uma array de strings aleatórias gerada pela classe
     * {@link TestUtils}. Caso algum elemento da array seja maior que o elemento
     * anterior, o teste falha, imprimindo quais são os elementos que falharam.
     * Caso isso não aconteça, a array está ordenada e essa informção é impressa.
     */
    @Test
    public void sortRandomStringArray() {
        String[] array = TestUtils.getRandomStringArray(DEFAULT_SIZE);
        InsertionSort insertionSort = new InsertionSort();
        String[] sorted = insertionSort.sort(array);
        for (int i = 1; i < sorted.length - 1; i++)
            if (sorted[i - 1].compareTo(sorted[i]) > 0)
                fail(String.format("A array não está ordenada corretamente. Índice [%d]: %s, índice [%d]: %s", i - 1, sorted[i - 1], i, sorted[i]));
        System.out.println("Array de strings aleatórias ordenada corretamente");
    }

    /**
     * Testa a ordenação de uma lista de strings aleatórias gerada pela classe
     * {@link TestUtils}. Caso algum elemento da lista seja maior que o elemento
     * anterior, o teste falha, imprimindo quais são os elementos que falharam.
     * Caso isso não aconteça, a lista está ordenada e essa informção é impressa.
     */
    @Test
    public void sortRandomStringList() {
        List<String> list = TestUtils.getRandomStringList(DEFAULT_SIZE);
        InsertionSort insertionSort = new InsertionSort();
        List<String> sorted = insertionSort.sort(list);
        for (int i = 1; i < sorted.size() - 1; i++)
            if (sorted.get(i - 1).compareTo(sorted.get(i)) > 0)
                fail(String.format("A lista não está ordenada corretamente. Índice [%d]: %s, índice [%d]: %s", i - 1, sorted.get(i - 1), i, sorted.get(i)));
        System.out.println("Lista de strings aleatórias ordenada corretamente");
    }
}

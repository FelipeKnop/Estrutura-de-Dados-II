package test.sort;

import org.junit.Test;
import sort.SortingAlgorithm;
import test.TestUtils;

import java.util.List;

import static org.junit.Assert.fail;

/**
 * Classe abstrata que implementa os métodos de teste de algoritmos de ordenação.
 */
public abstract class SortingAlgorithmTest {

    /**
     * Método abstrato (deve ser implementado por cada classe) que retorna o tamanho das arrays a serem ordenadas.
     * @return Tamanho das arrays
     */
    protected abstract int getDefaultSize();

    /**
     * Método abstrato (deve ser implementado por casa classe) que retorna o algoritmo de ordenação a ser uitilizado.
     * @return Algoritmo de ordenação que será utilizado nos testes
     */
    protected abstract SortingAlgorithm getSortingAlgorithm();

    /**
     * Variável que guarda o tamanho das arrays a serem ordenadas;
     */
    private final int defaultSize = this.getDefaultSize();

    /**
     * Variável que guarda o método de ordenação a ser utilizado.
     */
    private final SortingAlgorithm sortingAlgorithm = this.getSortingAlgorithm();

    /**
     * Testa a ordenação de uma array de inteiros aleatórios gerada pela classe
     * {@link TestUtils}. Caso algum elemento da array seja maior que o elemento
     * anterior, o teste falha, imprimindo quais são os elementos que falharam.
     * Caso isso não aconteça, a array está ordenada e essa informção é impressa.
     */
    @Test
    public final void sortRandomIntegerArray() {
        Integer[] array = TestUtils.getRandomIntegerArray(defaultSize);
        Integer[] sorted = sortingAlgorithm.sort(array);
        for (int i = 1; i < sorted.length - 1; i++)
            if (sorted[i - 1].compareTo(sorted[i]) > 0)
                fail(String.format("[" + getClass().getSimpleName() + "] - A array não está ordenada corretamente. Índice [%d]: %d, índice [%d]: %d", i - 1, sorted[i - 1], i, sorted[i]));
        System.out.println("[" + getClass().getSimpleName() + "] - Array de inteiros aleatórios ordenada corretamente");
        System.out.println("\tNúmero de comparações: " + sortingAlgorithm.getLastRunComparisons());
        System.out.println("\tNúmero de cópias de registros: " + sortingAlgorithm.getLastRunCopies());
        System.out.println(String.format("\tTempo gasto: %d milisegundos\n", sortingAlgorithm.getLastRunTimeSpent()));
    }

    /**
     * Testa a ordenação de uma lista de inteiros aleatórios gerada pela classe
     * {@link TestUtils}. Caso algum elemento da lista seja maior que o elemento
     * anterior, o teste falha, imprimindo quais são os elementos que falharam.
     * Caso isso não aconteça, a lista está ordenada e essa informção é impressa.
     */
    @Test
    public final void sortRandomIntegerList() {
        List<Integer> list = TestUtils.getRandomIntegerList(defaultSize);
        List<Integer> sorted = sortingAlgorithm.sort(list);
        for (int i = 1; i < sorted.size() - 1; i++)
            if (sorted.get(i - 1).compareTo(sorted.get(i)) > 0)
                fail(String.format("[" + getClass().getSimpleName() + "] - A lista não está ordenada corretamente. Índice [%d]: %d, índice [%d]: %d", i - 1, sorted.get(i - 1), i, sorted.get(i)));
        System.out.println("[" + getClass().getSimpleName() + "] - Lista de inteiros aleatórios ordenada corretamente");
        System.out.println("\tNúmero de comparações: " + sortingAlgorithm.getLastRunComparisons());
        System.out.println("\tNúmero de cópias de registros: " + sortingAlgorithm.getLastRunCopies());
        System.out.println(String.format("\tTempo gasto: %d milisegundos\n", sortingAlgorithm.getLastRunTimeSpent()));
    }

    /**
     * Testa a ordenação de uma array de strings aleatórias gerada pela classe
     * {@link TestUtils}. Caso algum elemento da array seja maior que o elemento
     * anterior, o teste falha, imprimindo quais são os elementos que falharam.
     * Caso isso não aconteça, a array está ordenada e essa informção é impressa.
     */
    @Test
    public final void sortRandomStringArray() {
        String[] array = TestUtils.getRandomStringArray(defaultSize);
        String[] sorted = sortingAlgorithm.sort(array);
        for (int i = 1; i < sorted.length - 1; i++)
            if (sorted[i - 1].compareTo(sorted[i]) > 0)
                fail(String.format("[" + getClass().getSimpleName() + "] - A array não está ordenada corretamente. Índice [%d]: %s, índice [%d]: %s", i - 1, sorted[i - 1], i, sorted[i]));
        System.out.println("[" + getClass().getSimpleName() + "] - Array de strings aleatórias ordenada corretamente");
        System.out.println("\tNúmero de comparações: " + sortingAlgorithm.getLastRunComparisons());
        System.out.println("\tNúmero de cópias de registros: " + sortingAlgorithm.getLastRunCopies());
        System.out.println(String.format("\tTempo gasto: %d milisegundos\n", sortingAlgorithm.getLastRunTimeSpent()));
    }

    /**
     * Testa a ordenação de uma lista de strings aleatórias gerada pela classe
     * {@link TestUtils}. Caso algum elemento da lista seja maior que o elemento
     * anterior, o teste falha, imprimindo quais são os elementos que falharam.
     * Caso isso não aconteça, a lista está ordenada e essa informção é impressa.
     */
    @Test
    public final void sortRandomStringList() {
        List<String> list = TestUtils.getRandomStringList(defaultSize);
        List<String> sorted = sortingAlgorithm.sort(list);
        for (int i = 1; i < sorted.size() - 1; i++)
            if (sorted.get(i - 1).compareTo(sorted.get(i)) > 0)
                fail(String.format("[" + getClass().getSimpleName() + "] - A lista não está ordenada corretamente. Índice [%d]: %s, índice [%d]: %s", i - 1, sorted.get(i - 1), i, sorted.get(i)));
        System.out.println("[" + getClass().getSimpleName() + "] - Lista de strings aleatórias ordenada corretamente");
        System.out.println("\tNúmero de comparações: " + sortingAlgorithm.getLastRunComparisons());
        System.out.println("\tNúmero de cópias de registros: " + sortingAlgorithm.getLastRunCopies());
        System.out.println(String.format("\tTempo gasto: %d milisegundos\n", sortingAlgorithm.getLastRunTimeSpent()));
    }
}

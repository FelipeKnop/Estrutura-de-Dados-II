package test.sort;

import sort.BubbleSort;
import sort.SortingAlgorithm;

/**
 * Classe de testes para os métodos da classe {@link BubbleSort}
 */
public class BubbleSortTest extends SortingAlgorithmTest {

    @Override
    protected int getDefaultSize() {
        return 10000;
    }

    @Override
    protected SortingAlgorithm getSortingAlgorithm() {
        return new BubbleSort();
    }
}

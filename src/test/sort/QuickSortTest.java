package test.sort;

import sort.QuickSort;
import sort.SortingAlgorithm;

/**
 * Classe de testes para os métodos da classe {@link sort.QuickSort}
 */
public class QuickSortTest extends SortingAlgorithmTest {

    @Override
    protected int getDefaultSize() {
        return 100000;
    }

    @Override
    protected SortingAlgorithm getSortingAlgorithm() {
        return new QuickSort();
    }
}

package test.sort;

import sort.InsertionSort;
import sort.SortingAlgorithm;

/**
 * Classe de testes para os métodos da classe {@link sort.InsertionSort}
 */
public class InsertionSortTest extends SortingAlgorithmTest {

    @Override
    protected int getDefaultSize() {
        return 10000;
    }

    @Override
    protected SortingAlgorithm getSortingAlgorithm() {
        return new InsertionSort();
    }
}

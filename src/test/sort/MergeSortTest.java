package test.sort;

import sort.MergeSort;
import sort.SortingAlgorithm;

/**
 * Classe de testes para os métodos da classe {@link sort.MergeSort}
 */
public class MergeSortTest extends SortingAlgorithmTest {

    @Override
    protected int getDefaultSize() {
        return 100000;
    }

    @Override
    protected SortingAlgorithm getSortingAlgorithm() {
        return new MergeSort();
    }
}

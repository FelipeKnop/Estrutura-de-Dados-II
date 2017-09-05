package test.sort;

import sort.HeapSort;
import sort.SortingAlgorithm;

public class HeapSortTest extends SortingAlgorithmTest {

    @Override
    protected int getDefaultSize() {
        return 100000;
    }

    @Override
    protected SortingAlgorithm getSortingAlgorithm() {
        return new HeapSort();
    }
}

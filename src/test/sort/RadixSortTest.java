package test.sort;

import sort.RadixSort;
import sort.SortingAlgorithm;

public class RadixSortTest extends SortingAlgorithmTest {

    @Override
    protected int getDefaultSize() {
        return 100000;
    }

    @Override
    protected SortingAlgorithm getSortingAlgorithm() {
        return new RadixSort();
    }

    @Override
    public void sortRandomStringArray() {
        System.out.println("[" + getClass().getSimpleName() + "] - Esse algoritmo não aceita elementos do tipo String");
    }

    @Override
    public void sortRandomStringList() {
        System.out.println("[" + getClass().getSimpleName() + "] - Esse algoritmo não aceita elementos do tipo String");
    }
}

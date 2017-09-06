package test.sort;

import sort.QuickSortMediana;
import sort.SortingAlgorithm;

/**
 * Classe de testes para os métodos da classe {@link sort.QuickSortMediana}
 */
public class QuickSortMedianaTest extends SortingAlgorithmTest {

    @Override
    protected int getDefaultSize() {
        return 100000;
    }

    @Override
    protected SortingAlgorithm getSortingAlgorithm() {
        return new QuickSortMediana();
    }
}

package test.sort;

import sort.QuickSortInsercao;
import sort.SortingAlgorithm;

/**
 * Classe de testes para os métodos da classe {@link sort.QuickSortInsercao}
 */
public class QuickSortInsercaoTest extends SortingAlgorithmTest {

    @Override
    protected int getDefaultSize() {
        return 100000;
    }

    @Override
    protected SortingAlgorithm getSortingAlgorithm() {
        return new QuickSortInsercao();
    }
}

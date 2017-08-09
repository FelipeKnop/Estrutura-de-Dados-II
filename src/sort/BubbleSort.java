package sort;

import java.util.Comparator;
import java.util.List;

/**
 * Classe que implementa a interface {@link SortingAlgorithm SortingAlgorithm} utilizando o algoritmo
 * <a href="https://en.wikipedia.org/wiki/Bubble_sort">BubbleSort</a>
 */
public class BubbleSort implements SortingAlgorithm {

    @Override
    public <T> T[] sortArray(T[] array, Comparator<? super T> comparator) {
        for (int i = 0; i < array.length - 1; i++)
            for (int j = i + 1; j < array.length; j++)
                if (comparator.compare(array[j], array[i]) < 0) {
                    T temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
        return array;
    }

    @Override
    public <T> List<T> sortList(List<T> list, Comparator<? super T> comparator) {
        for (int i = 0; i < list.size() - 1; i++)
            for (int j = i + 1; j < list.size(); j++)
                if (comparator.compare(list.get(j), list.get(i)) < 0)
                    list.set(i, list.set(j, list.get(i)));
        return list;
    }
}

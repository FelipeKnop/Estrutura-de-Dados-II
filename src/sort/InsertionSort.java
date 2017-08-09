package sort;

import java.util.Comparator;
import java.util.List;

/**
 * Classe que implementa a interface {@link SortingAlgorithm SortingAlgorithm} utilizando o algoritmo
 * <a href="https://en.wikipedia.org/wiki/Insertion_sort">InsertionSort</a>
 */
public class InsertionSort implements SortingAlgorithm {

    @Override
    public <T> T[] sortArray(T[] array, Comparator<? super T> comparator) {
        for (int i = 1; i < array.length; i++)
            for (int j = i; j > 0 && comparator.compare(array[j], array[j - 1]) < 0; j--) {
                T temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;
            }
        return array;
    }

    @Override
    public <T> List<T> sortList(List<T> list, Comparator<? super T> comparator) {
        for (int i = 1; i < list.size(); i++)
            for (int j = i; j > 0 && comparator.compare(list.get(j), list.get(j - 1)) < 0; j--)
                list.set(j, list.set(j - 1, list.get(j)));
        return list;
    }
}

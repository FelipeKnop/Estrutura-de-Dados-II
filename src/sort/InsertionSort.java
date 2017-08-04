package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Classe que implementa a interface {@link SortingAlgorithm SortingAlgorithm} utilizando o algoritmo
 * <a href="https://en.wikipedia.org/wiki/Insertion_sort">InsertionSort</a>
 */
public class InsertionSort implements SortingAlgorithm {

    @Override
    public <T> T[] sortArray(T[] array, Comparator<? super T> comparator) {
        T[] a = Arrays.copyOf(array, array.length);
        for (int i = 1; i < a.length; i++)
            for (int j = i; j > 0 && comparator.compare(a[j], a[j - 1]) < 0; j--) {
                T temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
            }
        return a;
    }

    @Override
    public <T> List<T> sortList(List<T> list, Comparator<? super T> comparator) {
        List<T> l = new ArrayList<>(list);
        for (int i = 1; i < l.size(); i++)
            for (int j = i; j > 0 && comparator.compare(l.get(j), l.get(j - 1)) < 0; j--)
                l.set(j, l.set(j - 1, l.get(j)));
        return l;
    }
}

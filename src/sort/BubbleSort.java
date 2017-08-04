package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Classe que implementa a interface {@link SortingAlgorithm SortingAlgorithm} utilizando o algoritmo
 * <a href="https://en.wikipedia.org/wiki/Bubble_sort">BubbleSort</a>
 */
public class BubbleSort implements SortingAlgorithm {

    @Override
    public <T extends Comparable<? super T>> T[] sort(T[] array) {
        return sortArray(array, T::compareTo);
    }

    @Override
    public <T> T[] sort(T[] array, Comparator<? super T> comparator) {
        return sortArray(array, comparator);
    }

    @Override
    public <T extends Comparable<? super T>> List<T> sort(List<T> list) {
        return sortList(list, T::compareTo);
    }

    @Override
    public <T> List<T> sort(List<T> list, Comparator<? super T> comparator) {
        return sortList(list, comparator);
    }

    private <T> T[] sortArray(T[] array, Comparator<? super T> comparator) {
        T[] a = Arrays.copyOf(array, array.length);
        for (int i = 0; i < a.length - 1; i++)
            for (int j = i + 1; j < a.length; j++)
                if (comparator.compare(a[j], a[i]) < 0) {
                    T temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
        return a;
    }

    private <T> List<T> sortList(List<T> list, Comparator<? super T> comparator) {
        List<T> l = new ArrayList<>(list);
        for (int i = 0; i < l.size() - 1; i++)
            for (int j = i + 1; j < l.size(); j++)
                if (comparator.compare(l.get(j), l.get(i)) < 0)
                    l.set(i, l.set(j, l.get(i)));
        return l;
    }
}

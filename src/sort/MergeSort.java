package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Classe que implementa a interface {@link SortingAlgorithm SortingAlgorithm} utilizando o algoritmo
 * <a href="https://en.wikipedia.org/wiki/Merge_sort">MergeSort</a>
 */
public class MergeSort implements SortingAlgorithm {

    @Override
    public <T> T[] sortArray(T[] array, Comparator<? super T> comparator) {
        T[] a = Arrays.copyOf(array, array.length);
        T[] a2 = Arrays.copyOf(array, array.length);
        for (int width = 1; width < a.length; width <<= 1) {
            for (int left = 0; left < a.length; left += width << 1) {
                int right = Math.min(left + width, a.length), end = Math.min(left + (width << 1), a.length);
                int i = left, j = right;
                for (int k = left; k < end; k++)
                    if (i < right && (j >= end || comparator.compare(a[i], a[j]) <= 0)) {
                        a2[k] = a[i];
                        i++;
                    } else {
                        a2[k] = a[j];
                        j++;
                    }
            }
            a = Arrays.copyOf(a2, a2.length);
        }
        return a;
    }

    @Override
    public <T> List<T> sortList(List<T> list, Comparator<? super T> comparator) {
        List<T> l = new ArrayList<>(list);
        List<T> l2 = new ArrayList<>(list);
        for (int width = 1; width < l.size(); width <<= 1) {
            for (int left = 0; left < l.size(); left += width << 1) {
                int right = Math.min(left + width, l.size()), end = Math.min(left + (width << 1), l.size());
                int i = left, j = right;
                for (int k = left; k < end; k++)
                    if (i < right && (j >= end || comparator.compare(l.get(i), l.get(j)) <= 0)) {
                        l2.set(k, l.get(i));
                        i++;
                    } else {
                        l2.set(k, l.get(j));
                        j++;
                    }
            }
            l = new ArrayList<>(l2);
        }
        return l;
    }
}

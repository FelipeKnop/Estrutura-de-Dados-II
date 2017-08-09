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
        for (int width = 1; width < array.length; width <<= 1) {
            for (int left = 0; left < array.length; left += width << 1) {
                int right = Math.min(left + width, array.length), end = Math.min(left + (width << 1), array.length);
                int i = left, j = right;
                for (int k = left; k < end; k++)
                    if (i < right && (j >= end || comparator.compare(array[i], array[j]) < 0)) {
                        a[k] = array[i];
                        i++;
                    } else {
                        a[k] = array[j];
                        j++;
                    }
            }
            array = Arrays.copyOf(a, a.length);
        }
        return array;
    }

    @Override
    public <T> List<T> sortList(List<T> list, Comparator<? super T> comparator) {
        List<T> l = new ArrayList<>(list);
        for (int width = 1; width < list.size(); width <<= 1) {
            for (int left = 0; left < list.size(); left += width << 1) {
                int right = Math.min(left + width, list.size()), end = Math.min(left + (width << 1), list.size());
                int i = left, j = right;
                for (int k = left; k < end; k++)
                    if (i < right && (j >= end || comparator.compare(list.get(i), list.get(j)) < 0)) {
                        l.set(k, list.get(i));
                        i++;
                    } else {
                        l.set(k, list.get(j));
                        j++;
                    }
            }
            list = new ArrayList<>(l);
        }
        return list;
    }
}

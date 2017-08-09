package sort;

import java.util.Arrays;
import java.util.Comparator;

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
}

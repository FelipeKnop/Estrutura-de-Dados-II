package sort;

import java.util.Comparator;

/**
 * Classe que implementa a interface {@link SortingAlgorithm SortingAlgorithm} utilizando o algoritmo
 * <a href="https://en.wikipedia.org/wiki/Insertion_sort">InsertionSort</a>
 */
public class InsertionSort implements SortingAlgorithm {

    @Override
    public <T> T[] sortArray(T[] array, Comparator<? super T> comparator) {
        for (int i = 1; i < array.length; i++) {
            T pivot = array[i];
            int left = 0, right = i;
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (comparator.compare(pivot, array[mid]) < 0)
                    right = mid;
                else
                    left = mid + 1;
            }
            System.arraycopy(array, left, array, left + 1, i - left);
            array[left] = pivot;
        }
        return array;
    }
}

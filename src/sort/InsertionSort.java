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

    @Override
    public <T> List<T> sortList(List<T> list, Comparator<? super T> comparator) {
        for (int i = 1; i < list.size(); i++) {
            T pivot = list.get(i);
            int left = 0, right = i;
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (comparator.compare(pivot, list.get(mid)) < 0)
                    right = mid;
                else
                    left = mid + 1;
            }
            for (int j = i; j > left; j--)
                list.set(j, list.get(j - 1));
            list.set(left, pivot);
        }
        return list;
    }
}

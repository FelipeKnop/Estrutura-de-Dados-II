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
        for (int i = 1; i < array.length; ++i) {
            int j = i - 1;
            T selected = array[i];
            int loc = binarySearch(array, selected, 0, j, comparator);
            while (j >= loc) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = selected;
        }
        return array;
    }

    private <T> int binarySearch(T[] array, T selected, int left, int right, Comparator<? super T> comparator) {
        if (right <= left)
            return comparator.compare(selected, array[left]) > 0 ? left + 1 : left;

        int mid = (left + right) / 2;

        if (comparator.compare(selected, array[mid]) == 0)
            return mid + 1;

        if (comparator.compare(selected, array[mid]) > 0)
            return binarySearch(array, selected, mid + 1, right, comparator);

        return binarySearch(array, selected, left, mid - 1, comparator);
    }

    @Override
    public <T> List<T> sortList(List<T> list, Comparator<? super T> comparator) {
        for (int i = 1; i < list.size(); ++i) {
            int j = i - 1;
            T selected = list.get(i);
            int loc = binarySearch(list, selected, 0, j, comparator);
            while (j >= loc) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, selected);
        }
        return list;
    }

    private <T> int binarySearch(List<T> list, T selected, int left, int right, Comparator<? super T> comparator) {
        if (right <= left)
            return comparator.compare(selected, list.get(left)) > 0 ? left + 1 : left;

        int mid = (left + right) / 2;

        if (comparator.compare(selected, list.get(mid)) == 0)
            return mid + 1;

        if (comparator.compare(selected, list.get(mid)) > 0)
            return binarySearch(list, selected, mid + 1, right, comparator);

        return binarySearch(list, selected, left, mid - 1, comparator);
    }
}

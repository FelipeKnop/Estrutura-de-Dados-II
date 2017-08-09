package sort;

import java.util.Comparator;

/**
 * Classe que implementa a interface {@link SortingAlgorithm SortingAlgorithm} utilizando o algoritmo
 * <a href="https://en.wikipedia.org/wiki/Quicksort">QuickSort</a>
 */
public class QuickSort implements SortingAlgorithm {

    @Override
    public <T> T[] sortArray(T[] array, Comparator<? super T> comparator) {
        quickSort(array, 0, array.length - 1, comparator);
        return array;
    }

    private <T> void quickSort(T[] array, int left, int right, Comparator<? super T> comparator) {
        if (left < right) {
            int pi = partition(array, left, right, comparator);
            quickSort(array, left, pi - 1, comparator);
            quickSort(array, pi + 1, right, comparator);
        }
    }

    private <T> int partition(T[] array, int left, int right, Comparator<? super T> comparator) {
        int i = left - 1;
        for (int j = left; j < right; j++)
            if (comparator.compare(array[j], array[right]) <= 0) {
                i++;
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        T temp = array[i + 1];
        array[i + 1] = array[right];
        array[right] = temp;

        return i + 1;
    }
}

package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Classe que implementa a interface {@link SortingAlgorithm SortingAlgorithm} utilizando o algoritmo
 * <a href="https://en.wikipedia.org/wiki/Quicksort">QuickSort</a>
 */
public class QuickSort implements SortingAlgorithm {

    @Override
    public <T> T[] sortArray(T[] array, Comparator<? super T> comparator) {
        T[] a = Arrays.copyOf(array, array.length);
        quickArray(a, 0, a.length - 1, comparator);
        return a;
    }

    private <T> void quickArray(T[] array, int left, int right, Comparator<? super T> comparator) {
        if (left < right) {
            int pi = partitionArray(array, left, right, comparator);
            quickArray(array, left, pi - 1, comparator);
            quickArray(array, pi + 1, right, comparator);
        }
    }

    private <T> int partitionArray(T[] array, int left, int right, Comparator<? super T> comparator) {
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

    @Override
    public <T> List<T> sortList(List<T> list, Comparator<? super T> comparator) {
        List<T> l = new ArrayList<>(list);
        quickList(l, 0, l.size() - 1, comparator);
        return l;
    }

    private <T> void quickList(List<T> list, int left, int right, Comparator<? super T> comparator) {
        if (left < right) {
            int pi = partitionList(list, left, right, comparator);
            quickList(list, left, pi - 1, comparator);
            quickList(list, pi + 1, right, comparator);
        }
    }

    private <T> int partitionList(List<T> list, int left, int right, Comparator<? super T> comparator) {
        int i = left - 1;
        for (int j = left; j < right; j++)
            if (comparator.compare(list.get(j), list.get(right)) <= 0) {
                i++;
                list.set(i, list.set(j, list.get(i)));
            }
        list.set(i + 1, list.set(right, list.get(i + 1)));

        return i + 1;
    }
}

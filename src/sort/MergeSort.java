package sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Classe que extende a classe abstrata {@link SortingAlgorithm SortingAlgorithm} utilizando o algoritmo
 * <a href="https://en.wikipedia.org/wiki/Merge_sort">MergeSort</a>
 */
public class MergeSort extends SortingAlgorithm {

    /**
     * Implementação iterativa do MergeSort.
     *
     * @param array Array a ser ordenada
     * @param comparator Implementação da interface {@link Comparator} que define como um
     *                   elemento do tipo T deve ser comparado a outro
     * @param <T> Tipo da array
     * @return Retorna a array com seus elementos ordenados de acordo
     * com a implementação da interface {@link Comparator} recebida como argumento
     */
    @Override
    <T> T[] sortArray(T[] array, Comparator<? super T> comparator) {
        setup();
        T[] a = Arrays.copyOf(array, array.length);
        for (int width = 1; width < array.length; width <<= 1) {
            for (int left = 0; left < array.length; left += width << 1) {
                int right = Math.min(left + width, array.length), end = Math.min(left + (width << 1), array.length);
                // Início da função Merge
                int i = left, j = right;
                for (int k = left; k < end; k++) {
                    if (i < right && (j >= end || comparator.compare(array[i], array[j]) < 0)) {
                        a[k] = array[i];
                        this.lastRunCopies++;
                        i++;
                    } else {
                        a[k] = array[j];
                        this.lastRunCopies++;
                        j++;
                    }
                    this.lastRunComparisons++;
                }
            }
            array = Arrays.copyOf(a, a.length);
            this.lastRunCopies += a.length;
        }
        end();
        return array;
    }
}

package sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Classe que extende a classe abstrata {@link SortingAlgorithm SortingAlgorithm} utilizando uma
 * variação do algoritmo <a href="https://en.wikipedia.org/wiki/Quicksort">QuickSort</a> que utiliza
 * a mediana de k elementos da partição como pivot
 */
public class QuickSortMediana extends SortingAlgorithm {

    private int k = 3;

    /**
     * Implementação de uma variação do QuickSort recursivo que seleciona o pivot
     * da partição a partir da mediana de k elementos da partição selecionados
     * aleatoriamente.
     *
     * @param array      Array a ser ordenada
     * @param comparator Implementação da interface {@link Comparator} que define como um
     *                   elemento do tipo T deve ser comparado a outro
     * @param <T>        Tipo da array
     * @return Retorna a array com seus elementos ordenados de acordo
     * com a implementação da interface {@link Comparator} recebida como argumento
     */
    @Override
    <T> T[] sortArray(T[] array, Comparator<? super T> comparator) {
        setup();
        quickSort(array, 0, array.length - 1, comparator);
        end();
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
        T vet[], temp;
        int i = left - 1, ale = left;
        vet = Arrays.copyOf(array, k);
        for (int r = 0; r < k; r++) {
            ale = new Random().nextInt((right + 1) - left) + left;
            vet[r] = array[ale];
        }

        Arrays.sort(vet, comparator);
        for (int r = left; r <= right; r++)
            if (array[r] == vet[(k - 1) / 2]) {
                ale = r;
                break;
            }
        temp = array[right];
        array[right] = array[ale];
        array[ale] = temp;
        for (int j = left; j < right; j++) {
            if (comparator.compare(array[j], array[right]) <= 0) {
                i++;
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                this.lastRunCopies++;
            }
            this.lastRunComparisons++;
        }
        temp = array[i + 1];
        array[i + 1] = array[right];
        array[right] = temp;
        this.lastRunCopies++;
        return i + 1;
    }

    public QuickSortMediana setK(int k) {
        this.k = k;
        return this;
    }
}

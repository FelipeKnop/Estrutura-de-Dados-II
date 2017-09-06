package sort;

import java.util.Comparator;

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
        //TODO: Implementar
        return 0;
    }

    public void setK(int k) {
        this.k = k;
    }
}

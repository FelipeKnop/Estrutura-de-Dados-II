package sort;

import java.util.Comparator;

/**
 * Classe que extende a classe abstrata {@link SortingAlgorithm SortingAlgorithm} utilizando uma
 * variação do algoritmo <a href="https://en.wikipedia.org/wiki/Quicksort">QuickSort</a> que utiliza
 * o {@link InsertionSort} para ordenar partições de tamanho menor ou igual a m.
 */
public class QuickSortInsercao extends SortingAlgorithm {

    private int m = 10;

    /**
     * Implementação de uma variação do QuickSort recursivo que utiliza
     * o {@link InsertionSort} para ordernar partições de tamanho menor
     * ou igual a m.
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
        //TODO: Implementar
    }

    private <T> int partition(T[] array, int left, int right, Comparator<? super T> comparator) {
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (comparator.compare(array[j], array[right]) <= 0) {
                i++;
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                this.lastRunCopies++;
            }
            this.lastRunComparisons++;
        }
        T temp = array[i + 1];
        array[i + 1] = array[right];
        array[right] = temp;
        this.lastRunCopies++;
        return i + 1;
    }

    public void setM(int m) {
        this.m = m;
    }
}

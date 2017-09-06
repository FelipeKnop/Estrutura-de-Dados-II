package sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Classe que extende a classe abstrata {@link SortingAlgorithm SortingAlgorithm} utilizando uma
 * variação do algoritmo <a href="https://en.wikipedia.org/wiki/Quicksort">QuickSort</a> que utiliza
 * o {@link InsertionSort} para ordenar partições de tamanho menor ou igual a m.
 */
public class QuickSortInsercao extends SortingAlgorithm {

    /**
     * Variável M que indica o tamanho máximo da partição a ser ordenada
     * pelo método da inserção.
     */
    private int m = 100;

    /**
     * Instância da classe {@link InsertionSort} que será usada para
     * ordenar as partições pelo método da inserção.
     */
    private InsertionSort insertionSort = new InsertionSort();

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
        if (left < right) {
            int pi = partition(array, left, right, comparator);

            if ((pi - 1) - left <= m)
                insertionSort(array, left, pi - 1, comparator);
            else
                quickSort(array, left, pi - 1, comparator);

            if (right - (pi + 1) <= m)
                insertionSort(array, pi + 1, right, comparator);
            else
                quickSort(array, pi + 1, right, comparator);
        }
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

    /**
     * Método que utiliza a classe {@link InsertionSort} para ordenar a partição
     * utilziando o método da inserção.
     * @param array Array a ser ordenada
     * @param left Índice de início da partição a ser ordenada
     * @param right Índice de término da partição a ser ordenada
     * @param comparator Implementação da interface {@link Comparator} que define como um
     *                   elemento do tipo T deve ser comparado a outro
     * @param <T> Tipo da array
     */
    private <T> void insertionSort(T[] array, int left, int right, Comparator<? super T> comparator) {
        T[] sortedArray = insertionSort.sort(Arrays.copyOfRange(array, left, right + 1), comparator);
        this.lastRunComparisons += insertionSort.getLastRunComparisons();
        this.lastRunCopies += insertionSort.getLastRunCopies();
        for (int i = 0; i <= right - left; i++)
            array[i + left] = sortedArray[i];
//        System.arraycopy(sortedArray, 0, array, left, (right + 1) - left);
    }

    public QuickSortInsercao setM(int m) {
        this.m = m;
        return this;
    }
}

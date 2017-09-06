package sort;

import java.util.Comparator;

/**
 * Classe que extende a classe abstrata {@link SortingAlgorithm SortingAlgorithm} utilizando o algoritmo
 * <a href="https://en.wikipedia.org/wiki/Bubble_sort">BubbleSort</a>
 */
public class BubbleSort extends SortingAlgorithm {

    /**
     * Implementação padrão do BubbleSort.
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
        for (int i = 0; i < array.length - 1; i++)
            for (int j = i + 1; j < array.length; j++) {
                if (comparator.compare(array[j], array[i]) < 0) {
                    T temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                    this.lastRunCopies++;
                }
                this.lastRunComparisons++;
            }
        end();
        return array;
    }
}

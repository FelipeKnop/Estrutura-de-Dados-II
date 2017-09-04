package sort;

import java.util.Comparator;

/**
 * Classe que extende a classe abstrata {@link SortingAlgorithm SortingAlgorithm} utilizando o algoritmo
 * <a href="https://en.wikipedia.org/wiki/Insertion_sort">InsertionSort</a>
 */
public class InsertionSort extends SortingAlgorithm {

    /**
     * Implementação modificada do InsertionSort.
     *
     * O InsertionSort original, para cada elemento da array, procura seu lugar devido
     * na array ordenada, percorrendo todos os elementos a sua esquerda na array.
     * Isso faz com que o InsertionSort tenha subestrutura ótima, ou seja, que a cada
     * iteração do loop principal, uma subarray de tamanho i (sendo i o índice atual
     * do loop) iniciada na posição 0 da array original já se encontra ordenada.
     *
     * Dado que o algoritmo possui subestrutura ótima, é possível fazer uma alteração
     * em sua lógica: ao procurar a posição devida de um elemento comparando-o
     * aos elementos a sua esquerda, está se fazendo uma busca em uma subarray já
     * ordenada, o que permite o uso do algoritmo de busca binária para encontrar
     * o lugar onde será inserido tal elemento.
     *
     * Essa alteração faz com que o número de comparações feito pelo algoritmo seja
     * reduzido de n² para n*log(n), ainda que sua complexidade permaneça O(n²) por
     * conta das trocas feitas entre elementos da array.
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
        for (int i = 1; i < array.length; i++) {
            T pivot = array[i];
            int left = 0, right = i;
            while (left < right) {
                int mid = (left + right) >>> 1; // Equivale a (left + right) / 2, mas evita overflow fazendo um unsigned bit shift
                if (comparator.compare(pivot, array[mid]) < 0)
                    right = mid;
                else
                    left = mid + 1;
                this.lastRunComparisons++;
            }
            System.arraycopy(array, left, array, left + 1, i - left); // Desloca todos os elementos da array a partir do índice left para a direita em 1 posição
            array[left] = pivot;
        }
        end();
        return array;
    }
}

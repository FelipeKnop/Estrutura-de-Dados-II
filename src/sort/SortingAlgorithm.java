package sort;

import java.util.Comparator;
import java.util.List;

/**
 * Interface que contém os métodos de ordenação de arrays e listas de tipo genérico,
 * permitindo também passar um Comparator como argumento para classes que não implementam
 * a interface Comparable
 */
public interface SortingAlgorithm {

    /**
     * Ordena uma array de um tipo genérico que implementa a interface Comparable.
     * @param array Array de tipo genérico
     * @param <T> Tipo da array. T deve implementar a interface Comparable
     * @return Retorna uma cópia da array com seus elementos ordenados de acordo
     * com a implementação da interface Comparable definida na classe T
     */
    default <T extends Comparable<? super T>> T[] sort(T[] array) {
        return sortArray(array, T::compareTo);
    }

    /**
     * Ordena uma array de um tipo genérico utilizando um Comparator definido.
     * @param array Array de tipo genérico
     * @param comparator Implementação da interface Comparator que define como um
     *                   elemento do tipo T deve ser comparado a outro
     * @param <T> Tipo da array
     * @return Retorna uma cópia da array com seus elementos ordenados de acordo com
     * a implementação da interface Comparator recebida como argumento
     */
    default <T> T[] sort(T[] array, Comparator<? super T> comparator) {
        return sortArray(array, comparator);
    }

    /**
     * Ordena uma lista de um tipo genérico que implementa a interface Comparable.
     * @param list Lista de tipo genérico
     * @param <T> Tipo dos elementos da lista. T deve implementar a interface Comparable
     * @return Retorna uma cópia da lista com seus elementos ordenados de acordo
     * com a implementação da interface Comparable definida na classe T
     */
    default <T extends Comparable<? super T>> List<T> sort(List<T> list) {
        return sortList(list, T::compareTo);
    }

    /**
     * Ordena uma lista de um tipo genérico utilizando um Comparator definido.
     * @param list Lista de tipo genérico
     * @param comparator Implementação da interface Comparator que define como um
     *                   elemento do tipo T deve ser comparado a outro
     * @param <T> Tipo dos elementos da lista
     * @return Retorna uma cópia da lista com seus elementos ordenados de acordo com
     * a implementação da interface Comparator recebida como argumento
     */
    default <T> List<T> sort(List<T> list, Comparator<? super T> comparator) {
        return sortList(list, comparator);
    }

    /**
     * Declaração do método que implementa a ordenação de arrays. A implementação
     * varia para cada algoritmo.
     * @param array Array a ser ordenada
     * @param comparator Implementação da interface Comparator que define como um
     *                   elemento do tipo T deve ser comparado a outro
     * @param <T> Tipo da array
     * @return Retorna uma cópia da array com seus elementos ordenados de acordo com
     * a implementação da interface Comparator recebida como argumento
     */
    <T> T[] sortArray(T[] array, Comparator<? super T> comparator);

    /**
     * Declaração do método que implementa a ordenação de listas. A implementação
     * varia para cada algoritmo.
     * @param list Lista a ser ordenada
     * @param comparator Implementação da interface Comparator que define como um
     *                   elemento do tipo T deve ser comparado a outro
     * @param <T> Tipo dos elementos da lista
     * @return Retorna uma cópia da lista com seus elementos ordenados de acordo com
     * a implementação da interface Comparator recebida como argumento
     */
    <T> List<T> sortList(List<T> list, Comparator<? super T> comparator);
}

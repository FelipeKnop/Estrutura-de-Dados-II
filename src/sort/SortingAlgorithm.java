package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Interface que contém os métodos de ordenação de arrays e listas de tipo genérico,
 * permitindo também passar um Comparator como argumento para classes que não implementam
 * a interface Comparable
 */
public interface SortingAlgorithm {

    /**
     * Invoca o método de sort interno para ordenar a array recebida.
     * Aceita como argumento somente arrays de tipo T que implementam
     * a interface {@link Comparable} e utiliza o próprio método
     * {@link Comparable#compareTo(Object)} da classe de tipo T como
     * parâmetro para a função {@link #sortArray(Object[], Comparator)}.
     * @param array Array de tipo genérico a ser ordenada
     * @param <T> Tipo da array. T deve implementar a interface {@link Comparable}
     * @return Retorna uma cópia da array com seus elementos ordenados de acordo
     * com a implementação da interface {@link Comparable} definida na classe T
     */
    default <T extends Comparable<? super T>> T[] sort(T[] array) {
        return sortArray(Arrays.copyOf(array, array.length), T::compareTo);
    }

    /**
     * Invoca o método de sort interno para ordenar a array recebida.
     * Aceita como argumento uma implementação da interface {@link Comparator}
     * para definir o critério de comparação a ser utilizado na ordenação.
     * @param array Array de tipo genérico a ser ordenada
     * @param comparator Implementação da interface {@link Comparator} que define como um
     *                   elemento do tipo T deve ser comparado a outro
     * @param <T> Tipo da array
     * @return Retorna uma cópia da array com seus elementos ordenados de acordo
     * com a implementação da interface {@link Comparator} recebida como argumento
     */
    default <T> T[] sort(T[] array, Comparator<? super T> comparator) {
        return sortArray(Arrays.copyOf(array, array.length), comparator);
    }

    /**
     * Invoca o método de sort interno para ordenar a lista recebida.
     * Aceita como argumento somente listas de tipo T que implementam
     * a interface {@link Comparable} e utiliza o próprio método
     * {@link Comparable#compareTo(Object)} da classe de tipo T como
     * parâmetro para a função {@link #sortArray(Object[], Comparator)}.
     * @param list Lista de tipo genérico
     * @param <T> Tipo dos elementos da lista. T deve implementar a interface {@link Comparable}
     * @return Retorna uma cópia da lista com seus elementos ordenados de acordo
     * com a implementação da interface {@link Comparable} definida na classe T
     */
    @SuppressWarnings("unchecked")
    default <T extends Comparable<? super T>> List<T> sort(List<T> list) {
        return sortList(new ArrayList<>(list), T::compareTo);
    }

    /**
     * Invoca o método de sort interno para ordenar a lista recebida.
     * Para isso, utiliza o método {@link List#toArray()} para
     * converter a lista recebida para uma array e, após o sort
     * ser realizado, o método {@link Arrays#asList(Object[])} para
     * convertê-la de volta para uma lista.
     * Aceita como argumento uma implementação da interface {@link Comparator}
     * para definir o critério de comparação a ser utilizado na ordenação.
     * @param list Lista de tipo genérico
     * @param comparator Implementação da interface {@link Comparator} que define como um
     *                   elemento do tipo T deve ser comparado a outro
     * @param <T> Tipo dos elementos da lista
     * @return Retorna uma cópia da lista com seus elementos ordenados de acordo com
     * a implementação da interface {@link Comparator} recebida como argumento
     */
    @SuppressWarnings("unchecked")
    default <T> List<T> sort(List<T> list, Comparator<? super T> comparator) {
        return sortList(new ArrayList<>(list), comparator);
    }

    /**
     * Declaração do método que implementa a ordenação de arrays. A implementação
     * varia para cada algoritmo.
     * @param array Array a ser ordenada
     * @param comparator Implementação da interface {@link Comparator} que define como um
     *                   elemento do tipo T deve ser comparado a outro
     * @param <T> Tipo da array
     * @return Retorna uma cópia da array com seus elementos ordenados de acordo com
     * a implementação da interface {@link Comparator} recebida como argumento
     */
    <T> T[] sortArray(T[] array, Comparator<? super T> comparator);

    /**
     * Declaração do método que implementa a ordenação de listas. A implementação
     * varia para cada algoritmo.
     * @param list Lista a ser ordenada
     * @param comparator Implementação da interface {@link Comparator} que define como um
     *                   elemento do tipo T deve ser comparado a outro
     * @param <T> Tipo da lista
     * @return Retorna uma cópia da lista com seus elementos ordenados de acordo com
     * a implementação da interface {@link Comparator} recebida como argumento
     */
    <T> List<T> sortList(List<T> list, Comparator<? super T> comparator);
}

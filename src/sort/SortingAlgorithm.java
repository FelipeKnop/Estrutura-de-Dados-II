package sort;

import java.util.*;

/**
 * Classe abstrata que contém os métodos de ordenação de arrays e listas de tipo genérico,
 * permitindo também passar um Comparator como argumento para classes que não implementam
 * a interface Comparable.
 */
public abstract class SortingAlgorithm {

    /**
     * Número de comparações feitas na última vez que o algoritmo de sort foi executado.
     */
    long lastRunComparisons = 0;

    /**
     * Número de cópias de registros feitas na última vez que o algoritmo foi executado.
     */
    long lastRunCopies = 0;

    /**
     * Hora em que a última execução do algoritmo começou.
     */
    private long lastRunTimeStarted = 0;

    /**
     * Hora em que a última execução do algoritmo terminou.
     */
    private long lastRunTimeFinished = 0;

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
    public final <T extends Comparable<? super T>> T[] sort(T[] array) {
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
    public final <T> T[] sort(T[] array, Comparator<? super T> comparator) {
        return sortArray(Arrays.copyOf(array, array.length), comparator);
    }

    /**
     * Invoca o método de sort interno para ordenar a lista recebida.
     * Para isso, utiliza o método {@link List#toArray()} para
     * converter a lista recebida para uma array e, após o sort
     * ser realizado, percorre a array preenchendo a lista de volta.
     * Aceita como argumento somente listas de tipo T que implementam
     * a interface {@link Comparable} e utiliza o próprio método
     * {@link Comparable#compareTo(Object)} da classe de tipo T como
     * parâmetro para a função {@link #sortArray(Object[], Comparator)}.
     * @param list Lista de tipo genérico
     * @param <T> Tipo dos elementos da lista. T deve implementar a interface {@link Comparable}
     * @return Retorna uma cópia da lista com seus elementos ordenados de acordo
     * com a implementação da interface {@link Comparable} definida na classe T
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public final <T extends Comparable<? super T>> List<T> sort(List<T> list) {
        List<T> l = new ArrayList<>(list);
        Comparator<? super T> comparator = T::compareTo;
        Object[] array = l.toArray();
        array = sortArray(array, (Comparator) comparator);
        ListIterator<T> it = l.listIterator();
        for (Object object : array) {
            it.next();
            it.set((T) object);
        }
        return l;
    }

    /**
     * Invoca o método de sort interno para ordenar a lista recebida.
     * Para isso, utiliza o método {@link List#toArray()} para
     * converter a lista recebida para uma array e, após o sort
     * ser realizado, percorre a array preenchendo a lista de volta.
     * Aceita como argumento uma implementação da interface {@link Comparator}
     * para definir o critério de comparação a ser utilizado na ordenação.
     * @param list Lista de tipo genérico
     * @param comparator Implementação da interface {@link Comparator} que define como um
     *                   elemento do tipo T deve ser comparado a outro
     * @param <T> Tipo dos elementos da lista
     * @return Retorna uma cópia da lista com seus elementos ordenados de acordo com
     * a implementação da interface {@link Comparator} recebida como argumento
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public final <T> List<T> sort(List<T> list, Comparator<? super T> comparator) {
        List<T> l = new ArrayList<>(list);
        Object[] array = l.toArray();
        array = sortArray(array, (Comparator) comparator);
        ListIterator<T> it = l.listIterator();
        for (Object object : array) {
            it.next();
            it.set((T) object);
        }
        return l;
    }

    /**
     * Declaração do método que implementa a ordenação de arrays. A implementação
     * varia para cada algoritmo.
     * @param array Array a ser ordenada
     * @param comparator Implementação da interface {@link Comparator} que define como um
     *                   elemento do tipo T deve ser comparado a outro
     * @param <T> Tipo da array
     * @return Retorna a array com seus elementos ordenados de acordo com
     * a implementação da interface {@link Comparator} recebida como argumento
     */
    abstract <T> T[] sortArray(T[] array, Comparator<? super T> comparator);

    public final long getLastRunComparisons() {
        return lastRunComparisons;
    }

    public final long getLastRunCopies() {
        return lastRunCopies;
    }

    public final long getLastRunTimeSpent() {
        return lastRunTimeFinished - lastRunTimeStarted;
    }

    final void setup() {
        this.lastRunComparisons = 0;
        this.lastRunCopies = 0;
        this.lastRunTimeStarted = System.currentTimeMillis();
    }

    final void end() {
        this.lastRunTimeFinished = System.currentTimeMillis();
    }

    public final void printResults() {
        System.out.println("Algoritmo utilizado: " + getClass().getSimpleName());
        System.out.println("Número de comparações: " + getLastRunComparisons());
        System.out.println("Número de cópias de registros: " + getLastRunCopies());
        System.out.println(String.format("Tempo gasto: %d milisegundos", getLastRunTimeSpent()));
    }
}

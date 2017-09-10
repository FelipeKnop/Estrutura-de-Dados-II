package sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

/**
 * Classe que extende a classe abstrata {@link SortingAlgorithm SortingAlgorithm} utilizando o algoritmo
 * <a href="https://en.wikipedia.org/wiki/Radix_sort">RadixSort</a>
 */
public class RadixSort extends SortingAlgorithm {

    /**
     * Implementação da variação LSD do RadixSort. Essa variação, ao contrário
     * da variação MSD, é estável, ou seja, mantém a ordem de elementos "iguais".
     *
     * O RadixSort é um algoritmo de ordenação não-comparativo, ou seja, não
     * utiliza comparações entre elementos para ordená-los. A posição de cada
     * elemento é decidida a partir de elementos de sua representação inteira.
     *
     * A principal característica do RadixSort é seu parâmetro radix, que representa
     * tipicamente a base dos números representados (2 para binário, 10 para decimal...)
     * e determina o tamanho dos "baldes", assim como o tamanho da palavra (word) w, que
     * representa a quantidade de dígitos no maior número contido na array.
     *
     * Sua complexidade é de O(wn) em tempo e O(n + r) em espaço.
     *
     * @param array Array a ser ordenada
     * @param comparator Implementação da interface {@link Comparator} que define como um
     *                   elemento do tipo T deve ser comparado a outro
     * @param <T> <T> Tipo da array
     * @return Retorna a array com seus elementos ordenados de acordo
     * com a implementação da interface {@link Comparator} recebida como argumento
     */
    @Override
    <T> T[] sortArray(T[] array, Comparator<? super T> comparator) {
        setup();

        try {
            Long.valueOf(String.valueOf(array[0]));
            sortNumbers(array, comparator);
        } catch (NumberFormatException e) {
            System.out.println("Falha ao aplicar o RadixSort. Esse algoritmo requer" +
                    " uma array de valores inteiros(int, long, String que representa um número...)");
        }

        end();
        return array;
    }

    private <T> void sortNumbers(T[] array, Comparator<? super T> comparator) {

        // Conversão dos elementos da array para uma representação inteira
        HashMap<Long, ArrayList<T>> map = new HashMap<>();
        for (T element : array) {
            long representation = Long.valueOf(String.valueOf(element));
            ArrayList<T> indexList = map.computeIfAbsent(representation, k -> new ArrayList<>());
            indexList.add(element);
        }

        Set<Long> keySet = map.keySet();

        long[] longValues = new long[keySet.size()];
        int index = 0;
        for (Long element : keySet)
            longValues[index++] = element;

        int radix = 1 << 8; // Cada byte representa entre 0 e 255 (2^8 - 1)
        int w = 8; // Cada long ocupa 8 bytes de memória

        long[] aux = new long[longValues.length];

        for (int d = 0; d < w; d++) {

            long[] count = new long[radix + 1];
            for (long longValue : longValues) {
                long c = (longValue >> d * 8) & (radix - 1);
                count[(int) (c + 1)]++;
            }

            for (int r = 0; r < radix; r++)
                count[r + 1] += count[r];

            if (d == w - 1) {
                long shift1 = count[radix] - count[radix / 2];
                long shift2 = count[radix / 2];
                for (int r = 0; r < radix/2; r++)
                    count[r] += shift1;
                for (int r = radix/2; r < radix; r++)
                    count[r] -= shift2;
            }

            for (long longValue : longValues) {
                long c = (longValue >> d * 8) & (radix - 1);
                aux[(int) count[(int) c]++] = longValue;
                this.lastRunCopies++;
            }

            System.arraycopy(aux, 0, longValues, 0, longValues.length);
            this.lastRunCopies += longValues.length;
        }

        int count = 0;
        for (long longValue : longValues)
            for (T element : map.get(longValue)) {
                array[count] = element;
                count++;
            }
    }
}

package sort;

import java.util.Comparator;

/**
 * Classe que extende a classe abstrata {@link SortingAlgorithm SortingAlgorithm} utilizando o algoritmo
 * <a href="https://en.wikipedia.org/wiki/Heap_sort">HeapSort</a>
 */
public class HeapSort extends SortingAlgorithm {

    /**
     * Implementação padrão do HeapSort.
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
        T aux;
        int tam = array.length;
        for(int i = (tam /2 ) -1 ; i >= 0; i--)
            heapify(array, i, tam, comparator);

        for(int i = tam - 1; i >= 0; i--) {
            aux = array[i];
            array[i] = array[0];
            array[0] = aux;
            this.lastRunCopies++;
            heapify(array, 0, i, comparator);
        }

        end();
        return array;
    }

    private <T> void heapify(T[] array, int raiz, int tam, Comparator<? super T> comparator){
        int esq = (raiz *2 ) + 1;
        int dir = (raiz * 2) + 2;
        int r = raiz;
        T aux;

        if(esq < tam && comparator.compare(array[esq], array[r]) > 0)
            r = esq;
        this.lastRunComparisons++;

        if(dir < tam && comparator.compare(array[dir], array[r]) > 0)
            r = dir;
        this.lastRunComparisons++;

        if(raiz != r) {
            aux = array[raiz];
            array[raiz] = array[r];
            array[r] = aux;
            this.lastRunCopies++;
            heapify(array, r, tam, comparator);
        }
    }

}

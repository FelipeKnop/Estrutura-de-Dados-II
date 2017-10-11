package trabalhos.trabalho1;

import business.TweetFileReader;
import hash.BenchmarkCollisionResolutionMethod;
import hash.collision_resolution.*;
import sort.*;

import static business.FileManager.readInputFile;
import static business.FileManager.redirectOutput;

/**
 * Classe para a execução das funções especificadas na Parte 1 do Trabalho 1.
 */
public class Parte1 {

    /**
     * Método main que lê os valores do arquivo de entrada, cria
     * O TweetFileReader que lê o arquivo de Tweets, redireciona
     * a saída para o arquivo saida.txt e executa as funções pedidas
     * na especificação do trabalho.
     * @param args Parâmetros da linha de comando
     */
    public static void main(String[] args) {
        int[] nValues = readInputFile("entrada.txt");
        assert nValues != null;
        TweetFileReader tweetFileReader = TweetFileReader.create("tweets.txt");
        assert tweetFileReader != null;
        redirectOutput("saida.txt");
        cenario1(nValues, tweetFileReader);
        cenario2(nValues, tweetFileReader);
        cenario3(nValues, tweetFileReader);
        cenario4(nValues, tweetFileReader);
    }


    /**
     * Executa as funções pedidas no Cenário 1 da especificação do trabalho.
     * @param nValues Valores lidos do arquivo de entrada
     * @param tweetFileReader TweetFileReader com lista de Tweets preenchida
     */
    private static void cenario1(int[] nValues, TweetFileReader tweetFileReader) {
        System.out.println("-------------- CENÁRIO 1 --------------");
        System.out.println("\nQuickSort:\n");
        BenchmarkSortingAlgorithm.benchmarkIntegers(new QuickSort(), nValues, tweetFileReader);
        BenchmarkSortingAlgorithm.benchmarkTweets(new QuickSort(), nValues, tweetFileReader);
    }

    /**
     * Executa as funções pedidas no Cenário 2 da especificação do trabalho.
     * @param nValues Valores lidos do arquivo de entrada
     * @param tweetFileReader TweetFileReader com lista de Tweets preenchida
     */
    private static void cenario2(int[] nValues, TweetFileReader tweetFileReader) {
        System.out.println("\n\n\n-------------- CENÁRIO 2 --------------");
        System.out.println("\nQuickSort: \n");
        BenchmarkSortingAlgorithm.benchmarkIntegers(new QuickSort(), nValues, tweetFileReader);
        System.out.println("\n\nQuickSortMediana: \n");
        System.out.println("k = 3: \n");
        BenchmarkSortingAlgorithm.benchmarkIntegers(new QuickSortMediana().setK(3), nValues, tweetFileReader);
        System.out.println("\nk = 5: \n");
        BenchmarkSortingAlgorithm.benchmarkIntegers(new QuickSortMediana().setK(5), nValues, tweetFileReader);
        System.out.println("\n\nQuickSortInsercao: \n");
        System.out.println("m = 10: \n");
        BenchmarkSortingAlgorithm.benchmarkIntegers(new QuickSortInsercao().setM(10), nValues, tweetFileReader);
        System.out.println("\nm = 100: \n");
        BenchmarkSortingAlgorithm.benchmarkIntegers(new QuickSortInsercao().setM(100), nValues, tweetFileReader);
    }

    /**
     * Executa as funções pedidas no Cenário 3 da especificação do trabalho.
     *
     * De acordo com os resultados do Cenário 2, a melhor variação do QuickSort
     * foi o QuickSortInserção com m = 100, portanto essa variação será usada aqui.
     *
     * O outro algoritmo escolhido para a comparação foi o RadixSort, implementado
     * na classe {@link sort.RadixSort}.
     *
     * @param nValues Valores lidos do arquivo de entrada
     * @param tweetFileReader TweetFileReader com lista de Tweets preenchida
     */
    private static void cenario3(int[] nValues, TweetFileReader tweetFileReader) {
        System.out.println("\n\n\n-------------- CENÁRIO 3 --------------");
        System.out.println("\nQuickSort: \n");
        BenchmarkSortingAlgorithm.benchmarkIntegers(new QuickSortInsercao().setM(100), nValues, tweetFileReader);
        System.out.println("\n\nInsertionSort: \n");
        BenchmarkSortingAlgorithm.benchmarkIntegers(new InsertionSort(), nValues, tweetFileReader);
        System.out.println("\n\nMergeSort: \n");
        BenchmarkSortingAlgorithm.benchmarkIntegers(new MergeSort(), nValues, tweetFileReader);
        System.out.println("\n\nHeapSort: \n");
        BenchmarkSortingAlgorithm.benchmarkIntegers(new HeapSort(), nValues, tweetFileReader);
        System.out.println("\n\nRadixSort: \n");
        BenchmarkSortingAlgorithm.benchmarkIntegers(new RadixSort(), nValues, tweetFileReader);
    }

    /**
     * Executa as funções pedidas no Cenário 4 da especificação do trabalho.
     * @param nValues Valores lidos do arquivo de entrada
     * @param tweetFileReader TweetFileReader com lista de Tweets preenchida
     */
    private static void cenario4(int[] nValues, TweetFileReader tweetFileReader) {
        System.out.println("\n\n\n-------------- CENÁRIO 4 --------------");
        System.out.println("\nEndereçamento - Sondagem Linear: \n");
        BenchmarkCollisionResolutionMethod.benchmarkIntegers(new LinearProbingMethod(), nValues, tweetFileReader);
        System.out.println("\n\nEndereçamento - Sondagem Quadrática: \n");
        BenchmarkCollisionResolutionMethod.benchmarkIntegers(new QuadraticProbingMethod(), nValues, tweetFileReader);
        System.out.println("\n\nEndereçamento - Duplo Hash: \n");
        BenchmarkCollisionResolutionMethod.benchmarkIntegers(new DoubleHashingMethod(), nValues, tweetFileReader);
        System.out.println("\n\nEncadeamento Separado: \n");
        BenchmarkCollisionResolutionMethod.benchmarkIntegers(new SeparateChainingMethod(), nValues, tweetFileReader);
        System.out.println("\n\nEncadeamento Coalescido: \n");
        BenchmarkCollisionResolutionMethod.benchmarkIntegers(new CoalescedChainingMethod(), nValues, tweetFileReader);
    }
}

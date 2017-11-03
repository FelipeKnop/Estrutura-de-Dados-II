package sort;

import business.TweetFileReader;
import models.Tweet;

import static business.BenchmarkHelper.*;

/**
 * Classe para análise de algoritmos de sort.
 */
public class BenchmarkSortingAlgorithm {

    /**
     * Método que analisa um algoritmo de sort com os valores dados de entrada.
     *
     * A array nValues contém os valores de N (tamanho da array a ser ordenada)
     * a serem utilizados na análise.
     *
     * A variável AMOUNT_OF_SETS é utilizada para determinar quantos testes
     * serão realizados com diferentes sementes e contabilizados na média
     * dos resultados obtidos.
     *
     * @param sortingAlgorithm Algoritmo de sort a ser analisado
     * @param nValues Valores de N (tamanho da array)
     * @param tweetFileReader TweetFileReader com a lista de Tweets já gerada
     */
    public static void benchmarkIntegers(SortingAlgorithm sortingAlgorithm, int[] nValues, TweetFileReader tweetFileReader) {
        System.out.println("--- Inteiros ---\n");
        for (int n : nValues) {
            System.out.println("N: " + n);
            Long[][] integerArrays = generateIntegerArrays(n, tweetFileReader.getTweets());
            long comparisonsSum = 0;
            long copiesSum = 0;
            long timeSpentSum = 0;
            for (Long[] integers : integerArrays) {
                sortingAlgorithm.sort(integers);
//                sortingAlgorithm.printResults();
                comparisonsSum += sortingAlgorithm.getLastRunComparisons();
                copiesSum += sortingAlgorithm.getLastRunCopies();
                timeSpentSum += sortingAlgorithm.getLastRunTimeSpent();
            }
            System.out.println("\tNúmero médio de comparações: " + (comparisonsSum / AMOUNT_OF_SETS));
            System.out.println("\tNúmero médio de cópias de registros: " + (copiesSum / AMOUNT_OF_SETS));
            System.out.println(String.format("\tTempo médio gasto: %d milisegundos\n", (timeSpentSum / AMOUNT_OF_SETS)));
        }
    }

    /**
     * Método que analisa um algoritmo de sort com os valores dados de entrada.
     *
     * A array nValues contém os valores de N (tamanho da array a ser ordenada)
     * a serem utilizados na análise.
     *
     * A variável AMOUNT_OF_SETS é utilizada para determinar quantos testes
     * serão realizados com diferentes sementes e contabilizados na média
     * dos resultados obtidos.
     *
     * @param sortingAlgorithm Algoritmo de sort a ser analisado
     * @param nValues Valores de N (tamanho da array)
     * @param tweetFileReader TweetFileReader com a lista de Tweets já gerada
     */
    public static void benchmarkTweets(SortingAlgorithm sortingAlgorithm, int[] nValues, TweetFileReader tweetFileReader) {
        System.out.println("\n\n--- Tweets ---\n");
        assert tweetFileReader != null;
        for (int n : nValues) {
            System.out.println("N: " + n);
            Tweet[][] tweetArrays = generateTweetArrays(n, tweetFileReader.getTweets());
            long comparisonsSum = 0;
            long copiesSum = 0;
            long timeSpentSum = 0;
            for (Tweet[] tweets : tweetArrays) {
                sortingAlgorithm.sort(tweets);
//                sortingAlgorithm.printResults();
                comparisonsSum += sortingAlgorithm.getLastRunComparisons();
                copiesSum += sortingAlgorithm.getLastRunCopies();
                timeSpentSum += sortingAlgorithm.getLastRunTimeSpent();
            }
            System.out.println("\tNúmero médio de comparações: " + (comparisonsSum / AMOUNT_OF_SETS));
            System.out.println("\tNúmero médio de cópias de registros: " + (copiesSum / AMOUNT_OF_SETS));
            System.out.println(String.format("\tTempo médio gasto: %d milisegundos\n", (timeSpentSum / AMOUNT_OF_SETS)));
        }
    }
}

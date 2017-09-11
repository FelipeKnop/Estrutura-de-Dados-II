package sort;

import business.TweetFileReader;
import models.Tweet;

import java.util.List;
import java.util.Random;

/**
 * Classe para análise de algoritmos de sort.
 */
public class BenchmarkSortingAlgorithm {

    /**
     * Quantidade de linhas no arquivo de Tweets.
     */
    private static final int FILE_LINES = 1000000;

    /**
     * Quantidade de conjuntos a serem testados com sementes diferentes.
     */
    private static final int AMOUNT_OF_SETS = 5;

    /**
     * Sementes a serem usadas para a geração de números aleatórios.
     */
    private static final int[] SEEDS = {42, 7, 102, 1, 56};

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
     * Função que gera arrays de inteiros de acordo com os parâmetros recebidos.
     *
     * Utiliza as sementes definidas na array SEEDS para gerar números aleatórios
     * que são utilizados para selecionar Tweets da lista recebida, os quais terão
     * seu TweetID adicionados às listas de inteiros que serão retornadas.
     *
     * Gera AMOUNT_OF_SETS arrays com sementes diferentes.
     *
     * @param n Tamanho das arrays a serem geradas
     * @param tweets Lista de Tweets lidos do arquivo
     * @return Retorna AMOUNT_OF_SETS arrays de tamanho n
     */
    private static Long[][] generateIntegerArrays(int n, List<Tweet> tweets) {
        Long[][] integerArrays = new Long[AMOUNT_OF_SETS][n];
        for (int i = 0; i < AMOUNT_OF_SETS; i++) {
            Random random = new Random(SEEDS[i]);
            integerArrays[i] = new Long[n];
            for (int j = 0; j < n; j++)
                integerArrays[i][j] = tweets.get(random.nextInt(FILE_LINES)).getTweetId();
        }
        return integerArrays;
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

    /**
     * Função que gera arrays de Tweets de acordo com os parâmetros recebidos.
     *
     * Utiliza as sementes definidas na array SEEDS para gerar números aleatórios
     * que são utilizados para selecionar Tweets da lista recebida, os quais serão
     * adicionados às listas de Tweets que serão retornadas.
     *
     * Gera AMOUNT_OF_SETS arrays com sementes diferentes.
     *
     * @param n Tamanho das arrays a serem geradas
     * @param tweets Lista de Tweets lidos do arquivo
     * @return Retorna AMOUNT_OF_SETS arrays de tamanho n
     */
    private static Tweet[][] generateTweetArrays(int n, List<Tweet> tweets) {
        Tweet[][] tweetsArrays = new Tweet[AMOUNT_OF_SETS][n];
        for (int i = 0; i < AMOUNT_OF_SETS; i++) {
            Random random = new Random(SEEDS[i]);
            tweetsArrays[i] = new Tweet[n];
            for (int j = 0; j < n; j++)
                tweetsArrays[i][j] = tweets.get(random.nextInt(FILE_LINES));
        }
        return tweetsArrays;
    }
}

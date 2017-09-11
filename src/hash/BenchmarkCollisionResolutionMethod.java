package hash;

import business.TweetFileReader;
import hash.collision_resolution.CollisionResolutionMethod;
import models.Tweet;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Classe para análise de algoritmos de tratamento de colisão.
 */
public class BenchmarkCollisionResolutionMethod {

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
     * Método que analisa um algoritmo de tratamento de colisão com os valores
     * dados de entrada.
     *
     * A array nValues contém os valores de N (tamanho da array a ser armazenada)
     * a serem utilizados na análise.
     *
     * A variável AMOUNT_OF_SETS é utilizada para determinar quantos testes
     * serão realizados com diferentes sementes e contabilizados na média
     * dos resultados obtidos.
     *
     * @param collisionResolutionMethod Algoritmo de tratamento de colisão a ser analisado
     * @param nValues Valores de N (tamanho da array)
     * @param tweetFileReader TweetFileReader com a lista de Tweets já gerada
     */
    public static void benchmarkIntegers(CollisionResolutionMethod collisionResolutionMethod, int[] nValues, TweetFileReader tweetFileReader) {
        System.out.println("--- Inteiros ---\n");
        for (int n : nValues) {
            System.out.println("N: " + n);
            Long[][] integerArrays = generateIntegerArrays(n, tweetFileReader.getTweets());
            long comparisonsSum = 0;
            long memorySpentSum = 0;
            for (Long[] integers : integerArrays) {
                collisionResolutionMethod.setTableSize(n * 2);
                for (Long integer : integers)
                    collisionResolutionMethod.insert(integer);
                comparisonsSum += collisionResolutionMethod.getComparisons();
                memorySpentSum += collisionResolutionMethod.getMemorySpent();
                collisionResolutionMethod.clear();
            }
            System.out.println("\tNúmero médio de comparações: " + (comparisonsSum / AMOUNT_OF_SETS));
            System.out.println(String.format("\tMemória média gasta: %d bytes\n", (memorySpentSum / AMOUNT_OF_SETS)));
        }
    }

    /**
     * Função que gera arrays de inteiros de acordo com os parâmetros recebidos.
     *
     * Utiliza as sementes definidas na array SEEDS para embaralhar a lista de
     * Tweets recebida e depois seleciona os n primeiros da lista embaralhada,
     * os quais terão seu TweetID adicionados às listas de inteiros que serão retornadas.
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
            List<Tweet> shuffledTweets = tweets.subList(0, n);
            Collections.shuffle(shuffledTweets, random);
            for (int j = 0; j < n; j++)
                integerArrays[i][j] = shuffledTweets.get(j).getTweetId();
        }
        return integerArrays;
    }
}

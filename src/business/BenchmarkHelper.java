package business;

import models.Tweet;

import java.util.List;
import java.util.Random;

public final class BenchmarkHelper {

    /**
     * Quantidade de linhas no arquivo de Tweets.
     */
    public static final int FILE_LINES = 1000000;

    /**
     * Quantidade de conjuntos a serem testados com sementes diferentes.
     */
    public static final int AMOUNT_OF_SETS = 5;

    /**
     * Sementes a serem usadas para a geração de números aleatórios.
     */
    public static final int[] SEEDS = {42, 7, 102, 1, 56};

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
    public static Long[][] generateIntegerArrays(int n, List<Tweet> tweets) {
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
    public static Tweet[][] generateTweetArrays(int n, List<Tweet> tweets) {
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

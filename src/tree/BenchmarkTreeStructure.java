package tree;

import business.TweetFileReader;
import models.Tweet;

import static business.BenchmarkHelper.AMOUNT_OF_SETS;
import static business.BenchmarkHelper.generateTweetArrays;

/**
 * Classe para análise de estruturas de árvore.
 */
public class BenchmarkTreeStructure {

    /**
     * Método que analisa a inserção em uma estrutura de árvore com os valores dados de entrada.
     *
     * A array nValues contém os valores de N (quantidade de elementos a serem inseridos)
     * a serem utilizados na análise.
     *
     * A variável AMOUNT_OF_SETS é utilizada para determinar quantos testes
     * serão realizados com diferentes sementes e contabilizados na média
     * dos resultados obtidos.
     *
     * @param tree Estrutura de árvore a ser analisada
     * @param nValues Valores de N (tamanho da array)
     * @param tweetFileReader TweetFileReader com a lista de Tweets já gerada
     */
    public static void benchmarkInsertTweets(BenchmarkableTree<Long, Tweet> tree, int[] nValues, TweetFileReader tweetFileReader) {
        System.out.println("\n\n--- Inserção ---\n");
        assert tweetFileReader != null;
        for (int n : nValues) {
            System.out.println("N: " + n);
            Tweet[][] tweetArrays = generateTweetArrays(n, tweetFileReader.getTweets());
            long comparisonsSum = 0;
            long copiesSum = 0;
            long timeSpentSum = 0;
            for (Tweet[] tweets : tweetArrays) {
                tree.clear();
                tree.start();
                for (Tweet tweet : tweets)
                    tree.insert(tweet.getTweetId(), tweet);
                comparisonsSum += tree.getComparisons();
                copiesSum += tree.getCopies();
                timeSpentSum += tree.getTimeSpent();
            }
            System.out.println("\tNúmero médio de comparações: " + (comparisonsSum / AMOUNT_OF_SETS));
            System.out.println("\tNúmero médio de cópias de registros: " + (copiesSum / AMOUNT_OF_SETS));
            System.out.println(String.format("\tTempo médio gasto: %d milisegundos\n", (timeSpentSum / AMOUNT_OF_SETS)));
        }
    }

    /**
     * Método que analisa a busca em uma estrutura de árvore com os valores dados de entrada.
     *
     * A array nValues contém os valores de N (quantidade de elementos a serem buscados)
     * a serem utilizados na análise.
     *
     * A variável AMOUNT_OF_SETS é utilizada para determinar quantos testes
     * serão realizados com diferentes sementes e contabilizados na média
     * dos resultados obtidos.
     *
     * @param tree Estrutura de árvore a ser analisada
     * @param nValues Valores de N (tamanho da array)
     * @param tweetFileReader TweetFileReader com a lista de Tweets já gerada
     */
    public static void benchmarkSearchTweets(BenchmarkableTree<Long, Tweet> tree, int[] nValues, TweetFileReader tweetFileReader) {
        System.out.println("\n\n--- Busca ---\n");
        assert tweetFileReader != null;
        for (int n : nValues) {
            System.out.println("N: " + n);
            Tweet[][] tweetArrays = generateTweetArrays(n, tweetFileReader.getTweets());
            long comparisonsSum = 0;
            long copiesSum = 0;
            long timeSpentSum = 0;
            for (Tweet[] tweets : tweetArrays) {
                tree.clear();
                for (Tweet tweet : tweets)
                    tree.insert(tweet.getTweetId(), tweet);
                tree.start();
                for (Tweet tweet : tweets)
                    tree.search(tweet.getTweetId());
                comparisonsSum += tree.getComparisons();
                copiesSum += tree.getCopies();
                timeSpentSum += tree.getTimeSpent();
            }
            System.out.println("\tNúmero médio de comparações: " + (comparisonsSum / AMOUNT_OF_SETS));
            System.out.println("\tNúmero médio de cópias de registros: " + (copiesSum / AMOUNT_OF_SETS));
            System.out.println(String.format("\tTempo médio gasto: %d milisegundos\n", (timeSpentSum / AMOUNT_OF_SETS)));
        }
    }

    /**
     * Método que analisa a remoção em uma estrutura de árvore com os valores dados de entrada.
     *
     * A array nValues contém os valores de N (quantidade de elementos a serem removidos)
     * a serem utilizados na análise.
     *
     * A variável AMOUNT_OF_SETS é utilizada para determinar quantos testes
     * serão realizados com diferentes sementes e contabilizados na média
     * dos resultados obtidos.
     *
     * @param tree Estrutura de árvore a ser analisada
     * @param nValues Valores de N (tamanho da array)
     * @param tweetFileReader TweetFileReader com a lista de Tweets já gerada
     */
    public static void benchmarkRemoveTweets(BenchmarkableTree<Long, Tweet> tree, int[] nValues, TweetFileReader tweetFileReader) {
        System.out.println("\n\n--- Remoção ---\n");
        assert tweetFileReader != null;
        for (int n : nValues) {
            System.out.println("N: " + n);
            Tweet[][] tweetArrays = generateTweetArrays(n, tweetFileReader.getTweets());
            long comparisonsSum = 0;
            long copiesSum = 0;
            long timeSpentSum = 0;
            for (Tweet[] tweets : tweetArrays) {
                tree.clear();
                for (Tweet tweet : tweets)
                    tree.insert(tweet.getTweetId(), tweet);
                tree.start();
                for (Tweet tweet : tweets)
                    tree.remove(tweet.getTweetId());
                comparisonsSum += tree.getComparisons();
                copiesSum += tree.getCopies();
                timeSpentSum += tree.getTimeSpent();
            }
            System.out.println("\tNúmero médio de comparações: " + (comparisonsSum / AMOUNT_OF_SETS));
            System.out.println("\tNúmero médio de cópias de registros: " + (copiesSum / AMOUNT_OF_SETS));
            System.out.println(String.format("\tTempo médio gasto: %d milisegundos\n", (timeSpentSum / AMOUNT_OF_SETS)));
        }
    }
}

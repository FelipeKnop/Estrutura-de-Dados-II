package test.tweet;

import models.Tweet;
import org.junit.Test;
import sort.QuickSort;
import sort.SortingAlgorithm;
import test.TestUtils;

import static org.junit.Assert.fail;

/**
 * Classe de teste para as funções do primeiro trabalho - Palavras do Momento do Twitter
 */
public class TweetTest {

    /**
     * Tamanho padrão das arrays/listas de teste.
     */
    private static final int DEFAULT_SIZE = 10000;

    /**
     * Instância do algoritmo de sort a ser utilizado.
     */
    private static final SortingAlgorithm sortingAlgorithm = new QuickSort();

    /**
     * Testa a ordenação de uma array de tweets aleatórios gerada pela classe
     * {@link TestUtils}. Caso algum elemento da array tenha um tweetId maior
     * que o do elemento anterior, o teste falha, imprimindo quais são os
     * elementos que falharam. Caso isso não aconteça, a array está ordenada e
     * essa informção é impressa.
     */
    @Test
    public void sortRandomTweetArray() {
        Tweet[] array = TestUtils.getRandomTweetArray(DEFAULT_SIZE);
        Tweet[] sorted = sortingAlgorithm.sort(array);
        for (int i = 1; i < sorted.length - 1; i++)
            if (sorted[i - 1].compareTo(sorted[i]) > 0)
                fail(String.format("A array não está ordenada corretamente. Índice [%d]: %d, índice [%d]: %d", i - 1, sorted[i - 1].getTweetId(), i, sorted[i].getTweetId()));
        System.out.println("Array de tweets aleatórios ordenada corretamente");
    }
}

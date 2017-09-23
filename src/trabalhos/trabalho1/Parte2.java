package trabalhos.trabalho1;

import business.TweetFileReader;
import business.WordFrequencyCounter;

import java.util.Scanner;

/**
 * Classe para a execução das funções especificadas na Parte 2 do Trabalho 1.
 */
public class Parte2 {

    /**
     * Método main que cria o TweetFileReader que lê o arquivo de Tweets
     * e executa a função pedida na parte 2 da especificaçõa do trabalho.
     * @param args Parâmetros da linha de comando
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a quantidade de Tweets a serem processados (1 a 1.000.000):");
        int numTweets = scanner.nextInt();
        System.out.println("Processando...");
        TweetFileReader tweetFileReader = TweetFileReader.create("tweets.txt");
        assert tweetFileReader != null;
        WordFrequencyCounter wordFrequencyCounter = criaHashTable(numTweets, tweetFileReader);
        System.out.println("Digite quantas palavras deseja imprimir, na ordem de mais frequente:");
        int numWords = scanner.nextInt();
        wordFrequencyCounter.printMostFrequentWords(numWords);
    }

    private static WordFrequencyCounter criaHashTable(int numTweets, TweetFileReader tweetFileReader) {
        WordFrequencyCounter wordFrequencyCounter = new WordFrequencyCounter();
        wordFrequencyCounter.countWords(tweetFileReader.getTweets().subList(0, numTweets));
        return wordFrequencyCounter;
    }
}

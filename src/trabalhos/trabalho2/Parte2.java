package trabalhos.trabalho2;

import business.TweetFileIndexer;
import models.Tweet;

import java.util.Scanner;

public class Parte2 {

    /**
     * Método main que cria o TweetFileIndexer que lê o arquivo de
     * Tweets os indexa por seu TweetID e a linha que se encontram
     * no arquivo.
     *
     * Após isso, lê um TweetID e chama a função
     * {@link TweetFileIndexer#getTweetById(Long)} para retornar
     * o Tweet a partir do ID.
     *
     * Finalmente, imprime o tempo gasto para efetuar a busca
     * @param args Parâmetros da linha de comando
     */
    public static void main(String[] args) {
        TweetFileIndexer tweetFileIndexer = new TweetFileIndexer("tweets.txt");
        System.out.println("Indexando o arquivo de Tweets...");
        tweetFileIndexer.indexFile();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o id do Tweet a ser buscado:");
        long idTweet = scanner.nextLong();
        System.out.println("Processando...");
        long currentTime = System.currentTimeMillis();
        Tweet tweet = tweetFileIndexer.getTweetById(idTweet);
        System.out.println(tweet);
        System.out.println("Tempo gasto para busca: " + String.valueOf(System.currentTimeMillis() - currentTime) + " milisegundos");
    }
}

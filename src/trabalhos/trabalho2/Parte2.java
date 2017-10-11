package trabalhos.trabalho2;

import java.util.Scanner;

public class Parte2 {

    /**
     * Método main que cria o TweetFileReader que lê oarquivo de
     * Tweets e executa as funções pedidas na especificação do trabalho.
     * @param args Parâmetros da linha de comando
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o id do Tweet a ser buscado:");
        long idTweet = scanner.nextLong();
        System.out.println("Processando...");
    }
}

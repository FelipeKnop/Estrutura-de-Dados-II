package business;

import models.Tweet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que contém a lógica de leitura de um arquivo contendo Tweets.
 */
public final class TweetFileReader {

    /**
     * Arquivo que contém os Tweets.
     */
    private final File tweetFile;

    /**
     * Lista de Tweets preenchida após a leitura do arquivo.
     */
    private final List<Tweet> tweets;

    private TweetFileReader(File tweetFile) {
        this.tweetFile = tweetFile;
        tweets = new ArrayList<>();
    }

    /**
     * Método que cria um TweetFileReader a partir do nome do arquivo
     * que contém os Tweets. Serve para evitar a ocorrência de uma
     * exceção no construtor quando o arquivo com o nome especificado
     * não é encontrado.
     *
     * @param fileName Nome do arquivo.
     * @return Retorna uma instância do TweetFileReader com uma lista
     * já preenchida de Tweets, lidos do arquivo especificado.
     */
    public static TweetFileReader create(String fileName) {
        TweetFileReader tweetFileReader = new TweetFileReader(new File(fileName));
        try {
            tweetFileReader.read();
        } catch (IOException e) {
            System.out.println("Não foi possível encontrar o arquivo de Tweets. Certifique-se" +
                    " de que o nome dele é tweets.txt e que ele está na pasta base (Estrutura de Dados II)");
            System.exit(0);
        }
        return tweetFileReader;
    }

    /**
     * Método que faz a leitura do arquivo e preenche a lista de Tweets.
     * @throws IOException Joga uma exceção caso o arquivo com o nome
     * especificado não seja encontrado ou não seja possível lê-lo.
     */
    private void read() throws IOException {
        FileReader fileReader = new FileReader(tweetFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            String[] parts = line.split("\t");
            tweets.add(new Tweet(Long.valueOf(parts[0]), Long.valueOf(parts[1]), parts[2], parts[3]));
        }
        bufferedReader.close();
    }

    public List<Tweet> getTweets() {
        return tweets;
    }
}

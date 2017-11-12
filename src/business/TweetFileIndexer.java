package business;

import models.Tweet;
import tree.BTree;
import tree.ITree;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Classe que realiza a indexação do arquivo de Tweets em uma árvore
 * de busca para que seja possível buscá-lo no arquivo a partir de seu
 * TweetID indo direto na linha dele, sem ter que passar pelo arquivo inteiro.
 */
public class TweetFileIndexer {

    /**
     * Instância da árvore de busca a ser utilizada
     * para a indexação dos Tweets.
     */
    private final ITree<Long, Long> tree;

    /**
     * Nome do arquivo de Tweets.
     */
    private final String fileName;

    public TweetFileIndexer(String fileName) {
        tree = new BTree<>(5); // Criação de uma árvore B de grau 5
        this.fileName = fileName;
    }

    /**
     * Função que realiza a indexação do arquivo de Tweets.
     *
     * Percorre o arquivo inteiro uma vez passando de Tweet em Tweet
     * adicionando na árvore seu TweetID e a linha em que se encontra.
     */
    public void indexFile() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Não foi possível encontrar o arquivo de Tweets. Certifique-se" +
                    " de que o nome dele é tweets.txt e que ele está na pasta base (Estrutura de Dados II)");
            System.exit(0);
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        int i = 1;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\t");
                tree.insert(Long.valueOf(parts[1]), (long) i);
                i++;
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Falha na leitura do arquivo.");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Função que retorna um Tweet a partir de seu TweetID.
     *
     * Usa a função {@link ITree#search(Object)} para obter a linha
     * em que o Tweet se encontra a partir de seu TweetID e depois
     * recupera o Tweet diretamente, agora que já se sabe a linha.
     * @param tweetID TweetID que identifica o Tweet a ser buscado
     * @return Tweet representado pelo TweetID recebido
     */
    public Tweet getTweetById(Long tweetID) {
        Long lineNumber = tree.search(tweetID);
        Stream<String> lines = null;
        try {
            lines = Files.lines(Paths.get(fileName));
        } catch (IOException e) {
            System.out.println("Não foi possível encontrar o arquivo de Tweets. Certifique-se" +
                    " de que o nome dele é tweets.txt e que ele está na pasta base (Estrutura de Dados II)");
            System.exit(0);
        }
        String line = lines.skip(lineNumber - 1).findFirst().get();
        String[] parts = line.split("\t");
        return new Tweet(Long.valueOf(parts[0]), Long.valueOf(parts[1]), parts[2], parts[3]);
    }
}

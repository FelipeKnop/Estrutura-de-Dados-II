package trabalhos.trabalho2;

import business.TweetFileReader;

import static business.FileManager.readInputFile;
import static business.FileManager.redirectOutput;

/**
 * Classe para a execução das funções especificadas na Parte 1 do Trabalho 2.
 */
public class Parte1 {

    /**
     * Método main que lê os valores dos arquivos de entrada, cria
     * O TweetFileReader que lê o arquivo de Tweets, redireciona
     * a saída para o arquivos de saida e executa as funções pedidas
     * na especificação do trabalho.
     * @param args Parâmetros da linha de comando
     */
    public static void main(String[] args) {
        TweetFileReader tweetFileReader = TweetFileReader.create("tweets.txt");
        assert tweetFileReader != null;
        int[] nValues = readInputFile("entradaInsercao.txt");
        assert nValues != null;
        redirectOutput("saidaInsercao.txt");
        insercao(nValues, tweetFileReader);
        nValues = readInputFile("entradaBusca.txt");
        assert nValues != null;
        redirectOutput("saidaBusca.txt");
        busca(nValues, tweetFileReader);
        nValues = readInputFile("entradaRemocao.txt");
        assert nValues != null;
        redirectOutput("saidaRemocao.txt");
        remocao(nValues, tweetFileReader);
    }

    private static void insercao(int[] nValues, TweetFileReader tweetFileReader) {

    }

    private static void busca(int[] nValues, TweetFileReader tweetFileReader) {

    }

    private static void remocao(int[] nValues, TweetFileReader tweetFileReader) {

    }
}

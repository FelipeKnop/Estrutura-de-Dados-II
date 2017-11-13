package trabalhos.trabalho3;

import business.TweetFileReader;
import compression.*;

import static business.FileManager.readInputFile;
import static business.FileManager.redirectOutput;

/**
 * Classe para a execução das funções especificadas na Parte 1 do Trabalho 3.
 */
public class Parte1 {

    /**
     * Método main que lê os valores do arquivo de entrada, cria
     * O TweetFileReader que lê o arquivo de Tweets, redireciona
     * a saída para o arquivo saida.txt e executa as funções pedidas
     * na especificação do trabalho.
     * @param args Parâmetros da linha de comando
     */
    public static void main(String[] args) {
        int[] nValues = readInputFile("entrada.txt");
        assert nValues != null;
        TweetFileReader tweetFileReader = TweetFileReader.create("tweets.txt");
        assert tweetFileReader != null;
        redirectOutput("saida.txt");
        benchmarkCompressionAlgorithms(nValues, tweetFileReader);
    }

    private static void benchmarkCompressionAlgorithms(int[] nValues, TweetFileReader tweetFileReader) {
        System.out.println("-------------- HUFFMAN --------------\n");
        BenchmarkCompressionAlgorithm.benchmarkCompressionAlgorithm(
                new HuffmanCompressionAlgorithm(), nValues, tweetFileReader);
        System.out.println("\n\n-------------- LZ77 --------------\n");
        BenchmarkCompressionAlgorithm.benchmarkCompressionAlgorithm(
                new LZ77CompressionAlgorithm(), nValues, tweetFileReader);
        System.out.println("\n\n-------------- LZ78 --------------\n");
        BenchmarkCompressionAlgorithm.benchmarkCompressionAlgorithm(
                new LZ78CompressionAlgorithm(), nValues, tweetFileReader);
        System.out.println("\n\n-------------- LZW --------------\n");
        BenchmarkCompressionAlgorithm.benchmarkCompressionAlgorithm(
                new LZWCompressionAlgorithm(), nValues, tweetFileReader);
    }
}

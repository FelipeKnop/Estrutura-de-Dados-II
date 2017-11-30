package compression;

import business.TweetFileReader;
import models.Tweet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static business.BenchmarkHelper.AMOUNT_OF_SETS;
import static business.BenchmarkHelper.generateTweetArrays;

/**
 * Classe para análise de algoritmos de compressão.
 */
public class BenchmarkCompressionAlgorithm {

    /**
     * Método que analisa um algoritmo de compressão com os dados valores de entrada.
     *
     * A array nValues contém os valores de N (quantidade de tweets a serem comprimidos)
     * a serem utilizados na análise.
     *
     * A variável AMOUNT_OF_SETS é utilizada para determinar quantos testes
     * serão realizados com diferentes sementes e contabilizados na média
     * dos resultados obtidos.
     * @param compressionAlgorithm Algoritmo de compressão a ser analisado.
     * @param nValues Valores de N (tamanho da array)
     * @param tweetFileReader TweetFileReader com a lista de Tweets já gerada
     */
    public static void benchmarkCompressionAlgorithm(CompressionAlgorithm compressionAlgorithm,
                                                     int[] nValues, TweetFileReader tweetFileReader) {
        assert tweetFileReader != null;
        for (int n : nValues) {
            System.out.println("N: " + n);
            Tweet[][] tweetArrays = generateTweetArrays(n, tweetFileReader.getTweets());
            long timeSpentSum = 0;
            double compressionRatioSum = 0;
            long fileSizeSum = 0;
            for (Tweet[] tweets : tweetArrays) {
                StringBuilder sb = new StringBuilder();
                for (Tweet tweet : tweets)
                    sb.append(tweet).append("\n");
                byte[] content = sb.toString().getBytes();
                long startTime = System.currentTimeMillis();
                byte[] compressedContent = compressionAlgorithm.compress(content);
                timeSpentSum += System.currentTimeMillis() - startTime;
                compressionRatioSum += (double) content.length / compressedContent.length;
                fileSizeSum += getFileSize(compressedContent);
            }
            System.out.println(String.format("\tTaxa de compressão média: %.2f", (compressionRatioSum / AMOUNT_OF_SETS)));
            System.out.println("\tTamanho médio do arquivo em disco: " + (fileSizeSum / AMOUNT_OF_SETS) + " bytes");
            System.out.println(String.format("\tTempo médio gasto: %d milisegundos\n", (timeSpentSum / AMOUNT_OF_SETS)));
        }
    }

    /**
     * Função que recebe uma array de bytes, escreve esse conteúdo
     * em um arquivo e retorna o tamanho de tal arquivo.
     * @param content Conteúdo a ser escrito
     * @return Tamanho do arquivo com conteúdo escrito
     */
    private static long getFileSize(byte[] content) {
        try {
            Path tempDir = Files.createTempDirectory("temp");
            Path tempFile = Files.createTempFile(tempDir, "tempCompression", ".tmp");
            Files.write(tempFile, content, StandardOpenOption.WRITE);
            long fileSize = Files.size(tempFile);
            Files.deleteIfExists(tempFile);
            Files.deleteIfExists(tempDir);
            return fileSize;
        } catch (IOException e) {
            System.out.println("Falha ao criar o arquivo temporário.");
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return 0;
    }
}

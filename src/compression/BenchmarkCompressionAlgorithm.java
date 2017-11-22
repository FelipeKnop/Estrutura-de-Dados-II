package compression;

import business.TweetFileReader;
import models.Tweet;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        File file = createFile();
        for (int n : nValues) {
            System.out.println("N: " + n);
            Tweet[][] tweetArrays = generateTweetArrays(n, tweetFileReader.getTweets());
            long timeSpentSum = 0;
            double compressionRatioSum = 0;
            long fileSizeSum = 0;
            for (Tweet[] tweets : tweetArrays) {
                clearFile(file);
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                    for (Tweet tweet : tweets)
                        bw.write(tweet.toString() + "\n");
                    long startTime = System.currentTimeMillis();
                    long fileSize = getFileSize(file);
                    compressionAlgorithm.compress(file);
                    long compressedFileSize = getFileSize(file);
                    timeSpentSum += System.currentTimeMillis() - startTime;
                    compressionRatioSum += fileSize / compressedFileSize;
                    fileSizeSum += compressedFileSize;
                } catch (IOException e) {
                    System.out.println("Falha ao escrever no arquivo temporário.");
                    System.out.println(e.getMessage());
                    System.exit(0);
                }
            }
            System.out.println("\tTaxa de compressão média: " + (compressionRatioSum / AMOUNT_OF_SETS));
            System.out.println("\tTamanho médio do arquivo em disco: " + (fileSizeSum / AMOUNT_OF_SETS) + " bytes");
            System.out.println(String.format("\tTempo médio gasto: %d milisegundos\n", (timeSpentSum / AMOUNT_OF_SETS)));
        }
        deleteFile(file.toPath());
    }

    /**
     * Cria um arquivo temporário para ser utilizado pelos algoritmos
     * de compressão a serem analisados.
     * @return Arquivo temporário criado
     */
    private static File createFile() {
        try {
            Path filePath = Paths.get("tempCompression.tmp");
            deleteFile(filePath);
            Path tempFile = Files.createFile(Paths.get("tempCompression.tmp"));
            return tempFile.toFile();
        } catch (IOException e) {
            System.out.println("Falha ao criar o arquivo temporário.");
            System.out.println(e.getMessage());
            System.exit(0);
            return null;
        }
    }

    /**
     * Limpa o conteúdo do arquivo recebido.
     * @param file Arquivo a ser limpado
     */
    private static void clearFile(File file) {
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();
        } catch (IOException e) {
            System.out.println("Falha ao limpar o arquivo temporário.");
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Retorna o tamanho em bytes do arquivo recebido.
     * @param file Arquivo
     * @return Um long que corresponde ao tamanho em bytes do arquivo
     */
    private static long getFileSize(File file) {
        try {
            return Files.size(file.toPath());
        } catch (IOException e) {
            System.out.println("Falha ao obter o tamanho do arquivo temporário.");
            System.out.println(e.getMessage());
            System.exit(0);
            return 0;
        }
    }

    /**
     * Deleta o arquivo recebido.
     * @param path Caminho do arquivo a ser deletado
     */
    private static void deleteFile(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            System.out.println("Falha ao deletar o arquivo temporário.");
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}

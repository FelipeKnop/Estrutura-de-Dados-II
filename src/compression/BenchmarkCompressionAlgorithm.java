package compression;

import business.TweetFileReader;
import models.Tweet;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        final int[] i = {-1}; // Recurso utilizado para criar uma variável final que pode ter seu conteúdo alterado
        new Runnable() { // Cria um novo Runnable que, quando chamado, executa o bloco de código abaixo
            @Override
            public void run() {
                i[0]++;
                if (i[0] < nValues.length) {
                    Tweet[][] tweetArrays = generateTweetArrays(nValues[i[0]], tweetFileReader.getTweets());
                    runBenchmarkForNElements(nValues[i[0]], tweetArrays, compressionAlgorithm, this);
                }
            }
        }.run();
    }

    /**
     * Executa o algoritmo de compressão para os AMOUNT_OF_SETS conjuntos de Tweets em paralelo.
     * @param n Quantidade de Tweets em cada array;
     * @param tweetArrays AMOUNT_OF_SETS arrays de Tweets
     * @param compressionAlgorithm Algoritmo de compressão
     * @param callback Função a ser chamada quando a análise terminar
     */
    private static void runBenchmarkForNElements(int n, Tweet[][] tweetArrays, CompressionAlgorithm compressionAlgorithm, Runnable callback) {
        System.out.println("N: " + n);
        final long[] timeSpentSum = {0}; // Recurso utilizado para criar uma variável final que pode ter seu conteúdo alterado
        final double[] compressionRatioSum = {0}; // Recurso utilizado para criar uma variável final que pode ter seu conteúdo alterado
        final long[] fileSizeSum = {0}; // Recurso utilizado para criar uma variável final que pode ter seu conteúdo alterado
        CountDownLatch countDownLatch = new CountDownLatch(AMOUNT_OF_SETS); // Contador para os AMOUNT_OF_SETS conjuntos
        for (Tweet[] tweets : tweetArrays) {
            ExecutorService executor = Executors.newCachedThreadPool(); // Cria o executor de Threads
            executor.submit(() -> { // Executa o bloco de código a seguir em outra Thread
                File tempFile = createTempFile();
                BinaryOutputStream out = null;
                try {
                    out = new BinaryOutputStream(new BufferedOutputStream(new FileOutputStream(tempFile)));
                } catch (FileNotFoundException e) {
                    System.out.println("Falha ao escrever no arquivo temporário.");
                    System.out.println(e.getMessage());
                    System.exit(0);
                }
                StringBuilder sb = new StringBuilder();
                for (Tweet tweet : tweets)
                    sb.append(tweet).append("\n");
                String content = sb.toString();
                long startTime = System.currentTimeMillis();
                long contentSize = content.getBytes().length;
                compressionAlgorithm.compress(content, out);
                out.close();
                long compressedFileSize = getFileSize(tempFile);
                timeSpentSum[0] += System.currentTimeMillis() - startTime;
                compressionRatioSum[0] += (double) contentSize / compressedFileSize;
                fileSizeSum[0] += compressedFileSize;
                countDownLatch.countDown();

                if (countDownLatch.getCount() == 0) { // Se já tiverem sido processados todos os conjuntos de Tweets
                    // Como os testes foram executados ao mesmo tempo, é necessário dividir por AMOUNT_OF_SETS
                    // duas vezes para obter o tempo médio de cada execução
                    int realTimeRatio = AMOUNT_OF_SETS * AMOUNT_OF_SETS;
                    System.out.println(String.format("\tTaxa de compressão média: %.2f", (compressionRatioSum[0] / AMOUNT_OF_SETS)));
                    System.out.println("\tTamanho médio do arquivo em disco: " + (fileSizeSum[0] / AMOUNT_OF_SETS) + " bytes");
                    System.out.println(String.format("\tTempo médio gasto: %d milisegundos\n", (timeSpentSum[0] / realTimeRatio)));
                    callback.run();
                }
            });
        }
    }

    private static File createTempFile() {
        try {
            Path tempDir = Files.createTempDirectory("temp");
            File tempFile = Files.createTempFile(tempDir, "tempCompression", ".tmp").toFile();
            tempFile.deleteOnExit();
            tempDir.toFile().deleteOnExit();
            return tempFile;
        } catch (IOException e) {
            System.out.println("Falha ao criar o arquivo temporário.");
            System.out.println(e.getMessage());
            System.exit(0);
            return null;
        }
    }

    private static long getFileSize(File file) {
        try {
            return Files.size(file.toPath());
        } catch (IOException e) {
            System.out.println("Falha ao accessar o arquivo temporário.");
            System.out.println(e.getMessage());
            System.exit(0);
            return 0;
        }
    }
}

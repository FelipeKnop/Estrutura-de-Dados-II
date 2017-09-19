import business.TweetFileReader;
import hash.BenchmarkCollisionResolutionMethod;
import hash.collision_resolution.*;
import sort.*;

import java.io.*;

public class Main {

    /**
     * Método main que lê os valores do arquivo de entrada, cria
     * O TweetFileReader que lê o arquivo de Tweets, redireciona
     * a saída para o arquivo saida.txt e executa as funções pedidas
     * na especificação do trabalho.
     * @param args Parâmetros da linha de comando
     */
    public static void main(String[] args) {
        int[] nValues = readInputFile();
        assert nValues != null;
        TweetFileReader tweetFileReader = createTweetFileReader();
        assert tweetFileReader != null;
        redirectOutput();
        cenario1(nValues, tweetFileReader);
        cenario2(nValues, tweetFileReader);
        cenario3(nValues, tweetFileReader);
        cenario4(nValues, tweetFileReader);
    }

    /**
     * Executa as funções pedidas no Cenário 1 da especificação do trabalho.
     * @param nValues Valores lidos do arquivo de entrada
     * @param tweetFileReader TweetFileReader com lista de Tweets preenchida
     */
    private static void cenario1(int[] nValues, TweetFileReader tweetFileReader) {
        System.out.println("-------------- CENÁRIO 1 --------------");
        System.out.println("\nQuickSort:\n");
        BenchmarkSortingAlgorithm.benchmarkIntegers(new QuickSort(), nValues, tweetFileReader);
        BenchmarkSortingAlgorithm.benchmarkTweets(new QuickSort(), nValues, tweetFileReader);
    }

    /**
     * Executa as funções pedidas no Cenário 2 da especificação do trabalho.
     * @param nValues Valores lidos do arquivo de entrada
     * @param tweetFileReader TweetFileReader com lista de Tweets preenchida
     */
    private static void cenario2(int[] nValues, TweetFileReader tweetFileReader) {
        System.out.println("\n\n\n-------------- CENÁRIO 2 --------------");
        System.out.println("\nQuickSort: \n");
        BenchmarkSortingAlgorithm.benchmarkIntegers(new QuickSort(), nValues, tweetFileReader);
        System.out.println("\n\nQuickSortMediana: \n");
        System.out.println("k = 3: \n");
        BenchmarkSortingAlgorithm.benchmarkIntegers(new QuickSortMediana().setK(3), nValues, tweetFileReader);
        System.out.println("\nk = 5: \n");
        BenchmarkSortingAlgorithm.benchmarkIntegers(new QuickSortMediana().setK(5), nValues, tweetFileReader);
        System.out.println("\n\nQuickSortInsercao: \n");
        System.out.println("m = 10: \n");
        BenchmarkSortingAlgorithm.benchmarkIntegers(new QuickSortInsercao().setM(10), nValues, tweetFileReader);
        System.out.println("\nm = 100: \n");
        BenchmarkSortingAlgorithm.benchmarkIntegers(new QuickSortInsercao().setM(100), nValues, tweetFileReader);
    }

    /**
     * Executa as funções pedidas no Cenário 3 da especificação do trabalho.
     *
     * De acordo com os resultados do Cenário 2, a melhor variação do QuickSort
     * foi o QuickSortInserção com m = 100, portanto essa variação será usada aqui.
     *
     * O outro algoritmo escolhido para a comparação foi o RadixSort, implementado
     * na classe {@link sort.RadixSort}.
     *
     * @param nValues Valores lidos do arquivo de entrada
     * @param tweetFileReader TweetFileReader com lista de Tweets preenchida
     */
    private static void cenario3(int[] nValues, TweetFileReader tweetFileReader) {
        System.out.println("\n\n\n-------------- CENÁRIO 3 --------------");
        System.out.println("\nQuickSort: \n");
        BenchmarkSortingAlgorithm.benchmarkIntegers(new QuickSortInsercao().setM(100), nValues, tweetFileReader);
        System.out.println("\n\nInsertionSort: \n");
        BenchmarkSortingAlgorithm.benchmarkIntegers(new InsertionSort(), nValues, tweetFileReader);
        System.out.println("\n\nMergeSort: \n");
        BenchmarkSortingAlgorithm.benchmarkIntegers(new MergeSort(), nValues, tweetFileReader);
        System.out.println("\n\nHeapSort: \n");
        BenchmarkSortingAlgorithm.benchmarkIntegers(new HeapSort(), nValues, tweetFileReader);
        System.out.println("\n\nRadixSort: \n");
        BenchmarkSortingAlgorithm.benchmarkIntegers(new RadixSort(), nValues, tweetFileReader);
    }

    /**
     * Executa as funções pedidas no Cenário 4 da especificação do trabalho.
     * @param nValues Valores lidos do arquivo de entrada
     * @param tweetFileReader TweetFileReader com lista de Tweets preenchida
     */
    private static void cenario4(int[] nValues, TweetFileReader tweetFileReader) {
        System.out.println("\n\n\n-------------- CENÁRIO 4 --------------");
        System.out.println("\nEndereçamento - Sondagem Linear: \n");
        BenchmarkCollisionResolutionMethod.benchmarkIntegers(new LinearProbingMethod(), nValues, tweetFileReader);
        System.out.println("\n\nEndereçamento - Sondagem Quadrática: \n");
        BenchmarkCollisionResolutionMethod.benchmarkIntegers(new QuadraticProbingMethod(), nValues, tweetFileReader);
        System.out.println("\n\nEndereçamento - Duplo Hash: \n");
        BenchmarkCollisionResolutionMethod.benchmarkIntegers(new DoubleHashingMethod(), nValues, tweetFileReader);
        System.out.println("\n\nEncadeamento Separado: \n");
        BenchmarkCollisionResolutionMethod.benchmarkIntegers(new SeparateChainingMethod(), nValues, tweetFileReader);
        System.out.println("\n\nEncadeamento Coalescido: \n");
        BenchmarkCollisionResolutionMethod.benchmarkIntegers(new CoalescedChainingMethod(), nValues, tweetFileReader);
    }

    /**
     * Lê o arquivo de entrada no formato descrito no arquivo Trabalho1.pdf:
     *
     * 7 -> número de valores de N que se seguem, por linha
     * 1000
     * 5000
     * 10000
     *  .
     *  .
     *  .
     *
     * @return Array de inteiros com os valores de N lidos
     */
    private static int[] readInputFile() {
        File file = new File("entrada.txt");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int nAmount = Integer.parseInt(bufferedReader.readLine());
            int[] nValues = new int[nAmount];
            for (int i = 0; i < nAmount; i++)
                nValues[i] = Integer.parseInt(bufferedReader.readLine());
            bufferedReader.close();
            return nValues;
        } catch (FileNotFoundException e) {
            System.out.println("Não foi possível encontrar o arquivo de entrada. Certifique-se" +
                    " de que o nome dele é entrada.txt e que ele está na pasta base (Estrutura de Dados II)");
            System.exit(0);
            return null;
        } catch (IOException e) {
            System.out.println("Falha ao ler o arquivo de entrada.\n" + e.getMessage());
            System.exit(0);
            return null;
        }
    }

    /**
     * Redireciona para o arquivo saida.txt tudo que é passado à função
     * {@link System#out#println()}.
     */
    private static void redirectOutput() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("saida.txt");
            System.setOut(new PrintStream(fileOutputStream));
        } catch (FileNotFoundException e) {
            System.out.println("Não foi possível criar o arquivo de saída.\n" + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Cria TweetFileReader para arquivo tweets.txr, que contém uma lista de
     * 1 milhão de Tweets obtidos no link contido no arquivo Trabalho1.pdf.
     * @return Retorna uma instância de TweetFileReader com o arquivo
     * de Tweets já lido a lista de Tweets já gerada e preenchida
     */
    private static TweetFileReader createTweetFileReader() {
        try {
            return TweetFileReader.create("tweets.txt");
        } catch (IOException e) {
            System.out.println("Não foi possível encontrar o arquivo de Tweets. Certifique-se" +
                    " de que o nome dele é tweets.txt e que ele está na pasta base (Estrutura de Dados II)");
            System.exit(0);
            return null;
        }
    }
}

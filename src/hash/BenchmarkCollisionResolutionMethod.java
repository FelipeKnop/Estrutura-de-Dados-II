package hash;

import business.TweetFileReader;
import hash.collision_resolution.CollisionResolutionMethod;

import static business.BenchmarkHelper.AMOUNT_OF_SETS;
import static business.BenchmarkHelper.generateIntegerArrays;

/**
 * Classe para análise de algoritmos de tratamento de colisão.
 */
public class BenchmarkCollisionResolutionMethod {

    /**
     * Método que analisa um algoritmo de tratamento de colisão com os valores
     * dados de entrada.
     *
     * A array nValues contém os valores de N (tamanho da array a ser armazenada)
     * a serem utilizados na análise.
     *
     * A variável AMOUNT_OF_SETS é utilizada para determinar quantos testes
     * serão realizados com diferentes sementes e contabilizados na média
     * dos resultados obtidos.
     *
     * @param collisionResolutionMethod Algoritmo de tratamento de colisão a ser analisado
     * @param nValues Valores de N (tamanho da array)
     * @param tweetFileReader TweetFileReader com a lista de Tweets já gerada
     */
    public static void benchmarkIntegers(CollisionResolutionMethod collisionResolutionMethod, int[] nValues, TweetFileReader tweetFileReader) {
        System.out.println("--- Inteiros ---\n");
        for (int n : nValues) {
            System.out.println("N: " + n);
            Long[][] integerArrays = generateIntegerArrays(n, tweetFileReader.getTweets());
            long comparisonsSum = 0;
            long memorySpentSum = 0;
            for (Long[] integers : integerArrays) {
                collisionResolutionMethod.setTableSize(n * 2);
                for (Long integer : integers)
                    collisionResolutionMethod.insert(integer);
                comparisonsSum += collisionResolutionMethod.getComparisons();
                memorySpentSum += collisionResolutionMethod.getMemorySpent();
                collisionResolutionMethod.clear();
            }
            System.out.println("\tNúmero médio de comparações: " + (comparisonsSum / AMOUNT_OF_SETS));
            System.out.println(String.format("\tMemória média gasta: %d bytes\n", (memorySpentSum / AMOUNT_OF_SETS)));
        }
    }
}

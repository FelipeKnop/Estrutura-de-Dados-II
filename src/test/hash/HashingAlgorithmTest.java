package test.hash;

import hash.HashingAlgorithm;
import org.junit.Test;
import test.TestUtils;

/**
 * Classe abstrata que implementa os métodos de teste de algoritmos de hashing.
 */
public abstract class HashingAlgorithmTest {

    /**
     * Método abstrato (deve ser implementado por cada classe) que retorna a quantidade
     * de valores a serem inseridos na tabela hash.
     * @return Quantidade de valores
     */
    protected abstract int getAmountOfValues();

    /**
     * Método abstrato (deve ser implementado por casa classe) que retorna o algoritmo de hashing a ser utilizado.
     * @return Algoritmo de hashing que será utilizado nos testes
     */
    protected abstract HashingAlgorithm getHashingAlgorithm();

    /**
     * Variável que guarda a quantidade de valores a serem inseridos;
     */
    private final int amountOfValues = getAmountOfValues();

    /**
     * Variável que guarda o método de hashing a ser utilizado.
     */
    private final HashingAlgorithm hashingAlgorithm = getHashingAlgorithm();

    /**
     * Testa a inserção de uma array de inteiros aleatórios gerada pela classe
     * {@link TestUtils}. Ao final, imprime a quantidade de comparações de chave
     * feitas pelo algoritmo de hashing e a quantidade final de colisões.
     */
    @Test
    public void hashRandomIntegerArray() {
        Integer[] array = TestUtils.getRandomIntegerArray(amountOfValues, random -> Math.abs(random.nextInt()) % 1000);
        for (Integer i : array)
            hashingAlgorithm.insert(Long.valueOf(i));
        System.out.println("[" + getClass().getSimpleName() + "] - " + amountOfValues + " valores inseridos na tabela hash");
        System.out.println("\tNúmero de comparações: " + hashingAlgorithm.getComparisons());
        System.out.println("\tNúmero de colisões: " + hashingAlgorithm.getCollisions());
    }
}

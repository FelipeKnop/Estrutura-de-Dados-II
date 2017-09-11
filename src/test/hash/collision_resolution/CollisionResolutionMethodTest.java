package test.hash.collision_resolution;

import hash.collision_resolution.CollisionResolutionMethod;
import org.junit.Test;
import test.TestUtils;

/**
 * Classe abstrata que implementa os métodos de teste de algoritmos de hashing
 * com tratamento de colisão.
 */
public abstract class CollisionResolutionMethodTest {

    /**
     * Método abstrato (deve ser implementado por cada classe) que retorna a quantidade
     * de valores a serem inseridos na tabela hash.
     * @return Quantidade de valores
     */
    protected abstract int getAmountOfValues();

    /**
     * Método abstrato (deve ser implementado por casa classe) que retorna o algoritmo de hashing
     * com tratamento de colisão a ser utilizado.
     * @return Algoritmo de hashing com tratamento de colisão que será utilizado nos testes
     */
    protected abstract CollisionResolutionMethod getCollisionResolutionMethod();

    /**
     * Variável que guarda a quantidade de valores a serem inseridos;
     */
    private final int amountOfValues = getAmountOfValues();

    /**
     * Variável que guarda o método de hashing com tratamento de colisão a ser utilizado.
     */
    private final CollisionResolutionMethod collisionResolutionMethod = getCollisionResolutionMethod();

    /**
     * Testa a inserção de uma array de inteiros aleatórios gerada pela classe
     * {@link TestUtils}. Ao final, imprime a quantidade de comparações de chave
     * feitas pelo algoritmo de hashing e a quantidade de memória gasta.
     */
    @Test
    public void hashRandomIntegerArray() {
        Integer[] array = TestUtils.getRandomIntegerArray(amountOfValues, random -> Math.abs(random.nextInt()) % 1000);
        for (Integer i : array)
            collisionResolutionMethod.insert(Long.valueOf(i));
        System.out.println("[" + getClass().getSimpleName() + "] - " + amountOfValues + " valores inseridos na tabela hash");
        System.out.println("\tNúmero de comparações: " + collisionResolutionMethod.getComparisons());
        System.out.println("\tGasto de memória em bytes: " + collisionResolutionMethod.getMemorySpent());
    }
}

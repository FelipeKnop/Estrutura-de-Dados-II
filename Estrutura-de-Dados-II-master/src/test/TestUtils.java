package test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe de métodos utilitários para os testes
 */
@SuppressWarnings("WeakerAccess")
public class TestUtils {

    /**
     * Seed padrão para o Random.
     */
    private static final int DEFAULT_SEED = 42;

    /**
     * Tamanho máximo para as strings geradas aleatoriamente.
     */
    private static final int MAX_STRING_SIZE = 12;

    /**
     * Utiliza o método {@link #getRandomIntegerArray(int, int)} para retornar
     * uma array de inteiros aleatórios.
     * @param size Tamanho da array
     * @return Retorna o resultado do método {@link #getRandomIntegerArray(int, int)},
     * que é uma array de números inteiros gerados aleatoriamente
     */
    public static Integer[] getRandomIntegerArray(int size) {
        return getRandomIntegerArray(size, DEFAULT_SEED);
    }

    /**
     * Utiliza o método genérico {@link #generateRandomArray(int, int, ElementGenerator, Class)}
     * para retornar uma array de inteiros aleatórios.
     * @param size Tamanho da array
     * @param seed Seed a ser utilizado para a geração dos números aleatórios
     * @return Retorna o resultado do método {@link #generateRandomArray(int, int, ElementGenerator, Class)},
     * que é uma array de números inteiros gerados aleatoriamente
     */
    public static Integer[] getRandomIntegerArray(int size, int seed) {
        return generateRandomArray(size, seed, random -> Math.abs(random.nextInt()) % size, Integer.class);
    }

    /**
     * Utiliza o método genérico {@link #generateRandomArray(int, int, ElementGenerator, Class)}
     * para retornar uma array de inteiros aleatórios.
     * @param size Tamanho da array
     * @param generator Função que define a regra de geração de elementos aleatórios
     * @return Retorna o resultado do método {@link #generateRandomArray(int, int, ElementGenerator, Class)},
     * que é uma array de números inteiros gerados aleatoriamente
     */
    public static Integer[] getRandomIntegerArray(int size, ElementGenerator<Integer> generator) {
        return generateRandomArray(size, DEFAULT_SEED, generator, Integer.class);
    }

    /**
     * Utiliza o método {@link #getRandomIntegerList(int, int)} para retornar
     * uma lista de inteiros aleatórios.
     * @param size Tamanho da lista
     * @return Retorna o resultado do método {@link #getRandomIntegerList(int, int)},
     * que é uma lista de números inteiros gerados aleatoriamente
     */
    public static List<Integer> getRandomIntegerList(int size) {
        return getRandomIntegerList(size, DEFAULT_SEED);
    }

    /**
     * Utiliza o método genérico {@link #generateRandomList(int, int, ElementGenerator)}
     * para retornar uma lista de inteiros aleatórios.
     * @param size Tamanho da lista
     * @param seed Seed a ser utilizado para a geração dos números aleatórios
     * @return Retorna o resultado do método {@link #generateRandomList(int, int, ElementGenerator)},
     * que é uma lista de números inteiros gerados aleatoriamente
     */
    public static List<Integer> getRandomIntegerList(int size, int seed) {
        return generateRandomList(size, seed, random -> Math.abs(random.nextInt()) % size);
    }

    /**
     * Utiliza o método {@link #getRandomStringArray(int, int)} para retornar
     * uma array de strings aleatórias.
     * @param size Tamanho da array
     * @return Retorna o resultado do método {@link #getRandomStringArray(int, int)},
     * que é uma array de strings geradas aleatoriamente
     */
    public static String[] getRandomStringArray(int size) {
        return getRandomStringArray(size, DEFAULT_SEED);
    }

    /**
     * Utiliza o método genérico {@link #generateRandomArray(int, int, ElementGenerator, Class)}
     * para retornar uma array de strings aleatórias.
     * @param size Tamanho da array
     * @param seed Seed a ser utilizado para a geração dos números aleatórios
     * @return Retorna o resultado do método {@link #generateRandomArray(int, int, ElementGenerator, Class)},
     * que é uma array de strings geradas aleatoriamente
     */
    public static String[] getRandomStringArray(int size, int seed) {
        return generateRandomArray(size, seed, TestUtils::generateRandomString, String.class);
    }

    /**
     * Utiliza o método {@link #getRandomStringList(int, int)} para retornar
     * uma lista de strings aleatórias.
     * @param size Tamanho da lista
     * @return Retorna o resultado do método {@link #getRandomStringList(int, int)},
     * que é uma lista de strings geradas aleatoriamente
     */
    public static List<String> getRandomStringList(int size) {
        return getRandomStringList(size, DEFAULT_SEED);
    }

    /**
     * Utiliza o método genérico {@link #generateRandomList(int, int, ElementGenerator)}
     * para retornar uma lista de strings aleatórias.
     * @param size Tamanho da lista
     * @param seed Seed a ser utilizado para a geração dos números aleatórios
     * @return Retorna o resultado do método {@link #generateRandomList(int, int, ElementGenerator)},
     * que é uma lista de strings geradas aleatoriamente
     */
    public static List<String> getRandomStringList(int size, int seed) {
        return generateRandomList(size, seed, TestUtils::generateRandomString);
    }

    /**
     * Gera uma string aleatória recebendo um Random com seed já definido.
     * Utiliza esse random para gerar um número aleatório que corresponde à
     * posição de um caracter em uma string que contém todos os caracteres
     * de A a Z e 0 a 9 e insere esse caracter na string a ser gerada.
     * O tamanho da string é definida por um número gerado aleatoriamente
     * entre 0 e {@link #MAX_STRING_SIZE}
     * @param random Random com seed já definido
     * @return Retorna uma string gerada aleatoriamente
     */
    private static String generateRandomString(Random random) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int stringSize = Math.abs(random.nextInt()) % MAX_STRING_SIZE;

        StringBuilder sb = new StringBuilder();
        while (sb.length() < stringSize) {
            sb.append(chars.charAt(Math.abs(random.nextInt()) % chars.length()));
        }

        return sb.toString();
    }

    /**
     * Método genérico que gera uma array de elementos aleatórios a partir de
     * uma função definida que define a regra de geração desses elementos.
     * @param size Tamanho da array
     * @param seed Seed a ser utilizado para a geração de números aleatórios
     * @param generator Função que define a regra de geração de elementos aleatórios
     * @param clazz Tipo da array. Necessário para o uso do método {@link Array#newInstance(Class, int)}
     * @param <T> Tipo da array
     * @return Retorna uma array de elementos do tipo T gerados aleatoriamente
     */
    @SuppressWarnings("unchecked")
    private static <T> T[] generateRandomArray(int size, int seed, ElementGenerator<T> generator, Class<T> clazz) {
        Random random = new Random(seed);
        T[] array = (T[]) Array.newInstance(clazz, size);
        for (int i = 0; i < size; i++)
            array[i] = generator.generateElement(random);
        return array;
    }

    /**
     * Método genérico que gera uma lista de elementos aleatórios a partir de
     * uma função definida que define a regra de geração desses elementos.
     * @param size Tamanho da lista
     * @param seed Seed a ser utilizado para a geração de números aleatórios
     * @param generator Função que define a regra de geração de elementos aleatórios
     * @param <T> Tipo da lista
     * @return Retorna uma lista de elementos do tipo T gerados aleatoriamente
     */
    private static <T> List<T> generateRandomList(int size, int seed, ElementGenerator<T> generator) {
        Random random = new Random(seed);
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++)
            list.add(generator.generateElement(random));
        return list;
    }

    /**
     * Interface funcional a ser usada para definir a regra de geração de um novo elemento.
     * @param <T>
     */
    public interface ElementGenerator<T> {

        /**
         * Método que possui a implementação de geração de um elemento aleatório.
         * @param random Random com seed já definido
         * @return Retorna um novo elemento a ser inserido na array/lista
         */
        T generateElement(Random random);
    }
}

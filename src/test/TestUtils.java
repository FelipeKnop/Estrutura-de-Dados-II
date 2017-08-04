package test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("WeakerAccess")
public class TestUtils {

    private static final int DEFAULT_SEED = 42;
    private static final int MAX_STRING_SIZE = 12;

    public static Integer[] getRandomIntegerArray(int size) {
        return getRandomIntegerArray(size, DEFAULT_SEED);
    }

    public static Integer[] getRandomIntegerArray(int size, int seed) {
        return generateRandomArray(size, seed, random -> Math.abs(random.nextInt()) % size, Integer.class);
    }

    public static List<Integer> getRandomIntegerList(int size) {
        return getRandomIntegerList(size, DEFAULT_SEED);
    }

    public static List<Integer> getRandomIntegerList(int size, int seed) {
        return generateRandomList(size, seed, random -> Math.abs(random.nextInt()) % size);
    }

    public static String[] getRandomStringArray(int size) {
        return getRandomStringArray(size, DEFAULT_SEED);
    }

    public static String[] getRandomStringArray(int size, int seed) {
        return generateRandomArray(size, seed, TestUtils::generateRandomString, String.class);
    }

    public static List<String> getRandomStringList(int size) {
        return getRandomStringList(size, DEFAULT_SEED);
    }

    public static List<String> getRandomStringList(int size, int seed) {
        return generateRandomList(size, seed, TestUtils::generateRandomString);
    }

    private static String generateRandomString(Random random) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int stringSize = Math.abs(random.nextInt()) % MAX_STRING_SIZE;

        StringBuilder sb = new StringBuilder();
        while (sb.length() < stringSize) {
            sb.append(chars.charAt(Math.abs(random.nextInt()) % chars.length()));
        }

        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    private static <T> T[] generateRandomArray(int size, int seed, ElementGenerator<T> generator, Class<T> clazz) {
        Random random = new Random(seed);
        T[] array = (T[]) Array.newInstance(clazz, size);
        for (int i = 0; i < size; i++)
            array[i] = generator.generateElement(random);
        return array;
    }

    private static <T> List<T> generateRandomList(int size, int seed, ElementGenerator<T> generator) {
        Random random = new Random(seed);
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++)
            list.add(generator.generateElement(random));
        return list;
    }

    private interface ElementGenerator<T> {
        T generateElement(Random random);
    }
}

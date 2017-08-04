package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestUtils {

    private static final int DEFAULT_SEED = 42;
    private static final int MAX_STRING_SIZE = 12;

    public static List<Integer> getRandomIntegerList(int size) {
        return getRandomIntegerList(size, DEFAULT_SEED);
    }

    public static List<Integer> getRandomIntegerList(int size, int seed) {
        Random random = new Random(seed);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++)
            list.add(Math.abs(random.nextInt()) % size);
        return list;
    }

    public static List<String> getRandomStringList(int size) {
        return getRandomStringList(size, DEFAULT_SEED);
    }

    public static List<String> getRandomStringList(int size, int seed) {
        Random random = new Random(seed);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++)
            list.add(generateRandomString(random));
        return list;
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
}

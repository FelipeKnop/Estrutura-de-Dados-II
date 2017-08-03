import sort.BubbleSort;
import sort.SortingAlgorithm;

import java.util.Arrays;
import java.util.List;

public class Main {

    private static final List<Integer> list = Arrays.asList(6, 2, 1, 5, 4, 3);

    public static void main(String[] args) {
        System.out.println("Array não ordenada:");
        list.forEach(System.out::println);

        SortingAlgorithm sortingAlgorithm = new BubbleSort();
        List<?> sorted = sortingAlgorithm.sort(list);

        System.out.println("Array ordenada:");
        sorted.forEach(System.out::println);
    }
}

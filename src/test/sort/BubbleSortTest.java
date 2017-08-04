package test.sort;

import org.junit.Test;
import sort.BubbleSort;
import test.TestUtils;

import java.util.List;

import static org.junit.Assert.fail;

public class BubbleSortTest {

    @Test
    public void sortRandomIntegerList() {
        List<Integer> list = TestUtils.getRandomIntegerList(100);
        BubbleSort bubbleSort = new BubbleSort();
        List<Integer> sorted = bubbleSort.sort(list);
        for (int i = 1; i < sorted.size() - 1; i++)
            if (sorted.get(i - 1).compareTo(sorted.get(i)) > 0)
                fail(String.format("A lista não está ordenada corretamente. Índice [%d]: %d, índice [%d]: %d", i - 1, list.get(i - 1), i, list.get(i)));
        System.out.println("Lista de inteiros aleatórios ordenada corretamente");
    }

    @Test
    public void sortRandomStringList() {
        List<String> list = TestUtils.getRandomStringList(100);
        BubbleSort bubbleSort = new BubbleSort();
        List<String> sorted = bubbleSort.sort(list);
        for (int i = 1; i < sorted.size() - 1; i++)
            if (sorted.get(i - 1).compareTo(sorted.get(i)) > 0)
                fail(String.format("A lista não está ordenada corretamente. Índice [%d]: %s, índice [%d]: %s", i - 1, list.get(i - 1), i, list.get(i)));
        System.out.println("Lista de strings aleatórias ordenada corretamente");
    }
}

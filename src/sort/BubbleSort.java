package sort;

import java.util.List;

public class BubbleSort implements SortingAlgorithm {

    @Override
    public List<Integer> sort(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++)
            for (int j = i + 1; j < list.size(); j++)
                if (list.get(j) < list.get(i))
                    list.set(i, list.set(j, list.get(i)));
        return list;
    }
}

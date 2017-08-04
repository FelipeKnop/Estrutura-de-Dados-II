package sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("UnnecessaryLocalVariable")
public class BubbleSort implements SortingAlgorithm {

    @Override
    public <T extends Comparable<? super T>> List<T> sort(List<T> list) {
        List<T> l = new ArrayList<>(list);
        for (int i = 0; i < l.size() - 1; i++)
            for (int j = i + 1; j < l.size(); j++)
                if (l.get(j).compareTo(l.get(i)) < 0)
                    l.set(i, l.set(j, l.get(i)));
        return l;
    }

    @Override
    public <T> List<T> sort(List<T> list, Comparator<? super T> comparator) {
        List<T> l = new ArrayList<>(list);
        for (int i = 0; i < l.size() - 1; i++)
            for (int j = i + 1; j < l.size(); j++)
                if (comparator.compare(l.get(j), l.get(i)) < 0)
                    l.set(i, l.set(j, l.get(i)));
        return l;
    }
}

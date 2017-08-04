package sort;

import java.util.Comparator;
import java.util.List;

public interface SortingAlgorithm {

    <T extends Comparable<? super T>> List<T> sort(List<T> list);
    <T> List<T> sort(List<T> list, Comparator<? super T> comparator);
}

package hash;

import java.util.ArrayList;
import java.util.HashMap;

public class HashTable {

    private final int tSize;
    private final HashingAlgorithm hashingAlgorithm;
    private final HashMap<Integer, ArrayList<Integer>> table;

    public HashTable(int size, HashingAlgorithm hashingAlgorithm) {
        this.tSize = size;
        this.hashingAlgorithm = hashingAlgorithm;
        this.table = new HashMap<>(size);
    }

    public Integer insert(Integer value) {
        Integer index = hashingAlgorithm.hash(value, tSize);
        ArrayList<Integer> indexList = table.computeIfAbsent(index, k -> new ArrayList<>());
        indexList.add(value);
        return index;
    }

    public int getTableSize() {
        return this.table.size();
    }
}

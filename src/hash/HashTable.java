package hash;

import hash.collision_resolution.CollisionResolutionMethod;

import java.util.ArrayList;
import java.util.HashMap;

public class HashTable {

    private final int tSize;
    private final HashingAlgorithm hashingAlgorithm;
    private final CollisionResolutionMethod collisionResolutionMethod;
    private final HashMap<Integer, ArrayList<Object>> table;

    public HashTable(int size, HashingAlgorithm hashingAlgorithm) {
        this.tSize = size;
        this.hashingAlgorithm = hashingAlgorithm;
        this.collisionResolutionMethod = null;
        this.table = new HashMap<>(size);
    }

    public HashTable(int size, HashingAlgorithm hashingAlgorithm, CollisionResolutionMethod collisionResolutionMethod) {
        this.tSize = size;
        this.hashingAlgorithm = hashingAlgorithm;
        this.collisionResolutionMethod = collisionResolutionMethod;
        this.table = new HashMap<>(size);
    }

    public int insert(Object element) {
        int index = hashingAlgorithm.hash(element, tSize);
        int iterations = 1;
        while (table.containsKey(index) && collisionResolutionMethod != null) {
            index = collisionResolutionMethod.resolve(element, index, iterations, tSize);
            iterations++;
        }
        ArrayList<Object> indexList = table.computeIfAbsent(index, k -> new ArrayList<>());
        indexList.add(element);
        return index;
    }

    public int getTableSize() {
        return this.table.size();
    }
}

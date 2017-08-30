package test.hash.collision_resolution;

import hash.DJB2Hash;
import hash.HashTable;
import hash.HashingAlgorithm;
import hash.collision_resolution.CollisionResolutionMethod;
import hash.collision_resolution.OpenAddressingMethod;
import org.junit.Test;
import test.TestUtils;

public class OpenAddressingMethodTest {

    private static final int TABLE_SIZE = 200;
    private static final int AMOUNT_OF_VALUES = 200;

    private static HashingAlgorithm hashingAlgorithm = new DJB2Hash();
    private static CollisionResolutionMethod collisionResolutionMethod = new OpenAddressingMethod();

    @Test
    public void indexRandomIntegerArray() {
        Integer[] array = TestUtils.getRandomIntegerArray(AMOUNT_OF_VALUES, random -> Math.abs(random.nextInt()) % 1000);
        HashTable hashTable = new HashTable(TABLE_SIZE, hashingAlgorithm, collisionResolutionMethod);
        for (Integer i : array)
            hashTable.insert(i);
        System.out.println("[" + getClass().getSimpleName() + "] - Número de colisões: " + (AMOUNT_OF_VALUES - hashTable.getTableSize()));
    }
}

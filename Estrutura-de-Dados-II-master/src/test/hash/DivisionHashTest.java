package test.hash;

import hash.DivisionHash;
import hash.HashTable;
import hash.HashingAlgorithm;
import org.junit.Test;
import test.TestUtils;

public class DivisionHashTest {

    private static final int TABLE_SIZE = 200;
    private static final int AMOUNT_OF_VALUES = 100;

    private static HashingAlgorithm hashingAlgorithm = new DivisionHash();

    @Test
    public void indexRandomIntegerArray() {
        Integer[] array = TestUtils.getRandomIntegerArray(AMOUNT_OF_VALUES, random -> Math.abs(random.nextInt()) % 1000);
        HashTable hashTable = new HashTable(TABLE_SIZE, hashingAlgorithm);
        for (Integer i : array)
            hashTable.insert(i);
        System.out.println("[" + getClass().getSimpleName() + "] - Número de colisões: " + (AMOUNT_OF_VALUES - hashTable.getTableSize()));
    }

}

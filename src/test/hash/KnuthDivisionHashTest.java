package test.hash;

import hash.HashTable;
import hash.HashingAlgorithm;
import hash.KnuthDivisionHash;
import org.junit.Test;
import test.TestUtils;

public class KnuthDivisionHashTest {

    private static final int TABLE_SIZE = 200;
    private static final int AMOUNT_OF_VALUES = 100;

    private static HashingAlgorithm hashingAlgorithm = new KnuthDivisionHash();

    @Test
    public void indexRandomIntegerArray() {
        Integer[] array = TestUtils.getRandomIntegerArray(AMOUNT_OF_VALUES, random -> Math.abs(random.nextInt()) % 1000);
        HashTable hashTable = new HashTable(TABLE_SIZE, hashingAlgorithm);
        for (Integer i : array)
            hashTable.insert(i);
        System.out.println("[" + getClass().getSimpleName() + "] - Número de colisões: " + (AMOUNT_OF_VALUES - hashTable.getTableSize()));
    }
}

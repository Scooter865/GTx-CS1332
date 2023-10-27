package com.hashmaps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
* Unit test for simple App.
*/
public class PutTests {
    /**
    * Basic add to empty test:
    * Add an entry to an empty table.
    * Valdate the entry is at the expected index.
    * Validate other indeicies are empty.
    * Validate size increment.
    */
    @Test
    public void addToEmpty() {
        /* Set up a new test object and add <1, 11> */
        ExternalChainingHashMap<Integer, Integer> testTable = new ExternalChainingHashMap<>();
        int testSize = testTable.size();
        Integer testKey = 1;
        Integer testValue = 11;
        
        Integer result = testTable.put(testKey, testValue);
        
        /* Set up some locals and the node for examining values */
        ExternalChainingMapEntry<Integer, Integer>[] backingArray = testTable.getTable();
        int expectedIdx = Math.abs(testKey.hashCode() % backingArray.length);
        ExternalChainingMapEntry<Integer, Integer> node = backingArray[expectedIdx];
        
        /* Validate new entry is where it should be */
        for (int i = 0; i < backingArray.length; i++) {
            if (i == expectedIdx) {
                assertEquals(testKey, node.getKey());
                assertEquals(testValue, node.getValue());
            }
            else {
                assertNull(backingArray[i]);
            }
        }
        
        /* Validate nothing is replaceed and size increases by 1 */
        assertNull(result);
        assertEquals(testSize + 1, testTable.size());
    }
    
    /** 
    * Add with collision:
    * Add a non-duplicate entry where an entry already exists
    * Validate the new entry is added at the head of the list
    * Validate old entry is still in the list at the index
    */
    @Test
    public void addWithCollision() {
        /* Set up a new test object, add <1, 11> and <14, 22> */
        ExternalChainingHashMap<Integer, Integer> testTable = new ExternalChainingHashMap<>();
        int testSize = testTable.size();
        Integer testKey = 1;
        Integer testValue = 11;
        Integer testKey2 = 14;
        Integer testValue2 = 22;
        
        Integer result = testTable.put(testKey, testValue);
        Integer result2 = testTable.put(testKey2, testValue2);
        
        /* Set up some locals and the node for examining values */
        ExternalChainingMapEntry<Integer, Integer>[] backingArray = testTable.getTable();
        int expectedIdx = Math.abs(testKey2.hashCode() % backingArray.length); // Shouldn't matter whether this is testKey or testKey2
        ExternalChainingMapEntry<Integer, Integer> node = backingArray[expectedIdx];
        
        /* Validate new entry is where it should be */
        for (int i = 0; i < backingArray.length; i++) {
            if (i == expectedIdx) {
                /* Validate new entry is at the head and old entry is next */
                assertEquals(testKey2, node.getKey());
                assertEquals(testValue2, node.getValue());
                node = node.getNext();
                assertEquals(testKey, node.getKey());
                assertEquals(testValue, node.getValue());
            }
            else {
                assertNull(backingArray[i]);
            }
        }
        
        /* Validate nothing is replaceed and size increases by 2 */
        assertNull(result);
        assertNull(result2);
        assertEquals(testSize + 2, testTable.size());
    }
    
    
    /** 
    * Add duplicate:
    * Add an entry with a key that is already in the table
    * Validate the data for that entry is updated
    */
    @Test
    public void addDuplicate() {
        /* Set up a new test object and add <1, 11> and <1, 22> */
        ExternalChainingHashMap<Integer, Integer> testTable = new ExternalChainingHashMap<>();
        int testSize = testTable.size();
        Integer testKey = 1;
        Integer testValue = 11;
        Integer testValue2 = 22;
        
        Integer result = testTable.put(testKey, testValue);
        Integer result2 = testTable.put(testKey, testValue2);
        
        /* Set up some locals and the node for examining values */
        ExternalChainingMapEntry<Integer, Integer>[] backingArray = testTable.getTable();
        int expectedIdx = Math.abs(testKey.hashCode() % backingArray.length);
        ExternalChainingMapEntry<Integer, Integer> node = backingArray[expectedIdx];
        
        /* Validate entry is where it should be */
        for (int i = 0; i < backingArray.length; i++) {
            if (i == expectedIdx) {
                /* Validate updated value and no new node */
                assertEquals(testKey, node.getKey());
                assertEquals(testValue2, node.getValue());
                assertNull(node.getNext());
            }
            else {
                assertNull(backingArray[i]);
            }
        }
        
        /* Validate value replacement and no size increase */
        assertNull(result);
        assertEquals(testValue, result2);
        assertEquals(testSize + 1, testTable.size());
    }
    
    /**
    * Add with resize - flat table:
    * Build map with no chaining
    * Add an entry that exceeds the max load factor and triggers a resize.
    * Validate that new entry is where it should be.
    */
    @Test
    public void addResizeFlat() { //Must resize on 9th put (9/13 = 0.69)
        /* Set up a new test object and add 8 entries */
        ExternalChainingHashMap<Integer, Integer> testTable = new ExternalChainingHashMap<>();
        for (int i = 1; i <= 8; i++) {
            testTable.put(i, i*11);
        }
        
        /* Validate table isn't resized then add 9th entry */
        assertEquals(ExternalChainingHashMap.INITIAL_CAPACITY, testTable.getTable().length);
        
        Integer testKey = 9;
        Integer testValue = 99;
        
        Integer result = testTable.put(testKey, testValue);
        
        /* Set up some locals and the node for examining values */
        ExternalChainingMapEntry<Integer, Integer>[] backingArray = testTable.getTable();
        int expectedIdx = Math.abs(testKey.hashCode() % backingArray.length);
        ExternalChainingMapEntry<Integer, Integer> node = backingArray[expectedIdx];
        
        /* Validate entry is where it should be */
        assertEquals(testKey, node.getKey());
        assertEquals(testValue, node.getValue());
        
        
        /* Validate new entry and resized table */
        assertNull(result);
        assertEquals(2*ExternalChainingHashMap.INITIAL_CAPACITY +1, backingArray.length);
    }
    
    /**
    * Add with resize - chained table:
    * Build map with chaining
    * Add an entry to a chain that exceeds the max load factor and triggers a resize.
    * Validate that new entry is where it should be.
    */
    @Test
    public void addResizeChained() { //Must resize on 9th put (9/13 = 0.69)
        /* Set up a new test object and add 8 entries */
        ExternalChainingHashMap<Integer, Integer> testTable = new ExternalChainingHashMap<>();
        testTable.put(2, 1);
        testTable.put(15, 2);
        testTable.put(4, 3);
        testTable.put(17, 4);
        testTable.put(6, 5);
        testTable.put(19, 6);
        testTable.put(8, 7);
        testTable.put(21, 8);
        
        /* Validate table isn't resized then add 9th entry */
        assertEquals(ExternalChainingHashMap.INITIAL_CAPACITY, testTable.getTable().length);
        
        Integer testKey = 28;
        Integer testValue = 99;
        
        Integer result = testTable.put(testKey, testValue);
        
        /* Set up some locals and the node for examining values */
        ExternalChainingMapEntry<Integer, Integer>[] backingArray = testTable.getTable();
        int expectedIdx = Math.abs(testKey.hashCode() % backingArray.length);
        ExternalChainingMapEntry<Integer, Integer> node = backingArray[expectedIdx];
        
        /* Validate entry is where it should be
         * Should be the first entry at index 1
         */
        assertEquals(testKey, node.getKey());
        assertEquals(testValue, node.getValue());

        /* Validiate other entries are where they should be */
        assertEquals((Integer) 2, backingArray[2].getKey());
        assertEquals((Integer) 15, backingArray[15].getKey());


        /* Validate new entry and resized table */
        assertNull(result);
        assertEquals(2*ExternalChainingHashMap.INITIAL_CAPACITY +1, backingArray.length);
    }
}

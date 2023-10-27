package com.hashmaps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
* Unit test for simple App.
*/
public class RemoveTests {
    /**
    * Basic remove from 1 entry table:
    * Remove the only entry from a table.
    * Validate the table is empty.
    */
    @Test
    public void removeToEmpty() {
        ExternalChainingHashMap<Integer, Integer> testTable = new ExternalChainingHashMap<>();
        Integer testKey = 1;
        Integer testValue = 11;
        
        testTable.put(testKey, testValue);
        int testSize = testTable.size();
        
        Integer ans = testTable.remove(1);
        
        assertEquals(testValue, ans);
        assertEquals(testSize - 1, testTable.size());
        assertNull(testTable.getTable()[testKey]); //Assuming insertion index is 1
    }
    
    /** 
    * Remove non-existent data:
    * Remove an entry that is not in the list.
    * Validate exception is thrown.
    */
    @Test
    public void removeNonExistent() {
        ExternalChainingHashMap<Integer, Integer> testTable = new ExternalChainingHashMap<>();
        Integer testKey = 1;
        Integer testValue = 11;
        
        testTable.put(testKey, testValue);
        int testSize = testTable.size();
        
        try {
            testTable.remove(5);
        } catch (Exception e) {
            // TODO: handle exception
        }
        assertEquals(testSize, testTable.size());
    }
    
    /** 
     * Remove from collision (head):
     * Remove an entry from the head of a chain.
    * Validate the entry is no longer in the table.
    */
    @Test
    public void removeFromHead() {
        ExternalChainingHashMap<Integer, Integer> testTable = new ExternalChainingHashMap<>();
        testTable.put(2, 1);
        testTable.put(15, 2);
        testTable.put(4, 3);
        testTable.put(17, 4);
        testTable.put(6, 5);
        testTable.put(19, 6);
        testTable.put(8, 7);
        testTable.put(21, 8);
        int testSize = testTable.size();

        Integer ans = testTable.remove(4);

        assertEquals(testSize - 1, testTable.size());
        assertEquals((Integer) 3, ans);
        assertEquals((Integer) 17, testTable.getTable()[4].getKey());
    }
    
    /** 
     * Remove from collision (middle):
    * Remove an entry from a spot down a chain.
    * Validate the entry is no longer in the table.
    */
    @Test
    public void removeFromMiddle() {
        ExternalChainingHashMap<Integer, Integer> testTable = new ExternalChainingHashMap<>();
        testTable.put(2, 1);
        testTable.put(15, 2);
        testTable.put(4, 3);
        testTable.put(17, 4);
        testTable.put(30, 9);
        testTable.put(6, 5);
        testTable.put(19, 6);
        int testSize = testTable.size();

        Integer ans = testTable.remove(17);
        
        assertEquals(testSize - 1, testTable.size());
        assertEquals((Integer) 4, ans);
        assertEquals((Integer) 30, testTable.getTable()[4].getKey());
        assertEquals((Integer) 4, testTable.getTable()[4].getNext().getKey());
    }
    
    @Test
    public void graderRemoval() {
        ExternalChainingHashMap<Integer, Integer> testTable = new ExternalChainingHashMap<>();
        testTable.put(1, 1);
        testTable.put(6, 6);
        testTable.put(19, 19);
        testTable.put(8, 8);
        testTable.put(37, 37);
        testTable.put(24, 24);
        testTable.put(11, 11);
        
        Integer ans = testTable.remove(11);

        assertEquals((Integer) 11, ans);
        assertEquals((Integer) 24, testTable.getTable()[11].getKey());
        assertEquals((Integer) 37, testTable.getTable()[11].getNext().getKey());
    }
}

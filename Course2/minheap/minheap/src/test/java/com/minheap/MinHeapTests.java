package com.minheap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import java.io.*;

import org.junit.Test;

public class MinHeapTests {

    /**
     * Add test - Call add method and make sure the added element is present in the backing array.
     * Assert it's at the last index
     * Assert size is incremented
     */
    @Test
    public void AddTestBasic() {
        MinHeap<Integer> testObj = new MinHeap<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        int testSize = testObj.size();

        testObj.add(0);
        List<Integer> testHeap = testObj.getBackingArray();

        assertTrue(testHeap.contains(0));
        assertEquals(1, testHeap.indexOf(0));
        assertEquals(testSize+1, testObj.size());
    }

    /**
     * Add test 2 - Add element and resize backing array
     * Assert array size is doubled
     * Assert new element is in last index (size)
     * Assert size is incremented
     */
    @Test
    public void AddTestGrowArray() {
        MinHeap<Integer> testObj = new MinHeap<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        int testSize = testObj.size();

        testObj.add(50);
        List<Integer> testHeap = testObj.getBackingArray();

        assertEquals(26, testObj.getBackingArray().size());
        assertTrue(testHeap.contains(50));
        assertEquals(13, testHeap.indexOf(50));
        assertEquals(testSize+1, testObj.size());
    }

    /**
     * Remove test - Remove root of heap (min element)
     * Identify min value, assert return == min value
     * Assert there is a new min value
     * Assert removed value is no longer in heap
     */
    @Test
    public void RemoveTest() {
        MinHeap<Integer> testObj = new MinHeap<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        int testSize = testObj.size();

        int RemovedValue = testObj.remove();
        List<Integer> testHeap = testObj.getBackingArray();
        
        assertEquals(testSize-1, testObj.size());
        assertFalse(testHeap.contains(0));
        assertEquals(0, RemovedValue);
        assertEquals((Integer) 1, testHeap.get(1));
    }

    @Test
    public void remove2() {
        MinHeap<Integer> testObj = new MinHeap<>(Arrays.asList(5,8,6,9,11,22,7,66,99,12,77,88,33,44,55));
        int testSize = testObj.size();

        int RemovedValue = testObj.remove();
        List<Integer> testHeap = testObj.getBackingArray();

        assertEquals(testSize-1, testObj.size());
        assertFalse(testHeap.contains(5));
        assertEquals(5, RemovedValue);
        assertEquals((Integer) 6, testHeap.get(1));
    }

    @Test
    public void remove2NullChildren() {
        MinHeap<Integer> testObj = new MinHeap<>(Arrays.asList(1));
        int testSize = testObj.size();

        int RemovedValue = testObj.remove();
        List<Integer> testHeap = testObj.getBackingArray();

        assertEquals(testSize-1, testObj.size());
        assertFalse(testHeap.contains(1));
        assertEquals(1, RemovedValue);
        assertNull(testHeap.get(1));
    }

    @Test
    public void remove1Child() {
        MinHeap<Integer> testObj = new MinHeap<>(Arrays.asList(1,2));
        int testSize = testObj.size();

        int RemovedValue = testObj.remove();
        List<Integer> testHeap = testObj.getBackingArray();

        assertEquals(testSize-1, testObj.size());
        assertFalse(testHeap.contains(1));
        assertEquals(1, RemovedValue);
        assertEquals((Integer) 2, testHeap.get(1));
    }

    @Test
    public void remove2Child() {
        MinHeap<Integer> testObj = new MinHeap<>(Arrays.asList(1,2));
        int testSize = testObj.size();

        int RemovedValue = testObj.remove();
        List<Integer> testHeap = testObj.getBackingArray();

        assertEquals(testSize-1, testObj.size());
        assertFalse(testHeap.contains(1));
        assertEquals(1, RemovedValue);
        assertEquals((Integer) 2, testHeap.get(1));
    }

    @Test
    public void remove3() {
        MinHeap<Integer> testObj = new MinHeap<>(Arrays.asList(0, 14, 7, 42, 35, 28, 21, 56, 49));
        int testSize = testObj.size();

        int RemovedValue = testObj.remove();
        List<Integer> testHeap = testObj.getBackingArray();

        assertEquals(Arrays.asList(null, 7, 14, 21, 42, 35, 28, 49, 56, null, null, null, null), testHeap);
    }
}

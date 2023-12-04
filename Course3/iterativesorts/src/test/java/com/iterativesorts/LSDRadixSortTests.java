package com.iterativesorts;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


/**
 * Unit test for my bubble sort implementation.
 */
public class LSDRadixSortTests {

    // Emtpy array
    @Test
    public void EmptyArrayTest() {
        int[] testArray = {};
        int[] answer = {};

        DivideConquerSorts.lsdRadixSort(testArray);

        assertArrayEquals(answer, testArray);
    }

    // Array with only 1 element
    @Test
    public void SingleElementArrayTest() {
        int[] testArray = {10};
        int[] answer = {10};

        DivideConquerSorts.lsdRadixSort(testArray);

        assertArrayEquals(answer, testArray);
    }

    // Array with all duplicates
    @Test
    public void AllDuplicatesTest() {
        int[] testArray = {1,1,1,1,1};
        int[] answer = {1,1,1,1,1};

        DivideConquerSorts.lsdRadixSort(testArray);

        assertArrayEquals(answer, testArray);
    }

    // Strictly increasing array
    @Test
    public void StrictlyIncreasingTest() {
        int[] testArray = {1,2,3,4,5};
        int[] answer = {1,2,3,4,5};

        DivideConquerSorts.lsdRadixSort(testArray);

        assertArrayEquals(answer, testArray);
    }

    // Strictly decreasing array
    @Test
    public void StricltyDecreasingTest() {
        int[] testArray = {5,4,3,2,1};
        int[] answer = {1,2,3,4,5};

        DivideConquerSorts.lsdRadixSort(testArray);

        assertArrayEquals(answer, testArray);
    }

    // Strictly increasing excpet smallest element is at the end
    @Test
    public void StrictlyIncreasingSmallestAtEndTest() {
        int[] testArray = {2,3,4,5,1};
        int[] answer = {1,2,3,4,5};

        DivideConquerSorts.lsdRadixSort(testArray);

        assertArrayEquals(answer, testArray);
    }

    // Assignment example
    @Test
    public void AssignmentExampleTest() {
        int[] testArray = {17, 743, 672, 780, 917, 743, 623, 288, 432, 281, 76};
        int[] answer = {17, 76, 281, 288, 432, 623, 672, 743, 743, 780, 917};

        DivideConquerSorts.lsdRadixSort(testArray);

        assertArrayEquals(answer, testArray);
    }

    // Min and max ints
    @Test
    public void MinMaxIntTest() {
        int[] testArray = {Integer.MAX_VALUE, Integer.MIN_VALUE};
        int[] answer = {Integer.MIN_VALUE, Integer.MAX_VALUE};

        DivideConquerSorts.lsdRadixSort(testArray);

        assertArrayEquals(answer, testArray);
    }
}

package com.practiceexam;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


/**
 * Unit test for my selection sort implementation.
 */
public class SelectionSortTests {

    // Emtpy array
    @Test
    public void EmptyArrayTest() {
        Integer[] testArray = {};
        Integer[] answer = {};

        Sorting.selectionSort(testArray, new SortComparator());

        assertArrayEquals(answer, testArray);
    }

    // Array with only 1 element
    @Test
    public void SingleElementArrayTest() {
        Integer[] testArray = {10};
        Integer[] answer = {10};

        Sorting.selectionSort(testArray, new SortComparator());

        assertArrayEquals(answer, testArray);
    }

    // Array with all duplicates
    @Test
    public void AllDuplicatesTest() {
        Integer[] testArray = {1,1,1,1,1};
        Integer[] answer = {1,1,1,1,1};

        Sorting.selectionSort(testArray, new SortComparator());

        assertArrayEquals(answer, testArray);
    }

    // Strictly increasing array
    @Test
    public void StrictlyIncreasingTest() {
        Integer[] testArray = {1,2,3,4,5};
        Integer[] answer = {1,2,3,4,5};

        Sorting.selectionSort(testArray, new SortComparator());

        assertArrayEquals(answer, testArray);
    }

    // Strictly decreasing array
    @Test
    public void StricltyDecreasingTest() {
        Integer[] testArray = {5,4,3,2,1};
        Integer[] answer = {1,2,3,4,5};

        Sorting.selectionSort(testArray, new SortComparator());

        assertArrayEquals(answer, testArray);
    }

    // Strictly increasing excpet smallest element is at the end
    @Test
    public void StrictlyIncreasingSmallestAtEndTest() {
        Integer[] testArray = {2,3,4,5,1};
        Integer[] answer = {1,2,3,4,5};

        Sorting.selectionSort(testArray, new SortComparator());

        assertArrayEquals(answer, testArray);
    }

    // Assignment example
    @Test
    public void AssignmentExampleTest() {
        Integer[] testArray = {1,2,6,5,3,4,7,8,9};
        Integer[] answer = {1,2,3,4,5,6,7,8,9};

        Sorting.selectionSort(testArray, new SortComparator());

        assertArrayEquals(answer, testArray);
    }
}

package com.practiceexam;

import java.util.Comparator;

/**
* Your implementation of Merge Sort.
*/
public class Sorting {
    /**
     * Implement selection sort.
     *
     * It should be: in-place unstable not adaptive
     *
     * Have a worst case running time of: O(n^2) And a best case running time of:
     * O(n^2)
     *
     * You may assume that the passed in array and comparator are both valid and
     * will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        for (int n = arr.length-1; n > 0; n--) {
            int maxIdx = n;
            for (int i = 0; i < n; i++) {
                if (comparator.compare(arr[i], arr[maxIdx]) > 0) {
                    maxIdx = i;
                }
            }
            T holder = arr[n];
            arr[n] = arr[maxIdx];
            arr[maxIdx] = holder;
        }
    }
    
    /**
    * Implement merge sort.
    *
    * It should be: out-of-place stable not adaptive
    *
    * Have a worst case running time of: O(n log n) And a best case running time
    * of: O(n log n)
    *
    * You can create more arrays to run merge sort, but at the end, everything
    * should be merged back into the original T[] which was passed in.
    *
    * When splitting the array, if there is an odd number of elements, put the
    * extra data on the right side.
    *
    * Hint: You may need to create a helper method that merges two arrays back into
    * the original T[] array. If two data are equal when merging, think about which
    * subarray you should pull from first.
    *
    * You may assume that the passed in array and comparator are both valid and
    * will not be null.
    *
    * @param <T>        Data type to sort.
    * @param arr        The array to be sorted.
    * @param comparator The Comparator used to compare the data in arr.
    */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        /* Establish base cases of arr.length <= 0
        * Split the array into left and right subarrays
        * Make recursive calls on left, then right subarrays
        * Merge the returned subarrays into the sorted parent array 
        */
        if (arr.length <= 1) {
            return;
        }
        
        // Build and populate left and right subarrays
        T[] leftSubArr = (T[]) new Object[arr.length/2];
        for (int i = 0; i < leftSubArr.length; i++) {
            leftSubArr[i] = arr[i];
        }
        
        T[] rightSubArr = (T[]) new Object[arr.length - leftSubArr.length];
        for (int i = 0; i < rightSubArr.length; i++) {
            rightSubArr[i] = arr[i+leftSubArr.length];
        }

        // Recursive calls on left, then right subarrays
        mergeSort(leftSubArr, comparator);
        mergeSort(rightSubArr, comparator);

        // Merge
        /* i and j are iterators for left and right subarray, respectively
         * increment i over the left subarray and j over the right subarray
         * write the lower of the i and j values back to the parent array at i+j
         * drain the other array when one array empties
         */
        int i = 0, j = 0;
        while (i < leftSubArr.length && j < rightSubArr.length) {
            // <= makes it stable
            if (comparator.compare(leftSubArr[i], rightSubArr[j]) <= 0) {
                arr[i+j] = leftSubArr[i];
                i++;
            }
            else {
                arr[i+j] = rightSubArr[j];
                j++;
            }
        }
        // Now one subarray is empty so drain the other one
        while (i < leftSubArr.length) {
            arr[i+j] = leftSubArr[i];
            i++;
        }
        while (j < rightSubArr.length) {
            arr[i+j] = rightSubArr[j];
            j++;
        }
    }
}
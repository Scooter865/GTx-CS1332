package com.iterativesorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

/**
* Your implementation of various divide & conquer sorting algorithms.
*/

public class DivideConquerSorts {
    
    /**
    * Implement merge sort.
    *
    * It should be:
    * out-of-place
    * stable
    * not adaptive
    *
    * Have a worst case running time of: O(n log n)
    * And a best case running time of: O(n log n)
    *
    * You can create more arrays to run merge sort, but at the end, everything
    * should be merged back into the original T[] which was passed in.
    *
    * When splitting the array, if there is an odd number of elements, put the
    * extra data on the right side.
    *
    * Hint: You may need to create a helper method that merges two arrays
    * back into the original T[] array. If two data are equal when merging,
    * think about which subarray you should pull from first.
    *
    * You may assume that the passed in array and comparator are both valid
    * and will not be null.
    *
    * @param <T>        Data type to sort.
    * @param arr        The array to be sorted.
    * @param comparator The Comparator used to compare the data in arr.
    */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr.length <= 1) {
            return;
        }
        
        // Copy left and right halves into the proper subarrays
        T[] leftSubArr = (T[]) new Object[arr.length/2];
        for (int i = 0; i < leftSubArr.length; i++) {
            leftSubArr[i] = arr[i];
        }

        T[] rightSubArr = (T[]) new Object[arr.length - leftSubArr.length];
        int rIdx = 0;
        for (int i = leftSubArr.length; i < arr.length; i++) {
            rightSubArr[rIdx] = arr[i];
            rIdx++;
        }
        
        // Recursive calls to break down into subarrays of length 1
        mergeSort(leftSubArr, comparator);
        mergeSort(rightSubArr, comparator);
        
        // i is pointer in left subarray, j is pointer in right subarray
        int i = 0, j = 0;
        
        while ((i < leftSubArr.length) && (j < rightSubArr.length)) {
            if (comparator.compare(leftSubArr[i], rightSubArr[j]) <= 0) {
                arr[i+j] = leftSubArr[i];
                i++;
            }
            else {
                arr[i+j] = rightSubArr[j];
                j++;
            }
        }
        // Quickly empty out the arrays
        while (i < leftSubArr.length) {
            arr[i+j] = leftSubArr[i];
            i++;
        }
        while (j < rightSubArr.length) {
            arr[i+j] = rightSubArr[j];
            j++;
        }
    }
    
    /**
    * Implement LSD (least significant digit) radix sort.
    *
    * It should be:
    * out-of-place
    * stable
    * not adaptive
    *
    * Have a worst case running time of: O(kn)
    * And a best case running time of: O(kn)
    *
    * Feel free to make an initial O(n) passthrough of the array to
    * determine k, the number of iterations you need.
    *
    * At no point should you find yourself needing a way to exponentiate a
    * number; any such method would be non-O(1). Think about how how you can
    * get each power of BASE naturally and efficiently as the algorithm
    * progresses through each digit.
    *
    * You may use an ArrayList or LinkedList if you wish, but it should only
    * be used inside radix sort and any radix sort helpers. Do NOT use these
    * classes with merge sort. However, be sure the List implementation you
    * choose allows for stability while being as efficient as possible.
    *
    * Do NOT use anything from the Math class except Math.abs().
    *
    * You may assume that the passed in array is valid and will not be null.
    *
    * @param arr The array to be sorted.
    */
    public static void lsdRadixSort(int[] arr) {
        // Figure out how many buckets you need and init the buckets
        int maxDigits = 1;
        for (int num : arr) {
            int numDigits = 1;
            while (Math.abs(num/10) > 0) {
                numDigits++;
                num /= 10;
            }
            maxDigits = Math.max(maxDigits, numDigits);
        }
        
        LinkedList<Integer>[] buckets = new LinkedList[19];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
        
        int power = 1;
        for (int i = 0; i < maxDigits; i++) {
            // Load the buckets
            for (Integer element : arr) {
                int digit = (element/power) % 10;
                buckets[9+digit].add(element);
            }
            // "Incrementing" power to load/unload with the next digit on the next loop
            power *= 10;

            // Unload the buckets
            int unloadIdx = 0;
            for (LinkedList<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    arr[unloadIdx] = bucket.pop(); //Not sure if pop is the right method here
                    unloadIdx++;
                }
            }
        }
        return;
    }
}
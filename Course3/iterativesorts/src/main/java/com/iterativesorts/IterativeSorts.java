package com.iterativesorts;

import java.util.Comparator;

/**
 * Your implementation of various iterative sorting algorithms.
 */
public class IterativeSorts {

    /**
     * Implement bubble sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * NOTE: You should implement bubble sort with the last swap optimization.
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        T holder;
        int endIdx = arr.length - 1;
        while (endIdx > 0) {
            int lastSwappedIdx = 0;
            for (int i = 0; i < endIdx; i++) {
                if (comparator.compare(arr[i], arr[i+1]) > 0) {
                    holder = arr[i+1];
                    arr[i+1] = arr[i];
                    arr[i] = holder;
                    lastSwappedIdx = i;
                }
            }
            endIdx = lastSwappedIdx;
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n^2)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        T holder;
        for (int n = arr.length - 1; n > 0; n--) {
            int maxIdx = n;
            for (int i = 0; i < n; i++) {
                if (comparator.compare(arr[i], arr[maxIdx]) > 0) {
                    maxIdx = i;
                }
            }
            holder = arr[n];
            arr[n] = arr[maxIdx];
            arr[maxIdx] = holder;
        }
    }


    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        T holder;
        for (int n = 1; n < arr.length; n++) {
            int i = n;
            while((i > 0) && (comparator.compare(arr[i], arr[i-1]) < 0)) {
                holder = arr[i-1];
                arr[i-1] = arr[i];
                arr[i] = holder;
                i--;
            }
        }
    }
}
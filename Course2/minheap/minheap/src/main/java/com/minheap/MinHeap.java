package com.minheap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/**
* Your implementation of a MinHeap.
*/
public class MinHeap<T extends Comparable<? super T>> {
    
    /**
    * The initial capacity of the MinHeap.
    *
    * DO NOT MODIFY THIS VARIABLE!
    */
    public static final int INITIAL_CAPACITY = 13;
    
    /*
    * Do not add new instance variables or modify existing ones.
    */
    private T[] backingArray;
    private int size;
    
    /**
    * This is the constructor that constructs a new MinHeap.
    *
    * Recall that Java does not allow for regular generic array creation,
    * so instead we cast a Comparable[] to a T[] to get the generic typing.
    */
    public MinHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }
    
    /** Adding BuildHeap
    * Build a heap from an unsorted list for testing using the BuildHeap algorithm.
    * Utilize MinHeap constructor.
    * Do I need to specify this.backingArray or can I just say backingArray?
    */
    public MinHeap(List<T> seed) {
        this();
        while (seed.size() > this.backingArray.length) {
            T[] backingArrayTemp = (T[]) new Comparable[2*backingArray.length];
            backingArray = backingArrayTemp;
        }
        
        for (int i = 1; i <= seed.size(); i++) {
            backingArray[i] = seed.get(i-1);
            size++;
        }
    }
    
    /**
    * Adds an item to the heap. If the backing array is full (except for
    * index 0) and you're trying to add a new item, then double its capacity.
    *
    * Method should run in amortized O(log n) time.
    *
    * @param data The data to add.
    * @throws java.lang.IllegalArgumentException If the data is null.
    */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data cannot be null");
        }
        
        if (backingArray.length == size+1) {
            T[] tempBackingArray = (T[]) new Comparable[2*backingArray.length];
            
            for (int i = 1; i <= size; i++) {
                tempBackingArray[i] = backingArray[i];
            }
            backingArray = tempBackingArray;
        }
        
        size++;
        
        backingArray[size] = data;
        
        rUpHeap(size);
    }
    
    private void rUpHeap(int dataIdx) {
        int parentIdx = dataIdx/2;
        T node = backingArray[dataIdx];
        T parent = backingArray[parentIdx];
        
        // Base case - parent is greater than data or data is at the top of the heap
        if ((parent == null) || (node.compareTo(parent) > 0)) {
            return;
        }
        
        // Upheap when needed
        backingArray[dataIdx] = parent;
        backingArray[parentIdx] = node;
        dataIdx /= 2;
        
        rUpHeap(dataIdx);
        
        return;
    }
    
    /**
    * Removes and returns the min item of the heap. As usual for array-backed
    * structures, be sure to null out spots as you remove. Do not decrease the
    * capacity of the backing array.
    *
    * Method should run in O(log n) time.
    *
    * @return The data that was removed.
    * @throws java.util.NoSuchElementException If the heap is empty.
    */
    public T remove() {
        if (backingArray[1] == null || size == 0) {
            throw new java.util.NoSuchElementException("Root is null");
        }
        
        T removedData = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        
        int nodeIdx = 1;
        int leftIdx = 2;
        int rightIdx = 3;
        
        T node = backingArray[nodeIdx];
        T leftChild = backingArray[leftIdx];
        T rightChild = backingArray[rightIdx];
        
        while (leftChild != null) { //break when left/both children are null
            if (rightChild == null) {
                if (node.compareTo(leftChild) < 0) { //break when node < left child and right child null
                    break;
                }
                else if (leftChild.compareTo(node) < 0) {
                    backingArray[nodeIdx] = leftChild;
                    backingArray[leftIdx] = node;
                    nodeIdx = leftIdx;
                }
            }
            else {
                if ((node.compareTo(leftChild) < 0) && (node.compareTo(rightChild) < 0)) { //break when node < both children
                    break;
                }
                else if ((leftChild.compareTo(node) < 0) && (leftChild.compareTo(rightChild) < 0)) {
                    backingArray[nodeIdx] = leftChild;
                    backingArray[leftIdx] = node;
                    nodeIdx = leftIdx;
                }
                else if ((rightChild.compareTo(node) < 0) && (rightChild.compareTo(leftChild) < 0)) {
                    backingArray[nodeIdx] = rightChild;
                    backingArray[rightIdx] = node;
                    nodeIdx = rightIdx;
                }
            }
            
            leftIdx = nodeIdx*2;
            rightIdx = nodeIdx*2 + 1;
            node = backingArray[nodeIdx];
            leftChild = (leftIdx >= backingArray.length) ? null : backingArray[leftIdx];
            rightChild = (rightIdx >= backingArray.length) ? null : backingArray[rightIdx];
        }
        return removedData;
    }
    
    
    /**
    * Returns the backing array of the heap.
    *
    * For grading purposes only. You shouldn't need to use this method since
    * you have direct access to the variable.
    *
    * @return The backing array of the list
    */
    public List<T> getBackingArray() {
        // DO NOT MODIFY THIS METHOD! - LOL
        List<T> backingList = new ArrayList<>(Arrays.asList(backingArray));
        return backingList;
    }
    
    /**
    * Returns the size of the heap.
    *
    * For grading purposes only. You shouldn't need to use this method since
    * you have direct access to the variable.
    *
    * @return The size of the list
    */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}

/**
* Your implementation of a MaxHeap.
*/
public class MaxHeap<T extends Comparable<? super T>> {
    
    /*
    * The initial capacity of the MaxHeap when created with the default
    * constructor.
    *
    * DO NOT MODIFY THIS VARIABLE!
    */
    public static final int INITIAL_CAPACITY = 13;
    
    /*
    * Do not add new instance variables or modify existing ones.
    */
    public T[] backingArray;
    public int size;
    
    /**
    * This is the constructor that constructs a new MaxHeap.
    *
    * Recall that Java does not allow for regular generic array creation,
    * so instead we cast a Comparable[] to a T[] to get the generic typing.
    */
    public MaxHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }
    
    /**
    * Removes and returns the max item of the heap. As usual for array-backed
    * structures, be sure to null out spots as you remove. Do not decrease the
    * capacity of the backing array.
    *
    * Method should run in O(log n) time.
    *
    * You may assume that the heap is not empty.
    *
    * @return The data that was removed.
    */
    public T remove() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        /* remove root, replace it with the rightmost element, downheap as necessary */
        
        T removedData = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        
        downheap(1);
        return removedData;
    }
    
    /* General order seems to be detect situation with if-else, move things around in backingArray, make recursive call */
    private void downheap(int idx) {
        T node = backingArray[idx];
        T left = idx*2 > backingArray.length ? null : backingArray[idx*2];
        T right = idx*2 + 1 > backingArray.length ? null : backingArray[idx*2 + 1];
        
        if (left == null) {
            if (right == null) return; // Base case - No children base case
            else if (node.compareTo(right) > 0) return; // Base case - Only right child but it's less than node
            else { // Only right child and it's greater than node
                backingArray[idx] = right;
                backingArray[idx*2 + 1] = node;
                downheap(idx*2 + 1);
            }
        }
        else if (right == null) {
            // No children base case already handled
            if (node.compareTo(left) > 0) return; // Base case - only left child but it's less than node
            else { // Only left child and it's greater than node
                backingArray[idx] = left;
                backingArray[idx*2] = node;
                downheap(idx*2);
            }
        }
        else { // 2 Children - neither, one, or both could be greater than node
            if (left.compareTo(node) > 0) {
                if (right.compareTo(left) > 0) { // Both children greater than node, right child is greatest
                    backingArray[idx] = right;
                    backingArray[idx*2 + 1] = node;
                    downheap(idx*2 + 1);
                }
                else { // Left or both children greater than node, left child is greatest
                    backingArray[idx] = left;
                    backingArray[idx*2] = node;
                    downheap(idx*2);
                }
            }
            else if (right.compareTo(node) > 0) { // Right child greater than node, left less than node
                backingArray[idx] = right;
                backingArray[idx*2 + 1] = node;
                downheap(idx*2 + 1);
            }
            else return; // Base case - Neither child greater than node
        }
        return; // After swapping parent/child
    }


/**
* Returns the backing array of the heap.
*
* For grading purposes only. You shouldn't need to use this method since
* you have direct access to the variable.
*
* @return The backing array of the list
*/
public T[] getBackingArray() {
    // DO NOT MODIFY THIS METHOD!
    return backingArray;
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


public static void main(String[] args) {
    MaxHeap<Integer> testHeap = new MaxHeap<>();
    //Integer[] backingArray2 = {null, 42, 35, 28, 21, 14, 7, 0, null, null, null, null, null};
    Integer[] backingArray2 = {null, 56, 49, 42, 35, 28, 21, 14, 7, 0, null, null, null};
    
    testHeap.backingArray = backingArray2;
    testHeap.size = 9;
    
    testHeap.remove();
}
}

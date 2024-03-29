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
    private T[] backingArray;
    private int size;

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
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add. You may assume that data will always be valid.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        size++;

        // Resize if necessary
        if (backingArray.length <= size) {
            T[] tempBackingArray = (T[]) new Comparable[backingArray.length*2];
            for (int i = 0; i < backingArray.length; i++) {
                tempBackingArray[i] = backingArray[i];
            }
            backingArray = tempBackingArray;
        }

        backingArray[size] = data;

        upheap(size);
    }

    private void upheap(int idx) {
        // Base cases: parent > node, parent == null
        T node = backingArray[idx]; //This shoud never be null
        T parent = backingArray[idx/2];

        if ((parent== null) || (parent.compareTo(node) > 0)) {
            return;
        }

        // Not a base case - upheap
        backingArray[idx] = parent;
        backingArray[idx/2] = node;

        // Recursive call & return
        upheap(idx/2);
        return;
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
}
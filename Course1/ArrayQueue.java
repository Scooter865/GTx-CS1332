import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayQueue.
 */
public class ArrayQueue<T> {

    /*
     * The initial capacity of the ArrayQueue.
     *
     * DO NOT MODIFY THIS VARIABLE.
     */
    public static final int INITIAL_CAPACITY = 9;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int front;
    private int size;

    /**
     * This is the constructor that constructs a new ArrayQueue.
     * 
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast an Object[] to a T[] to get the generic typing.
     */
    public ArrayQueue() {
        // DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Adds the data to the back of the queue.
     *
     * If sufficient space is not available in the backing array, resize it to
     * double the current length. When resizing, copy elements to the
     * beginning of the new array and reset front to 0.
     *
     * Method should run in amortized O(1) time.
     *
     * @param data the data to add to the back of the queue
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void enqueue(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new java.lang.IllegalArgumentException("null input is not allowed for enqueue method.");
        }

        int baLength = backingArray.length;
        int idx = (front + size) % baLength;

        // Array is full if (front + size) % length = 0
        if (size == baLength) {
            T[] backingArray2 = (T[]) new Object[baLength * 2]; //resize
            for (int i = 0; i < size; i++) { //repopulate from 0
                backingArray2[i] = backingArray[(front + i) % size];
            }
            backingArray = backingArray2;
            backingArray[size] = data; //add new data
            front = 0;
        }
        // Normal enqueue
        else {
            backingArray[idx] = data;
        }

        size++;
    }

    /**
     * Removes and returns the data from the front of the queue.
     *
     * Do not shrink the backing array.
     *
     * Replace any spots that you dequeue from with null.
     *
     * If the queue becomes empty as a result of this call, do not reset
     * front to 0.
     *
     * Method should run in O(1) time.
     *
     * @return the data formerly located at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    
    public T dequeue() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (backingArray[front] == null) {
            throw new java.util.NoSuchElementException("Element is null, queue is empty.");
        }

        T temp = backingArray[front];
        backingArray[front] = null;
        front = (front + 1) % backingArray.length;
        size--;
        return temp;
    }

    /**
     * Returns the backing array of the queue.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the queue
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the queue.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the queue
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    public void printQueue() {
        for (T t : backingArray) {
            System.out.println(t);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> testQ = new ArrayQueue<>();
        /* testQ.enqueue(0);
        testQ.enqueue(1);
        testQ.enqueue(2);
        testQ.enqueue(3);
        testQ.enqueue(4);
        testQ.enqueue(5);
        testQ.printQueue();
        testQ.dequeue();
        testQ.dequeue();
        testQ.printQueue();
        testQ.enqueue(6);
        testQ.enqueue(7);
        testQ.enqueue(8);
        testQ.enqueue(9);
        testQ.printQueue();
        testQ.dequeue();
        testQ.dequeue();
        testQ.dequeue();
        testQ.dequeue();
        testQ.dequeue();
        testQ.dequeue();
        testQ.dequeue();
        testQ.enqueue(9);
        testQ.printQueue();
        testQ.dequeue();
        testQ.printQueue();*/

        testQ.enqueue(0);
        testQ.enqueue(1);
        testQ.enqueue(2);
        testQ.enqueue(3);
        testQ.dequeue();
        testQ.dequeue();
        testQ.enqueue(4);
        testQ.enqueue(5);
        testQ.enqueue(6);
        testQ.enqueue(7);
        testQ.enqueue(8);
        testQ.enqueue(9);
        testQ.enqueue(10);
        testQ.enqueue(11);
        testQ.enqueue(12);
        testQ.printQueue();
    }
}

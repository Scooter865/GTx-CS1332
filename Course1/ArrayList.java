import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayList.
 */
public class ArrayList<T> {

    /*
     * The initial capacity of the ArrayList.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 9;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new ArrayList.
     * 
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast an Object[] to a T[] to get the generic typing.
     */
    public ArrayList() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Adds the data to the front of the list.
     *
     * This add may require elements to be shifted.
     *
     * Method should run in O(n) time.
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        // Resize array case
        int baCap = backingArray.length;
        if (size == baCap) {
            ArrayList<T> tempArray = new ArrayList<T>();
            
            tempArray.addToBack(data);
            for (T t : backingArray) {
                tempArray.addToBack(t);
            }

            backingArray = tempArray.backingArray;
        }
        // Non resize case
        else {
            for (int i = size-1; i >= 0; i--) {
                backingArray[i+1] = backingArray[i];
            }
            backingArray[0] = data;
        }

        size++;
    }

    /**
     * Adds the data to the back of the list.
     *
     * Method should run in amortized O(1) time.
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        
        int baCap = backingArray.length;
        if (size == baCap) {
            T[] tempArray = (T[]) new Object[baCap*2];

            for (int i = 0; i < size; i++) {
                tempArray[i] = backingArray[i];
            }

            backingArray = tempArray;
        }
        
        backingArray[size] = data;
        size++;
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Do not shrink the backing array.
     *
     * This remove may require elements to be shifted.
     *
     * Method should run in O(n) time.
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (size < 1) {
            throw new NoSuchElementException("Empty array");
        }

        T oldFront = backingArray[0];

        for (int i = 0; i < size-1; i++) {
            backingArray[i] = backingArray[i+1];
        }

        backingArray[size-1] = null;
        size--;

        return oldFront;
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Do not shrink the backing array.
     *
     * Method should run in O(1) time.
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (size < 1) {
            throw new NoSuchElementException("Empty array");
        }

        T oldBack = backingArray[size-1];
        backingArray[size-1] = null;
        size--;

        return oldBack;
    }

    /**
     * Returns the backing array of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    void printArray() {
        for (T t : backingArray) {
            System.out.println(t);
        }
        System.out.println(size + " elements");
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayList<Integer> testAL = new ArrayList();
        testAL.printArray();

        // Add to back tests
        // null input
        //testAL.addToBack(null);
        //testAL.addToBack(69);
        //testAL.printArray();
        // fill init array
        /*for (int i = 0; i < 9; i++) {
            testAL.addToBack(i);            
        }
        testAL.printArray();*/
        // resize
        /*for (int i = 0; i < 10; i++) {
            testAL.addToBack(i);            
        }
        testAL.printArray();*/

        // Add to front tests
        // null input
        //testAL.addToFront(null);
        //testAL.addToFront(69);
        //testAL.printArray();
        // fill init array
        /*for (int i = 0; i < 9; i++) {
            testAL.addToFront(i);            
        }
        testAL.printArray();*/
        // resize
        /*for (int i = 0; i < 10; i++) {
            testAL.addToFront(i);            
        }
        testAL.printArray();*/

        // Remove from back tests
        // Empty array
        //testAL.removeFromBack();
        //testAL.printArray();


        // Remove from front tests
        //testAL.removeFromFront();
        //testAL.printArray();
    }
}

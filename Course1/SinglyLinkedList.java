import java.util.NoSuchElementException;

import org.w3c.dom.Node;

/**
* Your implementation of a Singly-Linked List.
*/
public class SinglyLinkedList<T> {
    
    /*
    * Do not add new instance variables or modify existing ones.
    */
    private SinglyLinkedListNode<T> head;
    private SinglyLinkedListNode<T> tail;
    private int size;
    
    /*
    * Do not add a constructor.
    */
    
    /**
    * Adds the element to the front of the list.
    *
    * Method should run in O(1) time.
    *
    * @param data the data to add to the front of the list
    * @throws java.lang.IllegalArgumentException if data is null
    */
    public void addToFront(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new java.lang.IllegalArgumentException("null input is not allowed for add method.");
        }

        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data,head);
        head = newNode;
        if (tail == null) {
            tail = newNode;
        }
        size++;
    }
    
    /**
    * Adds the element to the back of the list.
    *
    * Method should run in O(1) time.
    *
    * @param data the data to add to the back of the list
    * @throws java.lang.IllegalArgumentException if data is null
    */
    public void addToBack(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new java.lang.IllegalArgumentException("null input is not allowed for add method.");
        }

        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }
    
    /**
    * Removes and returns the first data of the list.
    *
    * Method should run in O(1) time.
    *
    * @return the data formerly located at the front of the list
    * @throws java.util.NoSuchElementException if the list is empty
    */
    public T removeFromFront() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (head == null) {
            throw new java.util.NoSuchElementException("Empty list, nothing to remove.");
        }
        T temp = head.getData();
        
        // 1 element corner case
        if (head == tail) {
            tail = null;
        }
        
        head = head.getNext();
        size --;
        return temp;
    }
    
    /**
    * Removes and returns the last data of the list.
    *
    * Method should run in O(n) time.
    *
    * @return the data formerly located at the back of the list
    * @throws java.util.NoSuchElementException if the list is empty
    */
    public T removeFromBack() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (head == null) {
            throw new java.util.NoSuchElementException("Empty list, nothing to remove.");
        }
        T temp = tail.getData();
        
        // 1 element corner case
        if (head == tail) {
            head = null;
            tail = null;
            size--;
            return temp;
        }
        else {
            SinglyLinkedListNode<T> cur = head;
            while (cur.getNext() != tail) {
                cur = cur.getNext();
            }
            tail = cur;
            cur.setNext(null);
            size--;
            return temp;
        }
    }
    
    /**
    * Returns the head node of the list.
    *
    * For grading purposes only. You shouldn't need to use this method since
    * you have direct access to the variable.
    *
    * @return the node at the head of the list
    */
    public SinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }
    
    /**
    * Returns the tail node of the list.
    *
    * For grading purposes only. You shouldn't need to use this method since
    * you have direct access to the variable.
    *
    * @return the node at the tail of the list
    */
    public SinglyLinkedListNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
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


    public void PrintList() {
        if (head == null) {
            System.out.println();
            return;
        }
        SinglyLinkedListNode<T> cur = head;
        while (cur.getNext() != null) {
            System.out.println(cur.getData());
            cur = cur.getNext();
        }
        System.out.println(cur.getData());
        System.out.println();
        return;
    }

    private SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    } 

    public static void main(String[] args) {
        SinglyLinkedList<Integer> test = new SinglyLinkedList<>();
        /*test.addToFront(1);
        test.addToBack(2);
        test.addToFront(0);
        test.addToBack(3);
        test.PrintList();
        test.removeFromFront();
        test.removeFromBack();
        test.PrintList();*/

        /*test.removeFromBack();
        test.removeFromFront();*/

        /*test.addToBack(null);
        test.addToFront(null);*/

        test.addToFront(9);
        //test.removeFromFront();
        int removed = test.removeFromBack();
        test.PrintList();
        System.out.println(removed);
    }
}

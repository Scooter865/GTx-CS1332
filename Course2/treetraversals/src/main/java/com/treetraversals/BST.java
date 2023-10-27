package com.treetraversals;

import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    public BSTNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The new data should become a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Should be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to add to the tree.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        root = rAdd(root, data);
    }

    private BSTNode<T> rAdd(BSTNode<T> cur, T data) {
        if (cur == null) {
            size++;
            return new BSTNode<>(data);
        }
        else if (data.compareTo(cur.getData()) < 0) {
            cur.setLeft(rAdd(cur.getLeft(), data));
        }
        else if (data.compareTo(cur.getData()) > 0) {
            cur.setRight(rAdd(cur.getRight(), data));
        }
        return cur;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the SUCCESSOR to
     * replace the data. You should use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If data is null.
     * @throws java.util.NoSuchElementException   If the data is not in the tree.
     */
    public T remove(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        BSTNode<T> holder = new BSTNode<>(null);
        root = rRemove(root, data, holder);
        return holder.getData();
    }

    private BSTNode<T> rRemove(BSTNode<T> cur, T data, BSTNode<T> holder) {
        if (cur == null) {
            throw new java.util.NoSuchElementException("Data not found in tree");
        }
        else if (data.compareTo(cur.getData()) < 0) {
            cur.setLeft(rRemove(cur.getLeft(), data, holder));
        }
        else if (data.compareTo(cur.getData()) > 0) {
            cur.setRight(rRemove(cur.getRight(), data, holder));
        }
        else { //implied that data == cur.data here
            holder.setData(cur.getData());
            size--;
            if (cur.getLeft() == null && cur.getRight() == null) {
                return null; //removing a node w/o children so there's no child to replace it when reinforcing pointer to parent
            }
            else if (cur.getLeft() != null && cur.getRight() == null) {
                return cur.getLeft(); //promote left child when right is null
            }
            else if (cur.getLeft() == null && cur.getRight() != null) {
                return cur.getRight(); //promote right child when left is null
            }
            else { //2 child case
                
                BSTNode<T> holder2 = new BSTNode<>(null);
                cur.setRight(successor(cur.getRight(), holder2)); //Start successor search 1 to the right
                cur.setData(holder2.getData());
            }
        }
        return cur;
    }

    private BSTNode<T> successor(BSTNode<T> cur, BSTNode<T> sholder) {
        if (cur.getLeft() == null) { //found successor
            sholder.setData(cur.getData());
            return cur.getRight(); //replaces successor with right child or null (there is no left child)
        }
        else {
            cur.setLeft(successor(cur.getLeft(), sholder));
            return cur;
        }
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
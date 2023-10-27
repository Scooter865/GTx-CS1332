package com.avls;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
* Your implementation of an AVL.
*/
public class AVL<T extends Comparable<? super T>> {
    
    /*
    * Do not add new instance variables or modify existing ones.
    */
    private AVLNode<T> root;
    private int size;
    
    /*
    * Do not add a constructor.
    */
    
    /**
     * Generates an inorder traversal so trees can be built & tested.
     * @return the inorder traversal as a list.
     */
    public List<T> inorder() {
        List<T> traversal = new ArrayList<T>();
        inorderHelper(root, traversal);
        return traversal;
    }
    
    private void inorderHelper(AVLNode<T> node, List<T> list) {
        if (node != null) {
            inorderHelper(node.getLeft(), list);
            list.add(node.getData());
            inorderHelper(node.getRight(), list);
        }
        else {
            return;
        } 
    }
    
    /**
    * Adds the element to the tree.
    *
    * Start by adding it as a leaf like in a regular BST and then rotate the
    * tree as necessary.
    *
    * If the data is already in the tree, then nothing should be done (the
    * duplicate shouldn't get added, and size should not be incremented).
    *
    * Remember to recalculate heights and balance factors while going back
    * up the tree after adding the element, making sure to rebalance if
    * necessary. This is as simple as calling the balance() method on the
    * current node, before returning it (assuming that your balance method
    * is written correctly from part 1 of this assignment).
    *
    * @param data The data to add.
    * @throws java.lang.IllegalArgumentException If data is null.
    */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new java.lang.IllegalArgumentException("data cannot be null");
        }

        root = rAdd(data, root);
    }

    private AVLNode<T> rAdd(T data, AVLNode<T> node) {
        // Base case: searched to the bottom and didn't find data - add a new node
        if (node == null) {
            size++;
            node = new AVLNode<T>(data);
        }

        // Searching for data
        if (data.compareTo(node.getData()) > 0) {
            node.setRight(rAdd(data, node.getRight()));
        }
        else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(rAdd(data, node.getLeft()));
        }
        // Do nothing if data found in tree

        // Recalculate height and BF and rotate if necessary before returning
        return balance(node);
    }
    
    /**
    * Removes and returns the element from the tree matching the given
    * parameter.
    *
    * There are 3 cases to consider:
    * 1: The node containing the data is a leaf (no children). In this case,
    *    simply remove it.
    * 2: The node containing the data has one child. In this case, simply
    *    replace it with its child.
    * 3: The node containing the data has 2 children. Use the successor to
    *    replace the data, NOT predecessor. As a reminder, rotations can occur
    *    after removing the successor node.
    *
    * Remember to recalculate heights and balance factors while going back
    * up the tree after removing the element, making sure to rebalance if
    * necessary. This is as simple as calling the balance() method on the
    * current node, before returning it (assuming that your balance method
    * is written correctly from part 1 of this assignment).
    *
    * Do NOT return the same data that was passed in. Return the data that
    * was stored in the tree.
    *
    * Hint: Should you use value equality or reference equality?
    *
    * @param data The data to remove.
    * @return The data that was removed.
    * @throws java.lang.IllegalArgumentException If the data is null.
    * @throws java.util.NoSuchElementException   If the data is not found.
    */
    public T remove(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new java.lang.IllegalArgumentException("data cannot be null");
        }

        AVLNode<T> holder = new AVLNode<>(null);
        root = rRemove(data, root, holder);
        return holder.getData();
    }

    private AVLNode<T> rRemove(T data, AVLNode<T> node, AVLNode<T> holder) {
        // Base case: Searched to bottom and didn't find data
        if (node == null) {
            throw new java.util.NoSuchElementException("data not in tree");
        }

        // Searching for data node
        if (data.compareTo(node.getData()) > 0) {
            node.setRight(rRemove(data, node.getRight(), holder));
        }
        else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(rRemove(data, node.getLeft(), holder));
        }
        // Found data node
        else {
            /* No need to call balance in this block. This logic doesn't change any subtrees,
            it just connects them to the parent of data which is what needs to be balanced. */

            size--;
            holder.setData(node.getData());

            // Data is in a leaf node - just remove it w/o balancing
            if ((node.getLeft() == null) && (node.getRight() == null)) {
                return null;
            }
            // Data node only has left child - connect left child with its grandparent
            else if ((node.getLeft() != null) && (node.getRight() == null)) {
                return node.getLeft();
            }
            // Data node only has right child - connect roght child with its grandparent
            else if ((node.getLeft() == null) && (node.getRight() != null)) {
                return node.getRight();
            }
            // 2 children - replace data node with successor
            else {
                AVLNode<T> successorHolder = new AVLNode<T>(null);
                node.setRight(successor(node.getRight(), successorHolder));
                node.setData(successorHolder.getData());
            }
        }

        // Rebalance as you return up the tree
        return balance(node);
    }

    /* Need to balance at parent of successor and back up from there */
    private AVLNode<T> successor(AVLNode<T> sNode, AVLNode<T> successorHolder) {
        /* Base case: left child is null, record successor data into successorHolder 
         connect the right child with its grandparent. */
        if (sNode.getLeft() == null) {
            successorHolder.setData(sNode.getData());
            return sNode.getRight();
        }
        else {
            sNode.setLeft(successor(sNode.getLeft(), successorHolder));
            //pointer reinforcement, may need to balance here because successor child was connected to its grandparent
            return balance(sNode); 
        }
    }
    
    /**
    * Updates the height and balance factor of a node using its
    * setter methods.
    *
    * Recall that a null node has a height of -1. If a node is not
    * null, then the height of that node will be its height instance
    * data. The height of a node is the max of its left child's height
    * and right child's height, plus one.
    *
    * The balance factor of a node is the height of its left child
    * minus the height of its right child.
    *
    * This method should run in O(1).
    * You may assume that the passed in node is not null.
    *
    * This method should only be called in rotateLeft(), rotateRight(),
    * and balance().
    *
    * @param currentNode The node to update the height and balance factor of.
    */
    public void updateHeightAndBF(AVLNode<T> node) {
        // COPY YOUR CODE FROM PART 1 OF THE ASSIGNMENT!
        int leftHeight = node.getLeft() == null ? -1 : node.getLeft().getHeight();
        int rightHeight = node.getRight() == null ? -1 : node.getRight().getHeight();
        
        node.setHeight(Math.max(leftHeight, rightHeight) + 1);
        node.setBalanceFactor(leftHeight - rightHeight);
    }
    
    /**
    * Method that rotates a current node to the left. After saving the
    * current's right node to a variable, the right node's left subtree will
    * become the current node's right subtree. The current node will become
    * the right node's left subtree.
    *
    * Don't forget to recalculate the height and balance factor of all
    * affected nodes, using updateHeightAndBF().
    *
    * This method should run in O(1).
    *
    * You may assume that the passed in node is not null and that the subtree
    * starting at that node is right heavy. Therefore, you do not need to
    * perform any preliminary checks, rather, you can immediately perform a
    * left rotation on the passed in node and return the new root of the subtree.
    *
    * This method should only be called in balance().
    *
    * @param currentNode The current node under inspection that will rotate.
    * @return The parent of the node passed in (after the rotation).
    */
    public AVLNode<T> rotateLeft(AVLNode<T> currentNode) {
        // COPY YOUR CODE FROM PART 1 OF THE ASSIGNMENT!
        AVLNode<T> newRoot = currentNode.getRight();
        currentNode.setRight(newRoot.getLeft());
        newRoot.setLeft(currentNode);
        updateHeightAndBF(currentNode);
        updateHeightAndBF(newRoot);
        return newRoot;
    }
    
    /**
    * Method that rotates a current node to the right. After saving the
    * current's left node to a variable, the left node's right subtree will
    * become the current node's left subtree. The current node will become
    * the left node's right subtree.
    *
    * Don't forget to recalculate the height and balance factor of all
    * affected nodes, using updateHeightAndBF().
    *
    * This method should run in O(1).
    *
    * You may assume that the passed in node is not null and that the subtree
    * starting at that node is left heavy. Therefore, you do not need to perform
    * any preliminary checks, rather, you can immediately perform a right
    * rotation on the passed in node and return the new root of the subtree.
    *
    * This method should only be called in balance().
    *
    * @param currentNode The current node under inspection that will rotate.
    * @return The parent of the node passed in (after the rotation).
    */
    public AVLNode<T> rotateRight(AVLNode<T> currentNode) {
        // COPY YOUR CODE FROM PART 1 OF THE ASSIGNMENT!
        AVLNode<T> newRoot = currentNode.getLeft();
        currentNode.setLeft(newRoot.getRight());
        newRoot.setRight(currentNode);
        updateHeightAndBF(currentNode);
        updateHeightAndBF(newRoot);
        return newRoot;
    }
    
    /**
    * Method that balances out the tree starting at the node passed in.
    * This method should be called in your add() and remove() methods to
    * facilitate rebalancing your tree after an operation.
    *
    * The height and balance factor of the current node is first recalculated.
    * Based on the balance factor, a no rotation, a single rotation, or a
    * double rotation takes place. The current node is returned.
    *
    * You may assume that the passed in node is not null. Therefore, you do
    * not need to perform any preliminary checks, rather, you can immediately
    * check to see if any rotations need to be performed.
    *
    * This method should run in O(1).
    *
    * @param currentNode The current node under inspection.
    * @return The AVLNode that the caller should return.
    */
    public AVLNode<T> balance(AVLNode<T> currentNode) {
        // COPY YOUR CODE FROM PART 1 OF THE ASSIGNMENT!
        // First, we update the height and balance factor of the current node.
        updateHeightAndBF(currentNode);
        
        if (currentNode.getBalanceFactor() < -1) { // Condition for a right heavy tree.
            if (currentNode.getRight().getBalanceFactor() > 0) { // Condition for a right-left rotation.
                currentNode.setRight(rotateRight(currentNode.getRight()));
            }
            currentNode = rotateLeft(currentNode);
        } else if (currentNode.getBalanceFactor() > 1) { // Condition for a left heavy tree.
            if (currentNode.getLeft().getBalanceFactor() < 0) { // Condition for a left-right rotation.
                currentNode.setLeft(rotateLeft(currentNode.getLeft()));
            }
            currentNode = rotateRight(currentNode);
        }
        
        return currentNode;
    }
    
    /**
    * Returns the root of the tree.
    *
    * For grading purposes only. You shouldn't need to use this method since
    * you have direct access to the variable.
    *
    * @return The root of the tree.
    */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
    
    /**
    * Returns the size of the tree.
    *
    * For grading purposes only. You shouldn't need to use this method since
    * you have direct access to the variable.
    *
    * @return The size of the tree.
    */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
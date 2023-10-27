import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * This should be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to search for. You may assume data is never null.
     * @return true if the parameter is contained within the tree, false otherwise.
     */
    public boolean contains(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        return rContains(root, data);
    }

    private boolean rContains(BSTNode<T> node, T data) {
        if (node == null) {
            return false;
        }
        else if (data.compareTo(node.getData()) < 0) {
            return rContains(node.getLeft(), data);
        }
        else if (data.compareTo(node.getData()) > 0) {
            return rContains(node.getRight(), data);
        }
        else {
            return true;
        }
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
     * @param data The data to remove. You may assume that data is never null.
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the data is not in the tree.
     */
    public T remove(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        BSTNode<T> holder = new BSTNode<>(null);
        root = rRemove(root, data, holder);
        return holder.getData();
    }

    private BSTNode<T> rRemove(BSTNode<T> node, T data, BSTNode<T> holder) {
        if (node == null) {
            throw new java.util.NoSuchElementException();
        }
        else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(rRemove(node.getLeft(), data, holder));
        }
        else if (data.compareTo(node.getData()) > 0) {
            node.setRight(rRemove(node.getRight(), data, holder));
        }
        else { // Implies data is found at node
            holder.setData(node.getData());
            size--;
            if (node.getLeft() == null && node.getRight() == null) {
                return null; // No children, set node to null
            }
            else if (node.getLeft() != null && node.getRight() == null) {
                return node.getLeft(); // Only left child, promote that
            }
            else if (node.getLeft() == null && node.getRight() != null) {
                return node.getRight(); // Only right child, promote that
            }
            else { // 2 children
                BSTNode<T> successorHolder = new BSTNode<>(null);
                node.setRight(successor(node.getRight(), successorHolder));
                node.setData(successorHolder.getData());
            }
        }
        return node;
    }

    private BSTNode<T> successor(BSTNode<T> node, BSTNode<T> holder) {
        /* Seach left to find successor. Promote its and return it */
        if (node.getLeft() == null) {
            holder.setData(node.getData());
            return node.getRight();
        }
        else {
            node.setLeft(successor(node.getLeft(), holder));
            return node;
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
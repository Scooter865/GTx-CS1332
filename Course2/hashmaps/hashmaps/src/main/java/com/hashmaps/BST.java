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
     * Returns the data from the tree matching the given parameter.
     *
     * This should be done recursively.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to search for. You may assume data is never null.
     * @return The data in the tree equal to the parameter.
     * @throws java.util.NoSuchElementException If the data is not in the tree.
     */
    public T get(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        T ans = searcher(root, data);
        if (ans == null) {
            throw new java.util.NoSuchElementException("Data not in BST");
        }
        else {
            return ans;
        }
    }

    private T searcher(BSTNode<T> node, T data) {
        // Base cases node is null (not found)
        if (node == null) {
            return null;
        }
        // Base case data is found, return data
        if (node.getData().equals(data)) {
            return node.getData();
        }
        // Other cases
        // Search right when data > node data
        else if (data.compareTo(node.getData()) > 0) {
            return searcher(node.getRight(), data);
        }
        // Search left when data < node data
        else if (data.compareTo(node.getData()) < 0) {
            return searcher(node.getLeft(), data);
        }
        // I don't think this will ever be utilized
        else {
            return null;
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
     * 3: The node containing the data has 2 children. Use the PREDECESSOR to
     * replace the data. You should use recursion to find and remove the
     * predecessor (you will likely need an additional helper method to
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
        root = remover(root, data, holder);
        return holder.getData();
    }

    private BSTNode<T> remover(BSTNode<T> node, T data, BSTNode<T> holder) {
        // Base case: data not in tree
        if (node == null) {
            throw new java.util.NoSuchElementException();
        }
        // Traverse left
        else if (data.compareTo(node.getData()) < 0) {
            return remover(node.getLeft(), data, holder);
        }
        // Traverse right
        else if (data.compareTo(node.getData()) > 0) {
            return remover(node.getRight(), data, holder);
        }
        // Found the node
        else {
            // Copy the data into holder and decrement
            holder.setData(node.getData());
            size--;
            // No children - return null
            if ((node.getLeft() == null) && (node.getRight() == null)) {
                return null;
            }
            // Only left child
            else if ((node.getLeft() != null) && (node.getRight() == null)) {
                return node.getLeft();
            }
            // Only right child
            else if ((node.getLeft() == null) && (node.getRight() != null)) {
                return node.getRight();
            }
            // 2 Children - promote predecessor
            else {
                BSTNode<T> predecessorHolder = new BSTNode<>(null);
                node.setLeft(predecessor(node.getLeft(), predecessorHolder));
                node.setData(predecessorHolder.getData());
            }
        }
        return node;
    }

    private BSTNode<T> predecessor(BSTNode<T> node, BSTNode<T> holder2) {
        // No nodes to the right, this is the predecessor
        if (node.getRight() == null) {
            holder2.setData(node.getData());
            return node.getLeft();
        }
        // Nodes to the right, need to get to the end of them
        else {
            node.setRight(predecessor(node.getRight(), holder2));
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
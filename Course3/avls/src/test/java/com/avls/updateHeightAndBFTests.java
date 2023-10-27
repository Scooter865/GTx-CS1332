package com.avls;

import org.junit.Before;
import org.junit.Test;

import org.junit.After;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class updateHeightAndBFTests {
    private AVL<Integer> testTree;
    private AVLNode<Integer> root;

    @Before
    public void createTreeAndNode() {
        testTree = new AVL<>();
        root = new AVLNode<Integer>(10);
        root.setHeight(0);
        root.setBalanceFactor(0);
    }


    @Test
    public void TwochildBalancedTest() {
        root.setLeft(new AVLNode<Integer>(5));
        root.setRight(new AVLNode<Integer>(15));

        testTree.updateHeightAndBF(root);

        assertEquals(1, root.getHeight()); // Root height == 1
        assertEquals(0, root.getBalanceFactor()); // Root BF == 0
    };
    
    @Test
    public void LeftChildBFTest() {
        root.setLeft(new AVLNode<Integer>(5));

        testTree.updateHeightAndBF(root);
        
        assertEquals(1, root.getHeight()); // Root height == 1
        assertEquals(1, root.getBalanceFactor()); // Root BF == 1
    };

    @Test
    public void RightChildBFTest() {
        root.setRight(new AVLNode<Integer>(15));

        testTree.updateHeightAndBF(root);
        
        assertEquals(1, root.getHeight()); // Root height == 1
        assertEquals(-1, root.getBalanceFactor()); // Root BF == -1
    };


    @Test
    public void NoChildrenBFTest() {
        testTree.updateHeightAndBF(root);
        
        assertEquals(0, root.getHeight()); // Root height == 0
        assertEquals(0, root.getBalanceFactor()); // Root BF == 0
    };

    @Test
    public void HighBFTest() {
        root.setLeft(new AVLNode<Integer>(5));
        root.getLeft().setLeft(new AVLNode<Integer>(2));
        root.getLeft().getLeft().setLeft(new AVLNode<Integer>(1));
        
        testTree.updateHeightAndBF(root.getLeft().getLeft().getLeft());
        testTree.updateHeightAndBF(root.getLeft().getLeft());
        testTree.updateHeightAndBF(root.getLeft());
        testTree.updateHeightAndBF(root);
        
        assertEquals(3, root.getHeight()); // Root height == 3
        assertEquals(3, root.getBalanceFactor()); // Root BF == 3
    };
}

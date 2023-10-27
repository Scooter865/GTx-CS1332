package com.avls;

import org.junit.Before;
import org.junit.Test;

import org.junit.After;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class RotateLeftTests {
    private AVL<Integer> testTree;
    private AVLNode<Integer> root;

    @Before
    public void setUp() {
        testTree = new AVL<>();
        root = new AVLNode<Integer>(10);
    }

    @Test
    public void ThreeNodeLeftRotationTest() {
        root.setRight(new AVLNode<Integer>(15));
        root.getRight().setRight(new AVLNode<Integer>(20));
        AVLNode<Integer> endRoot = root.getRight(); // Track the node that will become the root

        testTree.rotateLeft(root);

        assertSame(root, endRoot.getLeft());
    }
}

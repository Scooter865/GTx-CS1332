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

public class RotateRightTests {
    private AVL<Integer> testTree;
    private AVLNode<Integer> root;

    @Before
    public void setUp() {
        testTree = new AVL<>();
        root = new AVLNode<Integer>(10);
    }

    @Test
    public void ThreeNodeRightRotationTest() {
        root.setLeft(new AVLNode<Integer>(5));
        root.getLeft().setLeft(new AVLNode<Integer>(1));
        AVLNode<Integer> endRoot = root.getLeft(); // Track the node that will become the root

        testTree.rotateRight(root);

        assertSame(root, endRoot.getRight());
    }
}

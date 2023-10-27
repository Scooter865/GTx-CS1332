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


public class AddTests {
    private  AVL<Integer> testTree;

    @Before
    public void setUp() {
        testTree = new AVL<>();
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void NullInputTest() {
        testTree.add(10);
        testTree.add(null);
    }

    @Test
    public void AddToEmptyTreeTest() {
        testTree.add(10);

        assertEquals((Integer) 10, testTree.getRoot().getData());
        assertArrayEquals(new Integer[] {10}, testTree.inorder().toArray());
    }
    
    @Test
    public void BuildSmallTreeTest() {
        testTree.add(10);
        testTree.add(5);
        testTree.add(15);

        assertArrayEquals(new Integer[] {5, 10 , 15}, testTree.inorder().toArray());
    }
    
    @Test
    public void SmallRotationTest() {
        testTree.add(10);
        testTree.add(5);
        testTree.add(1);
        
        assertArrayEquals(new Integer[] {1, 5, 10}, testTree.inorder().toArray());
    }

    @Test
    public void AssignmentExampleTest() {
        testTree.add(2);
        testTree.add(0);
        testTree.add(7);
        testTree.add(1);
        testTree.add(4);
        testTree.add(8);
        testTree.add(3);
        testTree.add(6);
        
        testTree.add(5);
        
        assertArrayEquals(new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8}, testTree.inorder().toArray());
    }
}

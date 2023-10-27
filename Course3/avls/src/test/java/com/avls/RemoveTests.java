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


public class RemoveTests {
    private AVL<Integer> testTree;

    @Before
    public void setUp() {
        testTree = new AVL<>();
    }

    @Test
    public void RemoveToEmptyTest() {
        testTree.add(10);
        testTree.remove(10);

        assertArrayEquals(new Integer[] {}, testTree.inorder().toArray());
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void NullInputTest() {
        testTree.add(10);

        testTree.remove(null);
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void DataNotFoundTest() {
        testTree.add(2);
        testTree.add(0);
        testTree.add(4);
        testTree.add(1);
        testTree.add(3);
        testTree.add(6);
        testTree.add(5);

        testTree.remove(10);
    }

    @Test
    public void SmallRemoveTest() {
        testTree.add(10);
        testTree.add(5);
        testTree.add(15);
        testTree.add(1);
        
        testTree.remove(15);

        assertArrayEquals(new Integer[] {1, 5, 10}, testTree.inorder().toArray());
    }

    @Test
    public void SuccessorNoRotationTest() {
        testTree.add(10);
        testTree.add(5);
        testTree.add(15);
        testTree.add(1);
        testTree.add(7);
        testTree.add(12);
        testTree.add(20);
        testTree.add(13);

        testTree.remove(10);

        assertArrayEquals(new Integer[] {1, 5, 7, 12, 13, 15, 20}, testTree.inorder().toArray());
    }

    @Test
    public void SuccessorRotationTest() {
        testTree.add(10);
        testTree.add(5);
        testTree.add(15);
        testTree.add(1);
        testTree.add(7);
        testTree.add(12);
        testTree.add(20);
        testTree.add(3);
        testTree.add(13);
        testTree.add(18);
        testTree.add(22);
        testTree.add(25);
        
        testTree.remove(10);

        assertArrayEquals(new Integer[] {1, 3, 5, 7, 12, 13, 15, 18, 20, 22, 25}, testTree.inorder().toArray());
    }

    @Test
    public void AssignmentExampleTest() {
        testTree.add(2);
        testTree.add(0);
        testTree.add(4);
        testTree.add(1);
        testTree.add(3);
        testTree.add(6);
        testTree.add(5);

        testTree.remove(3);

        assertArrayEquals(new Integer[] {0, 1, 2, 4, 5, 6}, testTree.inorder().toArray());
    }
}

package com.treetraversals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class BSTtest {
    /* This is really hacky because the traversal methods are in a different class than the add and remove.
    And because the traversal methods act on a node, not on a tree like the add/remove do. 
    
    I modified the Traverseals.preorder method to use BSTNode instad of TreeNode because that's what's specified in the BST files.
    They're the same except for the name so hopefully everything works out.
    
    I also modified the BST.root attribute to be public since there's no BST constructor and I need to build a test tree somehow.
    */
    
    private void BuildTestBST(BST<Integer> tree) {
        BSTNode<Integer> ThisRoot = new BSTNode<>(50);
        
        ThisRoot.setLeft(new BSTNode<Integer>(15));
        ThisRoot.getLeft().setLeft(new BSTNode<Integer>(5));
        ThisRoot.getLeft().setRight(new BSTNode<Integer>(25));
        ThisRoot.getLeft().getRight().setLeft(new BSTNode<Integer>(20));
        ThisRoot.setRight(new BSTNode<Integer>(75));
        ThisRoot.getRight().setRight(new BSTNode<Integer>(100));
        
        tree.root = ThisRoot;
    }
    
    private BST<Integer> BSTTestObj = new BST<>();
    private Traversals<Integer> TravTestObj = new Traversals<>();
    
    // Add test: search for element and assert it's not there, run add method, search for element and assert it's there
    @Test
    void addTest() {
        BuildTestBST(BSTTestObj);
        
        List<Integer> preorderList = TravTestObj.preorder(BSTTestObj.root);
        assertFalse(preorderList.contains(10));
        
        BSTTestObj.add(10);
        
        preorderList = TravTestObj.preorder(BSTTestObj.root);
        assertTrue(preorderList.contains(10));
    }
    
    
    /* Remove tests: assert removed data == the thing to be removed
    Assert element is present, run remove method, assert element is not there.
    Test for 0, 1, and 2 child cases */
    @Test
    void Remove0Children() {
        BuildTestBST(BSTTestObj);
        
        List<Integer> preorderList = TravTestObj.preorder(BSTTestObj.root);
        assertTrue(preorderList.contains(20));
        
        int RemovedData = BSTTestObj.remove(20);
        
        preorderList = TravTestObj.preorder(BSTTestObj.root);
        assertEquals(RemovedData, 20);
        assertFalse(preorderList.contains(20));
    }

    @Test
    void Remove1Child() {
        BuildTestBST(BSTTestObj);
        
        List<Integer> preorderList = TravTestObj.preorder(BSTTestObj.root);
        assertTrue(preorderList.contains(75));
        
        int RemovedData = BSTTestObj.remove(75);
        
        preorderList = TravTestObj.preorder(BSTTestObj.root);
        assertEquals(RemovedData, 75);
        assertFalse(preorderList.contains(75));
    }

    @Test
    void Remove2Children() {
        BuildTestBST(BSTTestObj);
        
        List<Integer> preorderList = TravTestObj.preorder(BSTTestObj.root);
        assertTrue(preorderList.contains(15));
        
        int RemovedData = BSTTestObj.remove(15);
        
        preorderList = TravTestObj.preorder(BSTTestObj.root);
        assertEquals(RemovedData, 15);
        assertFalse(preorderList.contains(15));
    }
}

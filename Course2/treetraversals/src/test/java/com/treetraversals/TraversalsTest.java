package com.treetraversals;

import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TraversalsTest {
    private List<Integer> preorderAns = Arrays.asList(50, 25, 10, 100, 75, 125, 110);
    private List<Integer> inorderAns = Arrays.asList(10, 25, 50, 75, 100, 110, 125);
    private List<Integer> postorderAns = Arrays.asList(10, 25, 75, 110, 125, 100, 50);
    
    private TreeNode<Integer> TestTree() {
        TreeNode<Integer> root = new TreeNode<>(50);
        root.setLeft(new TreeNode<Integer>(25));
        root.getLeft().setLeft(new TreeNode<Integer>(10));
        root.setRight(new TreeNode<Integer>(100));
        root.getRight().setLeft(new TreeNode<Integer>(75));
        root.getRight().setRight(new TreeNode<Integer>(125));
        root.getRight().getRight().setLeft(new TreeNode<Integer>(110));
        
        return root;
    }

    private Traversals<Integer> TestObj = new Traversals<>();
    
    
    @Test
    void preorder() {
        List<Integer> guess = TestObj.preorder(TestTree());
        assertEquals(preorderAns, guess);
    }
    
    @Test
    void inorder() {
        assertEquals(inorderAns, TestObj.inorder(TestTree()));
    }
    
    @Test
    void postorder() {
        assertEquals(postorderAns, TestObj.postorder(TestTree()));
    }
}

package com.ulises.trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree {

    class BstNode {
        public int val;
        public BstNode left;
        public BstNode right;
        public BstNode(int val) {
            this.val = val;
        }
    }

    public BstNode getRoot() {
        return root;
    }

    private BstNode root;

    public BstNode searchBST(BstNode root, int val) {
        if (root == null || root.val == val) return root;
        return val < root.val ? searchBST(root.left, val) : searchBST(root.right, val);
    }

    private void insertValue(int val) {
        this.root = insertRec(this.root, val);
    }

    private BstNode insertRec(BstNode root, int val)  {
        if (root == null) {
            root = new BstNode(val);
            return root;
        } else if (val <= root.val) {
            root.left = insertRec(root.left, val);
        } else {
            root.right = insertRec(root.right, val);
        }
        return root;
    }

    public void preOrderRec(BstNode root) {
        if (root != null){
            System.out.println(root.val);
            preOrderRec(root.left);
            preOrderRec(root.right);
        }
    }

    public void preOrderIt(BstNode root) {
        Stack<BstNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            BstNode node = stack.pop();
            System.out.println(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    public void inOrderRec(BstNode root) {
        if (root != null){
            inOrderRec(root.left);
            System.out.println(root.val);
            inOrderRec(root.right);
        }
    }

    public void postOrderRec(BstNode root) {
        if (root != null){
            postOrderRec(root.left);
            postOrderRec(root.right);
            System.out.println(root.val);
        }
    }

    public void bfsIt(BstNode root) {
        Queue<BstNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            BstNode node = queue.poll();
            System.out.println(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insertValue(6);
        tree.insertValue(4);
        tree.insertValue(10);
        tree.insertValue(2);
        tree.insertValue(5);
        tree.insertValue(8);
        tree.insertValue(12);
        tree.inOrderRec(tree.getRoot());
        BSTInorderIterator iterator = new BSTInorderIterator(tree);
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        tree.bfsIt(tree.getRoot());
    }
}

package com.ulises.trees;

import java.util.Iterator;
import java.util.Stack;

public class BSTInorderIterator implements Iterator {

    private Stack<BinarySearchTree.BstNode> stack;

    public BSTInorderIterator(BinarySearchTree tree) {
        this.stack = new Stack<>();
        this.insertLeftBranch(tree.getRoot());
    }

    private void insertLeftBranch(BinarySearchTree.BstNode root) {
        while(root != null) {
            this.stack.push(root);
            root = root.left;
        }
    }

    @Override
    public boolean hasNext() {
        return stack.size() > 0;
    }

    @Override
    public Object next() {
        BinarySearchTree.BstNode node = stack.pop();
        insertLeftBranch(node.right);
        return node.val;
    }
}

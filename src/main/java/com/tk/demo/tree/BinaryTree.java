package com.tk.demo.tree;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

//二叉树
public class BinaryTree {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1, "a");
        TreeNode node2 = new TreeNode(2, "b");
        TreeNode node3 = new TreeNode(3, "c");
        TreeNode node4 = new TreeNode(4, "d");
        TreeNode node5 = new TreeNode(5, "e");
        TreeNode node6 = new TreeNode(6, "f");
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        BinaryTree binaryTree = new BinaryTree();
        System.out.println("\npreorder:");
        binaryTree.preorder(node1);
        System.out.println("\npreorder:");
        binaryTree.inorder(node1);
        System.out.println("\npreorder:");
        binaryTree.postorder(node1);
    }

    //前序遍历二叉树
    public void preorder(TreeNode node) {
        System.out.print(node.getId() + ",");
        if (node.getLeft() != null) {
            preorder(node.getLeft());
        }
        if (node.getRight() != null) {
            preorder(node.getRight());
        }
    }

    //中序遍历二叉树
    public void inorder(TreeNode node) {
        if (node.getLeft() != null) {
            inorder(node.getLeft());
        }
        System.out.print(node.getId() + ",");
        if (node.getRight() != null) {
            inorder(node.getRight());
        }
    }

    //后序遍历二叉树
    public void postorder(TreeNode node) {
        if (node.getLeft() != null) {
            postorder(node.getLeft());
        }
        System.out.print(node.getId() + ",");
        if (node.getRight() != null) {
            postorder(node.getRight());
        }
    }
}


package com.tk.demo.tree;

//二叉树
public class BinaryTree {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1, "a");
        TreeNode node2 = new TreeNode(2, "b");
        TreeNode node3 = new TreeNode(3, "c");
        TreeNode node4 = new TreeNode(4, "d");
        TreeNode node5 = new TreeNode(5, "e");
        TreeNode node6 = new TreeNode(6, "f");
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;

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
        System.out.print(node.id + ",");
        if (node.left != null) {
            preorder(node.left);
        }
        if (node.right != null) {
            preorder(node.right);
        }
    }

    //中序遍历二叉树
    public void inorder(TreeNode node) {
        if (node.left != null) {
            inorder(node.left);
        }
        System.out.print(node.id + ",");
        if (node.right != null) {
            inorder(node.right);
        }
    }

    //后序遍历二叉树
    public void postorder(TreeNode node) {
        if (node.left != null) {
            postorder(node.left);
        }
        System.out.print(node.id + ",");
        if (node.right != null) {
            postorder(node.right);
        }
    }
}


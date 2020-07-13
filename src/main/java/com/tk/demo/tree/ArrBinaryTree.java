package com.tk.demo.tree;

//顺序二叉树
class ArrBinaryTree {
    /*
        存储方式可以在数组和树间互相转换
        顺序二叉树只考虑完全二叉树
        第n个元素左子节点为2*n+1
        第n个元素右子节点为2*n+2
        第n个元素父节点为(n-1)/2
    */
    private final int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preorder();
        arrBinaryTree.inorder();
        arrBinaryTree.postorder();
    }

    //前序遍历顺序二叉树
    public void preorder() {
        System.out.print("\npreorder:");
        preorder(0);
    }

    public void preorder(int index) {
        if (arr == null || arr.length == 0) return;

        System.out.print(arr[index] + ",");

        if (2 * index + 1 < arr.length) {
            preorder(2 * index + 1);
        }
        if (2 * index + 2 < arr.length) {
            preorder(2 * index + 2);
        }

    }

    //中序遍历顺序二叉树
    public void inorder() {
        System.out.print("\ninorder:");
        inorder(0);
    }

    public void inorder(int index) {
        if (arr == null || arr.length == 0) return;

        if (2 * index + 1 < arr.length) {
            inorder(2 * index + 1);
        }

        System.out.print(arr[index] + ",");

        if (2 * index + 2 < arr.length) {
            inorder(2 * index + 2);
        }

    }

    //后序遍历顺序二叉树
    public void postorder() {
        System.out.print("\npostorder:");
        postorder(0);
    }

    public void postorder(int index) {
        if (arr == null || arr.length == 0) return;

        if (2 * index + 1 < arr.length) {
            postorder(2 * index + 1);
        }

        if (2 * index + 2 < arr.length) {
            postorder(2 * index + 2);
        }

        System.out.print(arr[index] + ",");

    }
}

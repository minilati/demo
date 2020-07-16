package com.tk.demo.tree;

import java.util.Stack;

//线索化二叉树
class ThreadedBinaryTree {
    private TreeNode pre = null;

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1, "a");
        TreeNode node2 = new TreeNode(2, "b");
        TreeNode node3 = new TreeNode(3, "c");
        TreeNode node4 = new TreeNode(4, "d");
        TreeNode node5 = new TreeNode(5, "e");
        TreeNode node6 = new TreeNode(6, "f");
        TreeNode node7 = new TreeNode(7, "g");
        TreeNode node8 = new TreeNode(8, "h");
        TreeNode node9 = new TreeNode(9, "i");
        TreeNode node10 = new TreeNode(10, "j");
        TreeNode node11 = new TreeNode(11, "k");
        TreeNode node12 = new TreeNode(12, "l");
        node1.left=(node2);
        node1.right=(node3);
        node2.left=(node4);
        node2.right=(node5);
        node3.left=(node6);
        node3.right=(node7);
        node4.left=(node8);
        node4.right=(node9);
        node5.left=(node10);
        node5.right=(node11);
        node6.left=(node12);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        //threadedBinaryTree.preThreaded(node1);
        //threadedBinaryTree.preThreadedList(node1);
        //threadedBinaryTree.inThreaded(node1);
        //threadedBinaryTree.inThreadedList(node1);
        threadedBinaryTree.postThreaded(node1);
        threadedBinaryTree.postThreadedList(node1);
    }

    //前序线索化
    public void preThreaded(TreeNode node) {
        if (node == null) return;
        //线索化当前节点
        if (node.left == null) {
            node.left=(pre);
            node.leftType=1;
        }
        if (pre != null && pre.right == null) {
            pre.right=(node);
            pre.rightType=1;
        }
        //线索完当前节点后，将该节点置为前驱节点
        pre = node;
        //线索化左子树
        if (node.leftType == 0) {
            preThreaded(node.left);
        }
        //线索化右子树
        if (node.rightType == 0) {
            preThreaded(node.right);
        }
    }

    //前序遍历线索化二叉树
    public void preThreadedList(TreeNode node) {
        System.out.print("\npreThreaded:");
        while (node != null) {

            System.out.print(node.id + ",");
            //遍历直到找到线索化节点
            while (node.leftType == 0) {
                node = node.left;
                System.out.print(node.id + ",");
            }
            //输出后续所有线索化节点
            while (node.rightType == 1) {
                node = node.right;
                System.out.print(node.id + ",");
            }
            //指向下一个左子节点（最后一个节点则停止）
            node = node.leftType == 0 ? node.left : null;
        }
    }

    //中序线索化
    public void inThreaded(TreeNode node) {
        if (node == null) return;
        //线索化左子树
        inThreaded(node.left);
        //线索化当前节点
        if (node.left == null) {
            node.left=(pre);
            node.leftType=1;
        }
        if (pre != null && pre.right == null) {
            pre.right=(node);
            pre.rightType=1;
        }
        //线索完当前节点后，将该节点置为前驱节点
        pre = node;
        //线索化右子树
        inThreaded(node.right);
    }

    //中序遍历线索化二叉树
    public void inThreadedList(TreeNode node) {
        System.out.print("\ninThreaded:");
        while (node != null) {
            //找到线索化节点
            while (node.leftType == 0) {
                node = node.left;
            }
            System.out.print(node.id + ",");
            //输出后续所有线索化节点
            while (node.rightType == 1) {
                node = node.right;
                System.out.print(node.id + ",");
            }
            node = node.right;
        }
    }

    //后序线索化
    public void postThreaded(TreeNode node) {
        if (node == null) return;

        //线索化左子树
        postThreaded(node.left);
        //线索化右子树
        postThreaded(node.right);

        //线索化当前节点
        if (node.left == null) {
            node.left=(pre);
            node.leftType=1;
        }
        if (pre != null && pre.right == null) {
            pre.right=(node);
            pre.rightType=1;
        }
        //线索完当前节点后，将该节点置为前驱节点
        pre = node;
    }

    //后序遍历线索化二叉树
    public void postThreadedList(TreeNode node) {
        System.out.print("\npostThreaded:");
        Stack<Integer> stack = new Stack<>();
        while (node != null) {
            //倒序从最右开始找
            //遍历直到找到线索化节点
            while (node.rightType == 0) {
                //System.out.print(node.id + ",");
                stack.push(node.id);
                node = node.right;
            }
            //System.out.print(node.id + ",");
            stack.push(node.id);
            //输出后续所有线索化节点
            while (node.leftType == 1 && node.left != null) {
                node = node.left;
                //System.out.print(node.id + ",");
                stack.push(node.id);
            }
            if (node.rightType == 1) {
                node = node.left;
            } else if (node.rightType == 0) {
                node = node.right;
            }
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + ",");
        }

    }
}

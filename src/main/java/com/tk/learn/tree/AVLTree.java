package com.tk.learn.tree;

public class AVLTree {
    private Node root;

    public static void main(String[] args) {
        //int[] arr = {4,3,6,5,7,8};
        //int[] arr = {10,12,8,9,7,6};
        int[] arr = {10,11,7,6,8,9};
        AVLTree avlTree = new AVLTree();
        //循环添加结点
        for (int value : arr) {
            avlTree.add(new Node(value));
        }
        avlTree.infixOrder();
        System.out.println("\n树高:"+avlTree.root.height()+"，左子树高:"+avlTree.root.leftHeight()+"，右子树高:"+avlTree.root.rightHeight());
    }

    //删除结点
    public void delNode(int value) {
        if (root == null) return;

        //先找到要删除的结点targetNode
        Node targetNode = root.search(value);
        if (targetNode == null) return;

        //如果我们发现当前这颗二叉排序树只有一个结点
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }

        //去找到targetNode的父结点
        Node parent = root.searchParent(value);

        if (targetNode.left == null && targetNode.right == null) {//删除叶子结点，操作其父结点
            if (parent.left != null && parent.left.value == value) {
                parent.left = null;
            } else if (parent.right != null && parent.right.value == value) {
                parent.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) {//删除有两颗子树的节点
            //targetNode.value = findRightMin(targetNode);
            targetNode.value = findLeftMax(targetNode);
        } else {//删除只有一颗子树的结点
            if (targetNode.left != null) {
                if (parent != null) {
                    if (parent.left.value == value) {
                        parent.left = targetNode.left;
                    } else {
                        parent.right = targetNode.left;
                    }
                } else {
                    root = targetNode.left;
                }
            } else {
                if (parent != null) {
                    if (parent.left.value == value) {
                        parent.left = targetNode.right;
                    } else {
                        parent.right = targetNode.right;
                    }
                } else {
                    root = targetNode.right;
                }
            }
        }
    }

    //找到左子树最小节点移动到目标结点位置
    public int findRightMin(Node node) {
        node = node.right;
        while (node.left != null) {
            node = node.left;
        }
        delNode(node.value);
        return node.value;
    }

    //找到左子树最小节点移动到目标结点位置
    public int findLeftMax(Node node) {
        node = node.left;
        while (node.right != null) {
            node = node.right;
        }
        delNode(node.value);
        return node.value;
    }

    //添加结点的方法
    public void add(Node node) {
        if (root == null) {
            root = node;//如果root为空则直接让root指向node
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            System.out.print("中序遍历:");
            root.infixOrder();
        }
    }
}

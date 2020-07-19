package com.tk.learn.tree;

public class BinarySortTree {
    private Node root;

    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加结点
        for (int value : arr) {
            binarySortTree.add(new Node(value));
        }

        //中序遍历二叉排序树
        binarySortTree.infixOrder();// 1, 3, 5, 7, 9, 10, 12

        //测试一下删除叶子结点
        binarySortTree.delNode(12);
        binarySortTree.delNode(5);
        binarySortTree.delNode(10);
        binarySortTree.delNode(2);
        binarySortTree.delNode(3);
        binarySortTree.delNode(9);
        binarySortTree.delNode(1);
        binarySortTree.delNode(7);

        System.out.println("\n删除结点后");
        binarySortTree.infixOrder();
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


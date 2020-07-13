package com.tk.demo;


import java.util.*;

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
        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);
        node4.setLeft(node8);
        node4.setRight(node9);
        node5.setLeft(node10);
        node5.setRight(node11);
        node6.setLeft(node12);

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
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        //线索完当前节点后，将该节点置为前驱节点
        pre = node;
        //线索化左子树
        if (node.getLeftType() == 0) {
            preThreaded(node.getLeft());
        }
        //线索化右子树
        if (node.getRightType() == 0) {
            preThreaded(node.getRight());
        }
    }

    //前序遍历线索化二叉树
    public void preThreadedList(TreeNode node) {
        System.out.print("\npreThreaded:");
        while (node != null) {

            System.out.print(node.getId() + ",");
            //遍历直到找到线索化节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
                System.out.print(node.getId() + ",");
            }
            //输出后续所有线索化节点
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.print(node.getId() + ",");
            }
            //指向下一个左子节点（最后一个节点则停止）
            node = node.getLeftType() == 0 ? node.getLeft() : null;
        }
    }

    //中序线索化
    public void inThreaded(TreeNode node) {
        if (node == null) return;
        //线索化左子树
        inThreaded(node.getLeft());
        //线索化当前节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        //线索完当前节点后，将该节点置为前驱节点
        pre = node;
        //线索化右子树
        inThreaded(node.getRight());
    }

    //中序遍历线索化二叉树
    public void inThreadedList(TreeNode node) {
        System.out.print("\ninThreaded:");
        while (node != null) {
            //找到线索化节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.print(node.getId() + ",");
            //输出后续所有线索化节点
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.print(node.getId() + ",");
            }
            node = node.getRight();
        }
    }

    //后序线索化
    public void postThreaded(TreeNode node) {
        if (node == null) return;

        //线索化左子树
        postThreaded(node.getLeft());
        //线索化右子树
        postThreaded(node.getRight());

        //线索化当前节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
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
            while (node.getRightType() == 0) {
                //System.out.print(node.getId() + ",");
                stack.push(node.getId());
                node = node.getRight();
            }
            //System.out.print(node.getId() + ",");
            stack.push(node.getId());
            //输出后续所有线索化节点
            while (node.getLeftType() == 1 && node.getLeft() != null) {
                node = node.getLeft();
                //System.out.print(node.getId() + ",");
                stack.push(node.getId());
            }
            if (node.getRightType() == 1) {
                node = node.getLeft();
            } else if (node.getRightType() == 0) {
                node = node.getRight();
            }
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + ",");
        }

    }
}

//树节点
class TreeNode {
    private int id;
    private String name;
    private TreeNode left;
    private TreeNode right;
    private int leftType;   //默认为0指向左子节点，为1则指向前驱节点
    private int rightType;  //默认为0指向右子节点，为1则指向后继节点

    public TreeNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "TreeNode[id=" + id + ", name=" + name + "]";
    }
}

//赫夫曼树
class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        //int[] arr = {1,1,1,1,1,1,1,1,1,1};
        createHuffmanTree(arr);
    }

    //创建哈夫曼树并前序遍历输出
    public static void createHuffmanTree(int[] arr) {
        List<Node> nodes = new ArrayList<>();
        for (int a : arr) {
            nodes.add(new Node(a));
        }
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        nodes.get(0).preorder();
    }
}

class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //前序遍历
    public void preorder() {
        System.out.print(this.value+",");
        if (this.left != null) {
            this.left.preorder();
        }
        if (this.right != null) {
            this.right.preorder();
        }
    }

    @Override
    public String toString() {
        return "Node[value=" + value + "]";
    }

    @Override
    public int compareTo(Node node) {
        //从小到大排序
        return this.value - node.value;
    }
}
package com.tk.demo.tree;

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

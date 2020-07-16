package com.tk.demo.tree;

//树节点
class TreeNode {
    public int id;
    public String name;
    public TreeNode left;
    public TreeNode right;
    public int leftType;   //默认为0指向左子节点，为1则指向前驱节点
    public int rightType;  //默认为0指向右子节点，为1则指向后继节点

    public TreeNode(int id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public String toString() {
        return "TreeNode[id=" + id + ", name=" + name + "]";
    }
}

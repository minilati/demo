package com.tk.demo.tree;

class HNode implements Comparable<HNode> {
    Byte data;
    int value;
    HNode left;
    HNode right;

    public HNode(int value) {
        this.value = value;
    }

    public HNode(Byte data, int value) {
        this.data = data;
        this.value = value;
    }

    //前序遍历
    public void preorder() {
        //System.out.print(this.value + ",");
        System.out.println(this);
        if (this.left != null) {
            this.left.preorder();
        }
        if (this.right != null) {
            this.right.preorder();
        }
    }

    @Override
    public String toString() {
        return "Node[data=" + data + " value=" + value + "]";
    }

    @Override
    public int compareTo(HNode node) {
        //从小到大排序
        return this.value - node.value;
    }
}

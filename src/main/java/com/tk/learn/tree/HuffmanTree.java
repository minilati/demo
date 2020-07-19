package com.tk.learn.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//赫夫曼树
class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        createHuffmanTree(arr);
    }

    //创建哈夫曼树并前序遍历输出
    public static void createHuffmanTree(int[] arr) {
        List<HNode> nodes = new ArrayList<>();
        for (int a : arr) {
            nodes.add(new HNode(a));
        }
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            HNode leftNode = nodes.get(0);
            HNode rightNode = nodes.get(1);
            HNode parent = new HNode(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        nodes.get(0).preorder();
    }
}

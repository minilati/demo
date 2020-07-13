package com.tk.demo.tree;

import java.util.*;

public class HuffmanCode {
    static Map<Byte, String> huffmanCodes = new HashMap<>();

    public static void main(String[] args) {
        //String content = "i am so alone i am try to find my way back home to you";
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println("原始长度：" + contentBytes.length);

        byte[] bytes = huffmanZip(contentBytes);

        System.out.println(Arrays.toString(bytes));
        System.out.println("压缩长度：" + bytes.length);
    }

    //压缩封装
    private static byte[] huffmanZip(byte[] bytes) {
        //生成赫夫曼树并返回根节点
        HNode root = createHuffmanTree(bytes);
        //生成每个叶子节点的赫夫曼编码
        getCodes(root);
        //根据赫夫曼编码对原始数据进行压缩
        return zip(bytes);
    }

    //生成赫夫曼树并返回根节点
    private static HNode createHuffmanTree(byte[] bytes) {
        List<HNode> nodes = new ArrayList<>();
        HashMap<Byte, Integer> byteCounts = new HashMap<>();
        //注入各字符出现次数
        for (byte b : bytes) {
            byteCounts.merge(b, 1, Integer::sum);
        }
        //取出并生成HNode节点加入集合
        for (Map.Entry<Byte, Integer> entry : byteCounts.entrySet()) {
            nodes.add(new HNode(entry.getKey(), entry.getValue()));
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
        return nodes.get(0);
    }

    //生成每个叶子节点的赫夫曼编码
    private static void getCodes(HNode node) {
        if (node == null) return;
        getCodes(node.left, "0", new StringBuilder());
        getCodes(node.right, "1", new StringBuilder());
    }

    private static void getCodes(HNode node, String code, StringBuilder s) {
        StringBuilder sb = new StringBuilder(s);
        if (node == null) return;
        sb.append(code);
        if (node.data == null) {
            getCodes(node.left, "0", sb);
            getCodes(node.right, "1", sb);
        } else {
            huffmanCodes.put(node.data, sb.toString());
        }
    }

    //根据赫夫曼编码对原始数据进行压缩
    private static byte[] zip(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(huffmanCodes.get(b));
        }
        //8位一字节，字节长度需放下所有位
        int len = (sb.length() + 7) / 8;
        byte[] zipBytes = new byte[len];
        for (int i = 0, index = 0; i < sb.length(); i += 8, index++) {
            String strByte = (i + 8) < sb.length() ? sb.substring(i, i + 8) : sb.substring(i);
            zipBytes[index] = (byte)Integer.parseInt(strByte,2);
        }
        return zipBytes;
    }

}

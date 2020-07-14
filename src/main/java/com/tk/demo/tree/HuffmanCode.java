package com.tk.demo.tree;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    static Map<Byte, String> huffmanCodes = new HashMap<>();

    public static void main(String[] args) {
        //字符串的压缩解压
        /*{
            String content = "I am so alone~~~I am try to find my way back home to you!!!";
            byte[] contentBytes = content.getBytes();
            System.out.println("原始长度：" + contentBytes.length);
            byte[] bytes = huffmanZip(contentBytes);
            System.out.println("压缩长度：" + bytes.length);
            byte[] decode = huffmanUnzip(bytes);
            System.out.println("解码输出：" + new String(decode));
        }*/
        //压缩文件
        {
            String srcFile = "C://Users/Administrator/Pictures/v1.mp4";
            String dstFile = "C://Users/Administrator/Pictures/test.zip";
            zipFile(srcFile, dstFile);
        }
        //解压文件
        {
            String zipFile = "C://Users/Administrator/Pictures/test.zip";
            String dstFile = "C://Users/Administrator/Pictures/00.mp4";
            unzipFile(zipFile,dstFile);
        }
    }

    //解压文件
    public static void unzipFile(String zipFile, String dstFile) {
        FileInputStream is = null;
        FileOutputStream os = null;
        ObjectInputStream ois = null;
        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            byte[] zipBytes = (byte[]) ois.readObject();
            huffmanCodes = (Map<Byte, String>) ois.readObject();
            byte[] b = huffmanUnzip(zipBytes);

            os = new FileOutputStream(dstFile);
            os.write(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) ois.close();
                if (os != null) os.close();
                if (is != null) is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("解压完成！");
    }

    //压缩文件
    public static void zipFile(String srcFile, String dstFile) {
        FileInputStream is = null;
        FileOutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            is = new FileInputStream(srcFile);
            byte[] b = new byte[is.available()];
            is.read(b);

            byte[] zipBytes = huffmanZip(b);
            os = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(os);
            oos.writeObject(zipBytes);
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) oos.close();
                if (os != null) os.close();
                if (is != null) is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("压缩完成！");
    }

    //解压
    private static byte[] huffmanUnzip(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        int end = -bytes[0];
        //字节数组生成二进制字符串
        for (int i = 1; i < bytes.length; i++) {
            int b = bytes[i];
            boolean flag = (i == bytes.length - 1) && b > 0 && end != 0;//最后一位为正数需要特殊处理，其余情况都需补位
            StringBuilder s;
            if (!flag) {
                s = new StringBuilder(Integer.toBinaryString(b | 256));
            } else {
                s = new StringBuilder(Integer.toBinaryString(b));
                for (int j = s.length(); j < end; j++) {
                    s.insert(0, "0");
                }
            }
            sb.append(flag ? s.toString() : (s.substring(s.length() - 8)));
        }

        //解析二进制字符串，生成对应顺序的字节数组
        List<Byte> list = new ArrayList<>();
        HashMap<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        for (int i = 0, j = 1; j <= sb.length(); ) {
            Byte val = map.get(sb.substring(i, j));
            if (val != null) {
                list.add(val);
                i = j;
            }
            j++;
        }
        byte[] arr = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }

        return arr;
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
        Map<Byte, Integer> byteCounts = new HashMap<>();
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
        int len = (sb.length() + 7) / 8 + 1;
        byte[] zipBytes = new byte[len];
        zipBytes[0] = 0;
        for (int i = 0, index = 1; i < sb.length(); i += 8, index++) {
            String strByte;
            if ((i + 8) <= sb.length()) {
                strByte = sb.substring(i, i + 8);
            } else {
                //第0位存放最后一组字节长度
                zipBytes[0] = (byte) (i - sb.length());
                strByte = sb.substring(i);
            }
            zipBytes[index] = (byte) Integer.parseInt(strByte, 2);
        }
        return zipBytes;
    }
}

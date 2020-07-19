package com.tk.learn.algorithm;

import java.util.ArrayList;
import java.util.Arrays;

public class Kruskal {
    int edge;//边数
    char[] vertex;//顶点数组
    int[][] matrix;//连接矩阵
    static final int N = 100;

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //克鲁斯卡尔算法的邻接矩阵
        int[][] matrix = {
                /////*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, N, N, N, 16, 14},
                /*B*/ {12, 0, 10, N, N, 7, N},
                /*C*/ {N, 10, 0, 3, 5, 6, N},
                /*D*/ {N, N, 3, 0, 4, N, N},
                /*E*/ {N, N, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, N, 2, 0, 9},
                /*G*/ {14, N, N, N, 8, 9, 0}};
        Kruskal kruskal = new Kruskal(vertex, matrix);
        kruskal.show();
        kruskal.kruskal();
    }


    public void kruskal() {
        int[] ends = new int[edge];
        ArrayList<EData> result = new ArrayList<>();

        EData[] edges = getEdges();
        Arrays.sort(edges);

        for (EData edge : edges) {
            int p1 = getPosition(edge.start);
            int p2 = getPosition(edge.end);

            int n1 = getEnd(ends, p1);
            int n2 = getEnd(ends, p2);
            if (n1 != n2) {
                ends[n1] = n2;
                result.add(edge);
            }
        }
        for (EData edge : result) {
            if (edge != null) System.out.println(edge);
        }
    }

    //获得以下标为i的点的终点
    public int getEnd(int[] end, int i) {
        while (end[i] != 0) {
            i = end[i];
        }
        return i;
    }

    private int getPosition(char start) {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == start) {//找到
                return i;
            }
        }
        return -1;
    }

    private EData[] getEdges() {
        EData[] edges = new EData[edge];
        int index = 0;
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if (matrix[i][j] != N) {
                    edges[index++] = new EData(vertex[i], vertex[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    public void show() {
        System.out.println("邻接矩阵为:");
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public Kruskal(char[] vertex, int[][] matrix) {
        int len = vertex.length;
        this.vertex = vertex;
        this.matrix = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (this.matrix[i][j] != N) {
                    edge++;
                }
            }
        }
    }
}

class EData implements Comparable<EData> {
    char start;
    char end;
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return start + " -- " + end + " : " + weight;
    }

    @Override
    public int compareTo(EData edata) {
        return this.weight - edata.weight;
    }
}

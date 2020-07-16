package com.tk.demo.algorithm;

import java.util.Arrays;

class Prim {
    int num;//表示图的节点个数
    char[] data;//存放结点数据
    int[][] weight; //存放边，就是我们的邻接矩阵

    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };
        Prim graph = new Prim(data, weight);
        graph.show();
        graph.prim(0);
    }

    public Prim(char[] data, int[][] weight) {
        this.data = data;
        this.weight = weight;
        this.num = data.length;
    }

    public void show() {
        for (int[] node : this.weight) {
            System.out.println(Arrays.toString(node));
        }
    }

    //Prim算法
    public void prim(int i) {
        boolean[] visited = new boolean[num];
        visited[i] = true;//表示该节点已访问
        int i1 = -1;
        int i2 = -1;

        int minWeight = 10000;
        for (int j = 1; j < num; j++) {
            for (int k = 0; k < num; k++) {
                for (int l = 0; visited[k] && l < num; l++) {
                    if (!visited[l] && minWeight > weight[k][l]) {
                        minWeight = weight[k][l];
                        i1 = k;
                        i2 = l;
                    }
                }
            }
            System.out.println(data[i1] + " -- " + data[i2] + " : " + minWeight);
            visited[i2] = true;
            minWeight = 10000;
        }
    }
}
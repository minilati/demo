package com.tk.learn.algorithm;

import java.util.Arrays;

class Prim {
    int len;//表示图的节点个数
    char[] vertex;//存放结点数据
    int[][] matrix; //存放边，就是我们的邻接矩阵
    static final int N = 10000;

    public static void main(String[] args) {
        char[] vertex = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[][]{
                {N, 5, 7, N, N, N, 2},
                {5, N, N, 9, N, N, 3},
                {7, N, N, N, 8, N, N},
                {N, 9, N, N, N, 4, N},
                {N, N, 8, N, N, 5, 4},
                {N, N, N, 4, 5, N, 6},
                {2, 3, N, N, 4, 6, N}};
        Prim graph = new Prim(vertex, matrix);

        graph.show();
        graph.prim(0);
    }

    public Prim(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
        this.len = vertex.length;
    }

    public void show() {
        for (int[] node : matrix) {
            System.out.println(Arrays.toString(node));
        }
    }

    //Prim算法
    public void prim(int i) {
        boolean[] visited = new boolean[len];
        visited[i] = true;//表示该节点已访问
        int i1 = -1;
        int i2 = -1;

        int min = N;
        for (int j = 1; j < len; j++) {//最终有len-1条边
            for (int k = 0; k < len; k++) {
                for (int l = 0; visited[k] && l < len; l++) {
                    if (!visited[l] && matrix[k][l] < min) {
                        min = matrix[k][l];
                        i1 = k;
                        i2 = l;
                    }
                }
            }
            System.out.println(vertex[i1] + " -- " + vertex[i2] + " : " + min);
            visited[i2] = true;
            min = N;
        }
    }
}
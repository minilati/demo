package com.tk.learn.algorithm;

import java.util.Arrays;

public class Floyd {
    int len;            //长度喽
    char[] vertex;      //顶点数组
    int[][] matrix;     //邻接矩阵
    int[][] distance;   //记录每个点到另一个点的距离
    char[][] pre;        //记录每个点的前驱节点
    final static int N = 1000;// 表示不可以连接

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

        Floyd floyd = new Floyd(vertex, matrix);
        floyd.floyd();
    }

    public void floyd() {
        int d;
        for (int m = 0; m < len; m++) {//把每个节点作为中间节点(公共前驱节点)来遍历
            for (int i = 0; i < len; i++) {//起点
                for (int j = 0; j < len; j++) {//终点
                    d = distance[i][m] + distance[m][j];
                    if (d < distance[i][j]) {
                        distance[i][j] = d;
                        pre[i][j] = pre[m][j];
                    }
                }
            }
        }
        show();
    }

    public void show(){
        for (int i = 0;i<len;i++) {
            System.out.println(Arrays.toString(pre[i]));
            System.out.println(Arrays.toString(distance[i])+"\n");
        }
    }

    public Floyd(char[] vertex, int[][] matrix) {
        this.len = vertex.length;
        this.vertex = vertex;
        this.matrix = matrix;
        this.distance = matrix;
        this.pre = new char[len][len];
        for (int i = 0; i < len; i++) {
            Arrays.fill(pre[i], vertex[i]);
        }
    }

}

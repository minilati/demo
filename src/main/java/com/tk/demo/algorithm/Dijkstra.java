package com.tk.demo.algorithm;

import sun.security.util.Length;

import java.util.Arrays;

public class Dijkstra {
    int len;            //长度喽
    char[] vertex;      //顶点数组
    int[][] matrix;     //邻接矩阵

    int[] distance;     //记录每个点到起始点的距离
    boolean[] visited;  //记录每个点的访问情况
    int[] pre;          //记录每个点的前驱节点
    final static int N = 1000;// 表示不可以连接

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};

        Dijkstra dijkstra = new Dijkstra(vertex, matrix);
        dijkstra.dijkstra(2);
        dijkstra.showArr();
    }

    public void dijkstra(int index) {
        distance[index] = 0;
        getDistance(index);
        for (int i = 0; i < len; i++) {
            int next = nextMin();
            getDistance(next);
        }
    }

    //从待选节点(已经有前驱节点)中选出距离最短且未被访问过的节点
    public int nextMin() {
        int min = N;
        int index = 0;
        for (int i = 0; i < len; i++) {
            if (!visited[i] && distance[i] < min) {
                min = distance[i];
                index = i;
            }
        }
        return index;
    }

    //更新到各点的最新距离
    public void getDistance(int index) {
        visited[index] = true;
        for (int j = 0; j < vertex.length; j++) {
            int d = matrix[index][j] + distance[index];
            if (!visited[j] && d < distance[j]) {
                distance[j] = d;
                pre[j] = index;
            }
        }
    }

    public void showArr() {
        System.out.println("distance" + Arrays.toString(distance));
        System.out.println("visited" + Arrays.toString(visited));
        System.out.println("pre" + Arrays.toString(pre));
        for (int i = 0; i < len; i++) {
            if (distance[i] != N) {
                System.out.print(vertex[i] + "(" + distance[i] + ")--");
            } else {
                System.out.print(vertex[i] + "(N)");
            }
        }
        System.out.println();
    }

    public Dijkstra(char[] vertex, int[][] matrix) {
        this.len = vertex.length;
        this.vertex = vertex;
        this.matrix = matrix;
        this.distance = new int[len];
        Arrays.fill(distance, 1000);
        this.visited = new boolean[len];
        this.pre = new int[len];
    }
}
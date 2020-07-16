package com.tk.demo.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Graph {
    private List<String> vertexList;
    private int[][] edges;
    private int edgeNum;
    private boolean[] visited;

    public static void main(String[] args) {
        String[] sArr = {"1", "2", "3", "4", "5", "6", "7", "8"};
        Graph graph = new Graph(sArr.length);
        graph.vertexList.addAll(Arrays.asList(sArr));
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);
        graph.showGraph();

        graph.dfs();
        graph.bfs();

    }

    //深度优先遍历算法,考虑到不连通图
    public void dfs() {
        System.out.print("\n深度优先：");
        for (int i = 0; i < vertexList.size(); i++) {
            if (!visited[i]) dfs(visited, i);
        }
        visited = new boolean[visited.length];
    }
    private void dfs(boolean[] visited, int i) {
        System.out.print(vertexList.get(i) + "->");
        visited[i] = true;
        int j = getFirst(i);
        while (j != -1) {
            if (!visited[j]) {
                dfs(visited, j);
            } else {
                j = getNext(i, j);
            }
        }
    }

    //广度优先遍历算法,考虑到不连通图
    public void bfs(){
        System.out.print("\n广度优先：");
        for (int i = 0; i < vertexList.size(); i++) {
            if (!visited[i]) bfs(visited, i);
        }
        visited = new boolean[visited.length];
    }
    private void bfs(boolean[] visited, int i){
        int first;
        int next;
        LinkedList<Integer> queue = new LinkedList<>();
        System.out.print(vertexList.get(i) + "->");
        visited[i] = true;
        queue.add(i);
        while(!queue.isEmpty()){
            first = queue.removeFirst();
            next = getFirst(first);
            while(next!=-1){
                if (!visited[next]) {
                    System.out.print(vertexList.get(next) + "->");
                    visited[next] = true;
                    queue.add(next);
                } else {
                    next = getNext(first, next);
                }
            }
        }
    }

    //获取该节点的第一个联结节点
    public int getFirst(int i) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[i][j] == 1)
                return j;
        }
        return -1;
    }

    //获取该节点已联结节点的下一个联结节点
    public int getNext(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] == 1)
                return i;
        }
        return -1;
    }

    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        visited = new boolean[n];
    }

    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        edgeNum++;
    }

    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

}

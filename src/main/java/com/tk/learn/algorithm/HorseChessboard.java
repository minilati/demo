package com.tk.learn.algorithm;

import java.util.*;

public class HorseChessboard {
    int X;//棋盘列
    int Y;//棋盘行
    int[][] chessboard;//表示各位置走第几步
    boolean[][] visited;
    boolean finished;

    public static void main(String[] args) {
        HorseChessboard board = new HorseChessboard(8, 8);

        long start = System.currentTimeMillis();

        board.runHorse(0, 0, 1);

        long end = System.currentTimeMillis();

        System.out.println("花费时间：" + (end - start));

        for (int[] b : board.chessboard) {
            System.out.println(Arrays.toString(b));
        }
    }

    public void runHorse(int x, int y, int step) {
        visited[x][y] = true;
        chessboard[x][y] = step;

        List<int[]> next = getNext(x, y);
        sortNext(next);
        for (int[] n : next) {
            if (!visited[n[0]][n[1]]) {
                runHorse(n[0], n[1], step + 1);
            }
        }
        if (step < X * Y && !finished) {//如果失败则回溯
            chessboard[x][y] = 0;
            visited[x][y] = false;
        } else if (step == X * Y) {
            finished = true;
        }
    }

    //获取下一步所有走法
    public List<int[]> getNext(int x, int y) {
        List<int[]> next = new ArrayList<>();
        if (x + 1 < X && y + 2 < Y) {
            next.add(new int[]{x + 1, y + 2});
        }
        if (x + 2 < X && y + 1 < Y) {
            next.add(new int[]{x + 2, y + 1});
        }
        if (x + 2 < X && y - 1 >= 0) {
            next.add(new int[]{x + 2, y - 1});
        }
        if (x + 1 < X && y - 2 >= 0) {
            next.add(new int[]{x + 1, y - 2});
        }
        if (x - 1 >= 0 && y - 2 >= 0) {
            next.add(new int[]{x - 1, y - 2});
        }
        if (x - 2 >= 0 && y - 1 >= 0) {
            next.add(new int[]{x - 2, y - 1});
        }
        if (x - 2 >= 0 && y + 1 < Y) {
            next.add(new int[]{x - 2, y + 1});
        }
        if (x - 1 >= 0 && y + 2 < Y) {
            next.add(new int[]{x - 1, y + 2});
        }
        return next;
    }

    //用贪心算法优化
    public void sortNext(List<int[]> next) {
        next.sort(Comparator.comparingInt(p -> getNext(p[0], p[1]).size()));
    }

    public HorseChessboard(int x, int y) {
        X = x;
        Y = y;
        visited = new boolean[x][y];
        chessboard = new int[x][y];
    }
}

package com.tk.demo.algorithm;

import java.util.Arrays;

public class Knapsack {

    public static void main(String[] args) {
        int[] w = {1, 4, 3};//物品重量
        int[] v = {1500, 3000, 2000};//物品价值
        String[] name = {"鼠标", "机箱", "显示屏"};
        int m = 4;//背包容量
        int n = v.length;//物品个数
        int[][] p = new int[n + 1][m + 1];//p[i][j]表示前i个物品中容量为j时能装下的最大价值
        int[][] t = new int[n + 1][m + 1];//记录往背包里放物品的位置

        for (int i = 1; i < p.length; i++) {
            for (int j = 1; j < p[0].length; j++) {
                if (w[i - 1] > j) {//从0开始，所以i-1
                    p[i][j] = p[i - 1][j];
                } else {
                    if (p[i - 1][j] > v[i - 1] + p[i - 1][j - w[i - 1]]) {
                        p[i][j] = p[i - 1][j];
                    } else {
                        p[i][j] = v[i - 1] + p[i - 1][j - w[i - 1]];
                        t[i][j] = 1;
                    }
                }
            }
        }

        //输出最大价值表
        for (int i = 0; i < p.length; i++) {
            System.out.println(Arrays.toString(p[i]));
        }
        //输出背包内物品信息
        for (int i = t.length - 1, j = t[0].length - 1; i > 0 && j > 0; i--) {
            if (t[i][j] == 1) {
                System.out.println("把[" + name[i - 1] + "]放入背包");
                j -= w[i - 1];
            }
        }
    }
}

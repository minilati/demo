package com.tk.demo.algorithm;

import java.util.Arrays;

public class EightQueens {
    //存放位置
    int[] site = new int[8];
    static int count;
    static int total;

    public static void main(String[] args) {
        EightQueens queens = new EightQueens();
        queens.put(0);
        System.out.println(count);
        System.out.println(total);
    }

    private void put(int n){
        if(n==8){
            count++;
            System.out.println(Arrays.toString(site));
            return;
        }
        for(int i=0;i<8;i++){
            site[n]=i;
            if(judge(n)){
                put(n+1);
            }

        }
    }


    //判断是否冲突
    private boolean judge(int n) {
        total++;
        for (int i = 0; i < n; i++) {
            if (site[n] == site[i] || Math.abs(site[n] - site[i]) == Math.abs(n - i)) {
                return false;
            }
        }
        return true;
    }
}

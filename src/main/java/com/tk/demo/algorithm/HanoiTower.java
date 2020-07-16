package com.tk.demo.algorithm;

public class HanoiTower {
    public static void main(String[] args) {
        move(3,'A','B','C');
    }

    public static void move(int n, char a, char b, char c) {
        if(n==1){
            System.out.println("第1个盘："+a+" -> "+c);
        }else{
            move(n-1,a,c,b);
            //move(n,a,b,c);
            System.out.println("第"+n+"个盘："+a+" -> "+c);
            move(n-1,b,a,c);

        }
    }

}

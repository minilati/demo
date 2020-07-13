package com.tk.demo;

import java.util.Arrays;

public class Basket {

    public static void main(String[] args) {
        int i = countWays(3, 2, 3);
        System.out.println(i);
    }

    static int count = 0;
    static int baskets, capacity, balls;
    static int[] bas;

    public static int countWays(int baskets, int capacity, int balls) {
        if (baskets * capacity < balls) {
            return 0;
        }
        if (balls < capacity) {
            capacity = balls;
        }

        Basket.baskets = baskets;
        Basket.balls = balls;
        Basket.capacity = capacity;
        bas = new int[baskets];


        putBalls(0);
        return count;
    }

    static void putBalls(int n) {
        //检测是不是到达最后篮子
        if (n == baskets) {
            int sum = 0;
            for (int i : bas) {
                sum += i;
            }
            //如果球用完则计数器加一
            if (sum == balls) {
                count++;
                System.out.println(Arrays.toString(bas));
            }
        } else {
            //给第n个篮子放球,并且从0->最大容量
            for (int i = 0; i <= capacity; i++) {
                bas[n] = i;
                putBalls(n+1);
            }
        }
    }

}

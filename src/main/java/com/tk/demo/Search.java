package com.tk.demo;

import java.util.ArrayList;
import java.util.Arrays;

public class Search {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 6, 12, 23, 39, 45, 57, 66, 78, 99, 99, 99, 99, 360, 788, 999};

        System.out.println(binarySearch(arr, 0, arr.length - 1, 99));
        System.out.println(fibSearch(arr, 99));
    }

    //二分法查找
    private static ArrayList<Integer> binarySearch(int[] arr, int left, int right, int val) {
        if (left > right || val < arr[0] || val > arr[arr.length - 1]) {
            return new ArrayList<>();
        }
        int mid = left + (right - left) * (val - arr[left]) / (arr[right] - arr[left]);
        if (val < arr[mid]) {
            return binarySearch(arr, left, mid - 1, val);
        } else if (val > arr[mid]) {
            return binarySearch(arr, mid + 1, right, val);
        } else {
            ArrayList<Integer> list = new ArrayList<>();
            int temp = mid - 1;
            while (temp >= 0 && arr[temp] == val) {
                list.add(temp--);
            }
            while (mid <= arr.length - 1 && arr[mid] == val) {
                list.add(mid++);
            }
            return list;
        }
    }

    //斐波那契查找
    private static int fibSearch(int[] arr, int val) {
        int left = 0;
        int right = arr.length - 1;
        int k = 0;
        int mid = 0;
        int[] f = fib();

        while (right > f[k] - 1) {
            k++;
        }
        int[] temp = Arrays.copyOf(arr, f[k] - 1);
        for (int i = arr.length; i < temp.length; i++) {
            temp[i] = arr[right];
        }

        while (left <= right) {
            mid = left + f[k - 1] - 1;
            if (val < temp[mid]) {
                right = mid - 1;
                k--;
            } else if (val > temp[mid]) {
                left = mid + 1;
                k -= 2;
            }else{
                return mid;
            }
        }
        return -1;
    }
    public static int[] fib() {
        int maxSize = 20;
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

}

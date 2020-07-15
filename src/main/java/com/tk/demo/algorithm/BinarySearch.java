package com.tk.demo.algorithm;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 23, 66};
        int index = search(arr, 11);
        System.out.println(index);
    }

    public static int search(int[] arr, int val) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == val) {
                return mid;
            } else if (arr[mid] > val) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}

package com.tk.learn.algorithm;

import java.util.Arrays;
import java.util.Date;

public class Sort {
    public static void main(String[] args) {
        int num = 10;
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = (int) (Math.random() * num);
        }
        arr = new int[]{101, 92, 83, 74, 65, 53, 2020, 12, 31, 47};

        System.out.println("排序前：" + Arrays.toString(arr));
        Date start = new Date();

        //bubbleSort(arr);
        //selectSort(arr);
        //insertSort(arr);
        //shellSort1(arr);
        //quickStore(arr, 0, arr.length - 1);
        //mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
        //radixSort(arr);
        heapSort(arr);

        Date end = new Date();
        System.out.println("排序后：" + Arrays.toString(arr));
        System.out.println("时间：" + (end.getTime() - start.getTime()));
    }

    //冒泡排序
    private static void bubbleSort(int[] arr) {
        int temp = 0;
        for (int i = 1; i < arr.length; i++) {
            boolean flag = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = false;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (flag) {
                System.out.println("第" + i + "次排序完成！");
                break;
            }
            //System.out.println("第" + i + "次：" + Arrays.toString(arr));
        }
    }

    //选择排序
    private static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[minIndex];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            //System.out.println("第" + i + "次：" + Arrays.toString(arr));
        }
    }

    //插入排序
    private static void insertSort(int[] arr) {
        int insertValue;//待插入值
        int insertIndex;//插入位置
        for (int i = 1; i < arr.length; i++) {
            insertValue = arr[i];
            insertIndex = i - 1;

            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertValue;
            }
            //System.out.println("第" + i + "次：" + Arrays.toString(arr));
        }
    }

    //希尔排序交换式 慢
    private static void shellSort0(int[] arr) {
        int temp;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            //System.out.println("第" + ++count + "次：" + Arrays.toString(arr));
        }
    }

    //希尔排序移位式 快
    private static void shellSort1(int[] arr) {
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i];
                int j = i - gap;
                if (temp < arr[j]) {
                    while (j >= 0 && temp < arr[j]) {
                        arr[j + gap] = arr[j];
                        j -= gap;
                    }
                    arr[j + gap] = temp;

                }
            }
            //System.out.println("第" + ++count + "次：" + Arrays.toString(arr));
        }
    }

    //快速排序
    private static void quickStore(int[] arr, int left, int right) {
        if (left < right) {
            /*int temp = arr[left];
            arr[left] = arr[(left + right) / 2];
            arr[(left + right) / 2] = temp;*/

            int l = left, r = right, m = arr[left];
            while (l < r) {
                // 从右向左找一个小于m的数
                while (l < r && arr[r] >= m) r--;
                if (l < r) arr[l++] = arr[r];

                // 从左向右找一个大于等于m的数
                while (l < r && arr[l] < m) l++;
                if (l < r) arr[r--] = arr[l];
            }
            arr[l] = m;
            quickStore(arr, left, l - 1);
            quickStore(arr, l + 1, right);
        }
    }

    //分治法
    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, right, temp);
        }
    }
    private static void merge(int[] arr, int left, int right, int[] temp) {
        int mid = (left + right) / 2;
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                i++;
            } else {
                temp[t] = arr[j];
                j++;
            }
            t++;
        }
        while (i <= mid) {
            temp[t] = arr[i];
            i++;
            t++;
        }
        while (j <= right) {
            temp[t] = arr[j];
            j++;
            t++;
        }
        t = 0;
        while (left <= right) {
            arr[left] = temp[t];
            left++;
            t++;
        }
    }

    //基数排序(桶排序)
    private static void radixSort(int[] arr) {
        int[][] bucket = new int[10][arr.length];
        int[] counts = new int[10];
        int max = counts[0];
        for (int a : arr) {
            if (a > max) max = a;

        }
        int maxLength = String.valueOf(max).length();

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int num = arr[j] / n % 10;
                bucket[num][counts[num]++] = arr[j];
            }

            for (int k = 0, index = 0; k < counts.length; k++) {
                for (int l = 0; l < counts[k]; l++) {
                    arr[index++] = bucket[k][l];
                }
                counts[k] = 0;
            }
            //System.out.println("第" + (i + 1) + "次：" + Arrays.toString(arr));
        }
    }

    //堆排序
    private static void heapSort(int[] arr) {
        int temp = 0;
        //构建一个大顶堆
        for(int i=arr.length/2-1;i>=0;i--){
            makeHeap(arr,i,arr.length);
        }
        for(int i=arr.length-1;i>0;i--){
            temp=arr[0];
            arr[0]=arr[i];
            arr[i]=temp;
            makeHeap(arr,0,i);
        }

    }
    public static void makeHeap(int[] arr, int i, int n) {
        int temp = arr[i];
        for (int j = 2 * i + 1; j < n; j = 2 * j + 1) {
            if(j+1<n&&arr[j]<arr[j+1]){
                j++;
            }
            if(arr[j]>temp){
                arr[i]=arr[j];
                i=j;
            }else{
                break;
            }
        }
        arr[i]=temp;
    }
}

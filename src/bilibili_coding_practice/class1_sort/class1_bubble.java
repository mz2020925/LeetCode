package bilibili_coding_practice.class1_sort;

import java.util.Arrays;

public class class1_bubble {
    public static void main(String[] args) {
        int[] arr = {6, 5, 4, 3, 2, 1};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr){
        if(arr==null || arr.length<2){
            return;
        }
        // 0 ~ N-1开始冒泡排序
        // 0 ~ N-2开始冒泡排序
        // 0 ~ N-3开始冒泡排序
        // ...
        // 0 ~ 1开始冒泡排序
        for (int i = arr.length-1; i > 0; i--) {
            // 开始冒泡排序
            for (int j = 0; j < i; j++) {
                if(arr[j]>arr[j+1]){
                    swap(arr, j, j+1);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

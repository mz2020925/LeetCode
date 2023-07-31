package bilibili_coding_practice.class1_sort;

import java.util.Arrays;

public class class1_insert {
    public static void main(String[] args) {
        int[] arr = {6, 5, 4, 3, 2, 1};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    // 插入排序法，时间复杂度O(N^2)
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length <= 2) {
            return;
        }

        // 0~0有序，0~i想有序
        for (int i = 1; i < arr.length; i++) {  // 一看
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {  // 二比
                swap(arr, j, j + 1);  // 三交换
            }
        }


    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void swap2(int[] arr, int i, int j) {  // 使用异或实现交换
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }


}





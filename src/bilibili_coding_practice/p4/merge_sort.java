package bilibili_coding_practice.p4;

import org.junit.Test;

import java.util.Arrays;

public class merge_sort {
    public static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;

        while (p1 <= mid && p2 <= r) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }

        while (p2 <= r) {
            help[i++] = arr[p2++];
        }

        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }


    public static void mergeSort(int[] arr, int l, int r) {
        // 递归终止条件
        if (l == r) {
            return;
        }

        int mid = l + ((r - l) >> 1);
        // 归并排序是先分层再写每层的操作。快速排序是先写每层的操作，再分层，因为快速排序必须先完成操作，才能得到分层边界
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }

    @Test
    public void test() {
        int[] arr = new int[]{-10, -34, 0, 3, 4, 45, 45, 3, 78, 1000, -40, -20, -20};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

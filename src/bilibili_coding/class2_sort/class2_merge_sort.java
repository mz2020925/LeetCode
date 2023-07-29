package bilibili_coding.class2_sort;

import java.util.Arrays;

public class class2_merge_sort {
    public static void main(String[] args) {
        int[] arr = new int[]{8, 7, 6, 5, 4, 3, 2, 1};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        process(array, 0, array.length - 1);
    }

    private static void process(int[] array, int L, int R) {
        // 递归终止点
        if (L == R) {
            return;
        }

        // 递归体
        int mid = L + ((R - L) >> 2);
        process(array, L, mid);
        process(array, mid + 1, R);
        merge(array, L, mid, R);
    }

    private static void merge(int[] array, int L, int mid, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;

        while (p1 <= mid && p2 <= R) {
            // 待合并的左部分、右部分都是有序的，合并过程会操作使得合并后的部分有序
            help[i++] = array[p1] < array[p2] ? array[p1++] : array[p2++];  // 不管是不是小和问题，相等的时候都移动p2
        }
        while (p1 <= mid) {
            help[i++] = array[p1++];
        }
        while (p2 <= R) {
            help[i++] = array[p2++];
        }

        for (i = 0; i < help.length; i++) {
            array[L + i] = help[i];
        }

    }
}

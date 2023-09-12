package bilibili_coding_practice.p4;

import org.junit.Test;

import java.util.Arrays;

public class quick_sort1 {
    public static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static int[] partition(int[] arr, int l, int r) {
        int less = l - 1;  // 小于等于区域右边界
        while (l <= r) {  // 当前值遍历完数组的所有值
            if (arr[l] <= arr[r]) {  // 当前值小于等于划分值
                swap(arr, ++less, l++);
            } else {  // 当前值大于划分值
                l++;
            }
        }
        // 上述循环结束之后，less指向的就是划分值。因为最后一次交换是把划分值换到了小于等于区域的下一个，并且小于等于区域会右扩，
        // 此时less指向划分值，less就是 等于区域的左边界和右边界
        return new int[]{less, less};
    }

    public static void quickSort(int[] arr, int l, int r) {
        // 递归分层
        if (l < r) {
            // swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
            int[] edge = partition(arr, l, r);
            quickSort(arr, l, edge[0] - 1);  // edge[0]是等于区域的左边界
            quickSort(arr, edge[1] + 1, r);  // edge[1]是等于区域的右边界
        }
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }


    @Test
    public void test() {
        int[] arr = new int[]{1, 2, -30, 1, 20, -30, -29, 30, 4, 0, 300, 20, 56};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

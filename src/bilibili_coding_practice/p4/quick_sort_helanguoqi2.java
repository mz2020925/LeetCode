package bilibili_coding_practice.p4;

import org.junit.Test;

import java.util.Arrays;

public class quick_sort_helanguoqi2 {
    public void partition(int[] arr, int l, int r, int midEdge) {
        int less = l - 1;  // 小于区域右边界
        int more = r + 1;  // 大于区域左边界
        while (l <= r) {  // 为什么这里不是 l <= r ，因为l <= r，不会把等于midEdge的数值换到前面去
            if (arr[l] < midEdge) {
                swap(arr, ++less, l++);
            } else if (arr[l] > midEdge) {
                swap(arr, --more, l);
            } else {
                l++;
            }
        }
        // 因为荷兰国旗问题并不要求数组排序，所以不用递归分层，只需要把等于划分值的放中间，小于划分值的放左边，大于划分值的放右边
    }

    public void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }


    @Test
    public void test() {
        int[] arr = new int[]{30, 1, 2, -30, 1, 20, -30, -29, 30, 4, 0, 300, 20, 56, 30};
        partition(arr, 0, arr.length - 1, 30);
        System.out.println(Arrays.toString(arr));
    }
}

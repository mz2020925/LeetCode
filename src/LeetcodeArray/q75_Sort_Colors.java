package LeetcodeArray;

import java.util.Arrays;

public class q75_Sort_Colors {
    // 荷兰国旗问题
    public static void main(String[] args) {
        int[] nums = {1,1,0,0,0,0,2,2,2,0,2,1,1,0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int smaller = -1;
        int bigger = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (i >= bigger) {
                break;
            }else if (nums[i] < 1) {
                swap(nums, smaller + 1, i);
                smaller++;
            } else if (nums[i] > 1) {
                swap(nums, bigger - 1, i);
                bigger--;
                i--;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        if (arr[i]==arr[j]) return;
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}

package LeetcodeHot100.normalArray;

import org.junit.Test;

import java.util.Arrays;

public class q189_rotate {
    @Test
    public void test01() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        // int[] nums = {-1, -100, 3, 99};
        int k = 3;
        rotate(nums, k);
        System.out.println(Arrays.toString(nums));
    }

    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return;
        }

        int[] temp_nums = Arrays.copyOfRange(nums, 0, nums.length);
        for (int i = 0; i < temp_nums.length; i++) {
            nums[(i + k) % nums.length] = temp_nums[i];
        }
    }
}

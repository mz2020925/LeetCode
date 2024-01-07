package LeetcodeHot100.doublePoint;

import org.junit.Test;

import java.util.Arrays;

public class q283_moveZeroes {

    @Test
    public void test() {
//        int[] nums = {0, 1, 0, 3, 12, -1, 2000, 4, 0};
        int[] nums = {4,2,4,0,0,3,0,5,1,0};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        int i = 0;
        while (i < nums.length && nums[i] !=0) i++;
        int j = i + 1;
        for (; i < nums.length; i++) {
            if (nums[i] == 0) {
                while (j < nums.length && nums[j] == 0) j++;
                if (j >= nums.length) break;
                swap(nums, i, j);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}

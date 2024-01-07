package LeetcodeHot100.normalArray;

import org.junit.Test;

import java.util.Arrays;

public class q238_productExceptSelf {

    @Test
    public void test01() {
        int[] nums = {1, 2, 3, 4};
        int[] res = productExceptSelf(nums);
        System.out.println(Arrays.toString(res));
    }

    public int[] productExceptSelf(int[] nums) {
        // 用二进制除法行不行？
        int[] res = new int[nums.length];


        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int temp = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            temp = temp * nums[i + 1];
            res[i] = res[i] * temp;
        }
        return res;
    }
}

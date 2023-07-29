package LeetcodeArray;

import java.util.Arrays;

/*
这是一道典型的使用「动态规划」解决的问题，需要我们掌握动态规划问题设计状态的技巧（无后效性），
并且需要知道如何推导状态转移方程，最后再去优化空间。
 */
public class q53_Maximum_Subarray {
    public static void main(String[] args) {
        // int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        // int[] nums = {1};
        int[] nums = {5, 4, -1, 7, 8};
        int res = maxSubArray(nums);
        System.out.println(res);
    }


    // 动态规划问题：分解为小问题，小问题的最优解，迭代更新为原问题的最优解
    public static int maxSubArray(int[] nums) {
        if (nums == null) {
            return 0;
        }

        int len = nums.length;
        // dp[i]表示：以nums[i]结尾的连续子数组的最大和
        int[] dp = new int[len];
        dp[0] = nums[0];  // 初始状态

        // 开始状态更迭，这里没有最大比较，只有正数判断，是正数就更新状态，非正数就从当前状态重新开始
        for (int i = 1; i < len; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
        }
        return Arrays.stream(dp).max().orElse(0);
    }

    public static int maxSubArray2(int[] nums) {
        if (nums == null) {
            return 0;
        }

        int status = 0;
        int res = nums[0];
        for (int num : nums) {
            status = Math.max(status + num, num);
            res = Math.max(res, status);
        }
        return res;
    }
}

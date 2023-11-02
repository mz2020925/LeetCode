package LeetcodeHot100;

import org.junit.Test;

import java.util.HashMap;

public class q560_SubarraySum {
    @Test
    public void test01() {
        int[] nums = {1};
        int k = 0;
        int res = subarraySum(nums, k);
        System.out.println(res);
    }


    // 暴力解法
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int res = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {  // 两层for循环将所有的子数组遍历完成
                int sum = 0;
                for (int l = i; l <= j; l++) {
                    sum += nums[l];
                }
                if (sum == k) {
                    res += 1;
                }
            }
        }
        return res;
    }

    // 暴力解法2
    public int subarraySum2(int[] nums, int k) {
        int len = nums.length;
        int res = 0;
        for (int i = 0; i < len; i++) {
            int sum = 0;
            for (int j = i; j < len; j++) {  // 两层for循环将所有的子数组遍历完成
                sum += nums[j];
                if (sum == k) {
                    res += 1;
                }
            }
        }
        return res;
    }


    /**
     * 1)前缀和：nums 的第 0 项到 当前项 的和。
     * 2)其实我们不关心具体是哪两项的前缀和之差等于k，只关心等于 k 的前缀和之差出现的次数c，就知道了有c个子数组求和等于k。
     * 3)遍历 nums 之前，我们让 -1 对应的前缀和为 0，这样通式在边界情况也成立。即在遍历之前，map 初始放入 0:1 键值对（前缀和为0出现1次了）。
     * 4)遍历 nums 数组，求每一项的前缀和，统计对应的出现次数，以键值对存入 map。
     * 5)边存边查看 map，如果 map 中存在 key 为[当前前缀和 - k]，说明这个之前出现的前缀和，满足[当前前缀和 - 该前缀和 == k]，它出现的次数，累加给 count。
     */
    public int subarraySum3(int[] nums, int k) {
        // key：前缀和，value：key对应的前缀和的个数
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);  // 即在遍历之前，map 初始放入 0:1 键值对（前缀和为0出现1次了）
        int preSum = 0;
        int count = 0;
        for (int num : nums) {
            preSum += num;  //
            if (map.containsKey(preSum - k)) {
                count += map.get(preSum - k);
            }

            Integer value = map.get(preSum);
            if (value != null) {
                map.put(preSum, value + 1);
            } else {
                map.put(preSum, 1);
            }

        }
        return count;

    }

    // 滑动窗口
    /**
     * 这里用
     */
}

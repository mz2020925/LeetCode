package LeetcodeHot100;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

public class q128_LongestConsecutive {
    // int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
    int[] nums = {0,1};

    @Test
    public void test() {
        // int res = longestConsecutive(nums);
        int res = longestConsecutive2(nums);
        System.out.println(res);
    }

    /**
     * 感觉这里面是在玩数字游戏。
     * 另外，一个一个往HashMap加元素这个技巧，在我印象中前面也用过，可是在什么情况下会用到这个技巧呢？
     * 猜想：当后面的计算需要前面的计算的结果的时候，需要用到HashMap，同时还要求复杂度是O(N)。
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        int temp = 0;
        for (int num : nums) {
            if (!map.containsKey(num)) {
                // 获取当前数的最左边连续长度,没有的话就更新为0
                Integer left = map.getOrDefault(num - 1, 0);
                // 同理获取右边的数
                Integer right = map.getOrDefault(num + 1, 0);
                // 不用担心左边和右边没有的情况，因为没有的话就是left或者right0，并不改变什么

                // 把当前数加入哈希表的键，值赋为1即可，代表当前数字出现过
                map.put(num, 1);

                // 更新长度
                temp = left + 1 + right;
                if (temp > res) {
                    res = temp;
                }

                // 更新最左端点的值，如果left = 0，表示当前数左边是断续的，如果left = n，表示左边是连起来的，并且是当前数的前n个都存在哈希表中
                map.put(num - left, temp);
                // 更新最右端点的值，如果right = 0，表示当前数左边是断续的，如果right = n，左边是连起来的，并且是当前数的前n个都存在哈希表中
                map.put(num + right, temp);
                // 此时 [num-left, num-right]范围的值都连续存在哈希表中了，当left = 0的时候，就是当前值是端点值
            }

        }
        return res;

    }

    /**
     *
     * 既然当后面的计算需要前面的计算的结果的时候，那么能不能用递归实现呢
     */


    /**
     * leetcode上9ms执行完的代码
     * 感觉这个思路就很简单，代码中总共包含两个for循环，也就是O(N)级别的复杂度。
     */
    public int longestConsecutive2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);

        int temp = 1;
        int res = 1;
        int pre = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[pre] == 1) {  // 表示是连续的
                temp++;
            } else if (nums[i] == nums[pre]) {  // 表示重复了，重复值不会叠加到长度中去
                continue;
            }else {  // 断续了就把temp结果赋值给res，并把temp恢复初始值
                res = Math.max(res, temp);
                temp = 1;
            }
            pre = i;
        }
        res = Math.max(res, temp);  // 将最后一个连续区间的temp和res比较，返回结果
        return res;
    }
}

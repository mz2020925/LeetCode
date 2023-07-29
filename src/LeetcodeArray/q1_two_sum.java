package LeetcodeArray;

import java.util.Arrays;
import java.util.HashMap;

/*
题⽬⼤意
    在数组中找到 2 个数之和等于给定值的数字，结果返回 2 个数字在数组中的下标。
解题思路
    这道题最优的做法时间复杂度是 O(n)。
    顺序扫描数组，对每⼀个元素，在 map 中找 能组合给定值的另⼀半数字，如果找到了，直接返回 2 个
    数字的下标即可。如果找不到，就把这个数字存⼊ map 中，等待扫到“另⼀半”数字的时候，再取出来返
    回结果。
* */
class q1_two_sum {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int[] result = twoSum(nums, 9);
        System.out.println(Arrays.toString(result));

        int[] nums2 = {3, 2, 4};
        int[] result2 = twoSum2(nums2, 6);
        System.out.println(Arrays.toString(result2));
    }

    // 天真的解法
    public static int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }

    /* 更好的解法
     * target=19, hashMap = {}
     * nums =  [1,   2,   3,   4,   5,   6,   7,   8,   9,   10]
     * hashMap={18:0,17:1,16:2,15:3,14:4,13:5,12:6,11:7,10:8}
     *
     * */
    public static int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i])) {
                int index = hashMap.get(nums[i]);
                res[0] = index;
                res[1] = i;
                break;
            }else {
                hashMap.put(target-nums[i], i);
            }
        }
        return res;
    }




    // 二次默写
    public static int[] twoSum3(int[] nums, int target){
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int[] res = new int[2];
        for (int i : nums) {
            if (hashMap.containsKey(nums[i])) {
                res[0] = i;  // 当前数字所在下标
                res[1] = hashMap.get(nums[i]);  // 之前的另一半数字所在的下标存在map中的”值“中
            }else {
                hashMap.put(target - nums[i], i);
            }
        }
        return res;
    }
}

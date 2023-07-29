package LeetcodeArray;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 */
public class q41_First_Missing_Positive {

    public static void main(String[] args) {
        // int[] nums = {3,4,-1,1};
        int[] nums = {1,2,3,4};
        // int res = methodWithHash(nums);
        // System.out.println(res);
        //
        // int res2 = methodWithBinarySearch(nums);
        // System.out.println(res2);

        int res3 = firstMissingPositive(nums);
        System.out.println(res3);
    }

    // 最终方法
    public static int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            while (nums[i]>0 && nums[i]<=len && nums[nums[i]-1]!=nums[i]){
                // 满足在指定范围内，并且没有放在正确位置上时，才交换
                // 例如：数值1要放在索引0上，数值2要放在索引1上，数值3应该放在索引2的位置上，以此类推
                swap(nums, nums[i]-1, i);
            }
        }
        // 现在得到我们想要的数组了，找到那个不等于索引+1的数值
        // [3,4,-1,1]  =>  [1, -1, 3, 4]
        for (int i = 0; i < len; i++) {
            if (nums[i] != i+1){
                return i+1;
            }
        }
        return len +1;
    }

    private static void swap(int[] nums, int i, int j) {
        nums[i]=nums[i]^nums[j];
        nums[j]=nums[i]^nums[j];
        nums[i]=nums[i]^nums[j];
    }


    // 方法一：哈希表（空间复杂度不符合要求）
    // 该问题存在一种必然情况，就是从最小正整数开始，一次增1，直到数组长度
    /*
    时间复杂度：O(N)，这里 N 表示数组的长度。第 1 次遍历了数组，第 2 次遍历了区间 [1, len] 里的元素。
    空间复杂度：O(N)，把 N 个数存在哈希表里面，使用了 N 个空间。
     */
    public static int methodWithHash(int[] nums) {
        int len = nums.length;

        Set<Integer> hashSet = new HashSet<>();

        for (int num : nums) {
            hashSet.add(num);
        }

        for (int i = 1; i <= len; i++) {
            if (!hashSet.contains(i)) {
                return i;
            }
        }
        return len + 1;
    }


    // 方法二：二分查找（时间复杂度不符合要求）
    public static int methodWithBinarySearch(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);

        for (int i = 1; i <= len; i++) {
            int res = binarySearch(nums, i);  // 把方法一种的hashSet.contains()换成了这里的二分查找
            if (res == -1) {  // 不包含i（i=1~len），i才是符合题意的结果，才返回
                return i;
            }
        }

        return len + 1;  // 如果1~len都没有包含，就返回len + 1，因为数组总共可以包含较小正整数1~len
    }
    private static int binarySearch(int[] nums, int target) {
        // 二分法查找target
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target) {
                return target;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;  // 代表没找到
    }


}

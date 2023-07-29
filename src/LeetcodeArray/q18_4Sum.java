package LeetcodeArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
给你一个由 n 个整数组成的数组nums ，和一个目标值 target。
请你找出并返回满足下述全部条件且不重复的四元组[nums[a], nums[b], nums[c], nums[d]]
（若两个四元组元素一一对应，则认为两个四元组重复）：
要求：
    0 <= a, b, c, d< n
    a、b、c 和 d 互不相同
    nums[a] + nums[b] + nums[c] + nums[d] == target
你可以按 任意顺序 返回答案 。

提示：
    1 <= nums.length <= 200
    -109 <= nums[i] <= 109
    -109 <= target <= 109
* */

public class q18_4Sum {
    public static void main(String[] args) {
        // int[] nums = {1, 0, -1, 0, -2, 2};
        // int[] nums = {2,2,2};
        // int[] nums = {0,0,0,0};
        int[] nums = {1000000000, 1000000000, 1000000000, 1000000000};
        // int targe = 0;
        int targe = -294967296;
        List<List<Integer>> listList = fourSum(nums, targe);
        System.out.println(listList);
    }

    static public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> listList = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return listList;
        }

        Arrays.sort(nums);
        // System.out.println(Arrays.toString(nums));
        for (int i = 0; i <= nums.length - 4; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j <= nums.length - 3; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int k = j + 1;
                int l = nums.length - 1;
                while (k < l) {
                    if ((long)nums[i] + nums[j] + nums[k] + nums[l] < (long)target) {
                        k++;
                    } else if ((long)nums[i] + nums[j] + nums[k] + nums[l] > (long)target) {
                        l--;
                    } else if((long)nums[i] + nums[j] + nums[k] + nums[l] == (long)target){
                        ArrayList<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        temp.add(nums[l]);
                        listList.add(temp);
                        k++;
                        l--;
                        while (k < l && nums[k] == nums[k - 1]) {
                            k++;
                        }
                        while (k < l && nums[l] == nums[l + 1]) {
                            l--;
                        }

                    }
                }
            }
        }

        return listList;
    }
}

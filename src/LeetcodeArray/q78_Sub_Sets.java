package LeetcodeArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class q78_Sub_Sets {
    public static void main(String[] args) {
        // int[] nums = {1,2,3};
        int[] nums = {0};
        List<List<Integer>> res = subsets(nums);
        System.out.println(res);
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        process(nums, 0, temp, res);
        return res;
    }

    public static void process(int[] nums, int i, List<Integer> temp, List<List<Integer>> res) {
        // 递归结束点
        if (i == nums.length) {
            res.add(temp);
            return;
        }

        // 递归体
        ArrayList<Integer> tempNoInclude = new ArrayList<>(temp);
        process(nums, i + 1, tempNoInclude, res);

        ArrayList<Integer> tempInclude = new ArrayList<>(temp);
        tempInclude.add(nums[i]);
        process(nums,i+1, tempInclude, res);  // 要这个元素


    }

    // public static List<List<Integer>> copyList(List<List<Integer>> temp){
    //     List<List<Integer>> sameList = new ArrayList<>();
    //     if (temp==null){
    //         return sameList;
    //     }
    //     sameList.addAll(temp);
    //     return sameList;
    // }

}

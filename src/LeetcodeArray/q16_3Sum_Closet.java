package LeetcodeArray;

import java.util.Arrays;

public class q16_3Sum_Closet {
    public static void main(String[] args) {

        int[] nums = {-1,2,1,-4};
        // int[] nums = {0, 0, 0};
        // int[] nums = {0, 1, 2};
        int target = 1;
        // int target = 3;
        int result = threeSumClosest(nums, target);
        System.out.println(result);
    }

    // 正确答案
    public static int threeSumCloset3(int[] nums, int target) {
        int min = Integer.MAX_VALUE;
        int result = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                int diff = Math.abs(sum - target);
                if (diff == 0) return sum;
                if (diff < min) {
                    min = diff;
                    result = sum;
                }
                if (sum <= target) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return result;
    }

    // 自己默写一遍，并改进了一下
    public static int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            System.exit(1);
        }
        Arrays.sort(nums);

        int res = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            // 下面这句代码是过滤掉重复数字的方式，这道题不需要过滤重复数字，加了过滤重复数字的代码会增加代码执行时间
            if (i == 0 || nums[i - 1] < nums[i]) {
                int start = i + 1;
                int end = nums.length - 1;
                while (start < end) {
                    int sum = nums[i] + nums[start] + nums[end];
                    int delta = Math.abs(target - sum);
                    if (delta == 0) {
                        return sum;
                    } else if (delta < min) {
                        min = delta;
                        res = sum;
                    }

                    if (sum < target) {
                        start++;
                        // 下面循环是过滤掉重复数字的方式，并且保证start < end
                        while (start < end && nums[start - 1] == nums[start]) {
                            start++;
                        }
                    } else {
                        end--;
                        // 下面循环是过滤掉重复数字的方式，并且保证start < end
                        while (start < end && nums[end] == nums[end + 1]) {
                            end--;
                        }
                    }
                }
            }
        }
        return res;
    }

    /*
     * 自己写的(错误答案)
     * */
    public static int threeSumClosest2(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            System.exit(-1);
        }
        // 排序
        Arrays.sort(nums);

        int res = 0;
        int tempRes = 0;
        boolean flag = false;
        outer:
        for (int i = 0; i < nums.length; i++) {
            // 下面这句代码是过滤掉重复数字的方式
            if (i == 0 || nums[i] > nums[i - 1]) {
                // 使用对撞指针
                int start = i + 1;
                int end = nums.length - 1;
                int temp;
                while (start < end) {
                    temp = target - nums[i] - nums[start] - nums[end];
                    if (temp == 0) {
                        res = target - temp;
                        break outer;
                    } else if (!flag || Math.abs(temp) < tempRes) {
                        tempRes = Math.abs(temp);
                        res = target - temp;
                        flag = true;
                    }
                    // 两个条件语句块移动指针
                    if (temp > 0) {
                        start++;
                        // 下面循环是过滤掉重复数字的方式，并且保证start < end
                        while (start < end && nums[start] == nums[start - 1]) {
                            start++;
                        }
                    } else {
                        end--;
                        while (start < end && nums[end] == nums[start + 1]) {
                            end--;
                        }
                    }


                }


            }

        }
        return res;
    }


}

package LeetcodeArray;

/*
题⽬⼤意
    给出⼀个⾮负整数数组 a1，a2，a3，…… an，每个整数标识⼀个竖⽴在坐标轴 x 位置的⼀堵⾼度为 ai
    的墙，选择两堵墙，和 x 轴构成的容器可以容纳最多的⽔。
解题思路
    这⼀题也是对撞指针的思路。⾸尾分别 2 个指针，每次移动(每次移动一个指针)以后都分别判断⻓宽的乘积是否最⼤。
* */
public class q11_container_with_most_water {
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 3};
        System.out.println(maxArea2(arr));
    }

    // 正确答案
    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        int max = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            max = Math.max(max, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right])
                left++;
            else
                right--;
        }

        return max;
    }

    // 错误写法，反例：int[] arr = {1,2,4,3};
    public static int maxArea2(int[] height) {
        int ans = 0;
        int leftHigh = 0;
        int left = 0;
        int rightHigh = 0;
        int right = 0;
        int len = height.length;
        for (int i = 0; i < len; i++) {
            // 错在这两个if，每次循环都会被执行，极有可能出现两边同时移动一格的情况
            if (leftHigh < height[i]) {
                left = i;
                leftHigh = height[i];
            }
            if (rightHigh < height[len - 1 - i]) {
                right = len - 1 - i;
                rightHigh = height[len - 1 - i];
            }

            if (right - left <= 0) {
                break;
            }

            int h = Math.min(leftHigh, rightHigh);
            if (ans < (right - left) * h) {
                ans = (right - left) * h;
            }

        }
        return ans;
    }

    // 自己默写一遍
    public static int maxArea3(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        int maxValue = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            maxValue = Math.max(maxValue, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxValue;
    }
}

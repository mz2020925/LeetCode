package LeetcodeArray;

import java.util.Arrays;
import java.util.Stack;

/*
解法一： 月暗送湖风， 相寻路不通。 知在此塘中，AC 不让过。
解法二： 大力出奇迹，两个 for 循环。
解法三： 空间换时间，有房是大爷。
解法四： 四两拨千斤，两个小指针。
解法五： 欲穷世间理，上下而求索。
 */

public class q42_Trapping_Rain_Water {
    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        // int[] height = {4, 2, 0, 3, 2, 5};

        // 方法一
        // int res = trap(height);
        // System.out.println(res);

        // 方法二
        // int res2 = trap2(height);
        // int res2 = trap22(height);
        // System.out.println(res2);

        // 方法三
        // int res2 = trap3(height);
        // System.out.println(res2);

        // 方法四
        int res2 = trap4(height);
        System.out.println(res2);
    }

    // 方法一：一行一行来计算累加
    public static int trap(int[] height) {
        int maxNum = Arrays.stream(height).max().orElse(0);
        int res = 0;
        for (int i = 1; i <= maxNum; i++) {  // i表示第几层
            int temp = 0;  // 用于存放暂时累积的水量，还没有加入到 res 中
            int j = 0;  // 用于在当前层，遍历数组 height
            while (j < height.length && height[j] < i) {
                j++;
            }
            while (j < height.length) {
                if (height[j] >= i) {
                    res += temp;
                    temp = 0;
                } else {
                    temp += 1;  // 即使最后一个格子时，加了1，也并不会被加入到最终结果中去
                }
                j++;
            }
        }
        return res;
    }


    // 方法二：一列一列来计算累加
    public static int trap2(int[] height) {
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            int leftMax = startEnd(height, 0, i - 1);
            int rightMax = startEnd(height, i + 1, height.length - 1);
            int minNum = Math.min(leftMax, rightMax);
            if (minNum > height[i]) {
                res += minNum - height[i];
                // System.out.println(minNum-height[i]);
            } else if (minNum <= height[i]) {
                res += 0;
                // System.out.println(0);
            }
        }
        return res;
    }

    public static int startEnd(int[] height, int start, int end) {
        if (end >= 0 && start <= height.length - 1 && start <= end) {
            int[] tempArray = Arrays.stream(height, start, end + 1).toArray();
            return Arrays.stream(tempArray).max().orElse(0);
        } else {
            return 0;
        }
    }

    // 方法二另一版本：一列一列来计算累加
    public static int trap22(int[] height) {
        int res = 0;
        for (int i = 1; i < height.length - 1; i++) {  // 两端的格子里面一定不会有水
            // 左部最大值
            int maxLeft = 0;
            for (int l = i - 1; l >= 0; l--) {
                if (maxLeft < height[l]) {
                    maxLeft = height[l];
                }
            }

            // 右部最大值
            int minRight = 0;
            for (int r = i + 1; r <= height.length - 1; r++) {
                if (minRight < height[r]) {
                    minRight = height[r];
                }
            }

            int minNum = Math.min(maxLeft, minRight);
            if (minNum > height[i]) {
                res += minNum - height[i];
            }
        }
        return res;
    }

    // 方法三：动态规划——空间（new 新的数组）换时间（用 并列循环 代替 双层嵌套循环）：
    // 解法二中，对于每一列，我们求它左边最高的墙和右边最高的墙，都是重新遍历一遍所有高度，这里我们可以优化一下。
    public static int trap3(int[] height) {
        int[] maxLeft = new int[height.length];  // 代表第 i 列左边最高的墙的高度（计算时不包括i列）,初始化值是0
        int[] maxRight = new int[height.length];  // 代表第 i 列右边最高的墙的高度（计算时不包括i列）,初始化值是0
        int res = 0;

        for (int i = 1; i < height.length; i++) {  // maxLeft的最左端、最右端都是0
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i - 1]);
        }
        for (int i = height.length - 2; i >= 0; i--) { // 这样用自减运算符来计算maxRight的每个值，就可以实现一个一个递推计算
            maxRight[i] = Math.max(maxRight[i + 1], height[i + 1]);
        }

        for (int i = 0; i < height.length; i++) {
            int minNum = Math.min(maxLeft[i], maxRight[i]);
            if (minNum > height[i]) {
                res += minNum - height[i];
            }
        }
        return res;
    }

    // 方法四：双指针——动态规划中，我们常常可以对空间复杂度进行进一步的优化
    // 因为方法三的 新数组 中的元素是 height中的元素，那么就可以直接使用索引指向 height中的元素即可，不用新建数组
    public static int trap4(int[] height) {
        int maxLeft = 0;
        int maxRight = 0;
        int res = 0;
        int start = 1;
        int end = height.length - 2;
        while (start <= end) {  // 由于最左端和最右端不可能储存水，所以双指针分别从1，height.length - 2开始，这里的意思是两个指针一起遍历可能存有水的格子
            if (height[start - 1] < height[end + 1]) {  // 考察left~right作为一个整体，它的左右部分的大小，这里是左边比较小的情况
                maxLeft = Math.max(maxLeft, height[start - 1]);  // 在左边部分比较小的情况下，更新左部分最大值 <==> 左右部分的最大值都求出来，在比较找出较小的那一个
                int minNum = maxLeft;  // 已找出接下来需要判断的数
                if (minNum > height[start]) {  // 判断start指针
                    res += minNum - height[start];  //
                }
                start++;  // start指针指向的数已经判断完成了，向右移动一位
            } else {
                maxRight = Math.max(maxRight, height[end + 1]);
                int minNum = maxRight;
                if (minNum > height[end]) {
                    res += minNum - height[end];
                }
                end--;
            }
        }
        return res;
    }

    // 方法五：栈
    /*
    当遍历墙的高度的时候，如果当前高度小于栈顶的墙高度，说明这里会有积水，我们将墙的高度的下标入栈。
    如果当前高度大于栈顶的墙的高度（并且栈顶下面必须还有墙），说明之前的积水到这里停下，我们可以计算下有多少积水了。
    计算完，就把当前的墙继续入栈，作为新的积水的墙。
     */
    public static int trap5(int[] height) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        int current = 0;  // 指向数组的索引

        while (current < height.length) {
            // 如果栈不空并且当前指向的高度大于栈顶高度就一直循环
            while (!stack.empty() && height[current] > height[stack.peek()]) {
                int top = height[stack.peek()];  // 取出要出栈的元素
                stack.pop();
                if (stack.empty()) {
                    break;
                }
                int distance = current - stack.peek() - 1;   // 两堵墙之前的距离。
                int minNum = Math.min(height[stack.peek()], height[current]);
                res += distance * (minNum - top);
            }
            stack.push(current);  // 当前指向的墙入栈
            current++;  // 指针索引后移
        }
        return res;
    }
}

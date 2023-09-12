package LeetcodeArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class q84_Largest_Rectangle_in_Histogram {
    // 该题使用单调栈结构求解，求解左右两边离当前元素最近的，且小于当前元素，那么单调栈从下往上——从小到大。
    // 给定的数组可能存在重复元素
    public static int largestRectangleArea(int[] heights) {
        int[][] res = new int[heights.length][2];  // 保存数组中每个元素左右的结果
        Stack<List<Integer>> stack = new Stack<>();  // 单调栈

        for (int i = 0; i < heights.length; i++) {
            // 当栈刚创建的时候，里面没有元素，不能进行仍和操作，所以需要排除这个情况
            while (!stack.isEmpty() && (heights[stack.peek().get(0)] > heights[i])) {
                List<Integer> popIs = stack.pop();
                // 对于当前元素，左边最近且不大于当前元素，是底下压着的链表的末尾索引
                int leftNearIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer popI : popIs) {
                    res[popI][0] = leftNearIndex;  // 对于当前元素，左边最近且小于当前元素的元素索引
                    res[popI][1] = i;  // 对于当前元素，右边最近且小于当前元素的元素索引
                }
            }

            if (!stack.empty() && heights[stack.peek().get(0)] == heights[i]) {
                stack.peek().add(i);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }

        // 清算阶段，数组遍历完了
        while (!stack.isEmpty()) {
            List<Integer> popIs = stack.pop();
            // 因为上一句代码弹出一个元素，所以要判断栈是否为空，如果此时是空，那么stack.peek()就是null了
            int leftNearIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer popI : popIs) {
                res[popI][0] = leftNearIndex;
                res[popI][1] = heights.length;  // 数组遍历完了，没有要压栈的元素，说明右边不存在 离当前元素最近且比他小的
            }
        }

        int rectangleArea = 0;

        for (int i = 0; i < heights.length; i++) {
            int temp = (res[i][1] - 1 - res[i][0]) * heights[i];
            rectangleArea = Math.max(rectangleArea, temp);
        }
        return rectangleArea;

    }

    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea(heights));
    }


}

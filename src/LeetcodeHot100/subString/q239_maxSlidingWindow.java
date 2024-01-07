package LeetcodeHot100.subString;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

public class q239_maxSlidingWindow {
    @Test
    public void test01() {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        // int[] nums = {1};
        int k = 3;
        int[] res = maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(res));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return null;
        }

        LinkedList<Integer> maxWin = new LinkedList<>();  // 双端队列

        int[] res = new int[nums.length - k + 1];
        int index = 0;
        for (int r = 0; r < nums.length; r++) {  // 右边界
            // 需要维持一个从大到小的双端队列
            while (!maxWin.isEmpty() && nums[maxWin.peekLast()] <= nums[r]) {  // 如果不满足严格从大到小，maxWin.peekLast()是获取队尾元素，不是弹出
                maxWin.pollLast();  // 弹出队尾
            }

            maxWin.addLast(r);  // 追加到队尾

            if (maxWin.getFirst() == r - k) {  // r-k就是左边过期的元素，其实就是上一个k长度窗口的左边界，现在相当于是k长度窗口右移了一格。
                maxWin.pollFirst();  // 如果这个过期元素的索引恰好是双端队列头部的那个索引，那么弹出队头，否则就不弹，队列不变。
            }

            if (r >= k - 1) {
                res[index++] = nums[maxWin.getFirst()];
            }
        }
        return res;
    }
}

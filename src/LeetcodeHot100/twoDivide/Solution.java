package LeetcodeHot100.twoDivide;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class Solution {
    int[] res = new int[]{-1, -1};
    public int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        if(len == 0) {
            return res;
        }

        int start = 0;
        int end = len - 1;
        int firstGet = -1;

        while(start <= end) {
            int mid = start + ((end - start) >> 1);
            if(nums[mid] == target) {
                res[0] = mid;
                res[1] = mid;
                firstGet = mid;
                break;
            }
            if(nums[mid] < target) {
                start = mid+1;
            }
            if(nums[mid] > target) {
                end = mid -1;
            }
        }

        if(firstGet == -1){
            return res;
        }

        // 前半部分遍历找出最先出现的
        end = firstGet - 1;
        while(start <= end) {
            int mid = start + ((end - start) >> 1);
            if(nums[mid] == target) {
                res[0] = Math.min(res[0], mid);
            }
            if(nums[mid] < target) {
                start = mid + 1;
            }else {
                end = mid -1;
            }
        }


        // 后半部分遍历找出最后出现的
        start = firstGet + 1;
        while(start <= end) {
            int mid = start + ((end - start) >> 1);
            if(nums[mid] == target) {
                res[1] = Math.max(res[1], mid);
            }
            if(nums[mid] <= target) {
                start = mid + 1;
            }else {
                end = mid -1;
            }
        }
        return res;
    }

    @Test
    public void test(){
        int[] arr = {2,2};
        searchRange(arr, 2);
        System.out.println(Arrays.toString(res));

        // Linked表示底层使用双链表实现，Array表示底层使用数组实现
        List<Integer> list1 = new LinkedList<>();
        List<Integer> list2 = new ArrayList<>();

        Queue<Integer> queue1 = new LinkedBlockingQueue<>();
        Queue<Integer> queue2 = new ArrayBlockingQueue<>(12);

        Deque<Integer> deque1 = new LinkedBlockingDeque<>();
        Deque<Integer> deque2 = new ArrayDeque<>();

        Stack<Integer> stack = new Stack<>();

        while (true){

            int ssss=0;
            break;
        }
        while (true){

            int ssss=0;
        }

    }
}

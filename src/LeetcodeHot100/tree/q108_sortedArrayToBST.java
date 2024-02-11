package LeetcodeHot100.tree;

import org.junit.Test;

public class q108_sortedArrayToBST {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode res = f(nums, 0, nums.length - 1);
        return res;
    }

    public TreeNode f(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        int temp = (end - start) >> 2 + start;
        TreeNode node = new TreeNode(nums[temp]);
        node.left = f(nums, start, temp -1);
        node.right = f(nums, temp+1, end);
        return node;
    }

    @Test
    public void test(){
    }
}

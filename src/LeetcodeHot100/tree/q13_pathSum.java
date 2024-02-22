package LeetcodeHot100.tree;

import java.util.HashMap;
import java.util.Map;

public class q13_pathSum {
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

    // 为什么前缀和这种思路就能找到所有，满足《给定和》的路径的数目？？?
    // 仔细看这道题的“路径”，正是一种 “差值” 的形式。前缀和作差适用于解决这一问题的
    public int pathSum(TreeNode root, int targetSum) {
        if(root == null) {
            return 0;
        }
        Map<Long, Integer> prefix = new HashMap<>();
        prefix.put(0L, 1);  // 为了应对路径开始节点是根节点的情况：这个时候路径会包含根节点到当前节点，此时curr - targetSum = 0
        int res = f(root, prefix, 0L, targetSum);
        return res;
    }

    public int f(TreeNode root, Map<Long, Integer> prefix, Long curr, int targetSum) {
        if(root == null) {
            return 0;
        }
        curr += root.val;
        int count;
        count = prefix.getOrDefault(curr - targetSum, 0);  // 以当前节点作为路径终点的路径个数

        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        // 向左树要信息，以当前节点左孩子作为路径终点的路径个数
        count += f(root.left, prefix, curr, targetSum);
        // 向右树要信息，以当前节点右孩子作为路径终点的路径个数
        count += f(root.right, prefix, curr, targetSum);
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);

        return count;  // 返回当前节点、左孩子、右孩子作为路径终点的路径个数
    }
    // 这道题能不能用左程云的递归套路解题呢？？
}

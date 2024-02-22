package LeetcodeHot100.tree;

import org.junit.Test;

import java.util.*;

public class q2_maxDepth {
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

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        Map<TreeNode, Integer> curNodeLevel = new HashMap<>();
        curNodeLevel.put(root, 1);
        int maxLevel = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                Integer level = curNodeLevel.get(node);
                curNodeLevel.put(node.left, level + 1);
                queue.add(node.left);
            }
            if (node.right != null) {
                Integer level = curNodeLevel.get(node);
                curNodeLevel.put(node.right, level + 1);
                queue.add(node.right);
            }
        }

        return Collections.max(curNodeLevel.values());
    }

    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        Map<TreeNode, Integer> curNodeLevel = new HashMap<>();
        curNodeLevel.put(root, 1);
        int maxLevel = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                Integer level = curNodeLevel.get(node);
                curNodeLevel.put(node.left, level + 1);
                queue.add(node.left);
                maxLevel = Math.max(maxLevel, level + 1);
            }
            if (node.right != null) {
                Integer level = curNodeLevel.get(node);
                curNodeLevel.put(node.right, level + 1);
                maxLevel = Math.max(maxLevel, level + 1);
                queue.add(node.right);
            }
        }

        return maxLevel;
    }

    public int maxDepth3(TreeNode root){
        if (root == null){
            return 0;
        }
        int[] maxLevel = {1};
        f(maxLevel, 1, root);
        return maxLevel[0];
    }

    public void f(int[] maxLevel, Integer curLevel, TreeNode root) {
        if (root == null) {
            return;
        }

        maxLevel[0] = Math.max(maxLevel[0], curLevel);
        f(maxLevel, curLevel + 1, root.left);

        f(maxLevel,  curLevel + 1, root.right);

    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        int res = maxDepth3(root);
        System.out.println(res);
    }
}

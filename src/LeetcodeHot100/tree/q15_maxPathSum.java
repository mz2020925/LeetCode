package LeetcodeHot100.tree;

public class q15_maxPathSum {
    public class TreeNode {
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

    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        f(root);
        return maxSum;
    }

    // 0ms，这里利用了Math.max，减少了if判断，就更快一点
    public int f(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int gain = root.val;
        int pathSum = root.val;

        int leftGain = Math.max(f(root.left), 0);
        int rightGain = Math.max(f(root.right), 0);

        gain += Math.max(leftGain, rightGain);


        pathSum += leftGain;
        pathSum += rightGain;

        maxSum = Math.max(maxSum, pathSum);
        return gain;
    }

    // 1ms
    public int f2(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int gain = root.val;
        int pathSum = root.val;

        int leftGain = f2(root.left);
        int rightGain = f2(root.right);
        int bigger = Math.max(leftGain, rightGain);
        if(bigger > 0){
            gain += bigger;
        }

        if(leftGain > 0) {
            pathSum += leftGain;
        }
        if(rightGain > 0) {
            pathSum += rightGain;
        }
        maxSum = Math.max(maxSum, pathSum);
        return gain;
    }
}

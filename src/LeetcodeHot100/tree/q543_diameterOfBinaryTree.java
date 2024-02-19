package LeetcodeHot100.tree;

import org.junit.Test;

public class q543_diameterOfBinaryTree {
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

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null){
            return 0;
        }
        int[] maxD = {0};
        f(root, maxD);
        return maxD[0];
    }

    public class Info {
        public int height;

        public Info(int height) {
            this.height = height;
        }
    }

    public Info f(TreeNode root, int[] maxD) {
        if (root == null) {
            return new Info(0);
        }

        Info left = f(root.left, maxD);
        Info right = f(root.right, maxD);

        maxD[0] = Math.max(maxD[0], left.height + right.height);

        int height = Math.max(left.height, right.height) + 1;
        return new Info(height);

    }

    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);

        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        int res = diameterOfBinaryTree(root);
        System.out.println(res);
    }


}

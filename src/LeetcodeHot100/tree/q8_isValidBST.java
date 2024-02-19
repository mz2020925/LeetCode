package LeetcodeHot100.tree;

import sun.reflect.generics.tree.Tree;

public class q8_isValidBST {
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

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return false;
        }
        boolean[] isBst = {true};
        f(isBst, root);
        return isBst[0];
    }

    public class ReturnData {
        public int min;
        public int max;

        public ReturnData(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    public ReturnData f(boolean[] isBst, TreeNode root) {
        if (root == null) {
            return null;
        }
        ReturnData left = f(isBst, root.left);
        ReturnData right = f(isBst, root.right);

        int min = root.val;
        int max = root.val;

        if (left != null) {
            if (left.max >= root.val) {
                isBst[0] = false;
            }
            min = Math.min(min, left.min);
            max = Math.max(max, left.max);
        }

        if (right != null) {
            if (right.min <= root.val) {
                isBst[0] = false;
            }
            min = Math.min(min, right.min);
            max = Math.max(max, right.max);
        }

        return new ReturnData(min, max);

    }
}

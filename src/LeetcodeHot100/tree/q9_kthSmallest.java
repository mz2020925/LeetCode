package LeetcodeHot100.tree;

import java.util.Stack;

public class q9_kthSmallest {
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

    // 方法1 -- 递归中序遍历，9ms
    public int kthSmallest(TreeNode root, int k) {
        int[] res = new int[]{-1, 0, k};

        f(root, res);
        return res[0];
    }

    public void f(TreeNode root, int[] res) {
        if (root == null || res[0] != -1) {
            return;
        }

        f(root.left, res);

        res[1] += 1;
        if (res[1] == res[2]) {
            res[0] = root.val;
            return;
        }

        System.out.print(root.val + ",");

        f(root.right, res);

    }

    // 方法2 -- 非递归中序遍历，更快，1ms

    public int kthSmallest2(TreeNode root, int k) {
        int index = 0;
        int res = -1;

        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                // 只要有左边界，就一直将左边界压栈
                stack.push(root);
                root = root.left;
            } else {
                // 直到左边界全部压栈，然后就弹出栈顶节点，弹出后向右蹿，
                // 从右子树开始重复上述步骤，把右子树的左边界全部压栈
                root = stack.pop();
                index++;
                if (index == k) {
                    res =  root.val;
                    break;
                }
                root = root.right;

            }
        }
        return res;
    }

    // 方法2，0ms
    int res = -1;
    int index = 0;
    public int kthSmallest3(TreeNode root, int k) {
        f(root, k);
        return res;
    }

    public void f(TreeNode root, int k){
        if(root == null){
            return;
        }

        f(root.left, k);
        index++;
        if(index == k){
            res = root.val;
        }
        f(root.right, k);
    }
}

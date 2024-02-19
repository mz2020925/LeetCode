package LeetcodeHot100.tree;

import java.util.Stack;

public class q11_flatten {
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

    // 方法1，非递归先序遍历，1ms
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = new TreeNode(-1);
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            cur.left = null;
            cur.right = temp;
            cur = cur.right;

            if (temp.right != null) {
                stack.add(temp.right);
            }
            if (temp.left != null) {
                stack.add(temp.left);
            }
        }
    }
    // 方法2，递归先序遍历
    TreeNode temp;
    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        f1(root);
    }

    public void f1(TreeNode root) {
        if (root == null) {
            return;
        }

        f1(root.right);  // 这样写可以保证每层最先访问到最右节点
        f1(root.left);

        root.right = temp;
        root.left = null;
        temp = root;
    }




}

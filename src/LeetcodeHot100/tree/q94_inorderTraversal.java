package LeetcodeHot100.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class q94_inorderTraversal {
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

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        inorder(res, root);
        return res;
    }

    public void inorder(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(res, root.left);
        res.add(root.val);
        inorder(res, root.right);
    }

    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        List<Integer> integers = inorderTraversal(root);
        System.out.println(integers);
    }
}

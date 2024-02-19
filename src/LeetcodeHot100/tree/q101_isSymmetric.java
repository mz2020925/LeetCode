package LeetcodeHot100.tree;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class q101_isSymmetric {

    // 这个思路不对，因为二叉树是有左右孩子位置的，存在几何对称，我这个思路是使用列表对称，所以是错的。下面举了个反例。
    //        1
    //      /   \
    //     2     2
    //    /     /
    //   2     2
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }
        List<TreeNode> list = new ArrayList<>();
        f(list, root);
        // System.out.println(list.stream().map(x -> x.val).collect(Collectors.toList()));
        int start = 0;
        int end = list.size() - 1;
        while (start < end) {
            if (list.get(start).val != list.get(end).val) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public void f(List<TreeNode> list, TreeNode root) {
        if (root == null) {
            return;
        }
        f(list, root.left);
        list.add(root);
        f(list, root.right);
    }

    // 正确做法 -- 双指针
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return false;
        }
        boolean[] flag = {true};

        TreeNode left = root.left;
        TreeNode right = root.right;
        f(flag, left, right);
        return flag[0];
    }

    public void f(boolean[] flag, TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return;
        } else if (left != null && right != null) {
            if (left.val != right.val) {
                flag[0] = false;
            }
            f(flag, left.left, right.right);
            f(flag, left.right, right.left);
        } else {
            flag[0] = false;
        }
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        boolean res = isSymmetric2(root);
        System.out.println(res);
    }
}

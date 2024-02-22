package LeetcodeHot100.tree;

public class q14_lowestCommonAncestor {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;  // 这个递归函数是有返回值的，
        // 之前用的比较多的是无返回值递归遍历二叉树，这种递归是想在递归过程中做些操作。
        // 而这种有返回值的递归，返回值就很重要了，我们可以指定返回哪些东西
        // 在这里，遇见o1或者o2一定返回，如果遇见其他节点，就往下递归，总能递归到null
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);  // 递归方法的返回值在这里
        TreeNode right = lowestCommonAncestor(root.right, p, q);  // 递归方法的返回值在这里
        // 这里我们拿到的返回值left和right要么是null，要么是o1，要么是o2
        if (left != null && right != null) {  // 这就是o1和o2公共祖先是其他节点，这里left和right都不是空，对应着o1和o2
            return root;
        }
        // 这就是o1和o2公共祖先是o1或者o2
        return left != null ? left : right;  // 左非null，右是null，返回左；左是null，右非null，返回右，左右是null，无所谓，返回右
    }
}

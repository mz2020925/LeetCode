package LeetcodeHot100.tree;

import java.util.HashMap;
import java.util.Map;

public class q12_buildTree {
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


    int[] pre;
    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        pre = preorder;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        TreeNode res = f(0, pre.length - 1, 0);
        return res;
    }

    public TreeNode f(int preStart, int preEnd, int inStart) {  // 子树的先序遍历序列的起始索引和末尾索引
        if (preStart > preEnd) {
            return null;
        }

        TreeNode root = new TreeNode(pre[preStart]);  // 先把根节点建立出来
        int rootIndexInOrder = map.get(pre[preStart]);  // 在中序遍历中定位根节点
        int leftSize = rootIndexInOrder - inStart;  // 得到左子树中的节点数目

        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素（中序末尾边界不要了）
        root.left = f(preStart + 1, preStart + leftSize, inStart);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = f(preStart + leftSize + 1, preEnd, rootIndexInOrder + 1);

        return root;
    }
}

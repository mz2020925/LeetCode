package LeetcodeHot100.tree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class q226_invertTree {
    /*
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
    }*/

    public TreeNode invertTree(TreeNode root) {
        if (root == null){
            return root;
        }
        // 宽度优先遍历
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
        return root;
    }

    @Test
    public void test(){
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        PrintBinaryTree.printTree(root);
        invertTree(root);
        PrintBinaryTree.printTree(root);

    }

}

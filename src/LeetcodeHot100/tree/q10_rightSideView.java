package LeetcodeHot100.tree;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class q10_rightSideView {
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

    // 本方法借鉴了 宽度优先遍历求取二叉树最大宽度（不使用HashMap）的思路，1ms
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode curEnd = root;
        TreeNode nextEnd = null;
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode temp = queue.poll();
            if(temp.left != null) {
                queue.add(temp.left);
                nextEnd = temp.left;
            }
            if(temp.right != null) {
                queue.add(temp.right);
                nextEnd = temp.right;
            }
            if(temp == curEnd){
                res.add(temp.val);
                curEnd = nextEnd;
                nextEnd = null;
            }
        }
        return res;
    }

    // 方法2，借鉴递归法先序遍历思路，只是交换了左右节点的先后，0ms
    List<Integer> res = new ArrayList<>();
    public List<Integer> rightSideView2(TreeNode root) {
        if (root == null){
            return new ArrayList<>();
        }

        f(root, 0);  // 从根节点开始访问，这里认为根节点深度是0
        return res;
    }

    public void f(TreeNode root , int depth){
        if(root == null){
            return;
        }

        // 先访问 当前节点，再递归地访问 右子树 和 左子树。
        if(depth == res.size()) { // 如果当前节点所在深度还没有出现在res里，说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中。
            // res.size()表示每层的最右边节点是否已经放入，在这个递归中，每层节点的depth是一样的，
            // 当res.size() == 0的时候，表示没有一层的最右节点还没有放入
            // 当res.size() == 1的时候，表示第0层的节点已经放入，等待depth=1的时候，表示当前节点的层数是1，应该放入res，放入之后，res.size()增加1
            // 当res.size() == 2的时候，表示第0、1层的节点已经放入，等待depth=2的时候，表示当前节点的层数是2，应该放入res，放入之后，res.size()增加1
            res.add(root.val);
        }

        depth++;
        f(root.right, depth);
        f(root.left, depth);
    }

}

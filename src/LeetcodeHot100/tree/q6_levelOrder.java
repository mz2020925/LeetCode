package LeetcodeHot100.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class q6_levelOrder {
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

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null){
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> resItem = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        // 下面这两个指针是实现一层一层遍历的关键，curEnd表示当前层的末尾节点
        // nextEnd在前面推进，找到下一层的末尾节点。当curEnd指针指向的节点被遍历到的时候，nextEnd要把值赋给curEnd，然后nextEnd继续向前面推进。
        TreeNode curEnd = root;
        TreeNode nextEnd = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();

            // 下面两个if使得nextEnd始终保持是最后进栈的，这样就能实现当curEnd出栈的时候，nextEnd指向下一层的末尾节点。
            if (temp.left != null) {
                queue.add(temp.left);
                nextEnd = temp.left;
            }
            if (temp.right != null) {
                queue.add(temp.right);
                nextEnd = temp.right;
            }
            if (temp == curEnd) {
                resItem.add(temp.val);  // 插入每层的最后一个元素
                res.add(resItem);
                resItem = new ArrayList<>();

                curEnd = nextEnd;
                nextEnd = null;
            } else {
                resItem.add(temp.val);  // 插入每层的元素
            }
        }
        return res;
    }

    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);

        /*
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        */
        List<List<Integer>> res = levelOrder(root);
        System.out.println(res);
    }
}

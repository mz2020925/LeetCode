package bilibili_match.p15;

public class Code01_MorrisInJudgeBST {
    // 通过morris中序遍历，判断一个树是否是搜索二叉树：一个二叉树，它的中序遍历是严格升序的，那么就称为搜索二叉树。
    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }

        Node cur = head;
        Node mostRight;
        int preValue = Integer.MIN_VALUE;
        while (cur != null) {
            if (cur.left != null) {
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {  // 当不满足条件时结束循环，得到的就是cur的左子树 的最右节点
                    mostRight = mostRight.right;
                }

                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            // 中序遍历只在这里加了一句打印代码，所以搜索二叉树的判断代码也应该写在这里。
            // 原来时打印行为，现在是比较行为
            if (cur.value <= preValue) {  // 说明出现了非严格升序的
                return false;
            }
            preValue = cur.value;
            cur = cur.right;
        }
        return true;
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(7);
        PrintTree.printTree(head);
        System.out.println("是搜索二叉树吗？" + isBST(head));
    }

}


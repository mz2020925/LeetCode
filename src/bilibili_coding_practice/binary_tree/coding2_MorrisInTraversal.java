package bilibili_coding_practice.binary_tree;

public class coding2_MorrisInTraversal {
    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // morris实现二叉树的中序遍历
    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;  // cur从头开始
        Node mostRight = null;  // 定义左子树的最右节点
        while (cur != null) {  // 3）当cur为空的时候说明遍历结束
            if (cur.left != null) {  // 2）cur有左孩子
                mostRight = cur.left;  // 先找到左子树的最右节点，下面的循环就是这个作用
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {  // 2）a.如果左子树的最右节点 的右指针指向空，让其指向cur，cur左移（这种情况下发生在当前节点是第一次到达）
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    mostRight.right = null;  // 2）a.如果左子树的最右节点 的右指针指向cur，让其指向null，cur右移（这种情况下发生在当前节点是第二次到达）
                    // 当然有的节点会到达1次，有的会到达2次。
                }
            }
            System.out.print(cur.value+" ");
            cur = cur.right;  // 1）cur没有左孩子
        }
        System.out.println();
    }

    // for test -- print tree
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
		head.right = new Node(6);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.right.left = new Node(5);
		head.right.right = new Node(7);
		printTree(head);
        morrisIn(head);
    }
}

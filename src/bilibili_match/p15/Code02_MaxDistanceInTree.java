package bilibili_match.p15;

public class Code02_MaxDistanceInTree {

	public static class Node {  // 二叉树节点
		public int value;  // 在这个问题中节点的值是没有用的，有用的是节点的个数
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int maxDistance(Node head) {
		int[] record = new int[1];
		return posOrder(head, record);
	}
	
	public static class ReturnType{  // 返回值结构
		public int maxDistance;
		public int h;
		
		public ReturnType(int m, int h) {
			this.maxDistance = m;;
			this.h = h;
		}
	}

	// 返回两个信息
	public static ReturnType process(Node head) {
		// 1.base case
		if(head == null) {
			return new ReturnType(0,0);
		}
		// 给子树提要求。类似于黑盒。
		ReturnType leftReturnType = process(head.left);
		ReturnType rightReturnType = process(head.right);
		// 不光只给子树提要求，自己也要实现这个要求。相当于拆黑盒。
		int p1 = leftReturnType.maxDistance;  // 可能性1
		int p2 = rightReturnType.maxDistance;  // 可能性2
		int includeHeadDistance = leftReturnType.h + 1 + rightReturnType.h;  // 可能性3
		int resultDistance = Math.max(Math.max(p1, p2), includeHeadDistance);  // 三者取最大得到最大距离信息
		int hitself  = Math.max(leftReturnType.h, leftReturnType.h) + 1;  // 不要忘了还有高度信息
		return new ReturnType(resultDistance, hitself);
	}

	public static int posOrder(Node head, int[] record) {
		if (head == null) {
			record[0] = 0;
			return 0;
		}
		int lMax = posOrder(head.left, record);
		int maxfromLeft = record[0];
		int rMax = posOrder(head.right, record);
		int maxFromRight = record[0];
		int curNodeMax = maxfromLeft + maxFromRight + 1;
		record[0] = Math.max(maxfromLeft, maxFromRight) + 1;  // 这是高度信息
		return Math.max(Math.max(lMax, rMax), curNodeMax);  // 这是三者取最大得到最大距离信息
	}

	public static void main(String[] args) {
		Node head1 = new Node(1);
		head1.left = new Node(2);
		head1.right = new Node(3);
		head1.left.left = new Node(4);
		head1.left.right = new Node(5);
		head1.right.left = new Node(6);
		head1.right.right = new Node(7);
		head1.left.left.left = new Node(8);
		head1.right.left.right = new Node(9);
		System.out.println(maxDistance(head1));

		Node head2 = new Node(1);
		head2.left = new Node(2);
		head2.right = new Node(3);
		head2.right.left = new Node(4);
		head2.right.right = new Node(5);
		head2.right.left.left = new Node(6);
		head2.right.right.right = new Node(7);
		head2.right.left.left.left = new Node(8);
		head2.right.right.right.right = new Node(9);
		System.out.println(maxDistance(head2));

	}

}

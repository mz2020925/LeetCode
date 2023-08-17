package bilibili_match.p15;

public class Code02_MaxDistanceInTree {
	public static class Info {  // 返回值结构
		public int maxDistance;
		public int height;

		public Info(int dis, int h) {
			this.maxDistance = dis;
			this.height = h;
		}
	}

	// 递归套路中的递归函数，返回两个信息，最大距离和高度
	public static Info process(Node head) {
		// 1.递归终止条件
		if(head == null) {
			return new Info(0,0);
		}
		// 给孩子提要求，你要给我你的最大距离和你的高度。类似于黑盒。
		Info leftInfo = process(head.left);
		Info rightInfo = process(head.right);
		// 不光只给孩子提要求，自己也要实现这个要求，也是要到信息之后的整合自己的信息，这是最终目的。相当于拆黑盒。
		int p1 = leftInfo.maxDistance;  // 可能性1，左树两个信息中的最大距离
		int p2 = rightInfo.maxDistance;  // 可能性2，右树两个信息中的最大距离
		int p3 = leftInfo.height + 1 + rightInfo.height;  // 可能性3
		int maxDistance = Math.max(Math.max(p1, p2), p3);  // 三者取最大得到最大距离信息
		int height  = Math.max(leftInfo.height, rightInfo.height) + 1;  // 不要忘了两个信息中还有高度信息

		// 上面的比较找最大值操作结束之后，自己的信息整合完毕，返回
		return new Info(maxDistance, height);
	}

	public static int maxDistance(Node head) {
		return process(head).maxDistance;
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
		PrintTree.printTree(head1);
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
		PrintTree.printTree(head2);
		System.out.println(maxDistance(head2));
	}

}

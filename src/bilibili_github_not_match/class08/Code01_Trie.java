package bilibili_github_not_match.class08;

public class Code01_Trie {

	// 测试链接 : https://leetcode.cn/problems/implement-trie-ii-prefix-tree/
	// 提交Trie类可以直接通过
	// 原来代码是对的，但是既然找到了直接测试的链接，那就直接测吧
	// 这个链接上要求实现的功能和课上讲的完全一样
	// 该前缀树的路用数组实现
	class Trie {

		class Node {
			public int pass;
			public int end;
			public Node[] nexts;

			public Node() {
				pass = 0;
				end = 0;
				nexts = new Node[26];
			}
		}

		private Node root;

		public Trie() {
			root = new Node();
		}

		// 前缀树中没有存储字符，但却能表示字符串，关键就在于：nexts = new Node[26]与26个字母的对应关系
		public void insert(String word) {
			if (word == null) {
				return;
			}
			char[] str = word.toCharArray();
			Node node = root;
			node.pass++;
			int path = 0;
			for (int i = 0; i < str.length; i++) { // 从左往右遍历字符
				path = str[i] - 'a'; // 由字符，对应成走向哪条路
				if (node.nexts[path] == null) {
					node.nexts[path] = new Node();  // 相当于每个节点都可以伸出去26个边，代表26个字母
				}
				node = node.nexts[path];  // node始终跟着指向当前的最后的哪个节点
				node.pass++;
			}
			node.end++;
		}

		// 删除某个字符串
		public void erase(String word) {
			if (countWordsEqualTo(word) != 0) {
				char[] chs = word.toCharArray();
				Node node = root;
				node.pass--;
				int path = 0;
				for (int i = 0; i < chs.length; i++) {
					path = chs[i] - 'a';
					if (--node.nexts[path].pass == 0) {
						node.nexts[path] = null;  // 当--node.nexts[path].pass == 0的时候，说明nexts[path]节点不需要了，赋值null用于释放空间——java中才能这么写，C++需要手动释放空间
						return;
					}
					node = node.nexts[path];
				}
				node.end--;
			}
		}

		// 查询word字符串加入了几次
		public int countWordsEqualTo(String word) {
			if (word == null) {
				return 0;
			}
			char[] chs = word.toCharArray();
			Node node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.end;
		}

		// 所有加入的字符串，有几个字符串是以pre为前缀的
		public int countWordsStartingWith(String pre) {
			if (pre == null) {
				return 0;
			}
			char[] chs = pre.toCharArray();
			Node node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.pass;
		}
	}
}

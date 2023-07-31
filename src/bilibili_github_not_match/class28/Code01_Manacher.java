package bilibili_github_not_match.class28;

public class Code01_Manacher {

	public static int manacher(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		// "12132" -> "#1#2#1#3#2#"
		char[] str = manacherString(s);
		// 每个字符对应的子串回文半径数组
		int[] pArr = new int[str.length];
		int C = -1;
		// 讲述中：R代表最右的扩成功的位置
		// coding：回文右边界的再往右一个位置，最右的有效区是R-1位置。
		int R = -1;
		int max = Integer.MIN_VALUE;  // 扩出来的最大值
		for (int i = 0; i < str.length; i++) { // 每个位置都求回文半径
			// 第一个大情况，i位置扩成功的区域已经有1，接下来扩不扩成功另说
			// 第二个大情况的小情况1：严格不超过L边界，i位置扩成功的区域已经有pArr[2 * C - i]，接下来扩不成功另说
			// 第二个大情况的小情况2：严格超过L边界，i位置扩成功的区域已经有R - i，接下来扩不成功另说
			// 第二个大情况的小情况3：正好是L边界，i位置扩成功的区域已经有R - i，接下来能不能扩成功另说
			// 上面四种情况综合成这一行代码，使代码更短，条理更清晰，不会影响时间复杂度。
			pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
			while (i + pArr[i] < str.length && i - pArr[i] > -1) {  // 判断当前字符是否扩到超出左右边界
				if (str[i + pArr[i]] == str[i - pArr[i]])
					pArr[i]++;
				else {
					break;
				}
			}
			if (i + pArr[i] > R) {
				R = i + pArr[i];
				C = i;
			}
			max = Math.max(max, pArr[i]);
		}
		// 上述代码处理的是插入特殊字符后的字符串，max对应的处理后字符串的最大回文半径，max-1对应的是原字符串的最大回文直径
		return max - 1;
	}

	public static char[] manacherString(String str) {
		char[] charArr = str.toCharArray();
		char[] res = new char[str.length() * 2 + 1];
		int index = 0;
		for (int i = 0; i != res.length; i++) {
			res[i] = (i & 1) == 0 ? '#' : charArr[index++];  // 偶数位插入特殊字符，奇数位插入原本字符
		}
		return res;
	}

	// for test，传统方法暴力求出最大回文子串长度，然后下面回合Manacher算法的结果进行比较，发现完全一致。
	public static int right(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = manacherString(s);
		int max = 0;
		for (int i = 0; i < str.length; i++) {
			int L = i - 1;
			int R = i + 1;
			while (L >= 0 && R < str.length && str[L] == str[R]) {
				L--;
				R++;
			}
			max = Math.max(max, R - L - 1);
		}
		return max / 2;
	}

	// for test，创建一个随机字符串
	public static String getRandomString(int possibilities, int size) {
		char[] ans = new char[(int) (Math.random() * size) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
		}
		return String.valueOf(ans);
	}

	public static void main(String[] args) {
		int possibilities = 5;
		int strSize = 20;
		int testTimes = 5000000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			String str = getRandomString(possibilities, strSize);
			if (manacher(str) != right(str)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");
	}

}

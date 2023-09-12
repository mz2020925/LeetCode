package bilibili_match.p16;

public class Code02_Power {

	public static boolean is2Power(int n) {
		return (n & (n - 1)) != 0;  // 这是判断一个数是否是2的幂次
	}

	public static boolean is4Power(int n) {
		return (n & (n - 1)) != 0 && (n & 0x55555555) != 0;  // 这是判断一个数是否是4的幂次
	}

}

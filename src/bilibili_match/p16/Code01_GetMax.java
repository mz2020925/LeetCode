package bilibili_match.p16;

public class Code01_GetMax {

	// 输入参数n，只能是 0 或者 1
	// 1 -> 0
	// 0 -> 1
	public static int flip(int n) {
		return n ^ 1;
	}

	// n是整型类型，因为整型
	// 如果n是非负数，返回1
	// 如果n是负数，返回0
	public static int sign(int n) {
		return flip((n >> 31) & 1);
	}

	public static int getMax1(int a, int b) {
		int c = a - b;
		int scA = sign(c);  // a-b为非负，a>=b，scA为1；a-b为负，a<b，scA为0。scA是1表示 a 更大。
		int scB = flip(scA);  // scB的值与scA的值相反。scB是1表示 b 更大。
		return a * scA + b * scB;  // a更大，scA为1，scB为0；b更大，scA为0，scB为1。
		// 把if-else替代成了加号两边互斥的相加
	}
	// 上面这个方法存在问题，a - b可能会溢出（java的整型是由范围的），溢出就不对了。

	// 下面的方法解决了溢出问题
	public static int getMax2(int a, int b) {
		int c = a - b;  // 先不管是否溢出，计算出 a - b。a和b的符号不同的情况下才可能发生溢出。
		int sa = sign(a);
		int sb = sign(b);
		int sc = sign(c);
		int difSab = sa ^ sb;  // a 与 b的符号相同，difSab是 0；a 与 b的符号不同，就是 1。difSab是1表示a和b的符号不同。
		int sameSab = flip(difSab);  // sameSab与difSab相反，sameSab是1表示a和b的符号相同。
		int returnA = difSab * sa + sameSab * sc;  // 这里明确返回a的情况：a和b的符号不同且a是非负，或者a和b的符号相同且a-b是非负。returnA表示a更大
		int returnB = flip(returnA);  // returnB和returnA相反，因为二者是互斥的。returnB是1表示b更大
		return a * returnA + b * returnB;
	}

	public static void main(String[] args) {
		int a = -16;
		int b = 1;
		System.out.println(getMax1(a, b));
		System.out.println(getMax2(a, b));
		a = 2147483647;
		b = -2147480000;
		System.out.println(getMax1(a, b)); // wrong answer because of overflow
		System.out.println(getMax2(a, b));

	}

}

package bilibili_match.p16;

public class Code03_AddMinusMultiDivideByBit {
	public static int add(int a, int b) {
		int sum = a;
		while (b != 0) {
			sum = a ^ b;  //无进位相加
			b = (a & b) << 1;  // 进位信息
			a = sum;  // 准备为了下一次的两数字相加
		}
		return sum;
	}

	public static int negNum(int n) {
		return add(~n, 1);
	}

	public static int minus(int a, int b) {
		return add(a, negNum(b));
	}

	public static int multi(int a, int b) {
		int res = 0;
		while (b != 0) {
			if ((b & 1) != 0) {
				res = add(res, a);
			}
			a <<= 1;
			b >>>= 1;  // >>>表示无符号右移，负数的无符号右移会变成正数，怎么办？
		}
		return res;
	}

	public static boolean isNeg(int n) {
		return n < 0;
	}

	public static int div(int a, int b) {
		int x = isNeg(a) ? negNum(a) : a;  // 把a和b全部转换为正数
		int y = isNeg(b) ? negNum(b) : b;  // 把a和b全部转换为正数
		int res = 0;
		for (int i = 31; i > -1; i = minus(i, 1)) {
			if ((x >> i) >= y) {  //  b（y）左移到快比a大；a（x）右移快比b小；两者是等价的。如果b（y）左移可能会溢出，也可能把1移动到符号位上了，不安全，所以用a（x）右移，x此时是正数。
				res |= (1 << i);  // 记录除法结果里面，1左移i位的位置上是1
				x = minus(x, y << i);  // 减法，确定了i之后，x减去 y左移i位的值
			}
		}
		return isNeg(a) ^ isNeg(b) ? negNum(res) : res;  // 最后再为结果加上符号
	}

	public static int divide(int a, int b) {
		if (b == 0) {
			throw new RuntimeException("divisor is 0");
		}
		if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
			return 1;
		} else if (b == Integer.MIN_VALUE) {  // a和b不全是最小值
			return 0;
		} else if (a == Integer.MIN_VALUE) {
			int res = div(add(a, 1), b);
			return add(res, div(minus(a, multi(res, b)), b));
		} else {
			return div(a, b);
		}
	}

	public static void main(String[] args) {
		int a = (int) (Math.random() * 100000) - 50000;
		int b = (int) (Math.random() * 100000) - 50000;
		System.out.println("a = " + a + ", b = " + b);
		System.out.println(add(a, b));
		System.out.println(a + b);
		System.out.println("=========");
		System.out.println(minus(a, b));
		System.out.println(a - b);
		System.out.println("=========");
		System.out.println(multi(a, b));
		System.out.println(a * b);
		System.out.println("=========");
		System.out.println(divide(a, b));
		System.out.println(a / b);
		System.out.println("=========");

		a = Integer.MIN_VALUE;
		b = 32;
		System.out.println(divide(a, b));
		System.out.println(a / b);

	}
}

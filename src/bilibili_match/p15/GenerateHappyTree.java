package bilibili_match.p15;

public class GenerateHappyTree {
    // for test
	public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
		if (Math.random() < 0.02) {
			return null;  // 一个很小的概率根节点是null，也就是一个空树
		}
		Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));  // [0, 101)的整数
		// Math.random ()是令系统随机选取大于等于 0.0 且小于 1.0 的伪随机 double 值
		// Math.random()*(n-m)+m，生成大于等于m小于n的随机数
		genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
		return boss;
	}

	// for test
	public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
		if (level > maxLevel) {
			return;
		}
		int nextsSize = (int) (Math.random() * (maxNexts + 1));  // [0, 8)的整数
		for (int i = 0; i < nextsSize; i++) {
			Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));  // 创建孩子节点
			e.nexts.add(next);  // 孩子节点添加到nexts
			genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);  // level逐层递增，直到maxLevel
		}
	}

}

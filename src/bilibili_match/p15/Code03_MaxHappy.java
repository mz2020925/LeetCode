package bilibili_match.p15;

public class Code03_MaxHappy {
    public static class Info {
        int laiMaxHappy;
        int buMaxHappy;

        public Info(int lai, int bu) {
            this.laiMaxHappy = lai;
            this.buMaxHappy = bu;
        }
    }

    public static Info process(Employee head) {
        if (head.nexts.isEmpty()) {  // 若为真，说明x是底层员工, 返回这个底层员工的两个信息
            return new Info(head.happy, 0);
        }

        // 最终目的是通过向孩子要信息，整合得到自己的信息
        int lai = head.happy;  // 自己信息的初值
        int bu = 0;  // 自己信息的初值
        for (Employee next : head.nexts) {  // 问它的孩子要信息来整合得到自己的信息
            Info nextInfo = process(next);
            lai += nextInfo.buMaxHappy;  // 自己来，孩子就不来
            bu += Math.max(nextInfo.laiMaxHappy, nextInfo.buMaxHappy);  // 自己不来，孩子可以来，可以不来
        }
        // 上面的for循环结束之后，自己的信息整合完毕，返回
        return new Info(lai, bu);
    }

    public static int maxHappy(Employee head) {
        Info headInfo = process(head);
        return Math.max(headInfo.laiMaxHappy, headInfo.buMaxHappy);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxHappy = 100;
        int maxNexts = 7;
        Employee boss = GenerateHappyTree.genarateBoss(maxLevel, maxNexts, maxHappy);
        System.out.println(maxHappy(boss));
    }
}

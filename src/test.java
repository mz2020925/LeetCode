import org.junit.Test;

import java.util.ArrayList;

public class test {
    @Test
    public void test01() {
        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add("");
        System.out.println(arrayList);
        System.out.println(arrayList.size());
    }

    @Test
    public void main1() {
        int a = 10;

        int b = a++ + ++a + a--;  // 两个点：一是运算符的优先级；二是算式是从左到右运算的。
        System.out.println(a);
        System.out.println(b);
    }

    @Test
    public void main2() {
        int result = test02();
        System.out.println(result);
    }

    public int test02() {
        try {
            int i = 1 / 0;
            return 10;
        } finally {
            return 20;
        }
    }
}

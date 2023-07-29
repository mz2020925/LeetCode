import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {
    @Test
    public void test01() {
        // 递归方式实现求数组最大值
        // int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        // int maxNum = process(candidates, 0, candidates.length - 1);
        // System.out.println(maxNum);

        // List<int[]> listInt = new ArrayList<>();
        // listInt.add(new int[]{1});
        // listInt.add(new int[]{2});
        // listInt.add(new int[]{3});
        // int size = listInt.size();
        // int[][] res = new int[size][];
        // for (int i = 0; i < size; i++) {
        //     res[i] = listInt.get(i);
        // }
        // System.out.println(Arrays.deepToString(res));
        String str = "12345";
        System.out.println(str.charAt(2));
        System.out.println(str.length());
        System.out.println(str.substring(1));
        System.out.println(str.substring(1,5));
        System.out.println(str.substring(str.length()));
    }


    // public int process(int[] array, int L, int R) {
    //     // 递归终止点
    //     if (L == R) {
    //         return array[L];
    //     }
    //
    //     // 递归体,有一个后序遍历的思想在里面
    //     int mid = L + ((R - L) >> 1);  // 求数组的中点索引
    //     int leftMax = process(array, L, mid);
    //     int rightMax = process(array, mid+1, R);
    //     return Math.max(leftMax, rightMax);
    //
    // }
}

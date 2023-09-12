package bilibili_coding_practice.p4;

import org.junit.Test;

import java.util.Arrays;

public class quick_sort_test_123 {
    public int[] getRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];  // 因为Math.random()产生的随机数范围是[0, 1)，所以要乘以(maxSize+1)
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue) * Math.random()); // 作差是为了有正有负
        }
        return arr;
    }

    @Test
    public void test01() {
        int[] arr = new int[]{1, 2, -30, 1, 20, -30, -29, 30, 4, 0, 300, 20, 56};
        quick_sort1.quickSort(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{1, 2, -30, 1, 20, -30, -29, 30, 4, 0, 300, 20, 56};
        quick_sort2.quickSort(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{1, 2, -30, 1, 20, -30, -29, 30, 4, 0, 300, 20, 56};
        quick_sort3.quickSort(arr);
        System.out.println(Arrays.toString(arr));

    }


    @Test
    public void test() {
        int maxSize = 10000;
        int[] arr = new int[maxSize];
        for (int i = 0; i < maxSize; i++) {
            arr[i] = i;
        }
        long startTime = System.nanoTime();
        quick_sort1.quickSort(arr);
        long endTime = System.nanoTime();
        System.out.println("快排1.0执行时长：" + (endTime - startTime) + " 纳秒");

        startTime = System.nanoTime();
        quick_sort2.quickSort(arr);
        endTime = System.nanoTime();
        System.out.println("快排2.0执行时长：" + (endTime - startTime) + " 纳秒");

        startTime = System.nanoTime();
        quick_sort3.quickSort(arr);
        endTime = System.nanoTime();
        System.out.println("快排3.0执行时长：" + (endTime - startTime) + " 纳秒");

    }

}

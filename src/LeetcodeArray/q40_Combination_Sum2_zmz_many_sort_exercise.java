package LeetcodeArray;

import java.util.*;

public class q40_Combination_Sum2_zmz_many_sort_exercise {
    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        // int[] candidates = {2, 5, 2, 1, 2};
        // int target = 5;
        // int[] candidates = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        // int target = 30;


        List<List<Integer>> res = combinationSum2(candidates, target);
        System.out.println(res);

        // int[]转换成Integer，数组转换成List
        // Integer[] integers = Arrays.stream(candidates).boxed().toArray(Integer[]::new);
        // List<Integer> arrayList = new ArrayList<>(integers.length);
        // Collections.addAll(arrayList, integers);
        // System.out.println(arrayList);
    }


    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 创建存储结果的二维List
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }

        // 通过双端队列创建栈
        Deque<Integer> path = new ArrayDeque<>();


        // Arrays.sort(candidates);
        // 自己练习排序算法
        // selectionSort(candidates);  // 选择排序
        // bubbleSort(candidates);  // 冒泡排序
        // insertSort(candidates);  // 插入排序
        mergeSort(candidates);// 归并排序
        System.out.println(Arrays.toString(candidates));

        dfs(candidates, target, path, 0, res);
        return res;
    }


    public static void dfs(int[] candidates, int target, Deque<Integer> path, int begin, ArrayList<List<Integer>> res) {
        // 递归终止点
        if (target == 0) {
            List<Integer> temp = new ArrayList<>(path);
            // if (!res.contains(temp)) {
            //     res.add(temp);
            // }
            res.add(temp);
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            // 大剪枝
            if (target - candidates[i] < 0) {
                break;
            }

            // 小剪枝
            // 在一个for循环中，所有被遍历到的数都是属于一个层级的。我们要让一个层级中，
            // 必须出现且只出现一个2，那么就放过第一个出现重复的2，但不放过后面出现的2。
            // 第一个出现的2的特点就是 i == begin. 第二个出现的2 特点是i > begin.
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }

            path.addLast(candidates[i]);
            dfs(candidates, target - candidates[i], path, i + 1, res);
            path.removeLast();
        }

    }

    /*
    以下排序算法都是升序
     */
    // 选择排序
    public static void selectionSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                array[i] = array[i] ^ array[minIndex];
                array[minIndex] = array[i] ^ array[minIndex];
                array[i] = array[i] ^ array[minIndex];
                // 0 ^ N == N ;   N ^ N == 0
            }
        }
    }

    // 冒泡排序
    public static void bubbleSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    array[j] = array[j] ^ array[j + 1];
                    array[j + 1] = array[j] ^ array[j + 1];
                    array[j] = array[j] ^ array[j + 1];
                }
            }
        }
    }

    // 插入排序
    public static void insertSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 1; i < array.length; i++) {  // 0 ~ i 范围上有序：
            for (int j = i; j > 0 && array[j] < array[j - 1]; j--) {  // 升序：1~0，2~0，3~0，4~0，，，
                array[j] = array[j] ^ array[j - 1];
                array[j - 1] = array[j] ^ array[j - 1];
                array[j] = array[j] ^ array[j - 1];
            }
        }
    }

    // 归并排序
    public static void mergeSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        process(array, 0, array.length - 1);
    }
    public static void process(int[] array, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);  // 找到数组中点索引
        process(array, L, mid);
        process(array, mid + 1, R);
        merge(array, L, mid, R);
    }
    public static void merge(int[] array, int L, int mid, int R) {
        int[] help = new int[R - L + 1];  // 针对原数组的右侧部分的辅助空间
        int i = 0;  // 指向辅助空间help
        int p1 = L;  // 指向原数组左侧部分
        int p2 = mid + 1;  // 指向原数组右侧部分

        while (p1 <= mid && p2 <= R) {
            help[i++] = array[p1] <= array[p2] ? array[p1++] : array[p2++];  // 后自增，使用完当前值后再自增1，但注意如果没使用到它就不会自增1

        }

        while (p1 <= mid) {
            help[i++] = array[p1++];
        }

        while (p2 <= R) {
            help[i++] = array[p2++];
        }

        for (i = 0; i < help.length; i++) {
            array[L + i] = help[i];
        }

    }

    // 堆排序


    // 快速排序

}

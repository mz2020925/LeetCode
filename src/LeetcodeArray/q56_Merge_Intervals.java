package LeetcodeArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class q56_Merge_Intervals {
    public static void main(String[] args) {
        // int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] intervals = {{1, 4}, {4, 5}};
        int[][] res = merge(intervals);
        System.out.println(Arrays.deepToString(res));
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[][]{};
        }

        List<int[]> listInt = new ArrayList<>();
        // 先排序，可以复习一下重写比较器的方法来实现排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int firstNum = o1[0] - o2[0];
                return firstNum == 0 ? (o1[1] - o2[1]) : firstNum;
            }
        });

        // 开始合并
        for (int left = 0; left < intervals.length; ) {  // left表示左边界
            int right = intervals[left][1];  // right表示右边界
            int move = left + 1;  // move是移动指针
            while (move < intervals.length && intervals[move][0] <= right) {
                right = Math.max(intervals[move][1], right);
                move++;
            }
            listInt.add(new int[]{intervals[left][0], right});
            left = move;
        }


        int[][] res = new int[listInt.size()][];
        for (int i = 0; i < listInt.size(); i++) {
            res[i] = listInt.get(i);
        }
        return res;
    }

    public static int[][] merge2(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[][]{};
        }

        List<int[]> listInt = new ArrayList<>();
        // 先排序，可以复习一下重写比较器的方法来实现排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int firstNum = o1[0] - o2[0];
                return firstNum == 0 ? (o1[1] - o2[1]) : firstNum;
            }
        });

        //
        for (int left = 0; left < intervals.length; ) {  // i表示左边界
            int right = intervals[left][1];  // t表示右边界
            int move = left + 1;  // j是移动指针
            while (move < intervals.length && intervals[move][0] <= right) {
                right = Math.max(intervals[move][1], right);
                move++;
            }
            listInt.add(new int[]{intervals[left][0], right});
            left = move;
        }

        //
        for (int left = 0; left < intervals.length; left++) {
            int finalRight = intervals[left][1];
            int move = left + 1;
            while (move < intervals.length && intervals[move][0] <= finalRight) {
                finalRight = Math.max(finalRight, intervals[move][1]);
                move++;
            }
            listInt.add(new int[]{intervals[left][0], finalRight});
            left = move;
        }


        int[][] res = new int[listInt.size()][];
        for (int i = 0; i < listInt.size(); i++) {
            res[i] = listInt.get(i);
        }
        return res;
    }

}

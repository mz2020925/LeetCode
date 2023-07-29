package LeetcodeArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class q57_Insert_Interval {
    public static void main(String[] args) {
        // int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        // int[] newInterval = {4, 8};
        // int[][] intervals = {};
        // int [] newInterval = {5,7};
        int[][] intervals = {{1,5}};
        int[] newInterval = {2,3};
        int[][] res = insert(intervals, newInterval);
        System.out.println(Arrays.deepToString(res));
    }


    public static int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || newInterval == null) {
            return new int[][]{};
        }

        // 将newInterval插入到intervals，并保证有序
        List<int[]> listInt = new ArrayList<>(Arrays.asList(intervals));
        listInt.add(newInterval);
        listInt.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int temp = o1[0] - o2[0];
                return temp == 0 ? (o1[1] - o2[1]) : temp;
            }
        });
        List<int[]> resList = new ArrayList<>();
        // 合并重叠区间
        for (int left = 0; left < listInt.size(); ) {
            int right = listInt.get(left)[1];
            int move = left + 1;
            while (move < listInt.size() && listInt.get(move)[0] <= right) {
                right = Math.max(listInt.get(move)[1], right);
                move++;
            }
            resList.add(new int[]{listInt.get(left)[0], right});
            left = move;
        }
        return resList.toArray(new int[0][0]);

    }
}

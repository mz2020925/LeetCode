package LeetcodeArray;

import java.util.Arrays;

public class q59_Spiral_Matrix2 {

    public static void main(String[] args) {
        int[][] ints = new int[4][4];
        // System.out.println(Arrays.deepToString(ints));
        int[][] res = generateMatrix(1);
        for (int[] re : res) {
            System.out.println(Arrays.toString(re));
        }
    }

    public static int[][] generateMatrix(int n) {
        if (n <= 0) {
            return new int[][]{};
        }
        int[][] res = new int[n][n];
        int left = 0;
        int right = n - 1;
        int up = 0;
        int down = n - 1;
        int num = 1;
        while (true) {
            // 遍历上边界
            for (int i = left; i <= right; i++) {
                res[up][i] = num;
                num++;
            }
            if (++up > down) {
                break;
            }
            // 遍历右边界
            for (int i = up; i <= down; i++) {
                res[i][right] = num;
                num++;
            }
            if (--right < left) {
                break;
            }
            // 遍历下边界
            for (int i = right; i >= left; i--) {
                res[down][i] = num;
                num++;
            }
            if (--down < up) {
                break;
            }
            // 遍历左边界
            for (int i = down; i >= up; i--) {
                res[i][left] = num;
                num++;
            }
            if (++left > right) {
                break;
            }
        }
        return res;
    }
}

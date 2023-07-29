package LeetcodeArray;

import java.util.Arrays;

public class q64_Minimum_Path_Sum {
    public static void main(String[] args) {
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        int res = minPathSum(grid);
        System.out.println(res);
    }

    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length;  // 行数
        int n = grid[0].length;  // 列数
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {  // 第一列
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < n; i++) {  // 第一行
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        System.out.println(Arrays.deepToString(dp));

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j] + grid[i][j], dp[i][j - 1] + grid[i][j]);
            }
        }

        return dp[m-1][n-1];
    }
}

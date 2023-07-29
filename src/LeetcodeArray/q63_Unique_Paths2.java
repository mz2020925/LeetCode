package LeetcodeArray;

import java.util.Arrays;

public class q63_Unique_Paths2 {


    public static void main(String[] args) {
        // int[][] obstacleGrid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        // int[][] obstacleGrid = {{0, 1}, {0, 0}};
        // int[][] obstacleGrid = {{1, 0}};
        int[][] obstacleGrid = {{0, 0}, {1, 1}, {0, 0}};

        int res = uniquePathsWithObstacles(obstacleGrid);
        System.out.println(res);
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 0) {
                dp[i][0] = 1;  // 第1列
            } else {
                for (; i < m; i++) {
                    dp[i][0] = 0;  // 第1列
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 0) {
                dp[0][i] = 1;  // 第1行
            } else {
                for (; i < n; i++) {
                    dp[0][i] = 0;  // 第1行
                }
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = (obstacleGrid[i][j] == 0) ? dp[i - 1][j] + dp[i][j - 1] : 0;
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[m - 1][n - 1];
    }

    // 方法二，优化初始化初始化dp二维数组的第一行与第一列的值
    public static int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        if (obstacleGrid == null) {
            return 0;
        }
        int m = obstacleGrid.length;  // 行数
        int n = obstacleGrid[0].length;  // 列数
        int[][] dp = new int[m][n];

        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {  // 第一列
            dp[i][0] = 1;
        }

        for (int i = 0; i < n && obstacleGrid[0][i] == 0; i++) {  // 第一行
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = (obstacleGrid[i][j] == 0) ? dp[i - 1][j] + dp[i][j - 1] : 0;
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[m - 1][n - 1];
    }
}

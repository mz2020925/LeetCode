package LeetcodeHot100.matrix;

import org.junit.Test;

public class q204_searchMatrix {
    @Test
    public void test() {
        int[][] matrix = {{1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        int target = 20;
        boolean res = searchMatrix(matrix, target);
        System.out.println(res);


    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        // boolean res = false;
        int row = 0;
        int col = n - 1;
        while (row <= m - 1 && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                row++;
            } else if (matrix[row][col] > target) {
                col--;
            }
        }

        return false;
    }
}

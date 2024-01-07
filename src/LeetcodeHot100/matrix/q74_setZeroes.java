package LeetcodeHot100.matrix;

import org.junit.Test;

import java.util.Arrays;

public class q74_setZeroes {
    @Test
    public void test() {
        int[][] matrix = {{0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}};

        setZeroes(matrix);
        for (int[] ints : matrix) {

            System.out.println(Arrays.toString(ints));
        }
    }


    public void setZeroes(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        boolean rowExistZero = false;
        boolean colExistZero = false;

        // 第1行是否存在0
        for (int j = 0; j < col; j++) {
            if (matrix[0][j] == 0) {
                rowExistZero = true;
                break;
            }
        }

        // 第1列是否存在0
        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == 0) {
                colExistZero = true;
                break;
            }
        }

        // 第1行和第1列已经完成判断，现在可以作为标志位了
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (rowExistZero) {
            for (int j = 0; j < col; j++) {
                matrix[0][j] = 0;
            }
        }

        if (colExistZero) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }

    }
}

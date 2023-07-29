package LeetcodeArray;

import java.util.Arrays;

public class q48_Rotate_Image {

    public static void main(String[] args) {
        // int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        // 打印初始矩阵
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
        rotate2(matrix);
        System.out.println();
        // 打印旋转后的矩阵
        // System.out.println(Arrays.deepToString(matrix));
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /*方式一

    (i, j)是矩阵中任意一个元素
    也就是说对于一次旋转来说，(i, j) -> (j,n-1-i) -> (n-1-i, n-1-j) -> (n-1-j, i) ->这四个位置的元素循环交换了位置，
    并没有影响到其他位置。
    也就是说我们只需要遍历矩阵四分之一的部分，然后通过坐标拿到互相交换的4个位置，然后交换它们的元素即可。
     */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < (n >> 1); i++) {
            for (int j = i; j < n - 1 - i; j++) {
                swap(matrix, i, j, j, n - 1 - i);
                swap(matrix, i, j, n - 1 - j, i);
                swap(matrix, n - 1 - j, i, n - 1 - i, n - 1 - j);
            }
        }
    }

    public static void swap(int[][] array, int i, int j, int k, int l) {
        int temp = array[i][j];
        array[i][j] = array[k][l];
        array[k][l] = temp;
    }

    // 方式二：两次翻转
    public static void rotate2(int[][] matrix) {
        if (matrix.length == 0 || matrix.length != matrix[0].length) {
            return;
        }
        // 先沿右上 - 左下的对角线翻转（顺时针270°+ 一次镜像）
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i -1; j++) {  // j < n - i -1保证副对角线上的元素不用再重复交换了
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][n - 1 - i];
                matrix[n - 1 - j][n - 1 - i] = temp;
            }
        }
        // 再沿水平中线上下翻转（顺时针−180°(那就是逆时针)+ 一次镜像），可以实现顺时针 90 度的旋转效果
        for (int i = 0; i < (n >> 1); i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];
                matrix[n - 1 - i][j] = temp;
            }
        }


    }

}

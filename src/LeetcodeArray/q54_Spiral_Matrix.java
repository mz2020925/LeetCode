package LeetcodeArray;

import java.util.ArrayList;
import java.util.List;

public class q54_Spiral_Matrix {
    public static void main(String[] args) {
        // int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        List<Integer> res = spiralOrder(matrix);
        System.out.println(res);
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null) {
            return res;
        }
        int ceil = 0;
        int floor = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        while (true) {
            for (int i = left; i <= right; i++) {  // 遍历上边界
                res.add(matrix[ceil][i]);
            }
            if (++ceil > floor) {  // 上边界遍历完了，要更新
                break;
            }

            for (int i = ceil; i <= floor; i++) {  // 遍历右边界
                res.add(matrix[i][right]);
            }
            if (--right < left) {  // 有边界遍历完了，要更新
                break;
            }

            for (int i = right; i >= left; i--) {  // 遍历下边界
                res.add(matrix[floor][i]);
            }
            if (--floor < ceil) {  // 下边界遍历完了，要更新
                break;
            }

            for (int i = floor; i >= ceil; i--) {  // 遍历左边界
                res.add(matrix[i][left]);
            }
            if (++left > right) {  // 左边界遍历完了，要更新
                break;
            }
        }
        return res;
    }
}

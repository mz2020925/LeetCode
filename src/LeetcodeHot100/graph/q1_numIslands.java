package LeetcodeHot100.graph;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class q1_numIslands {
    // 方法1，利用递归方法 - 这种题目是利用了什么思想呢？标记思想？
    // 还有一种方法需要利用查并集
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    f(i, j, grid);
                }
            }
        }
        return count;
    }

    public void f(int i, int j, char[][] grid) {
        if (i < 0 || i > grid.length - 1) {
            return;
        }
        if (j < 0 || j > grid[0].length - 1) {
            return;
        }
        if (grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        f(i - 1, j, grid);
        f(i + 1, j, grid);
        f(i, j + 1, grid);
        f(i, j - 1, grid);

    }


    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int res = 0;


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    // 下面开始宽度优先遍历
                    Queue<Integer> queue = new LinkedList<>();
                    HashSet<Integer> set = new HashSet<>();
                    queue.add(i * cols + j);
                    set.add(i * cols + j);
                    while (!queue.isEmpty()) {
                        int nodeId = queue.poll();
                        int row = nodeId / cols;
                        int col = nodeId % cols;
                        grid[row][col] = '0';

                        // 下面寻找当前节点的指向节点
                        if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                            int next = (row - 1) * cols + col;
                            if (!set.contains(next)) {
                                set.add(next);
                                queue.add(next);
                            }
                        }
                        if (row + 1 < rows && grid[row + 1][col] == '1') {
                            int next = (row + 1) * cols + col;
                            if (!set.contains(next)) {
                                set.add(next);
                                queue.add(next);
                            }
                        }
                        if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                            int next = row * cols + col - 1;
                            if (!set.contains(next)) {
                                set.add(next);
                                queue.add(next);
                            }
                        }
                        if (col + 1 < cols && grid[row][col + 1] == '1') {
                            int next = row * cols + col + 1;
                            if (!set.contains(next)) {
                                set.add(next);
                                queue.add(next);
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    @Test
    public void test() {
        char[][] grid = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};
        int res = numIslands2(grid);
        System.out.println(res);
    }
}

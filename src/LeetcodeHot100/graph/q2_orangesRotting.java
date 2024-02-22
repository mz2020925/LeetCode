package LeetcodeHot100.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class q2_orangesRotting {
        public int orangesRotting(int[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int minute = 0;
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(grid[i][j] == 2){
                    queue.add(i * cols + j);
                    map.put(i * cols + j, 0);
                }
            }
        }
        // 下面开始宽度优先遍历，这道题不需要两层for循环，因为不要求图的所有节点都得到遍历
        while(!queue.isEmpty()) {
            int nodeId = queue.poll();
            int row = nodeId / cols;
            int col = nodeId % cols;

            // 下面寻找当前节点的指向节点，因为要向下进行宽度优先遍历
            if(row - 1>= 0 && grid[row - 1][col] == 1){
                int next = (row - 1) * cols + col;
                queue.add(next);
                grid[row - 1][col] = 2;
                map.put(next, map.get(nodeId) + 1);

                minute = map.get(next);  // 这里并没有写Math.max(minute, map.get(next))，因为层数越深的节点，一定是后到达，所以minute最后的赋值行为一定是层数最深的节点处
            }
            if(row + 1 < rows && grid[row + 1][col] == 1){
                int next = (row + 1) * cols + col;
                queue.add(next);
                grid[row + 1][col] = 2;
                map.put(next, map.get(nodeId) + 1);

                minute = map.get(next);
            }
            if(col - 1 >= 0 && grid[row][col - 1] == 1){
                int next = row * cols + col - 1;
                queue.add(next);
                grid[row][col - 1] = 2;
                map.put(next, map.get(nodeId) + 1);

                minute = map.get(next);
            }
            if(col + 1 < cols && grid[row][col + 1] == 1){
                int next = row * cols + col + 1;
                queue.add(next);
                grid[row][col + 1] = 2;
                map.put(next, map.get(nodeId) + 1);

                minute = map.get(next);
            }
        }

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(grid[i][j] == 1){
                    return -1;
                }
            }
        }
        return minute;
    }
}

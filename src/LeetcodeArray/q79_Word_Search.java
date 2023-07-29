package LeetcodeArray;

public class q79_Word_Search {
    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        boolean res = exist(board,word);
        System.out.println(res);
    }

    public static boolean exist(char[][] board, String word) {
        if (board == null || word == null || word.length() == 0) {
            return false;
        }

        int[][] mark = new int[board.length][board[0].length];
        int[][] directs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};  // 从左边开始逆时针
        // 外层遍历
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    mark[i][j] = 1;
                    // 内层递归，str.substring(1)表示从索引1开始到最后
                    boolean res = process(i, j, mark, board, word.substring(1), directs);
                    if (res) {
                        return true;
                    } else {
                        mark[i][j] = 0;
                    }
                }
            }
        }
        return false;
    }

    private static boolean process(int curr_i, int curr_j, int[][] mark, char[][] board, String word, int[][] directs) {
        // 递归结束点
        if (word.length() == 0) {
            return true;
        }

        // 递归体
        for (int[] direct : directs) {
            int i = curr_i + direct[0];
            int j = curr_j + direct[1];
            if (0 <= i && i < board.length && 0 <= j && j < board[0].length && board[i][j]==word.charAt(0)){
                if (mark[i][j]==1){
                    continue;
                }
                mark[i][j]=1;
                if (process(i, j, mark, board, word.substring(1),directs)){
                    return true;
                }else {
                    mark[i][j]=0;  // 回溯
                }
            }

        }
        return false;
    }
}


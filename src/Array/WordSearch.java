/*
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, 
 * where "adjacent" cells are those horizontally or vertically neighboring. 
 * The same letter cell may not be used more than once.
 * For example, given board =
 * [
 *  ["ABCE"],
 *  ["SFCS"],
 *  ["ADEE"]
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 */
package Array;

public class WordSearch {

    public boolean exist(char[][] board, String word) {
        if (word.length() == 0 || board == null || board.length < 1 || board[0].length < 1) {
            return false;
        }

        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < visited.length * visited[0].length - 1; i++) {
            visited[i / col][i % col] = false;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (existHelper(board, word, i, j, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean existHelper(char[][] board, String word, int i, int j, boolean[][] visited) {
        if (word.length() == 0) {
            return true;
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
            return false;
        }

        if (board[i][j] != word.charAt(0)) {
            return false;
        }

        if (visited[i][j]) {
            return false;
        }

        visited[i][j] = true;
        boolean res = existHelper(board, word.substring(1), i + 1, j, visited)
                || existHelper(board, word.substring(1), i - 1, j, visited)
                || existHelper(board, word.substring(1), i, j + 1, visited)
                || existHelper(board, word.substring(1), i, j - 1, visited);
        visited[i][j] = false;
        return res;
    }

    //一樣解法
    public boolean exist2(char[][] board, String word) {
        if (word.length() == 0 || board == null || board.length < 1 || board[0].length < 1) {
            return false;
        }

        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (exist(board, i, j, word, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean exist(char[][] board, int i, int j, String word, boolean[][] visited) {
        if (word.length() == 0) {
            return true;
        }
        if (board[i][j] != word.charAt(0)) {
            return false;
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
            return false;
        }
        if (visited[i][j]) {
            return false;
        }

        visited[i][j] = true;
        boolean tmp = false;
        if (i > 0) {
            tmp = tmp || exist(board, i - 1, j, word.substring(1), visited);
        }
        if (i < board.length - 1) {
            tmp = tmp || exist(board, i + 1, j, word.substring(1), visited);
        }
        if (j > 0) {
            tmp = tmp || exist(board, i, j - 1, word.substring(1), visited);
        }
        if (j < board[0].length - 1) {
            tmp = tmp || exist(board, i, j + 1, word.substring(1), visited);
        }
        visited[i][j] = false;
        return tmp;
    }

    public static void main(String[] args) {
        char[][] board = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };

        WordSearch w = new WordSearch();
        System.out.println(w.exist(board, "ABCCED"));
        System.out.println(w.exist(board, "ABCB"));
        System.out.println(w.exist2(board, "ABCCED"));
        System.out.println(w.exist2(board, "ABCB"));
        
    }
}

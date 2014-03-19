/*
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * For example,
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 
 * DFS, BFS 兩種解法
 * DFS 會超時
 */
package DeepFirstSearch;

import java.util.*;

public class SurroundedRegions {

    /*好，普通数字组合dfs和一维dfs我们已经攻克了，那么我们进军到平面上的dfs问题（具体点是矩阵里的dfs）
     * 其实思路都一样的，如果说一维我们只能向前走这一个方向，那么二维，我们就有上，下，左，右四个方向可以走
     * 这道题的切入点就是如果O区域和边界联结上，那么就不可能被X所包围，换句话和边界联接上的O，我们需要保留，
     * 而不和边界联通区域的O，都将被X所吞噬 我们可以从边界运用dfs，只要是O，便转化为另一个字母D，
     * 这样，等dfs都结束后，最后我们再扫一遍board，如果还存在O，便证明是处于中间区域，都转化为X, 
     * 所有字母D的区域，便都是和边界相邻的O，我们将他们还原成O
     */
    public void solveDFS(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int row = board.length;
        int col = board[0].length;
        for (int j = 0; j < col; j++) {
            dfs(board, 0, j);
            dfs(board, row - 1, j);        //第一行和最后一行  
        }
        for (int i = 1; i < row - 1; i++) {
            dfs(board, i, 0);
            dfs(board, i, col - 1);       //第一列和最后一列抛去和上一个循环相交的格子  
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'D') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(char[][] board, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'D';
        dfs(board, x - 1, y);
        dfs(board, x + 1, y);
        dfs(board, x, y - 1);
        dfs(board, x, y + 1);
    }

    /*
     * So how BFS is conducted, we can think from out to inside. 
     * Because the boundary 'O' are definitely "live" (have a path out) element,
     * so, we BFS from each 'O' in the boundary, mark all its four directions (where is also 'O') as "live". 
     * If you think here, you almost done, the standard BFS using a queue 
     * (here I use vector for simplicity) can solve the problem. 
     * Last step is to flip "O" to "X" because there is no way out, 
     * and flip "P"(live) to "O", because it has a path out. See code (big case) for details.
     * All the test cases are passed.
     */
    public void solveBFS(char[][] board) {
        int m = board.length;
        if (m == 0) {
            return;
        }
        int n = board[0].length;
        Queue<Integer> sti = new LinkedList<Integer>();
        Queue<Integer> stj = new LinkedList<Integer>();
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                sti.offer(i);
                stj.offer(0);
            }
            if (board[i][n - 1] == 'O') {
                sti.offer(i);
                stj.offer(n - 1);

            }
        }
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                sti.offer(0);
                stj.offer(j);
            }
            if (board[m - 1][j] == 'O') {
                sti.offer(m - 1);
                stj.offer(j);

            }
        }

        while (!sti.isEmpty()) {
            int x = sti.poll();
            int y = stj.poll();
            board[x][y] = 'P';

            if (x - 1 >= 0 && board[x - 1][y] == 'O') {
                sti.offer(x - 1);
                stj.offer(y);
            }
            if (x + 1 < m && board[x + 1][y] == 'O') {
                sti.offer(x + 1);
                stj.offer(y);
            }
            if (y - 1 >= 0 && board[x][y - 1] == 'O') {
                sti.offer(x);
                stj.offer(y - 1);
            }
            if (y + 1 < n && board[x][y + 1] == 'O') {
                sti.offer(x);
                stj.offer(y + 1);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'P') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public static void main(String[] args) {
        SurroundedRegions s = new SurroundedRegions();
        char[][] board = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        s.solveDFS(board);
        for (char[] ca : board) {
            for (char c : ca) {
                System.out.print(c + " ");
            }
            System.out.println("");
        }
    }
}

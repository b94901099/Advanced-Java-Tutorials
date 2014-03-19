/*
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * Empty cells are indicated by the character '.'.
 * You may assume that there will be only one unique solution.
 * 
 * 首先这道题的思路的比较直观，我们先把空着的格子统计出来放到一个ArrayList<int> 里面, 然后开始运用dfs
 * 首先每当填入一个数字的时候我们需要用isValid来验证，只有不冲突的情况下才能添加进格子里
 * dfs 函数返回值定义为boolean 
 * if(dfs(empty,board,cur+1,len))运行成功往上一层返回true，如果不成功, 把刚填入的数字抹去再去试下一个数字，
 * 如果这一层的数字都不成功，返回false, 跳回上一层进行修改，最后如果cur==len 的时候所有格子都填完返回true，一路返回true上去完成任务
 * 几点注意的地方： 
 * 1. 验证小九宫格的时候，坐标为｛3*(row/3)+i/3 , 3*(col/3)+i%3｝
 * 2. char到int的转换 - '0'
 */
package DeepFirstSearch;

import java.util.*;

public class SudokuSolver {

    public void solveSudoku(char[][] board) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    list.add(i * 9 + j);
                }
            }
        }
        int len = list.size();
        solveHelper(board, list, len, 0);
    }

    private boolean solveHelper(char[][] board, ArrayList<Integer> list, int len, int cur) {
        if (cur == len) {
            return true;
        }
        int position = list.get(cur);
        int row = position / 9;
        int col = position % 9;
        for (int val = 1; val <= 9; val++) {
            if (isValid(board, row, col, val)) {
                board[row][col] = (char) (val + '0');
                if (solveHelper(board, list, len, cur + 1)) {
                    return true;
                }
                board[row][col] = '.';
            }
        }
        return false;
    }

    private boolean isValid(char[][] board, int row, int col, int val) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] - '0' == val) {
                return false;
            }
            if (board[i][col] - '0' == val) {
                return false;
            }
            int row_s = 3 * (row / 3) + i / 3;
            int col_s = 3 * (col / 3) + i % 3;
            if (board[row_s][col_s] - '0' == val) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        SudokuSolver s = new SudokuSolver();
        s.solveSudoku(board);

        for (char[] ca : board) {
            for (char c : ca) {
                System.out.print(c + ", ");
            }
            System.out.println("");
        }
    }
}

package Implement;

import java.util.*;

public class ValidSudoku {

    public static boolean isValidSudoku(char[][] board) {
        int n = board.length;

        HashSet<Character> hs;
        //row
        for (int i = 0; i < n; i++) {
            hs = new HashSet<Character>();
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '.' && hs.contains(board[i][j])) {
                    return false;
                }
                hs.add(board[i][j]);
            }
        }
        //column
        for (int j = 0; j < n; j++) {
            hs = new HashSet<Character>();
            for (int i = 0; i < n; i++) {
                if (board[i][j] != '.' && hs.contains(board[i][j])) {
                    return false;
                }
                hs.add(board[i][j]);
            }
        }
        //3X3
        int col_offset;
        int row_offset;
        for (int k = 0; k < n; k++) {
            row_offset = (3 * k) % 9;
            col_offset = 3 * (k / 3);

            hs = new HashSet<Character>();
            for (int i = row_offset; i < row_offset + 3; i++) {
                for (int j = col_offset; j < col_offset + 3; j++) {
                    if (board[i][j] != '.' && hs.contains(board[i][j])) {
                        return false;
                    }
                    hs.add(board[i][j]);
                }
            }
        }
        return true;
    }


    // 省空間解法
    public static boolean isValidSudoku2(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) {
            return false;
        }

        ArrayList<Integer> nums = new ArrayList<Integer>();

        for (int position = 0; position < 81; position++) {

            int row = position / 9;
            int col = position % 9;

            if (board[row][col] != '.') {
                nums.add(position);
            }
        }

        for (int num = 0; num < nums.size(); num++) {
            int position = nums.get(num);
            int row = position / 9;
            int col = position % 9;
            char cur = board[row][col];

            for (int i = 0; i < 9; i++) {
                if (board[row][i] == cur && i != col) {
                    return false;
                }
                if (board[i][col] == cur && i != row) {
                    return false;
                }
                int x = 3 * (row / 3) + i / 3;
                int y = 3 * (col / 3) + i % 3;
                if (board[x][y] == cur) {
                    if (x == row && y == col) continue;
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        char[][] test = {"....5..1.".toCharArray(), ".4.3.....".toCharArray(),
                ".....3..1".toCharArray(), "8......2.".toCharArray(), "..2.7....".toCharArray(),
                ".15......".toCharArray(), ".....2...".toCharArray(), ".2.9.....".toCharArray(),
                "..4......".toCharArray()};

        char[][] test2 = {".87654321".toCharArray(), "2........".toCharArray(),
                "3........".toCharArray(), "4........".toCharArray(), "5........".toCharArray(),
                "6........".toCharArray(), "7........".toCharArray(), "8........".toCharArray(), "9........".toCharArray()};

        System.out.println(isValidSudoku(test));
        System.out.println(isValidSudoku(test2));
        System.out.println(isValidSudoku2(test));
        System.out.println(isValidSudoku2(test2));

    }
}

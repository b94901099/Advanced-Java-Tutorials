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

    public static void main(String[] args) {
        char[][] test = {"....5..1.".toCharArray(), ".4.3.....".toCharArray(),
            ".....3..1".toCharArray(), "8......2.".toCharArray(), "..2.7....".toCharArray(),
            ".15......".toCharArray(), ".....2...".toCharArray(), ".2.9.....".toCharArray(),
            "..4......".toCharArray()};
        System.out.println(isValidSudoku(test));
    }
}

/*
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, 
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 * For example, given board =
 * [
 * ["ABCE"],
 * ["SFCS"],
 * ["ADEE"]
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 * 
 * 思路
 * BFS + DFS
 * 每次找一个字符，使用BFS
 * 第一个字符的位置遍历整个board得到
 * 从第i个找第i+1个只能查找周围没有被使用过的，用DFS
 * 用数组标记每个位置是否已被使用
 * 题意很简单，给你一个二维字母的数组，可以上下左右走，查找是否某个单词是否存在。同一位置的字母不可以被使用多次。
 * 解题思路： 类似于迷宫，递归回溯。需要一个辅助数组记录走过的位置，防止同一个位置被使用多次。
 */
package DeepFirstSearch;

import java.util.*;

public class WordSearch {

    public boolean exist(char[][] board, String word) {
        if (word.length() == 0) {
            return false;
        }

        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                visited[i][j] = false;
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    visited[i][j] = true;
                    if (word.length() == 1 || search(board, i, j, word.substring(1), visited)) {
                        return true;
                    }
                    visited[i][j] = false;
                }
            }
        }
        return false;
    }

    boolean search(char[][] board, int i, int j, String word, boolean[][] visited) {
        if (word.length() == 0) {
            return true;
        }

        int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int k = 0; k < direction.length; k++) {
            int ii = i + direction[k][0];
            int jj = j + direction[k][1];
            if (ii >= 0 && ii < board.length
                    && jj >= 0 && jj < board[i].length
                    && board[ii][jj] == word.charAt(0)
                    && visited[ii][jj] == false) {
                visited[ii][jj] = true;
                if (word.length() == 1 || search(board, ii, jj, word.substring(1), visited)) {
                    return true;
                }
                visited[ii][jj] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] c = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word1 = "ABCCED";
        String word2 = "SEE";
        String word3 = "ABCB";
        WordSearch w = new WordSearch();
        System.out.println(w.exist(c, word1));
        System.out.println(w.exist(c, word2));
        System.out.println(w.exist(c, word3));
    }
}

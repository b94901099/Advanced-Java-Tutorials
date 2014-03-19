package DeepFirstSearch;

import java.util.ArrayList;

class NQueensSolution2 {

    /* 我们把这一题分成几个小问题
     * 1. 传统的dfs递归
     * 2. 验证放置Queen的地方是否合法
     * 3. 输出Board结果
     * 这么做的好处就是，一开始，我们不用建立一个庞大的Board，我们选用一个数组对应Board里面的每一行，数组每一个值对应这一行放置Queen的列号
     * 比如： int[ ] {3,1,4,2} 代表放置的地点分别为[1,3], [2,1], [3,4], [2,4] 
     * 这么一来，我们用很简单的用数组表示了整个Board，而且在isValid函数里判断的时候会非常简洁，而且把输出Board单独隔离了出来
     * 
     * 来分析一下代码：
     * dfs的循环是指这一行里，从第一列到最后一列放置的所有可能，如果放置的地点通过isValid验证，
     * 通过cur+1进入下一行进行递归， 如果没通过验证，试下一个位置，如果所有位置都不Valid，跳回上一层
     * 采用int[ ]的好处是，每一次我们只需改变一个数字就相当于改变了棋子的放置位置
     * isValid函数，首先int[ ]代表行，这样就避免了每一行出现重复的Queen 
     * （因为你不可能在一个int里面放2个值）这样简化了验证 接下来我们只需验证列和对角线
     * 验证列的时候，要验证这一行之前的行有没有重复的（注意是验证之前的喔）
     * 验证对角线，根据对角线性质，长 ＝ 宽 那么我们不难写出 Math.abs(loc[i] - loc[cur]) == (cur - i) 
     * 最后loc［］里面记录的是解的信息（如果有解）我们把它转换成String, 输出Board即可
     */
    
    public ArrayList<String[]> solveNQueens(int n) {
        ArrayList<String[]> res = new ArrayList<String[]>();
        int[] loc = new int[n];
        dfs(res, loc, 0, n);
        return res;
    }

    public void dfs(ArrayList<String[]> res, int[] loc, int cur, int n) {
        if (cur == n) {
            printboard(res, loc, n);
        } else {
            for (int i = 0; i < n; i++) {
                loc[cur] = i;
                if (isValid(loc, cur)) {
                    dfs(res, loc, cur + 1, n);
                }
            }
        }
    }

    public boolean isValid(int[] loc, int cur) {
        for (int i = 0; i < cur; i++) {
            if (loc[i] == loc[cur] || Math.abs(loc[i] - loc[cur]) == (cur - i)) {
                return false;
            }
        }
        return true;
    }

    public void printboard(ArrayList<String[]> res, int[] loc, int n) {
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            String row = new String();
            for (int j = 0; j < n; j++) {
                if (j == loc[i]) {
                    row += "Q";
                } else {
                    row += ".";
                }
            }
            ans[i] = row;
        }
        res.add(ans);
    }
}

class NQueensSolution1 {

    private String[] drawChessBoard(ArrayList<Integer> cols) {
        String[] chessBoard = new String[cols.size()];
        for (int i = 0; i < cols.size(); i++) {
            chessBoard[i] = "";
            for (int j = 0; j < cols.size(); j++) {
                if (j == cols.get(i)) {
                    chessBoard[i] += "Q";
                } else {
                    chessBoard[i] += ".";
                }
            }
        }
        return chessBoard;
    }

    private boolean isValid(ArrayList<Integer> cols, int col) {
        int row = cols.size();
        for (int i = 0; i < row; i++) {
            // same column
            if (cols.get(i) == col) {
                return false;
            }
            //left-top to right-bottom
            if (i - cols.get(i) == row - col) {
                return false;
            }
            //right-top to left-bottom
            if (i + cols.get(i) == row + col) {
                return false;
            }
        }
        return true;
    }

    private void search(int n, ArrayList<Integer> cols, ArrayList<String[]> result) {
        if (cols.size() == n) {
            result.add(drawChessBoard(cols));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (!isValid(cols, col)) {
                continue;
            }
            cols.add(col);
            search(n, cols, result);
            cols.remove(cols.size() - 1);
        }
    }

    public ArrayList<String[]> solveNQueens(int n) {
        ArrayList<String[]> result = new ArrayList<String[]>();
        search(n, new ArrayList<Integer>(), result);
        return result;
    }

    public static void main(String[] args) {
    }
}

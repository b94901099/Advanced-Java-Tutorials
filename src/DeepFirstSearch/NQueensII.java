/*
 * Follow up for N-Queens problem.
 * Now, instead outputting board configurations, return the total number of distinct solutions.
 */
package DeepFirstSearch;

public class NQueensII {

    int totalNum;

    public int totalNQueens(int n) {
        totalNum = 0;
        int[] loc = new int[n];
        helper(loc, n, 0);
        return totalNum;
    }

    private void helper(int[] loc, int n, int pos) {
        if (pos == n) {
            totalNum++;
        }
        for (int i = 0; i < n; i++) {
            loc[pos] = i;
            if (isValid(loc, pos)) {
                helper(loc, n, pos + 1);
            }
        }
    }

    private boolean isValid(int[] loc, int pos) {
        for (int i = 0; i < pos; i++) {
            if (loc[i] == loc[pos] || Math.abs(loc[pos] - loc[i]) == pos - i)
                return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
    }
}

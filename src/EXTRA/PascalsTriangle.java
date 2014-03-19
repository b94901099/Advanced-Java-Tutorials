/*
 * Given numRows, generate the first numRows of Pascal's triangle.
 * For example, given numRows = 5,
 * Return
 * [
 *     [1],
 *    [1,1],
 *   [1,2,1],
 *  [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 */
package EXTRA;

import java.util.*;

public class PascalsTriangle {

    public ArrayList<ArrayList<Integer>> generate(int numRows) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (numRows < 1) {
            return result;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        result.add(new ArrayList(list));
        list.clear();

        if (numRows == 1) {
            return result;
        }

        for (int i = 1; i < numRows; i++) {
            list.add(1);
            for (int j = 0; j < result.get(i - 1).size() - 1; j++) {
                list.add(result.get(i - 1).get(j) + result.get(i - 1).get(j + 1));
            }
            list.add(1);
            result.add(new ArrayList(list));
            list.clear();
        }

        return result;
    }

    public static void main(String[] args) {
    }
}

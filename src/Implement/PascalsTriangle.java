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
package Implement;

import EXTRA.*;
import java.util.*;

public class PascalsTriangle {

    public ArrayList<ArrayList<Integer>> generate2(int numRows) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (numRows < 1) {
            return result;
        }
        ArrayList<Integer> level = new ArrayList<Integer>();
        level.add(1);
        result.add(new ArrayList(level));

        if (numRows == 1) {
            return result;
        }

        for (int i = 1; i < numRows; i++) {
            level = result.get(i - 1);
            ArrayList<Integer> newLevel = new ArrayList<Integer>();

            newLevel.add(1);
            for (int j = 0; j < result.get(i - 1).size() - 1; j++) {
                newLevel.add(level.get(j) + level.get(j + 1));
            }
            newLevel.add(1);
            result.add(newLevel);
        }
        return result;
    }

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
        PascalsTriangle p = new PascalsTriangle();
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> result2 = new ArrayList<ArrayList<Integer>>();
        result = p.generate(5);
        result2 = p.generate(5);
        for (ArrayList<Integer> level : result) {
            System.out.println(level);
        }
        for (ArrayList<Integer> level : result2) {
            System.out.println(level);
        }
    }
}

/*
 * Given an index k, return the kth row of the Pascal's triangle.
 * For example, given k = 3,
 * Return [1,3,3,1].
 * Note: Could you optimize your algorithm to use only O(k) extra space?
 */
package Implement;

import java.util.*;

public class PascalsTriangleII {

    public ArrayList<Integer> getRow(int rowIndex) {
                
        ArrayList<Integer> result = new ArrayList<Integer>();        

        if (rowIndex < 0) {
            return result;
        }
        
        Queue<Integer> queue = (Queue<Integer>) new LinkedList<Integer>();
        queue.offer(1);
        
        for (int i = 0; i < rowIndex; i++) {
            int size = queue.size();
            queue.offer(1);
            int f1 = 0, f2 = 0;
            f1 = queue.poll();
            for (int j = 0; j < size - 1; j++) {
                f2 = queue.poll();
                queue.offer(f1 + f2);
                f1 = f2;
            }
            queue.offer(1);
        }

        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }
        return result;
    }

    public static void main(String[] args) {
        PascalsTriangleII p = new PascalsTriangleII();
        System.out.println(p.getRow(0));
        System.out.println(p.getRow(1));
        System.out.println(p.getRow(2));
    }
}

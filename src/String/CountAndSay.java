/*
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth sequence.
 * Note: The sequence of integers will be represented as a string.
 */
package String;

import java.util.*;

public class CountAndSay {

    public String countAndSay(int n) {
        if (n <= 0) {
            return "1";
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(1);
        for (int i = 1; i < n; i++) {
            int tmp = queue.poll();
            int count = 1;
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                if (tmp == queue.peek()) {
                    queue.poll();
                    count++;
                } else {
                    queue.offer(count);
                    queue.offer(tmp);
                    tmp = queue.poll();
                    count = 1;
                }
            }
            queue.offer(count);
            queue.offer(tmp);
        }
        
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            sb.append(queue.poll());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        CountAndSay c = new CountAndSay();
        System.out.println(c.countAndSay(1));
    }
}

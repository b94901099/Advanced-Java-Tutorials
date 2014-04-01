/*
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * For example, Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * Your algorithm should run in O(n) complexity.
 * 
 * 用Hashset, 依照 set.contains(i - 1) 跟 set.contains(i + 1) 增加長度
 */
package Array;

import java.util.*;

public class LongestConsecutiveSequence {

    public static int longestConsecutive(int[] num) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i : num) {
            set.add(i);
        }

        int maxLength = 0;
        for (int i : num) {
            set.remove(i);
            int length = 1;
            int left = i;
            int right = i;
            while (set.contains(left - 1)) {
                left--;
                set.remove(left);
                length++;
            }
            while (set.contains(right + 1)) {
                right++;
                set.remove(right);
                length++;
            }
            maxLength = Math.max(length, maxLength);
        }
        
        return maxLength;
    }

    public static void main(String[] args) {
        int[] test = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(test));
    }
}

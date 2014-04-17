package String;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * For example, the longest substring without repeating letters for "abcabcbb" is "abc",
 * which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
 *
 * 基本思路是维护一个窗口，每次关注窗口中的字符串，在每次判断中，左窗口和右窗口选择其一向前移动。
 * 同样是维护一个HashSet, 正常情况下移动右窗口，如果没有出现重复则继续移动右窗口，如果发现重复字符，
 * 则说明当前窗口中的串已经不满足要求，继续移动有窗口不可能得到更好的结果，此时移动左窗口，直到不再有重复字符为止，
 * 中间跳过的这些串中不会有更好的结果，因为他们不是重复就是更短。因为左窗口和右窗口都只向前，
 * 所以两个窗口都对每个元素访问不超过一遍，因此时间复杂度为O(2*n)=O(n),是线性算法。
 * 空间复杂度为HashSet的size,也是O(n). 代码如下：
 *
 */

import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {

    //一樣的想法, 用 for 作, rightBound 不停前進
    public int lengthOfLongestSubstring2(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        HashSet<Character> set = new HashSet<Character>();
        int leftBound = 0;
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                while (leftBound < i && s.charAt(leftBound) != s.charAt(i)) {
                    set.remove(s.charAt(leftBound));
                    leftBound++;
                }
                leftBound++;
            } else {
                set.add(s.charAt(i));
                max = Math.max(max, i - leftBound + 1);
            }
        }
        return max;
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null && s.length() == 0) {
            return 0;
        }
        HashSet<Character> set = new HashSet<Character>();
        int max = 0;
        int leftBound = 0;
        int rightBound = 0;
        while (rightBound < s.length()) {
            if (set.contains(s.charAt(rightBound))) {
                if (max < rightBound - leftBound) {
                    max = rightBound - leftBound;
                }
                while (s.charAt(leftBound) != s.charAt(rightBound)) {
                    set.remove(s.charAt(leftBound));
                    leftBound++;
                }
                leftBound++;
            } else {
                set.add(s.charAt(rightBound));
            }
            rightBound++;
        }
        max = Math.max(max, rightBound - leftBound);
        return max;
    }

    public static void main(String[] args) {
    }
}

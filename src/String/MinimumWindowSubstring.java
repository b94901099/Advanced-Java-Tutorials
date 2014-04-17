package String;
/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 * Note:
 * If there is no such window in S that covers all characters in T, return the emtpy string "".
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */

import java.util.*;

public class MinimumWindowSubstring {

    public String minWindow3(String S, String T) {
        if (S == null || T == null || S.length() == 0 || T.length() == 0) {
            return "";
        }
        HashMap<Character, Integer> demand = new HashMap<Character, Integer>();
        for (int i = 0; i < T.length(); i++) {
            if (demand.containsKey(T.charAt(i))) {
                demand.put(T.charAt(i), demand.get(T.charAt(i)) + 1);
            } else {
                demand.put(T.charAt(i), 1);
            }
        }

        int count = 0;  // 若 count == T.length() 則需求已滿足, 可以開始收縮 leftBound
        int leftBound = 0;
        String result = "";
        int minLen = S.length() + 1;
        HashMap<Character, Integer> supply = new HashMap<Character, Integer>();

        for (int i = 0; i < S.length(); i++) {
            char cur = S.charAt(i);
            if (!demand.containsKey(cur)) {
                continue;
            }

            if (supply.containsKey(cur)) {
                supply.put(cur, supply.get(cur) + 1);
            } else {
                supply.put(cur, 1);
            }

            if (supply.get(cur) <= demand.get(cur)) {
                count++;
            }

            while (count == T.length()) {
                char leftChar = S.charAt(leftBound);
                if (supply.containsKey(leftChar)) {
                    if (supply.get(leftChar) > demand.get(leftChar)) {
                        supply.put(leftChar, supply.get(leftChar) - 1);
                    } else {
                        supply.put(leftChar, supply.get(leftChar) - 1);
                        count--;
                        if (i - leftBound + 1 < minLen) {
                            minLen = i - leftBound + 1;
                            result = S.substring(leftBound, i + 1);
                        }
                    }
                }
                leftBound++;
            }
        }
        return result;
    }

    /*
     * 这道题是字符串处理的题目，和Substring with Concatenation of All Words思路非常类似，同样是建立一个字典，
     * 然后维护一个窗口。区别是在这道题目中，因为可以跳过没在字典里面的字符（也就是这个串不需要包含且仅仅包含字典里面的字符
     * ，有一些不在字典的仍然可以满足要求），所以遇到没在字典里面的字符可以继续移动窗口右端，
     * 而移动窗口左端的条件是当找到满足条件的串之后，一直移动窗口左端直到有字典里的字符不再在窗口里。
     * 在实现中就是维护一个HashMap，一开始key包含字典中所有字符，value就是该字符的数量，
     * 然后遇到字典中字符时就将对应字符的数量减一。算法的时间复杂度是O(n),其中n是字符串的长度，
     * 因为每个字符再维护窗口的过程中不会被访问多于两次。空间复杂度则是O(字典的大小)，也就是代码中T的长度。
     */
    public String minWindow(String S, String T) {
        if (S == null || T == null || S.length() == 0 || T.length() == 0) {
            return "";
        }
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < T.length(); i++) {
            if (map.containsKey(T.charAt(i))) {
                map.put(T.charAt(i), map.get(T.charAt(i)) + 1);
            } else {
                map.put(T.charAt(i), 1);
            }
        }
        // map 中儲存 T 所有出現的 char 和次數, 可以視為"需求"

        int count = 0;  // 若 count == T.length() 則需求已滿足, 可以開始收縮 leftBound
        int leftBound = 0;
        String result = "";
        int minLen = S.length() + 1;

        for (int i = 0; i < S.length(); i++) {
            if (map.containsKey(S.charAt(i))) { // 只有碰到需求的 char 才會進行動作, 其他的 char 毫無反應
                map.put(S.charAt(i), map.get(S.charAt(i)) - 1); // 該 char 需求量 - 1
                if (map.get(S.charAt(i)) >= 0) { // 若對該 char 還有需求, 則count++, 若該 char 在範圍中已經過剩, 則毫無反應
                    count++;
                }
                while (count == T.length()) { //找齊了 T 裡所有 char 需求, 開始收縮 leftBound
                    if (map.containsKey(S.charAt(leftBound))) { // 若在 leftBound 上的 char 是毫不重要的, 則直接 leftBound++, 有影響的才進行動作
                        map.put(S.charAt(leftBound), map.get(S.charAt(leftBound)) + 1); // 因為刪除了 leftBound 上的 char, 該 char 需求 + 1
                        if (map.get(S.charAt(leftBound)) > 0) { // 如果該 char 需求無法滿足, 則 count--
                            count--;
                            if (minLen > i - leftBound + 1) { // 算出 leftBound 在進入"需求無法滿足"前的最後位置, 並更新 minLength
                                result = S.substring(leftBound, i + 1);
                                minLen = i - leftBound + 1;
                            }
                        }
                    }
                    leftBound++;
                }
            }
        }
        return result;
    }

    /* Sol 2
     * The general idea is that we find a window first, not necessarily the minimum, 
     * but it’s the first one we could find, traveling from the beginning of S. 
     * We could easily do this by keeping a count of the target characters we have found. 
     * After finding an candidate solution, we try to optimize it.
     * We do this by going forward in S and trying to see if we could replace the first character of our candidate. 
     * If we find one, we then find a new candidate and we update our knowledge about the minimum.
     * We keep doing this until we reach the end of S. For the giving example:
     * We start with our very first window: “ADOBEC”, windowSize = 6. We now have “A”:1, “B”:1, “C”:1 （保存在needToFind数组里）
     * We skip the following character “ODE” since none of them is in our target T.
     * We then see another “B” so we update “B”:2. Our candidate solution starts with an “A” 
     * so getting another “B” cannot make us a “trade”. 
     * （体现在代码就是只有满足hasFound[S.charAt(start)] > needToFind[S.charAt(start)]) 才能移动左指针start）
     * We then see another “A” so we update “A”:2. Now we have two “A”s and we know we only need 
     * 1. If we keep the new position of this “A” and disregard the old one, we could move forward of our starting position of window. We move from A->D->O->B. Can we keep moving? Yes, since we know we have 2 “B”s so we can also disregard this one. So keep moving until we hit “C”: we only have 1 “C” so we have to stop. Therefore, we have a new candidate solution, “CODEBA”. Our new map is updated to “A”:1, “B”:1, “C”:1.
     * We skip the next “N” （这里忽略所有不在T的字符：用needToFind[S.charAt(start)] == 0来判断）
     * and we arrive at “C”. Now we have two “C”s so we can move forward the starting position of last candidate:
     * we move along this path C->O->D->E until we hit “B”. We only have one “B”
     * so we have to stop. We have yet another new candidate, “BANC”.
     * We have hit the end of S so we just output our best candidate, which is “BANC”
     */
    public String minWindow2(String S, String T) {
        // 因为处理的是字符，所以可以利用ASCII字符来保存  
        int[] needToFind = new int[256];    // 保存T中需要查找字符的个数，该数组一旦初始化完毕就不再改动  
        int[] hasFound = new int[256];      // 保存S中已经找到字符的个数，该数组会动态变化  

        for (int i = 0; i < T.length(); i++) { // 初始化needToFind为需要查找字符的个数，  
            needToFind[T.charAt(i)]++;  // 如例子中T为ABC，则将会被初始化为：needToFind[65]=1, nTF[66]=2, nTF[67]=3  
        }

        int count = 0;      // 用于检测第一个符合T的S的字串  
        int minWindowSize = Integer.MAX_VALUE;  // 最小窗口大小  
        int start = 0, end = 0;     // 窗口的开始喝结束指针  
        String window = "";         // 最小窗口对应的字串  

        for (; end < S.length(); end++) {        // 用end来遍历S字符串  
            if (needToFind[S.charAt(end)] == 0) { // 表示可以忽略的字符，即除了T(ABC)外的所有字符  
                continue;
            }
            char c = S.charAt(end);
            hasFound[c]++;      // 找到一个需要找的字符  

            if (hasFound[c] <= needToFind[c]) {    // 如果找到的已经超过了需要的，就没必要继续增加count  
                count++;
            }
            if (count == T.length()) {    // 该窗口中至少包含了T  
                while (needToFind[S.charAt(start)] == 0 || // 压缩窗口，往后移start指针，一种情况是start指针指的都是可忽略的字符  
                        hasFound[S.charAt(start)] > needToFind[S.charAt(start)]) {    // 另一种情况是已经找到字符的个数超过了需要找的个数，因此可以舍弃掉多余的部分  
                    if (hasFound[S.charAt(start)] > needToFind[S.charAt(start)]) {
                        hasFound[S.charAt(start)]--;        // 舍弃掉多余的部分  
                    }
                    start++;    // 压缩窗口  
                }

                if (end - start + 1 < minWindowSize) {     // 保存最小窗口  
                    minWindowSize = end - start + 1;
                    window = S.substring(start, end + 1);
                }
            }
        }
        return window;
    }

    public static void main(String[] args) {
        String S = "ADOBECODEBANC";
        String T = "ABC";
        MinimumWindowSubstring m = new MinimumWindowSubstring();
        System.out.println(m.minWindow(S, T));
        System.out.println(m.minWindow2(S, T));
        System.out.println(m.minWindow3(S, T));
        System.out.println(m.minWindowTest(S, T));

    }

    public String minWindowTest(String S, String T) {
        if (S == null || T == null || S.length() == 0 || T.length() == 0) {
            return "";
        }
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < T.length(); i++) {
            if (map.containsKey(T.charAt(i))) {
                map.put(T.charAt(i), map.get(T.charAt(i)) + 1);
            } else {
                map.put(T.charAt(i), 1);
            }
        }

        int count = 0;
        int leftBound = 0;
        int minLength = S.length() + 1;
        String result = "";

        for (int i = 0; i < S.length(); i++) {
            if (map.containsKey(S.charAt(i))) {
                map.put(S.charAt(i), map.get(S.charAt(i)) - 1);
                if (map.get(S.charAt(i)) >= 0) {
                    count++;
                }
                while (count == T.length()) {
                    if (map.containsKey(S.charAt(leftBound))) {
                        map.put(S.charAt(leftBound), map.get(S.charAt(leftBound)) + 1);
                        if (map.get(S.charAt(leftBound)) > 0) {
                            count--;
                            if (minLength > i - leftBound + 1) {
                                minLength = i - leftBound + 1;
                                result = S.substring(leftBound, i + 1);
                            }
                        }
                    }
                    leftBound++;
                }
            }
        }
        return result;
    }
}

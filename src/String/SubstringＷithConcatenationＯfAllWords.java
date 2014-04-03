/*
 * You are given a string, S, and a list of words, L, that are all of the same length.
 * Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once 
 * and without any intervening characters.
 * For example, given:
 * S: "barfoothefoobarman"
 * L: ["foo", "bar"]
 * You should return the indices: [0,9].
 * (order does not matter).
 * 
 * 
 * 这道题看似比较复杂，其实思路和Longest Substring Without Repeating Characters差不多。
 * 因为那些单词是定长的，所以本质上和单一个字符一样。和Longest Substring Without Repeating Characters的区别
 * 只在于我们需要维护一个字典，然后保证目前的串包含字典里面的单词有且仅有一次。
 * 思路仍然是维护一个窗口，如果当前单词在字典中，则继续移动窗口右端，否则窗口左端可以跳到字符串下一个单词了。
 * 假设源字符串的长度为n，字典中单词的长度为l。因为不是一个字符，所以我们需要对源字符串所有长度为l的子串进行判断。
 * 做法是i从0到l-1个字符开始，得到开始index分别为i, i+l, i+2*l, ...的长度为l的单词。
 * 这样就可以保证判断到所有的满足条件的串。因为每次扫描的时间复杂度是O(2*n/l)
 * (每个单词不会被访问多于两次，一次是窗口右端，一次是窗口左端)，
 * 总共扫描l次（i=0, ..., l-1)，所以总复杂度是O(2*n/l*l)=O(n)，是一个线性算法。
 * 空间复杂度是字典的大小，即O(m*l)，其中m是字典的单词数量。
 */
package String;

import java.util.*;

public class SubstringＷithConcatenationＯfAllWords {

    public ArrayList<Integer> findSubstring(String S, String[] L) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (S == null || S.length() < 1 || L == null || L.length < 1 || S.length() < L.length * L[0].length()) {
            return result;
        }

        HashMap<String, Integer> demand = new HashMap<String, Integer>();
        for (int i = 0; i < L.length; i++) {
            if (demand.containsKey(L[i])) {
                demand.put(L[i], demand.get(L[i]) + 1);
            } else {
                demand.put(L[i], 1);
            }
        }
        // 把所需出現的字和其次數紀錄下來

        for (int i = 0; i < L[0].length(); i++) { // for loop 以 0,3,6; 1,4,7; 2,5,8 的方式 go through S
            HashMap<String, Integer> supply = new HashMap<String, Integer>();
            int count = 0;
            int left = i;
            for (int j = i; j <= S.length() - L[0].length(); j += L[0].length()) {
                String str = S.substring(j, j + L[0].length());
                if (!demand.containsKey(str)) { // 若此字並非在所需之中, 則left bound 往下一個字跳, 之前累積的都清除, 因為不能有任何 intervene
                    supply.clear();
                    count = 0;
                    left = j + L[0].length();
                } else { // 若此字在所需之中, 則須判斷需求是否恰好滿足
                    if (supply.containsKey(str)) {
                        supply.put(str, supply.get(str) + 1);
                    } else {
                        supply.put(str, 1);
                    } // 先把字加進 supply

                    if (supply.get(str) <= demand.get(str)) { // 若尚未或恰好能滿足需求, 則 count++
                        count++;
                    } else { // 若是供給過剩, 則開始縮減 left bound
                        while (supply.get(str) > demand.get(str)) {
                            String temp = S.substring(left, left + L[0].length());
                            supply.put(temp, supply.get(temp) - 1); // 無須判斷 curMap.containsKey, 因為只有符合需求的字才會被加進來
                            if (supply.get(temp) < demand.get(temp)) { // 若刪除此字會使影響需求
                                count--;
                            }
                            left = left + L[0].length();
                        }
                    }

                    if (count == L.length) { // 若 L 中每個字的需求都被恰好滿足, 則紀錄下此 left, 並繼續往前
                        result.add(left);
                        String temp = S.substring(left, left + L[0].length());
                        supply.put(temp, supply.get(temp) - 1);
                        left = left + L[0].length();
                        count--;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
    }
}

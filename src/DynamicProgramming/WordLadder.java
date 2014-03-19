/*
 * Given two words (start and end), and a dictionary, find the length of shortest transformation sequence 
 * from start to end, such that: Only one letter can be changed at a time
 * Each intermediate word must exist in the dictionary For example,
 * Given:
 * start = "hit"
 * end = "cog"
 * dict = ["hot","dot","dog","lot","log"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters
 */
package DynamicProgramming;

import java.util.*;

/**
 * 最直观的思路就是DFS，每次变成新的单词，同时从字典中删除新单词然后不断递归。
 * 大数据时候超时。因为要求最短的变换次数，所以可以使用BFS，和DFS不一样不一次走到最深。逐个遍历变换一次、二次、三次的所有单词。
 * 思考一下DFS和BFS的区别，举个最简单的例子，111 -> 311。 DFS的话，111->112，之后需要DFS
 * 112的所有变形。同理111->113之后还要遍历113的所有变形。 而BFS的话， 111 112 113 121 131 211 311
 * 只需要6次就能找到最终结果。 实现方面的一个技巧是用2个队列，一个保存变形之后的单词，一个表示当前单词所经过的变换次数，
 * 两个对了保持同步。这样就能记录下来当前单词是经过了多少次变换得到的。
 */
public class WordLadder {

    public int ladderLength(String start, String end, HashSet<String> dict) {
        if (start.equals(end) == true || start == null || end == null) {
            return 0;
        }

        Queue<String> queue = new LinkedList<String>();
        //记录当前节点所在的层数，也就是经过了多少次变换  
        Queue<Integer> distanceQueue = new LinkedList<Integer>();
        queue.offer(start);
        distanceQueue.offer(1);
        
        while (!queue.isEmpty()) {
            String str = queue.poll();
            int ret = (int) distanceQueue.poll();
            //对当前单词的每个字符逐个测试，生成的新单词是否存在于字典中  
            for (int i = 0; i < str.length(); i++) {
                char[] strCharArr = str.toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {
                    strCharArr[i] = c;
                    String newWord = new String(strCharArr);
                    if (dict.contains(newWord)) {
                        if (newWord.equals(end)) {
                            return ret + 1;
                        }
                        queue.offer(newWord);
                        distanceQueue.offer(ret + 1);
                        dict.remove(newWord);
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
    }
}

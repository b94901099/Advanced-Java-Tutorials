/*
 * Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that: 
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the dictionary
 * For example,
 * Given:
 * start = "hit"
 * end = "cog"
 * dict = ["hot","dot","dog","lot","log"]
 * Return
 * [
 * ["hit","hot","dot","dog","cog"],
 * ["hit","hot","lot","log","cog"]
 * ]
 * Note:
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 */
package DynamicProgramming;

import java.util.*;

/*
 * 整个过程可以分成两个部分：先通过BFS从start找到end，在找的过程中需要记录前驱单词，再用DFS反向找回完整路径。
 * 但是用Java实现上述过程会遇到TLE。为了能让用时尽可能短，有如下几点需要注意的地方：
 * 1. 由于最后生成路径的时候，需要从end找到start构造ArrayList，即使用LinkList来协助构造，性能也不好。
 * 解决办法：不从start找end了，反过来从end找start，找到后，再从start往end构造路径，性能会有明显提升。
 * 2. 在BFS过程中，需要替换String的每一位字符，先转换成char数组再操作，性能也会有明显提升。
 * 3. 在BFS过程中，注意避免一些不必要的搜索，具体细节参考如下代码。
 * 最终在LeetCode测试中，Java实现达到了800ms用时。此外，双向BFS也可以用来提升性能，而且效果会十分显著，这里没有具体实现。
 */
public class WordLadderII {

    class WordWithLevel {

        String word;
        int level;

        public WordWithLevel(String word, int level) {
            this.word = word;
            this.level = level;
        }
    }
    private String end;
    private ArrayList<ArrayList<String>> result;
    private Map<String, List<String>> nextMap;

    public ArrayList<ArrayList<String>> findLadders(String start, String end,
            HashSet<String> dict) {
        result = new ArrayList<ArrayList<String>>();
        this.end = end;

        // unvisited words set  
        Set<String> unVisited = new HashSet<String>();
        unVisited.addAll(dict);
        unVisited.add(start);
        unVisited.remove(end);

        // used to record the map info of <word : the words of next level>  
        nextMap = new HashMap<String, List<String>>();
        for (String e : unVisited) {
            nextMap.put(e, new ArrayList<String>());
        }

        // BFS to search from the end to start  
        Queue<WordWithLevel> queue = new LinkedList<WordWithLevel>();
        queue.add(new WordWithLevel(end, 0));
        boolean found = false;
        int finalLevel = Integer.MAX_VALUE;
        int currentLevel = 0;
        int preLevel = 0;
        Set<String> visitedWordsInThisLevel = new HashSet<String>();
        while (!queue.isEmpty()) {
            WordWithLevel wordWithLevel = queue.poll();
            String word = wordWithLevel.word;
            currentLevel = wordWithLevel.level;
            if (found && currentLevel > finalLevel) {
                break;
            }
            if (currentLevel > preLevel) {
                unVisited.removeAll(visitedWordsInThisLevel);
            }
            preLevel = currentLevel;
            char[] wordCharArray = word.toCharArray();
            for (int i = 0; i < word.length(); ++i) {
                char originalChar = wordCharArray[i];
                boolean foundInThisCycle = false;
                for (char c = 'a'; c <= 'z'; ++c) {
                    wordCharArray[i] = c;
                    String newWord = new String(wordCharArray);
                    if (c != originalChar && unVisited.contains(newWord)) {
                        nextMap.get(newWord).add(word);
                        if (newWord.equals(start)) {
                            found = true;
                            finalLevel = currentLevel;
                            foundInThisCycle = true;
                            break;
                        }
                        if (visitedWordsInThisLevel.add(newWord)) {
                            queue.add(new WordWithLevel(newWord,
                                    currentLevel + 1));
                        }
                    }
                }
                if (foundInThisCycle) {
                    break;
                }
                wordCharArray[i] = originalChar;
            }
        }

        if (found) {
            // dfs to get the paths  
            ArrayList<String> list = new ArrayList<String>();
            list.add(start);
            getPaths(start, list, finalLevel + 1);
        }
        return result;
    }

    private void getPaths(String currentWord, ArrayList<String> list, int level) {
        if (currentWord.equals(end)) {
            result.add(new ArrayList<String>(list));
        } else if (level > 0) {
            List<String> parentsSet = nextMap.get(currentWord);
            for (String parent : parentsSet) {
                list.add(parent);
                getPaths(parent, list, level - 1);
                list.remove(list.size() - 1);
            }
        }
    }
}


/*
 * 超時
 * BFS搜索，节点的数据结构包含：当前单词、记录变换路径的hash表、记录变换路径的ArrayList。
 * 搜索所有和当前单词只差一个字母的单词，查询新单词是否在字典中同时是否已经存在于变换路径中，
 * 如果在字典中同时不在已有的变换路径中，把新单词放入队列，继续BFS。
 */
class Pair {

    String str;
    ArrayList<String> path;
    HashSet<String> hash;

    Pair(String str, ArrayList<String> path, HashSet<String> hash) {
        this.str = str;
        this.path = path;
        this.hash = hash;
    }
}

class WordLadderIIOverTime {

    public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        ArrayList<String> path = new ArrayList<String>();
        HashSet<String> hash = new HashSet<String>();
        if (start == null || end == null || start.length() != end.length()) {
            return result;
        }
        Queue queue = new LinkedList<Pair>();
        path.add(start);
        hash.add(start);
        Pair node = new Pair(start, path, hash);
        int min_length = -1;
        queue.add(node);
        while (!queue.isEmpty()) {
            node = (Pair) queue.poll();
            String str = node.str;
            for (int i = 0; i < str.length(); i++) {
                char[] strCharArr = str.toCharArray();
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    if (strCharArr[i] == ch) {
                        continue;
                    }
                    strCharArr[i] = ch;
                    String newWord = new String(strCharArr);
                    if (newWord.equals(end) == true) {
                        path = node.path;
                        path.add(newWord);
                        if (min_length == -1) {
                            min_length = path.size();
                        }
                        if (path.size() > min_length) {
                            return result;
                        } else {
                            result.add(path);
                            //dict.remove(newWord);  
                            continue;
                        }
                    } else {
                        if (dict.contains(newWord) && !node.hash.contains(newWord)) {
                            path = new ArrayList<String>(node.path);
                            hash = new HashSet<String>(node.hash);
                            path.add(newWord);
                            hash.add(newWord);
                            Pair newnode = new Pair(newWord, path, hash);
                            queue.add(newnode);
                            //dict.remove(newWord);  
                        }
                    }
                }
            }
        }
        return result;
    }
}

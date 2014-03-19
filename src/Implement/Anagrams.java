package Implement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Anagrams {

    public ArrayList<String> anagrams(String[] strs) {
        if (strs == null) {
            return null;
        }
        if (strs.length == 1) {
        }

        Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for (int i = 0; i < strs.length; i++) {
            char[] tmp = strs[i].toCharArray();
            Arrays.sort(tmp);
            String sorted = new String(tmp);
            if (map.get(sorted) == null) {
                ArrayList<String> list = new ArrayList<String>();
                list.add(strs[i]);
                map.put(sorted, list);
            } else {
                map.get(sorted).add(strs[i]);
            }
        }
        ArrayList<String> result = new ArrayList<String>();
        for (ArrayList<String> tmplist : map.values()) {
            if (tmplist.size() > 1) {
                for (String s : tmplist) {
                    result.add(s);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
    }
}

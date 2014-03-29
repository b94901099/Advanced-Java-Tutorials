/*
 * Letter Combinations of a Phone Number Total Accepted:
 * Given a digit string, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 */

package DeepFirstSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class LetterCombinationOfAPhoneNumberSol2 {

    /* 1. 数字1所对应的是空集，不要忘了
     * 2. tmp直接添加字符传入递归参数，这样返回后不用截取tmp
     */
    
    public ArrayList<String> letterCombinations(String digits) {

        ArrayList<String> result = new ArrayList<String>();

        if (digits == null) {
            return result;
        }

        Map<Character, char[]> map = new HashMap<Character, char[]>();
        map.put('0', new char[]{});
        map.put('1', new char[]{});
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});

        combineHelper(map, digits, "", result, 0);
        return result;
    }

    private void combineHelper(Map<Character, char[]> map, String digits, String tmp, ArrayList<String> result, int pos) {
        if (tmp.length() == digits.length()) {
            result.add(tmp);
            return;
        }

        for (int j = 0; j < map.get(digits.charAt(pos)).length; j++) {
            combineHelper(map, digits, tmp + map.get(digits.charAt(pos))[j], result, pos + 1);
        }
    }
}

public class LetterCombinationOfAPhoneNumber {

    public ArrayList<String> letterCombinations(String digits) {

        ArrayList<String> result = new ArrayList<String>();

        if (digits == null) {
            return result;
        }

        Map<Character, char[]> map = new HashMap<Character, char[]>();
        map.put('0', new char[]{});
        map.put('1', new char[]{});
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});

        StringBuilder sb = new StringBuilder();
        helper(map, digits, sb, result);
        return result;
    }

    private void helper(Map<Character, char[]> map, String digits,
            StringBuilder sb, ArrayList<String> result) {
        if (sb.length() == digits.length()) {
            result.add(sb.toString());
            return;
        }

        for (char c : map.get(digits.charAt(sb.length()))) {
            sb.append(c);
            helper(map, digits, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public ArrayList<String> letterCombinations2(String digits) {
        ArrayList<String> results = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        String[] board = new String[10];
        board[0] = " ";
        board[1] = "1";
        board[2] = "abc";
        board[3] = "def";
        board[4] = "ghi";
        board[5] = "jkl";
        board[6] = "mno";
        board[7] = "pqrs";
        board[8] = "tuv";
        board[9] = "wxyz";
        helper(results, sb, digits, board, 0);
        return results;
    }

    public void helper(ArrayList<String> results, StringBuilder sb, String digits, String[] board, int pos) {
        if (pos == digits.length()) {
            results.add(sb.toString());
            return;
        }
        String s = board[digits.charAt(pos) - '0'];
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            helper(results, sb, digits, board, pos + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        LetterCombinationOfAPhoneNumber l = new LetterCombinationOfAPhoneNumber();
        ArrayList<String> list = l.letterCombinations("123");
        for (String s : list) {
            System.out.print(s + ", ");
        }

        System.out.println("");
        System.out.println("sol2");
        ArrayList<String> list2 = l.letterCombinations2("123");
        for (String s : list2) {
            System.out.print(s + ", ");
        }

        System.out.println("");
        System.out.println("Solnew");
        LetterCombinationOfAPhoneNumberSol2 l2 = new LetterCombinationOfAPhoneNumberSol2();
        ArrayList<String> list3 = l2.letterCombinations("123");
        for (String s : list3) {
            System.out.print(s + ", ");
        }
    }
}

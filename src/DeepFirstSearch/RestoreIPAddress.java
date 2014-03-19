/*
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * For example: Given "25525511135", return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 * 
 * 首先我们要明确传统IP 地址的规律是分4个Part，每个Part是从0-255的数字
 * 0-255的数字，转换成字符，即每个Part 可能由一个字符组成，二个字符组成，或者是三个字符组成。那这又成为组合问题了，dfs便呼之欲出
 * 所以我们写一个For循环每一层从1个字符开始取一直到3个字符，再加一个isValid的函数来验证取的字符是否是合法数字，
 * 如果是合法的数字，我们再进行下一层递归，否则跳过。
 * 几点注意的地方：
 * 1. 在验证字符串是否是数字的时候，要注意0的情况，001，010，03都是非法的。
 * 所以，如果第一位取出来是0，那么我们就判断字符串是否是"0"，不是的情况都是非法的
 * 2. 取字符串的时候，注意位数不够的问题，不仅<4, 而且<s.length()
 * 3. 注意substring的范围
 * 4. 字符串转换成数字 Integer.parseInt(); 
 * 5. 别忘了IP 地址里面的 "."
 * 6. 到第4个Part的时候我们就可以整体验证剩下的所有字符串（因为第4个Part最后一定要取到结尾才是正确的字符串）
 */

package DeepFirstSearch;

import java.util.*;

public class RestoreIPAddress {

    public ArrayList<String> restoreIpAddresses(String s) {
        ArrayList<String> result = new ArrayList<String>();
        if (s == null || s.length() < 4 || s.length() > 12) {
            return result;
        }
        restoreHelper(s, "", result, 0);
        return result;
    }

    private void restoreHelper(String s, String tmp, ArrayList<String> result, int count) {
        if (count == 3 && isValid(s)) {
            result.add(tmp + s);
            return;
        }
        for (int i = 1; i < 4 && i < s.length(); i++) {
            String substr = s.substring(0, i);
            if (isValid(substr)) {
                restoreHelper(s.substring(i), tmp + substr + ".", result, count + 1);
            }
        }
    }

    private boolean isValid(String s) {
        if (s.charAt(0) == '0') {
            return s.equals("0");
        }
        int tmp = Integer.parseInt(s);
        if (tmp < 256 && tmp > 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        RestoreIPAddress r = new RestoreIPAddress();
        ArrayList<String> result = r.restoreIpAddresses("25525511135");
        for (String s : result) {
            System.out.println(s);
        }
    }
}

/*
 * Given a roman numeral, convert it to an integer.
 * Input is guaranteed to be within the range from 1 to 3999.
 * 
 * 
 * 题目是把罗马数字转成整形返回，其实对罗马数字只知道I 到X， 特意去维基百科查了下。发现了如下几条规则：
 * 羅馬數字共有7個，即I（1）、V（5）、X（10）、L（50）、C（100）、D（500）和M（1000）。
 * 按照下述的規則可以表示任意正整數。需要注意的是罗马数字中没有“0”，與進位制無關。一般認為羅馬數字只用來記數，而不作演算。
 * (1)相同的数字连写，所表示的数等于这些数字相加得到的数，如：Ⅲ = 3；
 * (2)小的数字在大的数字的右边，所表示的数等于这些数字相加得到的数， 如：Ⅷ = 8；Ⅻ = 12；
 * (3)小的数字，（限于Ⅰ、X 和C）在大的数字的左边，所表示的数等于大数减小数得到的数，如：Ⅳ= 4；Ⅸ= 9；
 * 于是我就想，其实这套计数规则也很简单，就是出现几次就是几倍，唯一注意的一点就是IV是4，IX是9，等等小数在左代表减法。
 * 然后就想到了，其实一个遍历O(log(n))时间，把所有字符过一遍，和加在一起. 然后把所有特殊情况都除去，就是实际的值。
 * 比如IV出现了，在后来计数里IV算了I+V=6 但是其实是应该减去的，所以相当于多加了2次，-2就可以了。
 */
package String;

import java.util.*;

public class RomanToInteger {

    // 把原先的sol用 hash map 優化速度
    public int romanToInt(String s) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("IV", -2);
        map.put("IX", -2);
        map.put("XL", -20);
        map.put("XC", -20);
        map.put("CD", -200);
        map.put("CM", -200);

        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1) {
                String substr = s.substring(i, i + 2);
                if (map.containsKey(substr)) {
                    sum += map.get(substr);
                }
            }
            
            switch (s.charAt(i)) {
                case 'I':
                    sum += 1;
                    break;
                case 'V':
                    sum += 5;
                    break;
                case 'X':
                    sum += 10;
                    break;
                case 'L':
                    sum += 50;
                    break;
                case 'C':
                    sum += 100;
                    break;
                case 'D':
                    sum += 500;
                    break;
                case 'M':
                    sum += 1000;
                    break;
            }
        }
        return sum;
    }

    public int romanToInt2(String s) {
        int sum = 0;
        if (s.indexOf("IV") != -1) {
            sum -= 2;
        }
        if (s.indexOf("IX") != -1) {
            sum -= 2;
        }
        if (s.indexOf("XL") != -1) {
            sum -= 20;
        }
        if (s.indexOf("XC") != -1) {
            sum -= 20;
        }
        if (s.indexOf("CD") != -1) {
            sum -= 200;
        }
        if (s.indexOf("CM") != -1) {
            sum -= 200;
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'M') {
                sum += 1000;
            }
            if (s.charAt(i) == 'D') {
                sum += 500;
            }
            if (s.charAt(i) == 'C') {
                sum += 100;
            }
            if (s.charAt(i) == 'L') {
                sum += 50;
            }
            if (s.charAt(i) == 'X') {
                sum += 10;
            }
            if (s.charAt(i) == 'V') {
                sum += 5;
            }
            if (s.charAt(i) == 'I') {
                sum += 1;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        RomanToInteger r = new RomanToInteger();
        String s1 = "MCMVXI";
        System.out.println(r.romanToInt(s1));
        System.out.println(r.romanToInt2(s1));
    }
}

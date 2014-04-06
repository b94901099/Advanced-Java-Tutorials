package String;

import java.util.*;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 * <p/>
 * n=4
 * P    I    N
 * A  L S  I G
 * Y A  H R
 * P    I
 * <p/>
 * N=5
 * P       H
 * A     S I
 * Y   I   R
 * P L     I G
 * A       N
 * <p/>
 * 这道题是cc150里面的题目了，其实比较简单，只要看出来他其实每个zigzag是2*m-1个字符就可以，
 * 这里m是结果的行的数量。接下来就是对于每一行先把往下走的那一列的字符加进去，然后有往上走的字符再加进去即可。
 * 时间复杂度是O(n),空间复杂度是O(1),代码如下
 */
public class ZigZagConversion {

    public String convert2(String s, int nRows) {
        if(s == null || s.length()==0 || nRows <=0)
            return "";
        if(nRows == 1)
            return s;
        StringBuilder res = new StringBuilder();
        int size = 2*nRows-2;
        for(int i=0;i<nRows;i++)
        {
            for(int j=i;j<s.length();j+=size)
            {
                res.append(s.charAt(j));
                if(i!=0 && i!=nRows-1 && j+size-2*i<s.length())
                    res.append(s.charAt(j+size-2*i));
            }
        }
        return res.toString();
    }

    public String convert(String s, int nRows) {
        if (s == null || s.length() == 0 || nRows <= 0)
            return "";
        if (s.length() <= nRows || nRows <= 1)
            return s;

        StringBuilder result = new StringBuilder();
        for (int row = 0; row < nRows; row++) {
            if (row == 0 || row == nRows - 1) {
                for (int i = row; i < s.length(); i += (nRows - 1) * 2) {
                    result.append(s.charAt(i));
                }
            } else {
                for (int i = row; i < s.length(); i += (nRows - 1) * 2) {
                    result.append(s.charAt(i));
                    if (i + (nRows - row - 1) * 2 < s.length()) {
                        result.append(s.charAt(i + (nRows - row - 1) * 2));
                    }
                }
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        ZigZagConversion z = new ZigZagConversion();
        System.out.println(z.convert("PAYPALISHIRING", 3));
    }
}

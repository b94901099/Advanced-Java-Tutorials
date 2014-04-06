package Implement;

/**
 * Implement atoi to convert a string to an integer.
 * Hint: Carefully consider all possible input cases. If you want a challenge,
 * please do not see below and ask yourself what are the possible input cases.
 * Notes: It is intended for this problem to be specified vaguely
 * (ie, no given input specs). You are responsible to gather all the input requirements up front.
 * spoilers alert... click to show requirements for atoi.
 * Requirements for atoi:
 * The function first discards as many whitespace characters as necessary until
 * the first non-whitespace character is found. Then, starting from this character,
 * takes an optional initial plus or minus sign followed by as many numerical digits as possible,
 * and interprets them as a numerical value.
 * The string can contain additional characters after those that form the integral number,
 * which are ignored and have no effect on the behavior of this function.
 * If the first sequence of non-whitespace characters in str is not a valid integral number,
 * or if no such sequence exists because either str is empty or it contains only whitespace characters,
 * no conversion is performed.
 * If no valid conversion could be performed, a zero value is returned.
 * If the correct value is out of the range of representable values,
 * INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
 *
 * 这道题还是对于Integer的处理，在Reverse Integer这道题中我有提到，这种题的考察重点并不在于问题本身，
 * 而是要注意corner case的处理，整数一般有两点，一个是正负符号问题，另一个是整数越界问题。思路比较简单，
 * 就是先去掉多余的空格字符，然后读符号（注意正负号都有可能，也有可能没有符号），接下来按顺序读数字，
 * 结束条件有三种情况：（1）异常字符出现（按照C语言的标准是把异常字符起的后面全部截去，保留前面的部分作为结果）；
 * （2）数字越界（返回最接近的整数）；（3）字符串结束。
 *
 * 这道题主要考察整数处理，注意点上面已经提到过，因为这个问题是C语言的一个基本问题，面试中还是有可能出现，
 * 相对比较底层，边缘情况的处理是关键，可能面试tester的职位会更常见一些。
 */
public class StringtoIntegerAtoi {
    public int atoi(String str) {
        if (str == null) return 0;
        str = str.trim();
        if (str.length() == 0)
            return 0;
        boolean isNeg = false;
        int i = 0;
        if (str.charAt(0) == '-' || str.charAt(0) == '+') {
            i++;
            if (str.charAt(0) == '-')
                isNeg = true;
        }
        int res = 0;
        while (i < str.length()) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9')
                break;
            int digit = (int) (str.charAt(i) - '0');
            if (isNeg && res > -((Integer.MIN_VALUE + digit) / 10))
                return Integer.MIN_VALUE;
            else if (!isNeg && res > (Integer.MAX_VALUE - digit) / 10)
                return Integer.MAX_VALUE;
            res = res * 10 + digit;
            i++;
        }
        return isNeg ? -res : res;
    }
}

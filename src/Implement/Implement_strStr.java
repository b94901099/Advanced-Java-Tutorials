package Implement;

public class Implement_strStr {

    public String strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return haystack;
        }
        if (haystack.length() < needle.length()) {
            return null;
        }
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
                if (j == needle.length() - 1) {
                    return haystack.substring(i);
                }
            }
        }
        return null;
    }

    public String strStr1(String haystack, String needle) {
        if (needle.length() == 0) {
            return haystack;
        }
        if (haystack.length() < needle.length()) {
            return null;
        }
        for (int i = 0; i < haystack.length(); i++) {
            int j = i;
            // first judge if there is no enough length left to hit needle, very important to save time
            // second obvious
            // tracking needle in haystack
            while (needle.length() + i <= haystack.length() && j < haystack.length() && haystack.charAt(j) == needle.charAt(j - i)) {
                j++;
                if (j - i == needle.length()) {
                    return haystack.substring(i);
                }
            }

        }
        return null;
    }

    /**
     * Make it by myself, probably misunderstand the meaning.
     *
     * @param haystack
     * @param needle
     * @return
     */
    public String strStr2(String haystack, String needle) {
        if (needle.length() == 0) {
            return haystack;
        }
        if (haystack.length() < needle.length()) {
            return null;
        }
        for (int i = 0; i < haystack.length(); i++) {
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
                if (j == needle.length() - 1) {
                    return haystack.substring(i);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Implement_strStr s = new Implement_strStr();
        System.out.println(s.strStr("bbbbabbbbbb", "aba"));
    }
}

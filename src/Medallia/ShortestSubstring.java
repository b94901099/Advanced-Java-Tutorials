/*
 * Given a string s (1<=s.length()<=1000), return the shortest substring
 * that is only occurring once.
 * Examples: s="aabbabbaab" gives either "bab" or "baa" 
 * s="aaaa" gives "aaaa""
 */
package Medallia;

public class ShortestSubstring {

    public static void main(String[] args) {
        System.out.println(new ShortestSubstring().shortestSubString("aabbabbaab"));
    }

    private String shortestSubString(String string) {

        String shortestSubString = null;

        for (int length = 1; length <= string.length(); length++) {
            for (int position = 0; position + length <= string.length(); position++) {
                String subString = string.substring(position, position + length);

                int occurences = checkNumberOfOccurences(string, subString);

                if (occurences == 1) {
                    return subString;
                }

            }
        }

        return shortestSubString;
    }

    private int checkNumberOfOccurences(String string, String subString) {

        int occurences = 0;
        for (int position = 0; position + subString.length() <= string.length(); position++) {

            String sub = string.substring(position, position + subString.length());

            if (sub.equals(subString)) {
                occurences++;
            }
        }

        return occurences;
    }
}

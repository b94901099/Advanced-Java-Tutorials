/*
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2.
 * (each operation is counted as 1 step.)
 * You have the following 3 operations permitted on a word:
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character
 */
package DynamicProgramming;

public class EditDistance {

    public int minDistance(String word1, String word2) {
        if (word2 == null || word1 == null) {
            return -1;
        }

        int distance[][] = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i <= word1.length(); i++) {
            distance[i][0] = i;
        }

        for (int j = 0; j <= word2.length(); j++) {
            distance[0][j] = j;
        }

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                distance[i][j] = Math.min(distance[i - 1][j], distance[i][j - 1]) + 1;
                distance[i][j] = Math.min(distance[i][j],
                        distance[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1));
            }
        }

        return distance[word1.length()][word2.length()];
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
}

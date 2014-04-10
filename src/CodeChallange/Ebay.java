package CodeChallange;

import java.util.*;
import java.io.*;

public class Ebay {

    private float maxSS = 0;

    /**
     * Calculate the maximum SS score of imported data
     *
     * @param strData text content of the imported data
     * @return maximum SS score
     */
    public float SSCalculator(String strData) {
        maxSS = 0;

        int commaIndex = strData.indexOf(";");
        String[] customers = strData.substring(0, commaIndex).split(",");
        String[] products = strData.substring(commaIndex + 1).split(",");

        // remove non-letter characters from each element in names of customer and product
        for (int i = 0; i < products.length; i++) {
            products[i] = strTrans(products[i]);
        }
        for (int i = 0; i < customers.length; i++) {
            customers[i] = strTrans(customers[i]);
        }

        // scoreTable record the score of each product assigning to each customer
        float[][] scoreTable = new float[products.length][customers.length];

        System.out.println("Score Table");
        for (int i = 0; i < products.length; i++) {
            for (int j = 0; j < customers.length; j++) {
                scoreTable[i][j] = ssScore(products[i], customers[j]);
            }
            System.out.println(Arrays.toString(scoreTable[i]));
        }

        Stopwatch timer4 = new Stopwatch();

//        boolean[] visitedCustomers = new boolean[customers.length];
//        scoreHelper(scoreTable, visitedCustomers, 0, 0);

        // find max score among the all table

        float max = Integer.MIN_VALUE;
        for (int i = 0; i < scoreTable.length; i++) {
            for (int j = 0; j < scoreTable[0].length; j++) {
                max = Math.max(max, scoreTable[i][j]);
            }
        }
        System.out.println("max score = " + max);

        float[][] lossProfitTable;
        boolean startFromRow = false;
        System.out.println("row = " + scoreTable.length);
        System.out.println("col = " + scoreTable[0].length);
        if (scoreTable.length >= scoreTable[0].length) {
            lossProfitTable = new float[scoreTable.length][scoreTable.length];
            startFromRow = true;
        } else {
            lossProfitTable = new float[scoreTable[0].length][scoreTable[0].length];
            startFromRow = false;
        }
        System.out.println("start from row = " + startFromRow);
        System.out.println("Lost Profit");
        for (int i = 0; i < lossProfitTable.length; i++) {
            for (int j = 0; j < lossProfitTable.length; j++) {
                if (i < scoreTable.length && j < scoreTable[0].length) {
                    lossProfitTable[i][j] = max - scoreTable[i][j];
                } else {
                    lossProfitTable[i][j] = max;
                }
            }
            System.out.println(Arrays.toString(lossProfitTable[i]));
        }

        HungarianAlgorithm h = new HungarianAlgorithm();

        int[][] assignment = h.computeAssignments(lossProfitTable);
        for (int i = 0; i < assignment.length; i++) {
            int row = assignment[i][0];
            int col = assignment[i][1];
            if (row < scoreTable.length && col < scoreTable[0].length) {
                maxSS = maxSS + scoreTable[row][col];
            }
        }
        System.out.println("Time for dfs " + timer4.elapsedTime()
                + " millisec");

        return maxSS;
    }
    
    //dfs 會超時
    // The method use dfs to search for the maximum ss score and update it to maxSS
    private void scoreHelper(float[][] scoreTable, boolean[] visitedCustomers, int productPos, float tmpSS) {
        if (productPos == scoreTable.length) {
            maxSS = Math.max(maxSS, tmpSS);
            return;
        }

        for (int i = 0; i < scoreTable[0].length; i++) {
            if (!visitedCustomers[i]) {
                visitedCustomers[i] = true;
                scoreHelper(scoreTable, visitedCustomers, productPos + 1, tmpSS + scoreTable[productPos][i]);
                visitedCustomers[i] = false;
            }
        }
    }

    // Return the ss score based on the assigned product and customer
    private float ssScore(String product, String name) {
        float tmpSS = 0;
        if (product.length() % 2 == 0) {
            tmpSS = getNumVowel(name) * (float) 1.5;
        } else {
            tmpSS = getNumConsonant(name);
        }
        if (!isCoprime(product.length(), name.length())) {
            tmpSS = tmpSS * (float) 1.5;
        }
        return tmpSS;
    }

    //Cast a string to lower case and only letters
    private String strTrans(String str) {
        str = str.replaceAll("[^a-zA-Z]+", "");
        return str.toLowerCase();
    }

    // return the number of vowel in a string
    private int getNumVowel(String str) {
        int result = 0;
        HashSet<Character> set = new HashSet<Character>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('y');
        for (int i = 0; i < str.length(); i++) {
            if (set.contains(str.charAt(i))) {
                result++;
            }
        }
        return result;
    }

    // return the number of consonant in the string
    private int getNumConsonant(String str) {
        return str.length() - getNumVowel(str);
    }

    // chech if two integers are coprime
    private boolean isCoprime(int a, int b) {
        if (a == 0 || b == 0) {
            return true;
        }

        if (a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        int c;
        while ((c = a % b) != 0) {
            a = b;
            b = c;
        }
        return b == 1;
    }

    public static void main(String[] args) {
        Ebay e = new Ebay();
        ArrayList<String> data = new ArrayList<String>();
        try {
            //File file = new File(args[0]);
            String file = "D:\\CMU_DSA\\CCLeetcode\\ebaytestdata.txt";
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;
            while ((line = in.readLine()) != null) {
                data.add(line);
            }
            in.close();
        } catch (IOException ex) {
            System.out.println("File Read Error: " + ex.getMessage());
        }

        float[] scores = new float[data.size()];
        for (int i = 0; i < data.size(); i++) {
            scores[i] = e.SSCalculator(data.get(i));
            System.out.println(String.format("%.2f", scores[i]));
        }
    }
}

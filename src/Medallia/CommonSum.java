/*
 * "Summing up the individual digits for each number from 0 to k (0<=k<=10000000),
 * return how many times the most common sum occurs. 
 * Examples: k=10 gives 2 (since 1 and 10 both sum up to 1) 
 * k=50 gives 6 (since 5, 14, 23, 32, 41, 50 all sum up to 5)
 * k=7777 gives 555"
 */

package Medallia;

public class CommonSum {
    public static void main(String[] args) {
        System.out.println(new CommonSum().mostCommonSum(10));
        System.out.println(new CommonSum().mostCommonSum(50));
        System.out.println(new CommonSum().mostCommonSum(7777));
    }

    private int mostCommonSum(int i) {

        if ((i < 0) || (i > 10000000))
            throw new IllegalArgumentException();

        int[] sums = new int[i];

        for (int j = 1; j <= i; j++) {
            int index = sum(j);
            sums[index]++;
        }

        int mostCommonSum = 0;

        for (int k = 0; k < sums.length; k++) {
            if (sums[k] > mostCommonSum) {
                mostCommonSum = sums[k];
            }

        }

        return mostCommonSum;
    }

    private int sum(int i) {

        int sum = 0;
        String integer = Integer.toString(i);

        for (int j = 0; j + 1 <= integer.length(); j++) {
            String individualInt = integer.substring(j, j+1);
            sum += new Integer(individualInt);
        }
        return sum;
    }

}

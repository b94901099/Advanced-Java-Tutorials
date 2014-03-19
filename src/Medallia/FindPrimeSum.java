/*
 * "Given an integer k (1<=k<=2000000000), find two prime numbers that sum up 
 * to it and return the lower number. If there are multiple solutions, always
 * return the lowest prime. If there are no solutions, return -1.
 * Examples: k=12 gives 5 (5 + 7 = 12) k=68 gives 7 (7 + 61 = 68) k=77 gives -1
 */
package Medallia;

public class FindPrimeSum {

    public static int primeSum(int k) {
        int i = 0;
        int j = k;
        while (i <= j) {
            while (i <= j) {
                if (isPrime(i)) {
                    break;
                }
                i++;
            }
            while (i <= j) {
                if (isPrime(j)) {
                    break;
                }
                j--;
            }
            if (i + j == k) {
                return i;
            }
            if (i + j > k) {
                j--;
            } else {
                i++;
            }
        }

        return -1;
    }

    private static boolean isPrime(int x) {
        if (x == 1) {
            return false;
        }
        int i = 2;
        while (i < x) {
            if (x % i == 0) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(primeSum(77));
    }
}

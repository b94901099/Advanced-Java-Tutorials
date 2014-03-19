/*
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target,
 * where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution.
 * Input: numbers={2, 7, 11, 15}, target = 9 
 * Output: index1=1, index2=2
 */
package TwoPointers;

import java.util.*;

public class TwoSum {
    
    public int[] twoSumUsingHashMap(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();  
        int n = numbers.length;  
        int[] result = new int[2];  
        for (int i = 0; i < numbers.length; i++) {  
            if (map.containsKey(target - numbers[i])){  
                result[0] = map.get(target-numbers[i]) + 1;  
                result[1] = i + 1;  
                break;  
            } else {
                map.put(numbers[i], i);  
            }  
        }  
        return result;
    }

    public int[] twoSum(int[] numbers, int target) {

        int[] sorted = new int[numbers.length];
        System.arraycopy(numbers, 0, sorted, 0, numbers.length);
        Arrays.sort(sorted);

        int start = 0;
        int end = sorted.length - 1;
        while (start <= end) {
            int sum = sorted[start] + sorted[end];
            if (sum == target) {
                break;
            }
            if (sum > target) {
                end--;
            } else {
                start++;
            }
        }

        int index1 = -1;
        int index2 = -1;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == sorted[start] || numbers[i] == sorted[end]) {
                if (index1 == -1) {
                    index1 = i;
                } else {
                    index2 = i;
                }
                if (index1 != -1 && index2 != -1) {
                    break;
                }
            }
        }

        int[] result = {index1 + 1, index2 + 1};
        return result;
    }

    public static void main(String[] args) {
    }
}

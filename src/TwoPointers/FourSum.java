package TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;

public class FourSum {

    public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
        if (num == null) {
            return null;
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (num.length < 4) {
            return result;
        }
        Arrays.sort(num);
        int sum = 0;
        for (int i = 0; i < num.length - 3; i++) {
            if (i > 0 && num[i] == num[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < num.length - 2; j++) {
                if (j > i + 1 && num[j] == num[j - 1]) {
                    continue;
                }

                int p = j + 1;
                int q = num.length - 1;
                while (p < q) {
                    sum = num[i] + num[j] + num[p] + num[q];
                    if (sum == target) {
                        ArrayList<Integer> tmp = new ArrayList<Integer>();
                        tmp.add(num[i]);
                        tmp.add(num[j]);
                        tmp.add(num[p]);
                        tmp.add(num[q]);
                        result.add(tmp);
                        do {
                            p++;
                        } while (num[p] == num[p - 1] && p < q);
                        do {
                            q--;
                        } while (num[q] == num[q + 1] && p < q);
                    } else if (sum < target) {
                        do {
                            p++;
                        } while (num[p] == num[p - 1] && p < q);
                    } else if (sum > target) {
                        do {
                            q--;
                        } while (num[q] == num[q + 1] && p < q);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
    }
}

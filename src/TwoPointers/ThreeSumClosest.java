package TwoPointers;

import java.util.Arrays;

public class ThreeSumClosest {

    public int threeSumClosest(int[] num, int target) {
        if (num == null) {
            return -1;
        }
        if (num.length < 3) {
            return -1;
        }

        Arrays.sort(num);
        int closest = num[0] + num[1] + num[num.length - 1];

        for (int i = 0; i < num.length - 2; i++) {
            int p = i + 1;
            int q = num.length - 1;

            while (p < q) {
                int sum = num[i] + num[p] + num[q];
                if (sum == target) {
                    return sum;
                }
                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
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
        return closest;
    }

    public static void main(String[] args) {
    }
}

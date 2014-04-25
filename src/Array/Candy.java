
package Array;
/**
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 * 1. Each child must have at least one candy
 * 2. Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 * Example: Input = [1,2,2], Output = 4
 */

import java.util.*;

public class Candy {

    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i - 1] < ratings[i]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        for (int i = ratings.length - 2; i >= 0; i--) { // 注意糖果等於的時候也要考慮
            if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                candies[i] = candies[i + 1] + 1;
            }
        }

        int total = 0;
        for (int i = 0; i < ratings.length; i++) {
            total += candies[i];
        }
        return total;
    }

    public static void main(String[] args) {
        int[] ratings = {1, 2, 2};
        Candy c = new Candy();
        System.out.println(c.candy(ratings));
        int[] ratings2 = {3, 6, 4, 7, 4, 1, 1, 2, 2};
        System.out.println(c.candy(ratings2));
    }
}

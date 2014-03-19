/*
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * O(n^2)的算法很容易想到：
 * 找寻一个点j，将原来的price[0..n-1]分割为price[0..j]和price[j..n-1]，分别求两段的最大profit。
 * 进行优化：
 * 对于点j+1，求price[0..j+1]的最大profit时，很多工作是重复的，在求price[0..j]的最大profit中已经做过了。
 * 类似于Best Time to Buy and Sell Stock，可以在O(1)的时间从price[0..j]推出price[0..j+1]的最大profit。
 * 但是如何从price[j..n-1]推出price[j+1..n-1]？反过来思考，我们可以用O(1)的时间由price[j+1..n-1]推出price[j..n-1]。
 * 最终算法：
 * 数组l[i]记录了price[0..i]的最大profit，
 * 数组r[i]记录了price[i..n]的最大profit。
 * 已知l[i]，求l[i+1]是简单的，同样已知r[i]，求r[i-1]也很容易。
 * 最后，我们再用O(n)的时间找出最大的l[i]+r[i]，即为题目所求。
 * 
 */
package Implement;

import java.util.Arrays;

public class BestTimeToBuyAndSellStockIII {

    public static int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int max = 0;
        // dp数组保存左边和右边的利润最大值  
        int[] left = new int[prices.length];        // 计算[0,i]区间的最大值  
        int[] right = new int[prices.length];   // 计算[i,len-1]区间的最大值  

        process(prices, left, right);

        // O(n)找到最大值  
        for (int i = 0; i < prices.length; i++) {
            max = Math.max(max, left[i] + right[i]);
        }

        return max;
    }

    public static void process2(int[] prices, int[] left, int[] right) {
        int min = prices[0];
        left[0] = 0; //初始利潤
        //以最低買入價開始找
        for (int i = 1; i < prices.length; i++) {
            left[i] = Math.max(left[i - 1], prices[i] - min);
            //每次利潤都是比較前面的利潤 (i - 1) 和加上這次以後 (prices[i] - min)
            min = Math.min(min, prices[i]);
        }
        
        //以最高賣出價開始找
        int max = prices[prices.length - 1];
        right[right.length - 1] = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--){
            right[i] = Math.max(right[i + 1], max - prices[i]);
            max = Math.max(max, prices[i]);
        }
    }

    public static void process(int[] prices, int[] left, int[] right) {
        left[0] = 0;
        int min = prices[0];        // 最低买入价  

        // 左边递推公式  
        for (int i = 1; i < left.length; i++) {
            left[i] = Math.max(left[i - 1], prices[i] - min);   // i的最大利润为（i-1的利润）和（当前卖出价和之前买入价之差）的较大那个  
            min = Math.min(min, prices[i]);     // 更新最小买入价  
        }

        right[right.length - 1] = 0;
        int max = prices[right.length - 1];       // 最高卖出价  
        // 右边递推公式  
        for (int i = right.length - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], max - prices[i]); // i的最大利润为（i+1的利润）和（最高卖出价和当前买入价之差）的较大那个  
            max = Math.max(max, prices[i]);     // 更新最高卖出价  
        }
        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));
    }

    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
//        int[] prices = {2, 1, 2, 0, 1};
        System.out.println(maxProfit(prices));
    }
}

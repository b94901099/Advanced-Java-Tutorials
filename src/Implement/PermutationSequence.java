/*
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 By listing and labeling all of the permutations in order,
 We get the following sequence (ie, for n = 3):

 "123"
 "132"
 "213"
 "231"
 "312"
 "321"
 Given n and k, return the kth permutation sequence.
 Note: Given n will be between 1 and 9 inclusive.
 * 
 * 其实学过数学，知道排列组合，无重复数据的话，n个数，从1到n就有n!种组合。
 那么如果我们知道第一位数字是多少，就能算出后面的(n-1)位数的组合，也就是(n-1)!种组合。
 这个分析很明显了，n可以分成n组，每组有(n-1)!个数，
 比如n = 6，那么以1,2,3,4,5,6开头的组合必然是各有(n-1)! = 5! = 120中组合。
 我们认为组数应该从0开始，那么k要-1;
 注意此时K = 299，那么我们先要求解这个k在第几组，k/(n-1)! = 299/120 = 2,也就是说k应该在第
 3组(注意组号从0开始)，第三组的首个数字应该是3。这样第一个数字就确定了。
 确定第2个数字的时候，注意这个时候，k应该等于k % 120 = 59,为什么要这么算呢，因为每个组有120个数字，
 而且k在第三组，那么前两组加起来是240，k在第二次循环的时候，应该是从(5-1)!中确定是属于哪个组，其实
 就是确定k在第三组中是属于哪个位置。这个时候59/24 = 2,确定应该是属于第三组，
 因为在上一步中，3已经用过了，所以这个时候的5个数字是1,2,4,5,6，
 所以第三组的首个数字是4，依次类推，一直到n个数字全部算完为止。
 答案就出来了。
 */
package Implement;

import java.util.*;

public class PermutationSequence {

    public String getPermutation(int n, int k) {
        List<Integer> numList = new ArrayList<Integer>();
        numList.add(1);
        int sum = 1;
        for (int i = 2; i <= n; i++) {
            sum *= i;
            numList.add(i);
        }
        sum /= n;
        k--;
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= n; i++) {
            int currNum = k / sum;
            sb.append(numList.get(currNum));
            numList.remove(currNum);
            if (i == n) {
                break;
            }
            k %= sum;
            sum /= (n - i);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        PermutationSequence p = new PermutationSequence();
        System.out.println(p.getPermutation(6, 1));
        System.out.println(p.getPermutation(6, 300));
        System.out.println(p.getPermutationRec(6, 300));

    }

    public String getPermutationRec(int n, int k) {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        StringBuilder sb = new StringBuilder();
        int div = 1;
        for (int i = 1; i <= n; i++) {
            div = div * i;
            nums.add(i);
        }

        getPermutationRec(nums, div, n, k - 1, sb);
        return sb.toString();
    }

    private void getPermutationRec(ArrayList<Integer> nums, int div, int n, int k, StringBuilder sb) {
        if (n == 1) {
            sb.append(nums.get(0));
            return;
        }
        div = div / n;
        int pos = (k / div) ;
        sb.append(nums.get(pos));
        nums.remove(pos);
        getPermutationRec(nums, div, n - 1, k % div, sb);
    }
}

/*1，2，3，4  （n = 4, k = 10)

第一次循环 
div /= 4   -> div = 6
pos = (k - 1) / 6  -> pos = 1
k -= (pos * div) -> k = 4
把2 放入 ret，从p剔除2

1，3，4     （ret = "2"）

第二次循环：
div /= 3   -> div = 2
pos = (k - 1) / 2  -> pos = 1
k -= (pos * div) -> k = 2
把3 放入 ret，从p剔除3

1，4     （ret = "2 3"）

第三次循环：
div /= 2   -> div = 1
pos = (k - 1) / 2  -> pos = 1
k -= (pos * div) -> k = 1
把4 放入 ret，从p剔除1

1 （ret= "2 3 4")

第四次循环：
div /= 1  -> div = 1
pos = (k - 1) / 1  -> pos = 0
k -= (pos * div) -> k = 1
把1 放入 ret，从p剔除1

ret = "2341"

从 “1234” 到 “2341“ ，k = 10

1234 -〉2134 ，消耗6次变换
2134 -> 2314， 消耗2次变换
2314-> 2341, 消耗1次变换

共消耗9次变换，位置从第一个变成第10个，这正是k= 10的要求。
 */
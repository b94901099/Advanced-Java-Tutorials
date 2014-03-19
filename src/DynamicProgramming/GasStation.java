/*
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel
 * from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * Note:
 * The solution is guaranteed to be unique.
 * 
 */
package DynamicProgramming;

public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curSum = 0;			// 用于记录当前gas剩余量
        int total = 0;				// 记录走完一圈的gas剩余量
        int startIndex = 0;		// 记录能走完一圈的开始位置

        for (int i = 0; i < gas.length; i++) {
            int curRemain = gas[i] - cost[i];
            if (curSum >= 0) {		// 如果当前还有剩余量，继续
                curSum += curRemain;
            } else {			// 否则，从这里重新开始
                curSum = curRemain;
                startIndex = i;
            }
            total += curRemain;
        }

        return total >= 0 ? startIndex : -1;
    }

    public static void main(String[] args) {
    }
}

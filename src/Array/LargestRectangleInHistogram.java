package Array;
/*
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, 
 * find the area of largest rectangle in the histogram.
 * Given height = [2,1,5,6,2,3], return 10.
 * 兩種sol是同樣的邏輯
 * 
 * For every bar ‘x’, we calculate the area with ‘x’ as the smallest bar in the rectangle. 
 * If we calculate such area for every bar ‘x’ and find the maximum of all areas, our task is done. 
 * How to calculate area with ‘x’ as smallest bar?
 * We need to know index of the first smaller (smaller than ‘x’) bar on left of ‘x’ 
 * and index of first smaller bar on right of ‘x’. 
 * Let us call these indexes as ‘left index’ and ‘right index’ respectively.
 * We traverse all bars from left to right, maintain a stack of bars.
 * Every bar is pushed to stack once. A bar is popped from stack when a bar of smaller height is seen. 
 * When a bar is popped, we calculate the area with the popped bar as smallest bar.
 * How do we get left and right indexes of the popped bar – 
 * the current index tells us the ‘right index’ and index of previous item in stack is the ‘left index’. 
 * Following is the complete algorithm.
 * 1) Create an empty stack.
 * 2) Start from first bar, and do following for every bar ‘hist[i]‘ where ‘i’ varies from 0 to n-1.
 * ……a) If stack is empty or hist[i] is higher than the bar at top of stack, then push ‘i’ to stack.
 * ……b) If this bar is smaller than the top of stack, then keep removing the top of stack while top of the stack is greater.
 * Let the removed bar be hist[tp]. Calculate area of rectangle with hist[tp] as smallest bar.
 * For hist[tp], the ‘left index’ is previous (previous to tp) item in stack and ‘right index’ is ‘i’ (current index).
 * 3) If the stack is not empty, then one by one remove all bars from stack and do step 2.b for every removed bar.
 */

import java.util.Stack;

public class LargestRectangleInHistogram {

    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        for (int i = 0; i <= height.length; i++) {
            int curt = (i == height.length) ? -1 : height[i];
            while (!stack.isEmpty() && curt <= height[stack.peek()]) {
                int h = height[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, h * w);
            }
            stack.push(i);
        }

        return max;
    }

    public int largestRectangleArea2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();
        int maxArea = 0;
        int top;
        int area_with_top;
        int i = 0;
        while (i < height.length) {
            if (stack.isEmpty() || height[i] >= height[stack.peek()]) {
                stack.push(i++);
            } else {
                top = stack.pop();
                area_with_top = height[top] * (stack.isEmpty() ? i : i - stack.peek() - 1);
                maxArea = Math.max(maxArea, area_with_top);
            }
        }

        while (!stack.isEmpty()) {
            top = stack.pop();
            area_with_top = height[top] * (stack.isEmpty() ? height.length : height.length - stack.peek() - 1);
            maxArea = Math.max(maxArea, area_with_top);
        }

        return maxArea;
    }
    
    // 這個有Error
    public int largestRectangleArea3(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();
        int maxArea = 0;
        int top;
        int area_with_top;

        for (int i = 0; i < height.length; i++) {
            if (stack.isEmpty() || height[i] >= height[stack.peek()]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && height[i] < height[stack.peek()]) {
                    top = stack.pop();
                    area_with_top = height[top] * (stack.isEmpty() ? i : i - stack.peek() - 1);
                    maxArea = Math.max(maxArea, area_with_top);
                    i--; // 停留在 cur
                }
            }
        }

        while (!stack.isEmpty()) {
            top = stack.pop();
            area_with_top = height[top] * (stack.isEmpty() ? height.length : height.length - stack.peek() - 1);
            maxArea = Math.max(maxArea, area_with_top);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        LargestRectangleInHistogram l = new LargestRectangleInHistogram();
        int[] height = {2, 1, 5, 6, 2, 3};
        System.out.println(l.largestRectangleArea3(height));
    }
}

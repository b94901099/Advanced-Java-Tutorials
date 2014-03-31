/*
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 */
package Array;

import java.util.*;

public class MaxPointsonaLine {

    public int maxPoints(Point[] points) {
        if (points.length == 0) {
            return 0;
        }
        int max_num = 0;
        int same_point_num = 0;
        for (int i = 0; i < (int) points.length; i++) {
            HashMap<Double, Integer> slope_map = new HashMap();
            same_point_num = 0;
            for (int j = 0; j < (int) points.length; j++) {
                if (j == i) {// i和i自己不需要计算
                    continue;
                }
                if (points[i].x - points[j].x == 0 && points[i].y - points[j].y == 0) { //相同点
                    same_point_num++;
                } else if (points[i].x - points[j].x == 0 && points[i].y - points[j].y != 0) { //斜率为tan90°的点
                    if (slope_map.containsKey((double) Integer.MAX_VALUE)) {
                        int tmp = slope_map.get((double) Integer.MAX_VALUE);
                        slope_map.put((double) Integer.MAX_VALUE, tmp + 1);
                    } else {
                        slope_map.put((double) Integer.MAX_VALUE, 1);
                    }
                } else {
                    double slope = (double) (points[j].y - points[i].y) / (points[j].x - points[i].x);//正常点，注意double写的位置
                    if (slope_map.containsKey(slope)) {
                        slope_map.put(slope, slope_map.get(slope) + 1);
                    } else {
                        slope_map.put(slope, 1);
                    }
                }
            }
            int tmp_max = 0;
            for (double key : slope_map.keySet()) {
                tmp_max = Math.max(slope_map.get(key), tmp_max);
            }
            if (tmp_max + same_point_num > max_num) { //用map中的最多点再加上same_point_num得到的数和历史的最大值做比较跟新历史最大值
                max_num = tmp_max + same_point_num;
            }
        }
        return max_num + 1;//当max_num为1时，确定了1条直线，2个点；当max_num为2时，确定了3个点;当max_num为i时，确定了i+1个点。

    }

    public static void main(String[] args) {
    }
}

class Point {

    int x;
    int y;

    Point() {
        x = 0;
        y = 0;
    }

    Point(int a, int b) {
        x = a;
        y = b;
    }
}
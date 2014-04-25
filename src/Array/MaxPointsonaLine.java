package Array;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 *
 * 这道题属于计算几何的题目，要求给定一个点集合，是求出最多点通过一条直线的数量。
 * 我的思路是n个点总共构成n*(n-1)/2条直线，然后对每条直线看看是有多少点在直线上，记录下最大的那个，
 * 最后返回结果。而判断一个点p_k在p_i, p_j构成的直线上的条件是(p_k.y-p_i.y)*(p_j.x-p_i.x)==(p_j.y-p_i.y)*(p_k.x-p_i.x)。
 * 算法总共是三层循环，时间复杂度是O(n^3),空间复杂度则是O(1),因为不需要额外空间做存储。代码如下：
 * 大家看到代码中还写了一个allSamePoints的函数，这是一个非常corner的情况，就是所有点都是一个点的情况，
 * 因为下面我们要跳过重复的点（否则两个重合点会认为任何点都在他们构成的直线上），但是偏偏当所有点都重合时，
 * 我们需要返回所有点。除了这种情况，只要有一个点不重合，我们就会从那个点得到结果，这是属于比较tricky的情况。
 * 计算几何的题目在现在的面试中挺常见的，可能因为有些问题比较实用的缘故，而且实现中一般细节比较多，容易出bug，所以还是得重视。
 *
 */

import java.util.*;

public class MaxPointsonaLine {

    public int maxPoints1(Point[] points) {
        if (points.length == 0) {
            return 0;
        }
        int max_num = 0;
        int same_point_num = 0;
        for (int i = 0; i < points.length; i++) {
            HashMap<Double, Integer> slope_map = new HashMap();
            same_point_num = 0;
            for (int j = 0; j < points.length; j++) {
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


    // has bug
    int maxPoints(Point[] points) {
        //点集合为空
        if (points == null || points.length == 0) {
            return 0;
        }
        int points_size = points.length;
        //点集合数目小于3个，返回点集合大小
        if (points_size <= 2) {
            return points_size;
        }

        //保存通过某点的斜率-点数目
        Map<Double, Integer> lines = new HashMap<Double, Integer>();

        int max_points = 0, total, vertical_num;
        double k;
        for (int i = 0; i < points_size - 1; ++i) {
            lines.clear();
            //total保存等于points[i]点的数目
            total = 1;
            //斜率不存在时，除了i之外的点数目
            vertical_num = 0;
            for (int j = i + 1; j < points_size; ++j) {
                //等于points[i]时
                if (points[i].x == points[j].x &&
                        points[i].y == points[j].y) {
                    ++total;
                    continue;
                }

                //斜率不存在
                if (points[i].x == points[j].x) {
                    ++vertical_num;
                } else {
                    k = (double) (points[i].y - points[j].y) / (points[i].x - points[j].x);
                    if (lines.containsKey(k)) {
                        lines.put(k, lines.get(k) + 1);
                    } else {
                        lines.put(k, 1);
                    }
                }
            }

            //更新最大值
            Set<Double> keys = lines.keySet();
            for (double key : keys) {
                if (lines.get(key) > vertical_num) {
                    vertical_num = lines.get(key);
                }
            }

            vertical_num += total;
            if (vertical_num > max_points) {
                max_points = vertical_num;
            }
        }

        return max_points;
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
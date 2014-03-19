/*
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 */
package Array;

import java.util.*;

public class InsertInterval {

    private Comparator<Interval> IntervalComparator = new Comparator<Interval>() {
        public int compare(Interval a, Interval b) {
            return a.start - b.start;
        }
    };

    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        if (intervals.size() < 1 || intervals == null) {
            return intervals;
        }
        intervals.add(newInterval);
        Collections.sort(intervals, IntervalComparator);
        ArrayList<Interval> result = new ArrayList<Interval>();
        Interval pre = intervals.get(0);

        for (int i = 1; i < intervals.size(); i++) {
            Interval cur = intervals.get(i);
            if (pre.end >= cur.start) {
                pre.end = Math.max(pre.end, cur.end);
            } else {
                result.add(pre);
                pre = cur;
            }
        }

        result.add(pre);
        return result;
    }

    public static void main(String[] args) {
        ArrayList<Interval> test = new ArrayList();
        test.add(new Interval(1, 2));
        test.add(new Interval(3, 5));
        test.add(new Interval(6, 7));
        test.add(new Interval(8, 10));
        test.add(new Interval(12, 16));
        InsertInterval i = new InsertInterval();
        ArrayList<Interval> testResult = i.insert(test, new Interval(4, 9));
        System.out.println(Arrays.toString(testResult.toArray()));
    }

    private static class Interval {

        int start;
        int end;

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }
}

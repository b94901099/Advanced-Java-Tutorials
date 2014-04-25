package Array;

/**
 * Merge Intervals Total Accepted: 6795 Total Submissions: 34036 My Submissions
 * Given a collection of intervals, merge all overlapping intervals.
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 */

import java.util.*;

public class MergeIntervals {

    private Comparator<Interval> IntervalComparator = new Comparator<Interval>() {
        public int compare(Interval a, Interval b) {
            return a.start - b.start;
        }
    };

    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }

        Collections.sort(intervals, IntervalComparator);
        ArrayList<Interval> result = new ArrayList<Interval>();
        Interval last = intervals.get(0);

        for (int i = 1; i < intervals.size(); i++) {
            Interval curt = intervals.get(i);
            if (curt.start <= last.end) {
                last.end = Math.max(last.end, curt.end);
            } else {
                result.add(last);
                last = curt;
            }
        }
        result.add(last);
        return result;
    }

//    private class IntervalComparator implements Comparator< Interval> {
//
//        public int compare(Interval a, Interval b) {
//            return a.start - b.start;
//        }
//    }
    public static void main(String[] args) {
    }
}

class Interval {

    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}
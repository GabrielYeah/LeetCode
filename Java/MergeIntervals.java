// Given a collection of intervals, merge all overlapping intervals.

// For example,
// Given [1,3],[2,6],[8,10],[15,18],
// return [1,6],[8,10],[15,18].

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<Interval>();
        if (intervals.size() == 0)
            return result;
        Collections.sort(intervals, new IntervalCompare());
        result.add(intervals.get(0));
        for (int i = 1; i < intervals.size(); i++) {
            Interval next = intervals.get(i);
            Interval curr = result.get(result.size() - 1);
            result.remove(result.size() - 1);
            if (next.start > curr.end) {
                result.add(curr);
                result.add(next);
            } else if (curr.start > next.end) {
                result.add(next);
                result.add(curr);
            } else {
                curr.start = Math.min(curr.start, next.start);
                curr.end = Math.max(curr.end, next.end);
                result.add(curr);
            }
        }
        return result;
    }
}

class IntervalCompare implements Comparator<Interval> {
    public int compare(Interval o1, Interval o2) {
        return o1.start - o2.start;
    }
}
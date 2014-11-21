// Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

// You may assume that the intervals were initially sorted according to their start times.

// Example 1:
// Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

// Example 2:
// Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

// This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
// First attempt. Almost aced.
public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<Interval>(intervals);
        if (result.size() == 0) {
            result.add(newInterval);
            return result;
        }
        int i = 0;
        for (i = 0; result.get(i).end < newInterval.start; i++) {
            if (i + 1 == result.size()) {
                result.add(newInterval);
                return result;
            }
        }
        if (newInterval.end < result.get(i).start) {
            result.add(i, newInterval);
            return result;
        }
        result.get(i).start = Math.min(result.get(i).start, newInterval.start);
        result.get(i).end = Math.max(result.get(i).end, newInterval.end);
        i = i + 1;
        while (i < result.size()) {
            if (result.get(i).start > result.get(i - 1).end)
                break;
            if (result.get(i - 1).end < result.get(i).end) {
                result.get(i - 1).end = result.get(i).end;
            }
            result.remove(i);
        }
        return result;
    }
}

// Final version. A much cleaner way.
public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<Interval>(Arrays.asList(newInterval));
        for (int i = 0; i < intervals.size(); ++i) {
            Interval next = intervals.get(i);
            Interval curr = result.get(result.size() - 1);
            result.remove(result.size() - 1);
            if (curr.start > next.end) {
                result.add(next);
                result.add(curr);
            } else if (curr.end < next.start) {
                result.add(curr);
                result.add(next);
            } else {
                curr.start = Math.min(curr.start, next.start);
                curr.end = Math.max(curr.end, next.end);
                result.add(curr);
            }
        }
        return result;
    }
}
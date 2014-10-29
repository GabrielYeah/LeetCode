// There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

// You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

// Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

// Note:
// The solution is guaranteed to be unique.

// AC at first attempt.
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length != cost.length)
            return -1;
        // Start from the end of the array
        int start = gas.length - 1;
        int end = 0;
        int tank = gas[start] - cost[start];
        while (start > end) {
            if (tank + gas[end] - cost[end] >= 0) {
                // If can reach a new end point from current start point,
                // move to this new end point and update tank
                tank += gas[end] - cost[end];
                end++;
            } else {
                // Cannot reach this new end point, try the start point
                // in front of the current start point
                start--;
                tank += gas[start] - cost[start];
            }
        }
        
        return tank >= 0 ? start : -1;
    }
}
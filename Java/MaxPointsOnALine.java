// Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

// Big mistake that I made was that did not cast int value to double!!!
// CAUTION ON DOUBLE VALUE CASTING!!!
// Anyway, I spent a lot of time trying to focus on edge and reduce time complexity.
// Here is a way focusing on point. In this case, time complexity is O(n^2).
// I thought a certain slope value may be contained in multiple lines, but if one point is fixed,
// a slope will represent a single line.

public class Solution {
    public int maxPoints(Point[] points) {
        if (points == null || points.length == 0)
            return 0;
            
        int bestValue = 1;
        for (int i = 0; i < points.length; ++i) {
            Map<Double, Integer> map = new HashMap<Double, Integer>();
            int duplicates = 0;
            boolean allSame = true;
            for (int j = i + 1; j < points.length; ++j) {
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    duplicates++;
                    continue;
                }
                allSame = false;
                Double key = Double.MAX_VALUE;
                if (points[i].x != points[j].x)
                    key = 0.0 + (double)(points[i].y - points[j].y) / (double)(points[i].x - points[j].x);
                    
                if (map.containsKey(key))
                    map.put(key, map.get(key) + 1);
                else
                    map.put(key, 2);
            }
            
            for (int value : map.values())
                bestValue = Math.max(bestValue, value + duplicates);
            if (allSame)
                bestValue = Math.max(bestValue, duplicates + 1);
        }
        
        return bestValue;
    }
}
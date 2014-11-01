// Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

// For example,
// Given [100, 4, 200, 1, 3, 2],
// The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

// Your algorithm should run in O(n) complexity.

// First attempt. Naive solution by sorting.
public class Solution {
    public int longestConsecutive(int[] num) {
        if (num == null)
            return 0;
        Arrays.sort(num);
        int max = 1;
        int[] lens = new int[num.length];
        lens[0] = 1;
        for (int i = 1; i < num.length; ++i) {
            if (num[i] == num[i - 1]) {
                lens[i] = lens[i - 1];
            } else if (num[i] == num[i - 1] + 1) {
                lens[i] = lens[i - 1] + 1;
            } else {
                lens[i] = 1;
            }
            max = Math.max(lens[i], max);
        }
        return max;
    }
}

// Final version. Use space to trade time.
// When considering range or some form of sequence, a possible solution will
// be check whether the element exists.
public class Solution {
    public int longestConsecutive(int[] num) {
        if (num == null)
            return 0;
        // Use a map to store all the integers in the array.
        // The value of each integer will indicate whether
        // this integer has been checked or not;
        Map<Integer, Boolean> all = new HashMap<Integer, Boolean>();
        for (int i : num)
            all.put(i, false);
            
        int max = 1;
        for (int i : num) {
            if (all.get(i))
                continue;
            // Check whether each integer's neighbor is in the map.
            int tmp = i - 1, count = 1;
            while (all.containsKey(tmp)) {
                all.put(tmp, true);
                count++; tmp--;
            }
            tmp = i + 1;
            while (all.containsKey(tmp)) {
                all.put(tmp, true);
                count++; tmp++;
            }
            max = Math.max(max, count);
        }
        return max;
    }
}
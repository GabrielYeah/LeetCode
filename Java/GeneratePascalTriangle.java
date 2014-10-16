// Given numRows, generate the first numRows of Pascal's triangle.

// For example, given numRows = 5,
// Return
// [
//      [1],
//     [1,1],
//    [1,2,1],
//   [1,3,3,1],
//  [1,4,6,4,1]
// ]

public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> current = new ArrayList<Integer>();
        for (int i = 1; i <= numRows; ++i) {
            for (int j = i - 2; j > 0; --j) {
                current.set(j, current.get(j) + current.get(j - 1));
            }
            current.add(1);
            result.add(new ArrayList<Integer>(current));
        }
        return result;
    }
}
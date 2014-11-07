// The gray code is a binary numeral system where two successive values differ in only one bit.

// Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

// For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

// 00 - 0
// 01 - 1
// 11 - 3
// 10 - 2
// Note:
// For a given n, a gray code sequence is not uniquely defined.

// For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

// For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.

// This is the pattern for this question:
// 000
// 001
// 011
// 010
// 110
// 111
// 101
// 100
// Here is a thought of adding new elements according to previous results
public class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<Integer>();
        if (n < 0)
            return result;
        result.add(0);
        for (int i = 0; i < n; i++) {
            int base = 1 << i;
            for (int j = result.size() - 1; j >= 0; j--) {
                result.add(result.get(j) + base);
            }
        }
        return result;
    }
}
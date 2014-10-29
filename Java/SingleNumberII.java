// Given an array of integers, every element appears three times except for one. Find that single one.

// Note:
// Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

// x0 stores the bits that does not appear or appear for three times
// x1 stores the bits that appears for once
// x2 stores the bits that appears for twice
// t is used for temporary storage for x2 since it will be modified
public class Solution {
    public int singleNumber(int[] A) {
        int x0 = ~0, x1 = 0, x2 = 0, t = 0;
        for (int i : A) {
            t = x2;
            x2 = (x1 & i) | (x2 & ~i);
            x1 = (x0 & i) | (x1 & ~i);
            x0 = (t & i) | (x0 & ~i);
        }
        return x1;
    }
}
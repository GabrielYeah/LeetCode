// Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

// If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

// The replacement must be in-place, do not allocate extra memory.

// Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
// 1,2,3 → 1,3,2
// 3,2,1 → 1,2,3
// 1,1,5 → 1,5,1

// A very tricky question. 
public class Solution {
    public void nextPermutation(int[] num) {
        if (num == null || num.length <= 1)
            return;
        int x = num.length - 1;
        while (x > 0) {
            if (num[x - 1] < num[x])
                break;
            x--;
        }
        if (x > 0) {
            int r = num.length - 1;
            while (r > 0 && num[r] <= num[x - 1]) 
                r--;
            int tmp = num[x - 1];
            num[x - 1] = num[r];
            num[r] = tmp;
        }
        Arrays.sort(num, x, num.length);
    }
}

// A neater way to write the code.
// Scan from the right of the array. Find the element x which breaks
// the increasing trend. Swap it with the least element that is larger
// the element x. Sort the part after x.
public class Solution {
    public void nextPermutation(int[] num) {
        if (num == null || num.length <= 1)
            return;
        int x = num.length - 1;
        while (x > 0 && num[x - 1] >= num[x])
            x--;
        if (x > 0) {
            int r = num.length - 1;
            while (r > 0 && num[r] <= num[x - 1]) 
                r--;
            int tmp = num[x - 1];
            num[x - 1] = num[r];
            num[r] = tmp;
        }
        Arrays.sort(num, x, num.length);
    }
}
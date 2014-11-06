// Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

// Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

// Note:
// You are not suppose to use the library's sort function for this problem.

// click to show follow up.

// Follow up:
// A rather straight forward solution is a two-pass algorithm using counting sort.
// First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

// Could you come up with an one-pass algorithm using only constant space?

// First attempt. More than one pass.
public class Solution1 {
    public void sortColors(int[] A) {
        if (A == null || A.length == 0)
            return;
        int start = 0, end = A.length - 1;
        int min = 0;
        while (start < end && min <= 1) {
            while (start < A.length && A[start] == min)
                start++;
            while (end >= 0 && A[end] != min)
                end--;
            if (start < end) {
                A[end] = A[start];
                A[start] = min;
            } else {
                min++;
                end = A.length - 1;
            }
        }
    }
}

// Final version.
public class Solution {
    public void sortColors(int[] A) {
        if (A == null || A.length == 0)
            return;
        // Record the red and blue index
        int red = 0, blue = A.length - 1;
        int i = 0;
        while (i < blue + 1) {
            if (A[i] == 0) {
                // If a value is 0, move to red index
                int tmp = A[red];
                A[red] = A[i];
                A[i] = tmp;
                red++;
            } else if (A[i] == 2) {
                // If a value is 2, move to blue index
                int tmp = A[blue];
                A[blue] = A[i];
                A[i] = tmp;
                blue--;
                // Since the old value at blue is swapped to
                // i, so should not increase i.
                continue;
            }
            i++;
        }
    }
}
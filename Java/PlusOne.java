// Given a non-negative number represented as an array of digits, plus one to the number.

// The digits are stored such that the most significant digit is at the head of the list.

// First attempt. Aced. But can be improved.
public class Solution1 {
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0)
            return digits;
        digits[digits.length - 1] += 1;
        for (int i = digits.length - 2; i >= 0; --i) {
            if (digits[i + 1] == 10) {
                digits[i + 1] = 0;
                digits[i] += 1;
            }
        }
        if (digits[0] == 10) {
            digits[0] = 0;
            int[] tmp = new int[digits.length + 1];
            for (int i = 0; i < digits.length; ++i)
                tmp[i + 1] = digits[i];
            tmp[0] = 1;
            digits = tmp;
        }
        return digits;
    }
}
// Given two numbers represented as strings, return multiplication of the numbers as a string.

// Note: The numbers can be arbitrarily large and are non-negative.

public class Solution {
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null)
            return null;
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();
        // Use an array to maintain the results at each digit
        int[] array = new int[num1.length() + num2.length()];
        for (int i = 0; i < num1.length(); i++) {
            int a = num1.charAt(i) - '0';
            for (int j = 0; j < num2.length(); j++) {
                int b = num2.charAt(j) - '0';
                array[i + j] += a * b;
            }
        }
        // Build the string digit by digit
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            result.insert(0, array[i] % 10);
            if (i < array.length - 1)
                array[i + 1] += array[i] / 10;
        }
        while (result.length() > 1 && result.charAt(0) == '0')
            result.deleteCharAt(0);
            
        return result.toString();
    }
}
// Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

// If the fractional part is repeating, enclose the repeating part in parentheses.

// For example,

// Given numerator = 1, denominator = 2, return "0.5".
// Given numerator = 2, denominator = 1, return "2".
// Given numerator = 2, denominator = 3, return "0.(6)".

// The concept here is to combine Math divide calculation with hashmap. 
// The remain part will be updated digit by digit. 
// When the same remain part has appeared, it means that the rest part will start recurring.
// Use a hashmap the record each remain part, so that we can locate the first position the recurring part occurs.
public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        boolean negative = numerator < 0 && denominator > 0 || numerator > 0 && denominator < 0;
        long num = Math.abs(Long.valueOf(numerator));
        long den = Math.abs(Long.valueOf(denominator));
        long res = num / den;
        long rem = num % den;
        StringBuilder sb = new StringBuilder();
        if (negative && (res != 0 || rem != 0))
            sb.append("-");
        sb.append(String.valueOf(res));
        if (rem == 0)
            return sb.toString();
        sb.append(".");
        Map<Long, Integer> dict = new HashMap<Long, Integer>();
        for (int pos = sb.length(); rem != 0; pos++) {
            if (dict.containsKey(rem)) {
                sb.insert(dict.get(rem), "(");
                sb.append(")");
                break;
            }
            dict.put(rem, pos);
            sb.append(String.valueOf((rem * 10) / den));
            rem = (rem * 10) % den;
        }
        return sb.toString();
    }
}
// Given an integer, convert it to a roman numeral.

// Input is guaranteed to be within the range from 1 to 3999.

// Aced.
public class Solution {
    public String intToRoman(int num) {
        int a = num % 10;
        num /= 10;
        int b = num % 10;
        num /= 10;
        int c = num % 10;
        num /= 10;
        int d = num % 10;
        StringBuilder sb = new StringBuilder();
        if (d > 0)
            sb.append(roman(d, 4));
        if (c > 0)
            sb.append(roman(c, 3));
        if (b > 0)
            sb.append(roman(b, 2));
        if (a > 0)
            sb.append(roman(a, 1));
        return sb.toString();
    }
    
    private String roman(int digit, int pos) {
        char one = 'I', five = 'V', ten = 'X';
        switch (pos) {
            case 1: one = 'I'; five = 'V'; ten = 'X'; break;
            case 2: one = 'X'; five = 'L'; ten = 'C'; break;
            case 3: one = 'C'; five = 'D'; ten = 'M'; break;
            case 4: one = 'M';
        }
        StringBuilder sb = new StringBuilder();
        if (digit <= 3) {
            for (int i = 0; i < digit; i++)
                sb.append(one);
        } else if (digit == 4) {
            sb.append(one); 
            sb.append(five);
        } else if (digit == 9) {
            sb.append(one);
            sb.append(ten);
        } else {
            sb.append(five);
            for (int i = 5; i < digit; i++)
                sb.append(one);
        }
        return sb.toString();
    }
}

// Revised version. 
public class Solution {
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        for (int pos = 1; num > 0; pos++) {
            int digit = num % 10;
            sb.insert(0, roman(digit, pos));
            num = num / 10;
        }
        return sb.toString();
    }
    
    private String roman(int digit, int pos) {
        char one = 'I', five = 'V', ten = 'X';
        switch (pos) {
            case 1: one = 'I'; five = 'V'; ten = 'X'; break;
            case 2: one = 'X'; five = 'L'; ten = 'C'; break;
            case 3: one = 'C'; five = 'D'; ten = 'M'; break;
            case 4: one = 'M'; break;
        }
        StringBuilder sb = new StringBuilder();
        if (digit <= 3) {
            for (int i = 1; i <= digit; i++)
                sb.append(one);
        } else if (digit == 4) {
            sb.append(one); 
            sb.append(five);
        } else if (digit == 9) {
            sb.append(one);
            sb.append(ten);
        } else {
            sb.append(five);
            for (int i = 6; i <= digit; i++)
                sb.append(one);
        }
        return sb.toString();
    }
}
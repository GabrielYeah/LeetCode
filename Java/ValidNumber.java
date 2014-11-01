// Validate if a given string is numeric.

// Some examples:
// "0" => true
// " 0.1 " => true
// "abc" => false
// "1 a" => false
// "2e10" => true
// Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.

// First attempt. A very complicated solution.
public class Solution {
    public boolean isNumber(String s) {
        if (s == null)
            return false;
        String str = s.trim();
        if (str.length() == 0)
            return false;
            
        char first = str.charAt(0);
        if (first == '+' || first == '-')
            str = str.substring(1, str.length());
        else if (first != 'e' && first != '.' && !Character.isDigit(first))
            return false;
            
        if (str.length() == 0)
            return false;
        
        boolean e = false, p = false, d = false, a = false;
        boolean hasDigit = false;
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                d = true;
                hasDigit = true;
            } else if (c == 'e') {
                if (e || !hasDigit)
                    return false;
                e = true; d = false; p = false;
            } else if (c == '.') {
                if (p || e)
                    return false;
                d = false; p = true;
            } else if (c == '+' || c == '-') {
                if (!e || d || p || a)
                    return false;
                a = true;
            } else {
                return false;
            }
        }
        return d || (hasDigit && p);
    }
}

// Revised version. The whole idea is based on the transition of the states.
// When dealing with state, use more direct names like can, have, etc.
public class Solution {
    public boolean isNumber(String s) {
        if (s == null)
            return false;
        String str = s.trim();
        if (str.length() == 0)
            return false;
        
        boolean canE = false, canP = true, canS = true;
        boolean hasD = false, hasE =false, hasP = false;
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                hasD = true; canE = true; canS = false; canP = !hasE && !hasP;
            } else if (c == 'e') {
                if (hasE || !canE)
                    return false;
                hasE = true; hasD = false; canS = true; canP = false;
            } else if (c == '.') {
                if (!canP || hasP)
                    return false;
                hasP = true; canP = false; canS = false;
            } else if (c == '+' || c == '-') {
                if (!canS)
                    return false;
                canS = false;
            } else {
                return false;
            }
        }
        return hasD;
    }
}
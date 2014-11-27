// Implement strStr().

// Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

// Update (2014-11-02):
// The signature of the function had been updated to return the index instead of the pointer. If you still see your function signature returns a char * or String, please click the reload button  to reset your code definition.

// KMP. 
public class Solution {
    public int strStr(String haystack, String needle) {
        if (haystack == null || haystack.length() == 0)
            return needle == null || needle.length() == 0 ? 0 : -1;
        if (needle == null || needle.length() == 0)
            return 0;
        int[] failure = new int[needle.length()];
        failure[0] = -1;
        for (int i = 1; i < needle.length(); i++) {
            int j = failure[i - 1];ImplementStrstr()
            while (j >= 0 && needle.charAt(i) != needle.charAt(j + 1))
                j = failure[j];
            if (needle.charAt(i) == needle.charAt(j + 1))
                failure[i] = j + 1;
            else
                failure[i] = -1;
        }
        
        int hp = 0, np = 0;
        while (hp < haystack.length() && np < needle.length()) {
            if (haystack.charAt(hp) == needle.charAt(np)) {
                hp++;
                np++;
            } else if (np == 0) {
                hp++;
            } else {
                np = failure[np - 1] + 1;
            }
        }
        return np == needle.length() ? hp - needle.length() : -1;
    }
}
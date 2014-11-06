// Given a string containing only digits, restore it by returning all possible valid IP address combinations.

// For example:
// Given "25525511135",

// return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)

// First attempt. Made a lot of mistakes. 
public class Solution1 {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        if (s == null || s.length() < 4)
            return result;
        search(s, 0, 0, "", result);
        return result;
    }
    
    private void search(String s, int start, int depth, String path, List<String> result) {
        int end = s.length() - 1;
        if (depth == 4 || start > end) {
            if (depth == 4 && start > end)
                result.add(path.substring(1, path.length()));
            return;
        }
        if (s.charAt(start) == '0') {
            search(s, start + 1, depth + 1, path + ".0", result);
        } else {
            for (int count = 0; count < 3 && start + count <= end; ++count) {
                String tmp = s.substring(start, start + count + 1);
                if (isValid(tmp))
                    search(s, start + count + 1, depth + 1, path + "." + tmp, result);
            }
        }
    }
    
    private boolean isValid(String s) {
        int value = Integer.valueOf(s);
        return value <= 255 && value >= 0;
    }
}

// Cleaner way to write the code. Try to include specail case into a loop.
public class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        if (s == null || s.length() < 4)
            return result;
        search(s, 0, 0, "", result);
        return result;
    }
    
    private void search(String s, int start, int depth, String path, List<String> result) {
        if (s.length() - start > (4 - depth) * 3) return;
        if (s.length() - start < (4 - depth)) return;
        if (s.length() == start && depth == 4) {
            result.add(path.substring(0, path.length() - 1));
            return;
        }
        
        int num = 0;
        for (int i = start; i < start + 3 && i < s.length(); i++) {
            num = num * 10 + s.charAt(i) - '0';
            if (num <= 255) {
                path += s.charAt(i);
                search(s, i + 1, depth + 1, path + ".", result);
            }
            if (num == 0)
                break;
        }
    }   
}
// Given an absolute path for a file (Unix-style), simplify it.

// For example,
// path = "/home/", => "/home"
// path = "/a/./b/../../c/", => "/c"

// Corner Cases:
// Did you consider the case where path = "/../"?
// In this case, you should return "/".
// Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
// In this case, you should ignore redundant slashes and return "/home/foo".

// Split the string by '/'. Check each element.
public class Solution {
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0)
            return "";
        Stack<String> stack = new Stack<String>();
        int i = 0;
        while (i < path.length()) {
            while (i < path.length() && path.charAt(i) == '/')
                i++;
            int start = i;
            while (i < path.length() && path.charAt(i) != '/')
                i++;
            int end = i;
            String str = path.substring(start, end);
            if (str.equals(".")) {
                continue;
            } else if (str.equals("..")) {
                if (stack.size() > 0)
                    stack.pop();
            } else if (str.length() > 0) {
                stack.push(str);
            }
        }
        if (stack.size() == 0)
            return "/";
        StringBuilder result = new StringBuilder();
        for (i = 0; i < stack.size(); ++i)
            result.append("/" + stack.get(i));
        return result.toString();
    }
}
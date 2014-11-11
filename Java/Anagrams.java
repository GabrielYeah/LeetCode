// Given an array of strings, return all groups of strings that are anagrams.

// Note: All inputs will be in lower-case.

// Use the sorted version of a string as a key.
// Put all the strings with same key together.
public class Solution {
    public List<String> anagrams(String[] strs) {
        List<String> result = new ArrayList<String>();
        if (strs == null || strs.length == 0)
            return result;
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String s: strs) {
            char[] array = s.toCharArray();
            Arrays.sort(array);
            String str = new String(array);
            if (map.containsKey(str)) {
                map.get(str).add(s);
            } else {
                List<String> list = new ArrayList<String>(Arrays.asList(s));
                map.put(str, list);
            }
        }
        Iterator iter = map.values().iterator();  
        while (iter.hasNext()) {  
            ArrayList<String> list = (ArrayList<String>)iter.next();  
            if (list.size() > 1)  
                result.addAll(list);  
        }
        return result;
    }
}
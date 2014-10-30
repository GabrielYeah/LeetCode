// A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

// Return a deep copy of the list.

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
// Recursion version. AC at first attempt.
public class Solution1 {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null)
            return null;
        Map<RandomListNode, RandomListNode> copied = new HashMap<RandomListNode, RandomListNode>();
        return copyRandomList(head, copied);
    }
    
    private RandomListNode copyRandomList(RandomListNode node, Map<RandomListNode, RandomListNode> copied) {
        if (node == null)
            return null;
        if (copied.containsKey(node))
            return copied.get(node);
            
        RandomListNode clone = new RandomListNode(node.label);
        copied.put(node, clone);
        clone.next = copyRandomList(node.next, copied);
        clone.random = copyRandomList(node.random, copied);
        
        return clone;
    }
}

// Iterative version.
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null)
            return null;
        Map<RandomListNode, RandomListNode> copied = new HashMap<RandomListNode, RandomListNode>();
        Queue<RandomListNode> rest = new LinkedList<RandomListNode>(Arrays.asList(head));
        while (!rest.isEmpty()) {
            RandomListNode original = rest.poll();
            RandomListNode copy = copyNode(original, copied);
            if (original != null) {
                if (!copied.containsKey(original.next))
                    rest.add(original.next);
                if (!copied.containsKey(original.random))
                    rest.add(original.random);
                copy.next = copyNode(original.next, copied);
                copy.random = copyNode(original.random, copied);
            }
        }
        return copied.get(head);
    }
    
    private RandomListNode copyNode(RandomListNode node, Map<RandomListNode, RandomListNode> copied) {
        if (node == null)
            return null;
        if (copied.containsKey(node))
            return copied.get(node);
            
        RandomListNode copy = new RandomListNode(node.label);
        copied.put(node, copy);
        return copy;
    }
}
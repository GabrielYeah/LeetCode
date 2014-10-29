// Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

// get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
// set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

// Detail!!
// Next time when handling linked list, pay more attention
// on the operation on the nodes.
public class LRUCache {
    
    Map<Integer, ListNode> map;
    ListNode head;
    ListNode tail;
    int limit;
    
    public LRUCache(int capacity) {
        map = new HashMap<Integer, ListNode>();
        head = new ListNode(0, 0); 
        tail = new ListNode(0, 0);
        head.next = tail;
        tail.prev = head;
        limit = capacity;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            use(node);
            return node.val;
        } else {
            return -1;
        }
    }
    
    public void set(int key, int value) {
        if (limit <= 0)
            return;
            
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            node.val = value;
            use(node);
        } else {
            if (map.size() == limit)
                remove();
            ListNode node = new ListNode(key, value);
            add(node);
        }
    }
    
    // Remove the last node
    void remove() {
        ListNode last = tail.prev;
        map.remove(last.key);
        last.prev.next = tail;
        tail.prev = last.prev;
    }
    
    // Add a node to the front
    void add(ListNode node) {
        map.put(node.key, node);
        ListNode first = head.next;
        first.prev = node;
        node.next = first;
        head.next = node;
        node.prev = head;
    }
    
    // Update the node and put it to the front
    void use(ListNode node) {
        ListNode p = node.prev;
        ListNode n = node.next;
        p.next = node.next;
        n.prev = node.prev;
        
        ListNode f = head.next;
        node.next = f;
        f.prev = node;
        
        head.next = node;
        node.prev = head;
    }
}

class ListNode {
    public int val;
    public int key;
    public ListNode prev;
    public ListNode next;
    public ListNode(int key, int value) {
        this.key = key;
        this.val = value;
        this.prev = null;
        this.next = null;
    }
}
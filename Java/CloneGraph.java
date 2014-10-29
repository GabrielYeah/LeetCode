// Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
// Recursive solution, accepted at first attempt
public class Solution {
    
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        Map<Integer, UndirectedGraphNode> map = new HashMap<Integer, UndirectedGraphNode>();
        
        return cloneGraph(node, map);
    }
    
    private UndirectedGraphNode cloneGraph(UndirectedGraphNode node, Map<Integer, UndirectedGraphNode> map) {
        if (node == null)
            return null;
        if (map.containsKey(node.label))
            return map.get(node.label);
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        map.put(node.label, clone);
        for (UndirectedGraphNode neighbor : node.neighbors) {
            if (map.containsKey(neighbor.label)) {
                clone.neighbors.add(map.get(neighbor.label));
            } else {
                UndirectedGraphNode clonedNeighbor = cloneGraph(neighbor, map);
                clone.neighbors.add(clonedNeighbor);
            }
        }
        return clone;
    }
}
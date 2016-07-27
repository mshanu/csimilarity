package bcentrality;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GraphBuilder<T> {
    Map<T, Node> nodeMap = new HashMap<>();

    public void addEdge(T from, T to, Double weight) {
        Node fromNode = nodeMap.getOrDefault(from, new Node(from));
        Node toNode = nodeMap.getOrDefault(to, new Node(to));
        if (!toNode.hasEdge(fromNode)) {
            fromNode.createEdge(toNode, weight);
            nodeMap.put(from, fromNode);
            nodeMap.put(to, toNode);
        }
    }

    public Graph build() {
        return new Graph(new ArrayList<>(nodeMap.values()));
    }
}

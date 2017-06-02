package bcentrality;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GraphBuilder<T> {
    Map<T, Node> nodeMap = new HashMap<>();

    public void addEdge(T documentA, T documentB, Double weight) {
        Node nodeA = nodeMap.getOrDefault(documentA, new Node(documentA));
        Node nodeB = nodeMap.getOrDefault(documentB, new Node(documentB));
        nodeMap.put(documentA, attachNode(weight, nodeA, nodeB));
        nodeMap.put(documentB, attachNode(weight, nodeB, nodeA));
    }

    private Node attachNode(Double weight, Node from, Node to) {
        if (!from.hasEdge(to)) {
            from.createEdge(to, weight);
        }
        return from;
    }

    public Graph build() {
        return new Graph(new ArrayList<>(nodeMap.values()));
    }
}

package bcentrality;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@AllArgsConstructor
public class GraphBuilder<T> {
    Map<T, Node> nodeMap = new HashMap<>();

    public void addEdge(T documentA, T documentB, Double weight) {
        Node nodeA = nodeMap.getOrDefault(documentA, new Node(documentA));
        Node nodeB = nodeMap.getOrDefault(documentB, new Node(documentB));
        nodeA.createEdge(nodeB, weight);
        nodeMap.put(documentA, nodeA);
        nodeMap.put(documentB, nodeB);
    }

    public Graph build() {
        return new Graph(new HashSet<>(nodeMap.values()));
    }
}

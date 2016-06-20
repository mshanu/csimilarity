package bcentrality;

import java.util.HashMap;
import java.util.Map;

public class Graph {
    private Map<Node, Edges> edges = new HashMap<>();

    public void addNodeAndEdges(Node node, Edges edges) {
        this.edges.put(node, edges);
    }
}

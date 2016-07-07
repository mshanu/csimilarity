package bcentrality;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Graph {
    private List<Node> nodes;
    @Getter
    private List<Node> shortestDistanceCalculatedNodes;

    public Graph(List<Node> nodes) {
        this.nodes = nodes;
    }

    public Nodes getShortestDistanceForAllNodesFrom(Node node) {
        shortestDistanceCalculatedNodes = new ArrayList<>();
        node.updateDistance(null, 0.0);
        dijstraksTraversal(nodes);
        return new Nodes(shortestDistanceCalculatedNodes);
    }

    private void dijstraksTraversal(List<Node> nodes) {
        if (nodes.isEmpty()) {
            return;
        }
        Node leastDistantNode = nodes.stream().min(Comparator.comparing(Node::getShortestDistanceFromSource)).get();
        nodes.remove(leastDistantNode);
        leastDistantNode.updateTheAdjacentNodeDistance();
        shortestDistanceCalculatedNodes.add(leastDistantNode);
        dijstraksTraversal(nodes);

    }
}

package bcentrality;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Graph {
    private List<Node> nodes;
    @Getter
    private List<Node> shortestDistanceCalculatedNodes;

    public Graph(List<Node> nodes) {
        this.nodes = nodes;
    }

    public Nodes getShortestDistanceForAllNodesFrom(Node node) {
        this.nodes.stream().forEach(Node::initShortDistanceCalculation);
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

    public Graph sparse(Double value) {
        Integer graphSize = nodes.size();
        Integer numberOfEdgesToRetain = new Double(Math.pow(graphSize, value)).intValue();
        return new Graph(nodes.stream().map(node -> node.prune(numberOfEdgesToRetain)).collect(Collectors.toList()));
    }

}

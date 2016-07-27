package bcentrality;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    @Getter
    private List<Node> nodes;
    @Getter
    private HashSet<Node> shortestDistanceCalculatedNodes;

    public Graph(List<Node> nodes) {
        this.nodes = nodes;
    }

    public Nodes getShortestDistanceForAllNodesFrom(Node node) {
        this.nodes.stream().forEach(Node::initShortDistanceCalculation);
        shortestDistanceCalculatedNodes = new HashSet<>();
        node.makeStartingNode();
        dijstraksTraversal(new ArrayList<>(nodes));
        return new Nodes(shortestDistanceCalculatedNodes);
    }

    public void calculateCentralityValue() {
        nodes.stream().forEach(node -> {
            Set<Node> nodesWithShortestPaths = getShortestDistanceForAllNodesFrom(node).getNodes();
            nodesWithShortestPaths.forEach(node1 -> {
                Integer totalShortestPathToSource = node1.getNumberOfShortestDistancePaths();
                node1.updateCentrality(totalShortestPathToSource);
            });
        });
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

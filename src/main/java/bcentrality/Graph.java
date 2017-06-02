package bcentrality;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    @Getter
    private List<Node> nodes;


    public Graph(List<Node> nodes) {
        this.nodes = nodes;
    }

    public Nodes getShortestDistanceForAllNodesFrom(Node node) {
        this.nodes.stream().forEach(Node::initShortDistanceCalculation);
        node.makeStartingNode();
        return new Nodes(dijstraksTraversal(new ArrayList<>(nodes), new HashSet<>()));
    }

    public Graph calculateCentralityValue() {
        nodes.stream().forEach(node -> {
            Set<Node> nodesWithShortestPaths = getShortestDistanceForAllNodesFrom(node).getNodes();
            nodesWithShortestPaths.forEach(node1 -> {
                Integer totalShortestPathToSource = node1.getNumberOfShortestPathFromSource();
                node1.updatePairDependencies(totalShortestPathToSource);
            });
        });
        return new Graph(nodes);
    }

    /*
    Learned: Previous implementation was of keeping another collection of nodes which are covered
    Removing the nodes from the list on each iteration and.
    nodes.stream().min(Comparator.comparing(Node::getShortestDistanceFromSource)).get()
    going with priority que next.
     */
    private HashSet<Node> dijstraksTraversal(List<Node> nodes, HashSet<Node> shortestDistanceCalculatedNodes) {
        if (nodes.isEmpty()) {
            return shortestDistanceCalculatedNodes;
        }
        Node leastDistantNode = nodes.stream().min(Comparator.comparing(Node::getShortestDistanceFromSource)).get();
        nodes.remove(leastDistantNode);
        leastDistantNode.updateTheAdjacentNodeDistance();
        shortestDistanceCalculatedNodes.add(leastDistantNode);
        return dijstraksTraversal(nodes, shortestDistanceCalculatedNodes);

    }

    public Graph sparse(Double value) {
        Integer graphSize = nodes.size();
        Integer numberOfEdgesToRetain = new Double(Math.pow(graphSize, value)).intValue();
        return new Graph(nodes.stream().map(node -> node.prune(numberOfEdgesToRetain)).collect(Collectors.toList()));
    }


    @Override
    public String toString() {
        return this.nodes.stream().map(Node::toString).reduce(String::concat).get();
    }
}

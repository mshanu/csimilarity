package bcentrality;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Graph {
    @Getter
    private List<Node> nodes;


    public Graph(List<Node> nodes) {
        this.nodes = nodes;
    }

    public Stack<Node> getShortestDistanceForAllNodesFrom(Node node) {
        this.nodes.stream().forEach(Node::initShortDistanceCalculation);
        node.makeStartingNode();
        Stack<Node> nodes = dijstraksTraversal(new ArrayList<>(this.nodes), new Stack<>());
        return nodes;
    }

    public Graph calculateCentralityValue() {
        nodes.stream().forEach(sourceNode -> {
            Stack<Node> nodesWithShortestPaths = getShortestDistanceForAllNodesFrom(sourceNode);
            nodesWithShortestPaths.forEach(node1 -> {
                node1.updatePairDependencies();
                if (!node1.equals(sourceNode)) {
                    node1.addCentrality();
                }
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
    private Stack<Node> dijstraksTraversal(List<Node> nodes, Stack<Node> shortestDistanceCalculatedNodes) {
        if (nodes.isEmpty()) {
            return shortestDistanceCalculatedNodes;
        }
        Node leastDistantNode = nodes.stream().min(Comparator.comparing(Node::getShortestDistanceFromSource)).get();
        nodes.remove(leastDistantNode);
        leastDistantNode.updateTheAdjacentNodeDistance();
        shortestDistanceCalculatedNodes.push(leastDistantNode);
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

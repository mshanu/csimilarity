package bcentrality;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

@EqualsAndHashCode(exclude = {"shortestDistanceFromSource", "parentNode", "edges"})
@Getter
@AllArgsConstructor
public class Node<T> {
    private T dataNode;
    private Double shortestDistanceFromSource;
    private Nodes parentNodes = new Nodes(new HashSet<>());
    private Edges edges = new Edges(new ArrayList<>());
    private Double centralityValue = 0.0;

    public Node(T dataNode) {
        this.dataNode = dataNode;
        this.shortestDistanceFromSource = Double.MAX_VALUE;
    }

    public void initShortDistanceCalculation() {
        this.parentNodes = new Nodes();
        this.shortestDistanceFromSource = Double.MAX_VALUE;
    }

    public void createEdge(Node node, Double weight) {
        edges.add(new Edge(node, weight));
    }

    public void updateTheAdjacentNodeDistance() {
        edges.updateShortestDistant(this, shortestDistanceFromSource);
    }

    public Node updateDistance(Node parentNode, Double distanceFromSource) {
        if (distanceFromSource <= shortestDistanceFromSource) {
            this.shortestDistanceFromSource = distanceFromSource;
            parentNodes.add(parentNode);
        }
        return this;
    }

    public Node prune(Integer numberOfEdgesToRetain) {
        edges = edges.prune(numberOfEdgesToRetain);
        return this;
    }

    public Integer getNumberOfShortestDistancePaths() {
        if (parentNodes.getNodes().isEmpty()) {
            return 1;
        }
        return parentNodes.getNodes().stream().collect(Collectors.summingInt(Node::getNumberOfShortestDistancePaths));
    }

    //Optimize here
    public void updateCentrality(Integer totalSumOfShortestPath) {
        if (parentNodes.getNodes().isEmpty()) {
            this.centralityValue = 1/totalSumOfShortestPath.doubleValue();
        }
        else {
            this.centralityValue = this.centralityValue + getNumberOfShortestDistancePaths() / totalSumOfShortestPath.doubleValue();
            this.parentNodes.getNodes().forEach(parentNode -> parentNode.updateCentrality(totalSumOfShortestPath));
        }
    }

    public Boolean hasInfiniteDistance() {
        return this.shortestDistanceFromSource.equals(Double.MAX_VALUE);
    }

    public void makeStartingNode() {
        this.shortestDistanceFromSource = 0.0;
    }

    public Boolean hasEdge(Node to) {
        return edges.hasNode(to);
    }

    @Override
    public String toString() {
        if (edges.isEmpty()) {
            return "";
        }
        String template = "\"%s\" -> \"%s\" [label =\"%s\"];";
        return edges.getEdges().stream().map(edge -> String.format(template, getNameAndCentrality(), edge.getToNode().getNameAndCentrality(), edge.getWeight())).reduce(String::concat).get();

    }

     String getNameAndCentrality() {
        return dataNode.toString() + " " + centralityValue;
    }
}

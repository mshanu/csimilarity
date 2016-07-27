package bcentrality;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.stream.Collectors;

@EqualsAndHashCode(exclude = {"shortestDistanceFromSource", "parentNode", "edges"})
@Getter
@AllArgsConstructor
public class Node<T> {
    private T dataNode;
    private Double shortestDistanceFromSource;
    private Nodes parentNodes = new Nodes(new ArrayList<>());
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
        this.centralityValue = this.centralityValue + getNumberOfShortestDistancePaths() / totalSumOfShortestPath.doubleValue();
    }

    public Boolean hasInfiniteDistance() {
        return this.shortestDistanceFromSource.equals(Double.MAX_VALUE);
    }

}

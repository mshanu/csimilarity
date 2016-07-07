package bcentrality;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;

@EqualsAndHashCode(exclude = {"shortestDistanceFromSource", "parentNode", "edges"})
@Getter
@AllArgsConstructor
public class Node<T> {
    private T dataNode;
    private Double shortestDistanceFromSource;
    private Node parentNode;
    private Edges edges = new Edges(new ArrayList<>());

    public Node(T dataNode) {
        this.dataNode = dataNode;
        this.shortestDistanceFromSource = Double.MAX_VALUE;
    }

    public void createEdge(Node node, Double shortestDistanceFromSource) {
        edges.add(new Edge(node, shortestDistanceFromSource));
    }

    public void updateTheAdjacentNodeDistance() {
        edges.updateShortestDistant(this, shortestDistanceFromSource);
    }

    public Node updateDistance(Node parentNode, Double distanceFromSource) {
        if (distanceFromSource < shortestDistanceFromSource) {
            this.shortestDistanceFromSource = distanceFromSource;
            this.parentNode = parentNode;
        }
        return this;
    }


    public Boolean hasInfiniteDistance() {
        return this.shortestDistanceFromSource.equals(Double.MAX_VALUE);
    }
}

package bcentrality;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(exclude = {"shortestDistanceFromSource", "parentNode"})
@Getter
@AllArgsConstructor
public class Node<T> {
    private T dataNode;
    private Double shortestDistanceFromSource;
    private Node parentNode;
    private Edges edges;

    public Node(T dataNode) {
        this.dataNode = dataNode;
        this.shortestDistanceFromSource = Double.MAX_VALUE;
    }


    public void updateTheLeastDistantEdgeNodeDistance() {
        Edge shortestDistantEdge = edges.getShortestDistantEdge();
        Node toNode = shortestDistantEdge.getToNode();
        toNode.updateDistance(this, shortestDistanceFromSource + shortestDistantEdge.getDistance());
    }

    public Node updateDistance(Node parentNode, Double distanceFromSource) {
        if (distanceFromSource < shortestDistanceFromSource) {
            this.shortestDistanceFromSource = distanceFromSource;
            this.parentNode = parentNode;
        }
        return this;
    }
}

package bcentrality;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Edge {
    private Node toNode;
    private Double distance;

    public Integer compareTheDistance(Edge edge) {
        return distance.compareTo(edge.distance);
    }


    public void updateShortestDistance(Node parent, double distanceFromSource) {
        toNode.updateDistance(parent, distanceFromSource + distance);
    }
}

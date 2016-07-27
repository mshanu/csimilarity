package bcentrality;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Edge {
    private Node toNode;
    private Double weight;

    public Integer compareTheDistance(Edge edge) {
        return weight.compareTo(edge.weight);
    }


    public void updateShortestDistance(Node parent, double distanceFromSource) {
        toNode.updateDistance(parent, distanceFromSource + weight);
    }
}

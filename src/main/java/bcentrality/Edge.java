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


}

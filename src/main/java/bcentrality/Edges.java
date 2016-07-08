package bcentrality;

import lombok.AllArgsConstructor;

import java.util.List;


@AllArgsConstructor
public class Edges {
    private List<Edge> edges;

    public void updateShortestDistant(Node parent, Double distanceFromSource) {
        edges.stream().forEach(edge -> edge.updateShortestDistance(parent, distanceFromSource));
    }

    public void add(Edge edge) {
        edges.add(edge);
    }
}

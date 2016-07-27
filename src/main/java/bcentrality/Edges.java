package bcentrality;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;


@AllArgsConstructor
@Getter
public class Edges {
    private List<Edge> edges;

    public void updateShortestDistant(Node parent, Double distanceFromSource) {
        edges.stream().forEach(edge -> edge.updateShortestDistance(parent, distanceFromSource));
    }

    public Edges prune(Integer numberOfEdgesToRetain) {
        edges.sort(Edge::compareTheDistance);
        return new Edges(edges.subList(0, numberOfEdgesToRetain));
    }

    public void add(Edge edge) {
        edges.add(edge);
    }
}

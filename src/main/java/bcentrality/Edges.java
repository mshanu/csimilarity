package bcentrality;

import lombok.AllArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;


@AllArgsConstructor
public class Edges {
    private List<Edge> edges;

    public Edge getShortestDistantEdge() {
        return edges.stream().min(Edge::compareTheDistance).get();
    }

    public List<Node> nodes() {
        return edges.stream().map(Edge::getToNode).collect(toList());
    }

    public void updateShortestDistant(Node parent,Double distanceFromSource) {
        edges.stream().forEach(edge -> edge.updateShortestDistance(parent,distanceFromSource));
    }

    public void add(Edge edge) {
        edges.add(edge);
    }
}

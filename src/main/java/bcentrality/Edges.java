package bcentrality;

import lombok.AllArgsConstructor;

import java.util.List;


@AllArgsConstructor
public class Edges {
    private List<Edge> edges;


    public Edge getShortestDistantEdge() {
        return edges.stream().min(Edge::compareTheDistance).get();
    }
}

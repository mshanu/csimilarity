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
        if(numberOfEdgesToRetain < edges.size()){
            return new Edges(edges.subList(0, numberOfEdgesToRetain));
        }
        return this;
    }

    public void add(Edge edge) {
        edges.add(edge);
    }

    public Boolean hasNode(Node to) {
        return edges.stream().filter(edge -> edge.getToNode().equals(to)).findAny().isPresent();
    }

    public boolean isEmpty() {
        return edges.isEmpty();
    }
}

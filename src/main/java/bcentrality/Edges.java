package bcentrality;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import static java.util.stream.Collectors.toList;


@AllArgsConstructor
@Getter
public class Edges {
    private List<Edge> edges;

    public void updateShortestDistant(Node predecessor, Double distanceFromSource) {
        edges.stream().forEach(edge -> edge.updateShortestDistance(predecessor, distanceFromSource));
    }

    public Edges prune(Integer numberOfEdgesToRetain) {
        edges.sort(Edge::compareTheDistance);
        if(numberOfEdgesToRetain < edges.size()){
            return new Edges(edges.subList(0, numberOfEdgesToRetain));
        }
        return this;
    }

    public Edges pruneWithCentralityValue(Double centralityValue) {
        return new Edges(edges.stream().filter(edge -> !edge.hasHigherCentralityValue(centralityValue)).collect(toList()));
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

    public <T> Integer size() {
        return edges.size();
    }
}

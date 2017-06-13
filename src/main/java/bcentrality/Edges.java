package bcentrality;

import lombok.Getter;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;


@Getter
public class Edges {
    private List<Edge> edges;
    private final Set<Node> nodes;

    public Edges(List<Edge> edges) {
        this.edges = edges;
        nodes = edges.stream().map(Edge::getToNode).collect(toSet());
    }

    public void updateShortestDistant(Node predecessor, Double distanceFromSource) {
        edges.stream().forEach(edge -> edge.updateShortestDistance(predecessor, distanceFromSource));
    }

    public Edges prune(Integer numberOfEdgesToRetain) {
        edges.sort(Edge::compareTheDistance);
        if (numberOfEdgesToRetain < edges.size()) {
            return new Edges(edges.subList(0, numberOfEdgesToRetain));
        }
        return this;
    }

    public List<Edge> pruneWithCentralityValue(Double centralityValue) {
        return edges.stream().filter(edge -> !edge.hasHigherCentralityValue(centralityValue)).collect(toList());
    }


    public <T> Integer size() {
        return edges.size();
    }

    public void create(Node node, Double weight) {
        edges.add(new Edge(node, weight));
        nodes.add(node);
    }

    public Boolean hasNode(Node to) {
        return nodes.contains(to);
    }

    public boolean isEmpty() {
        return edges.isEmpty();
    }
}

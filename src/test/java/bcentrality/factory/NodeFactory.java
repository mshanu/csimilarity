package bcentrality.factory;

import bcentrality.Edges;
import bcentrality.Node;
import bcentrality.Nodes;

import java.util.Arrays;
import java.util.HashSet;

public class NodeFactory {
    public static <T> Node<T> aNode(T e) {
        return new Node<>(e);
    }

    public static <T> Node<T> aNodeWithNumberOfShortestDistance(T e, Integer numberOfShortestPathFromSource) {
        return Builder.forClass(Node.class).with("dataNode", e).with("numberOfShortestPathFromSource", numberOfShortestPathFromSource).build();
    }

    public static <T> Node<T> aNodeWithShortestDistanceFromSource(T e, Double shortestDistanceFromSource) {
        return Builder.forClass(Node.class).with("dataNode", e).with("shortestDistanceFromSource", shortestDistanceFromSource).build();
    }

    public static <T> Node<T> aNodeWithShortestDistanceFromSource(T e, Double shortestDistanceFromSource, Nodes predecessors) {
        return Builder.forClass(Node.class)
                .with("dataNode", e).with("shortestDistanceFromSource", shortestDistanceFromSource)
                .with("predecessors", predecessors)
                .build();
    }

    public static Node aNodeWithEdgesAndShortestDistanceFromSource(Edges edges, Double shortestDistanceFromSource) {
        Builder<Node> node = Builder.forClass(Node.class);
        return node.with("edges", edges).with("shortestDistanceFromSource", shortestDistanceFromSource).build();

    }

    public static <T> Node<T> anUnvisitedNode(T a) {
        return Builder.forClass(Node.class).with("dataNode", a).with("isVisited", false).build();
    }

    public static <T> Node<T> aNodeWithNumberOfShortestDistanceAndPredecessors(T e, Integer numberOfShortestPathFromSource, Nodes predecessors) {
        return Builder.forClass(Node.class).with("dataNode", e)
                .with("numberOfShortestPathFromSource", numberOfShortestPathFromSource)
                .with("predecessors", predecessors)
                .build();
    }

    public static <T> Node<T> aNodeWithNumberOfShortestDistancPredecessorsAndPairDependency(T e, Double pairDependecy, Integer numberOfShortestPathFromSource, Nodes predecessors) {
        return Builder.forClass(Node.class).with("dataNode", e)
                .with("pairDependency", pairDependecy)
                .with("numberOfShortestPathFromSource", numberOfShortestPathFromSource)
                .with("predecessors", predecessors)
                .build();
    }


    public static Nodes nodes(Node... nodes) {
        return new Nodes(new HashSet<>(Arrays.asList(nodes)));
    }

    public static <T> Node<T> aNodeWithCentralityValue(T datanode, Double centralityValue) {
        return Builder.forClass(Node.class).with("dataNode", datanode).with("centralityValue", centralityValue).build();
    }
}

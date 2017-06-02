package bcentrality.factory;

import bcentrality.Edge;
import bcentrality.Node;

public class EdgeFactory {

    public static Edge anEdge(Node node, Double weight) {
        return Builder.forClass(Edge.class).with("toNode", node).with("weight", weight).build();
    }

}

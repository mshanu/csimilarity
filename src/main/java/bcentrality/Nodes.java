package bcentrality;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Nodes {
    private List<Node> nodes;

    public Node getNode(Node node) {
        return nodes.stream().filter(node1 -> node1.equals(node)).findAny().get();
    }

}

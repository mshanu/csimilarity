package bcentrality;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Nodes {
    private List<Node> nodes;

    public Node getNode(Node node) {
        return nodes.stream().filter(node1 -> node1.equals(node)).findAny().get();
    }

    public void add(Node node) {
        nodes.add(node);
    }

}

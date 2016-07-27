package bcentrality;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Getter
public class Nodes {
    private Set<Node> nodes;

    public Nodes() {
        nodes = new HashSet<>();
    }

    public Node getNode(Node node) {
        return nodes.stream().filter(node1 -> node1.equals(node)).findAny().get();
    }


    public void add(Node node) {
        nodes.add(node);
    }


}

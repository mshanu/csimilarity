package bcentrality;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NodeTest {

    @Test
    public void shouldUpdateTheNodeDistanceIfLessThanTheCurrentDistnaceFromSource() throws Exception {
        Node nodeA = new Node<>("a");
        Node nodeB = new Node<>("b");
        Node updatedNode = nodeA.updateDistance(nodeB, 101.2);
        assertThat(updatedNode.getParentNodes().getNode(nodeB), is(nodeB));
        assertThat(updatedNode.getShortestDistanceFromSource(), is(101.2));

    }

    @Test
    public void shouldUpdateTheNodeDistanceForAllTheAdjacentNodes() throws Exception {
        Node adjacentNode1 = mock(Node.class);
        Node adjacentNode2 = mock(Node.class);
        Node<Integer> node = new Node<>(1, 101.0, mock(Nodes.class), new Edges(asList(new Edge(adjacentNode1, 102.2), new Edge(adjacentNode2, 105.4))));
        node.updateTheAdjacentNodeDistance();
        verify(adjacentNode1).updateDistance(node, 203.2);
        verify(adjacentNode2).updateDistance(node, 206.4);

    }
}
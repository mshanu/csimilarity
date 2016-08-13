package bcentrality;

import org.junit.Test;

import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

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
        Node<Integer> node = new Node<>(1, 101.0, mock(Nodes.class), new Edges(asList(new Edge(adjacentNode1, 102.2), new Edge(adjacentNode2, 105.4))), 0.0);
        node.updateTheAdjacentNodeDistance();
        verify(adjacentNode1).updateDistance(node, 203.2);
        verify(adjacentNode2).updateDistance(node, 206.4);

    }

    @Test
    public void shouldReturnTheNumberOfShortestDistance() {

        Node node4 = new Node(4, null, new Nodes(), null, null);

        Nodes parentNodesToNode2 = new Nodes(new HashSet<>(asList(node4)));
        Nodes parentNodesToNode3 = new Nodes(new HashSet<>(asList(node4)));

        Node<Integer> node2 = new Node(2, null, parentNodesToNode2, null, null);
        Node<Integer> node3 = new Node(3, null, parentNodesToNode3, null, null);

        Nodes parentToNode1 = new Nodes(new HashSet<>(asList(node2, node3)));
        Node<Integer> node1 = new Node<>(1, null, parentToNode1, null, 0.0);
        assertThat(node1.getNumberOfShortestDistancePaths(), is(2));

    }

    @Test
    public void shouldCalculateTheCentralityValueIfGivenTheTotalPath() {
        Node<Integer> spiedNode = spy(new Node<>(1, null, new Nodes(new HashSet<Node>(asList(mock(Node.class)))), null, 0.0));
        when(spiedNode.getNumberOfShortestDistancePaths()).thenReturn(4);

        spiedNode.updateCentrality(5);
        assertThat(spiedNode.getCentralityValue(), is(0.8));
    }
}
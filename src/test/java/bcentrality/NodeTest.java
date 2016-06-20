package bcentrality;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NodeTest {

    @Test
    public void shouldUpdateTheNodeDistanceIfLessThanTheCurrentDistnaceFromSource() throws Exception {
        Node nodeA = new Node<>("a");
        Node nodeB = new Node<>("b");
        Node updatedNode = nodeA.updateDistance(nodeB, 101.2);
        assertThat(updatedNode.getParentNode(), is(nodeB));
        assertThat(updatedNode.getShortestDistanceFromSource(), is(101.2));

    }

    @Test
    public void shouldUpdateTheNodeDistanceForTheShortestEdgeNode() throws Exception {
        Edges edges = mock(Edges.class);
        Node destinationNode = mock(Node.class);
        when(edges.getShortestDistantEdge()).thenReturn(new Edge(destinationNode,12.3));

        Node<String> parentNode = new Node<>("a", 10.2, null, edges);
        parentNode.updateTheLeastDistantEdgeNodeDistance();
        verify(destinationNode).updateDistance(parentNode, 22.5);
    }
}
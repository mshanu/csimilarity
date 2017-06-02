package bcentrality;

import bcentrality.factory.NodeFactory;
import org.hamcrest.Matchers;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class EdgesTest {

    @Test
    public void shouldUpdateTheEdgeNotWithShortestDistance() {
        Edge edge1 = mock(Edge.class);
        Edge edge2 = mock(Edge.class);
        Edges edges = new Edges(asList(edge1, edge2));
        Node<String> node = NodeFactory.aNode("a");
        double shortestDistance = 123.3;
        edges.updateShortestDistant(node, shortestDistance);
        verify(edge1).updateShortestDistance(node, shortestDistance);
        verify(edge2).updateShortestDistance(node, shortestDistance);
    }

    @Test
    public void shouldReturnNewEdgesPrunedByNumberOfInputEdges() {
        Edge expectedNode1 = new Edge(mock(Node.class), 35.5);
        Edge expectedNode2 = new Edge(mock(Node.class), 25.5);
        Edge expectedNode3 = new Edge(mock(Node.class), 55.5);
        Edges edges = new Edges(asList(new Edge(mock(Node.class), 65.5), expectedNode1,
                expectedNode2, expectedNode3, new Edge(mock(Node.class), 75.5)));
        Edges prunedEdges = edges.prune(3);
        assertThat(prunedEdges.getEdges().size(), is(3));
        assertThat(prunedEdges.getEdges(), Matchers.containsInAnyOrder(expectedNode1, expectedNode2, expectedNode3));
    }

    @Test
    public void shouldReturnTheEdgesWhichHasNodeCentralityValueLessThanGivenCentralityValue() throws Exception {
        Edge edgeWithHigherCentralityValue = mock(Edge.class);
        Edge edgeWithLowerCentralityValue = mock(Edge.class);
        Edge anotherEdgeWithLowerCentralityValue = mock(Edge.class);
        when(edgeWithHigherCentralityValue.hasHigherCentralityValue(12.23)).thenReturn(true);
        when(edgeWithLowerCentralityValue.hasHigherCentralityValue(12.23)).thenReturn(false);
        when(anotherEdgeWithLowerCentralityValue.hasHigherCentralityValue(12.23)).thenReturn(false);
        Edges edges = new Edges(asList(edgeWithHigherCentralityValue, edgeWithLowerCentralityValue, anotherEdgeWithLowerCentralityValue));
        Edges prunedEdges = edges.pruneWithCentralityValue(12.23);
        assertThat(prunedEdges.size(), is(2));
        assertThat(prunedEdges.getEdges(), Matchers.containsInAnyOrder(edgeWithLowerCentralityValue, anotherEdgeWithLowerCentralityValue));

    }
}
package bcentrality;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class EdgesTest {

    @Test
    public void shouldReturnTheNodeWithShortestDistant() {
        Edge parentNode = mock(Edge.class);
        Edge expectedShortestDistantNode = new Edge(mock(Node.class), 11.3);
        Edges edges = new Edges(asList(
                new Edge(mock(Node.class), 12.3),
                expectedShortestDistantNode,
                new Edge(mock(Node.class), 18.3)
        ));

        Edge shortestDistantNode = edges.getShortestDistantEdge();
        assertThat(shortestDistantNode, is(expectedShortestDistantNode));
    }

}
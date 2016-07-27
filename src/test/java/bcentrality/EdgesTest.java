package bcentrality;

import org.hamcrest.Matchers;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class EdgesTest {
    @Test
    public void shouldReturnNewEdgesPrunedByNumberOfInputEdges() {
        Edge expectedNode1 = new Edge(mock(Node.class), 35.5);
        Edge expectedNode2 = new Edge(mock(Node.class), 25.5);
        Edge expectedNode3 = new Edge(mock(Node.class), 55.5);
        Edges edges = new Edges(asList(new Edge(mock(Node.class), 65.5),expectedNode1,
                expectedNode2, expectedNode3, new Edge(mock(Node.class), 75.5)));
        Edges prunedEdges = edges.prune(3);
        assertThat(prunedEdges.getEdges().size(), is(3));
        assertThat(prunedEdges.getEdges(), Matchers.containsInAnyOrder(expectedNode1,expectedNode2,expectedNode3));

    }

}
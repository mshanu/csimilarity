package bcentrality;

import org.junit.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GraphTest {

    @Test
    public void shouldDoTheShortestPathCalculationFromTheGivenNode() {
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        a.createEdge(b,12.3);
        a.createEdge(c,24.3);
        b.createEdge(d, 4.5);
        c.createEdge(d,4.5);
        Graph graph = new Graph(new ArrayList<>(asList(a, b, c, d)));
        Nodes shortestDistanceForAllNodesFrom = graph.getShortestDistanceForAllNodesFrom(a);
        Node nodeD = shortestDistanceForAllNodesFrom.getNode(d);
        DecimalFormat decimalFormat = new DecimalFormat("##.##");

        assertThat(decimalFormat.format(nodeD.getShortestDistanceFromSource()), is("16.8"));

    }
}
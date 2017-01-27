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
        a.createEdge(b, 12.3);
        a.createEdge(c, 24.3);
        b.createEdge(d, 4.5);
        c.createEdge(d, 4.5);
        Graph graph = new Graph(new ArrayList<>(asList(a, b, c, d)));
        Nodes shortestDistanceForAllNodesFrom = graph.getShortestDistanceForAllNodesFrom(a);
        Node nodeD = shortestDistanceForAllNodesFrom.getNode(d);
        DecimalFormat decimalFormat = new DecimalFormat("##.##");

        assertThat(decimalFormat.format(nodeD.getShortestDistanceFromSource()), is("16.8"));

    }

    @Test
    public void shouldGetMultipleShortestPathFromGivenNodeToDestination() {
//          12.4   +  15.6  + 13.4 = 41.4
//        a  ->  a11  ->  a21  ->  a41
//          |11.4   +  7.3  + 11.4 +  11.3 = 41.4
//           ->  a12  ->  a22  ->  a31  ->  a41
//                  | 3.4  +   44.5
//                    ->  a23  ->  a31
//                  | 10.6 +  11.4   +   8.00 = 41.4
//                    ->  a24  ->  a32  ->  a41

        Node a = new Node("a"), a11 = new Node("a11"), a12 = new Node("a12"), a21 = new Node("a21"),
                a22 = new Node("a22"), a23 = new Node("a23"), a24 = new Node("a24"), a31 = new Node("a31"),
                a32 = new Node("a32"), a41 = new Node("a41");

        a.createEdge(a11, 12.4);
        a.createEdge(a12, 11.4);
        a11.createEdge(a21, 15.6);
        a12.createEdge(a22, 7.3);
        a12.createEdge(a23, 3.4);
        a12.createEdge(a24, 10.6);
        a22.createEdge(a31, 11.4);
        a23.createEdge(a31, 44.5);
        a24.createEdge(a32, 11.4);
        a31.createEdge(a41, 11.3);
        a32.createEdge(a41, 8.00);


        Graph graph = new Graph(new ArrayList<>(asList(a, a11, a12, a21, a22, a23, a24, a31, a32, a41)));
        Nodes shortestDistanceForAllNodesFrom = graph.getShortestDistanceForAllNodesFrom(a);
        Node lastNode = shortestDistanceForAllNodesFrom.getNode(a41);
        DecimalFormat decimalFormat = new DecimalFormat("##.##");

        assertThat(decimalFormat.format(lastNode.getShortestDistanceFromSource()), is("41.4"));
        assertThat(lastNode.getNumberOfShortestDistancePaths(), is(3));
    }
}
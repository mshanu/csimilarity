package bcentrality;

import bcentrality.factory.NodeFactory;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;

import static bcentrality.factory.NodeFactory.aNodeWithCentralityValue;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class CentralityGraphTest {

    @Test
    public void shouldCreateClusterAfterPruningTheEdgesBasedOnHighCentralityValue() {
        Node<String> a = aNodeWithCentralityValue("a", 10.2328283);
        Node<String> b = aNodeWithCentralityValue("b", 10.23345435);
        Node<String> c = aNodeWithCentralityValue("c", 13.433245345);
        Node<String> d = aNodeWithCentralityValue("d", 14.23234677);
        Node<String> e = aNodeWithCentralityValue("e", 13.23858);
        Node<String> f = aNodeWithCentralityValue("f", 10.236787);
        a.createEdge(b, 1.1);
        b.createEdge(c, 1.1);
        c.createEdge(d, 1.1);
        c.createEdge(e, 1.1);
        d.createEdge(f, 1.1);
        Graph graph = new Graph(new HashSet<>(asList(a, b, c, d, e, f)));
        CentralityGraph centralityGraph = spy(new CentralityGraph(graph));
        doReturn(10.23434).when(centralityGraph).mean();
        doReturn(2.14545).when(centralityGraph).standardDeviationOfCentralities();

        List<Graph> clusters = centralityGraph.removeEdgesWithHigherCentrality().getClusters();
        assertThat(clusters.size(), is(3));
    }

    @Test
    public void shouldReturnMeanAndStandardDeviationOfCentralities() {
        CentralityGraph centralityGraph = new CentralityGraph(new Graph(new HashSet<>(asList(
                aNodeWithCentralityValue("a", 2.3),
                aNodeWithCentralityValue("b", 3.5),
                aNodeWithCentralityValue("c", 6.5),
                aNodeWithCentralityValue("d", 4.7)
        ))));
        assertThat(centralityGraph.mean(),is(4.25));
        assertThat(centralityGraph.standardDeviationOfCentralities(),is(1.55161206491829));
    }

}
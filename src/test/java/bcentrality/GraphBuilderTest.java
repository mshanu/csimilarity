package bcentrality;

import org.junit.Test;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

public class GraphBuilderTest {
    @Test
    public void shouldBuildTheGraph() {
        GraphBuilder<String> stringGraphBuilder = new GraphBuilder<>();
        stringGraphBuilder.addEdge("A", "B", 3.0);
        stringGraphBuilder.addEdge("B", "C", 4.0);
        stringGraphBuilder.addEdge("A", "D", 5.0);
        stringGraphBuilder.addEdge("D", "E", 2.0);
        stringGraphBuilder.addEdge("C", "E", 1.5);
        Graph graph = stringGraphBuilder.build();
        assertThat(graph.getNodes(), hasSize(5));
        assertThat(graph.getNodes().stream().map(Node::getDataNode).collect(toList()), containsInAnyOrder("A", "B", "C", "D", "E"));
    }

}
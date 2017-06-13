package bcentrality;

import csimilarity.Document;
import org.junit.Test;

import java.util.HashSet;

import static bcentrality.factory.NodeFactory.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class NodeTest {
    @Test
    public void shouldUpdateTheNodeDistanceIfLessThanTheCurrentDistnaceFromSource() throws Exception {
        Node nodeA = aNodeWithShortestDistanceFromSource("a", 101.2), nodeB = aNodeWithNumberOfShortestDistance("b", 2);
        assertThat(nodeA.getNumberOfShortestPathFromSource(), is(1));
        Node updatedNode = nodeA.updateDistance(nodeB, 100.2);
        assertThat(updatedNode.getPredecessors().getNode(nodeB), is(nodeB));
        assertThat(updatedNode.getShortestDistanceFromSource(), is(100.2));
        assertThat(updatedNode.getNumberOfShortestPathFromSource(), is(2));

    }

    @Test
    public void shouldAddThePredecessorAndAddTheNumberOfShortestPathsWhenUpdatedDistanceIsSameAsCurrentShortestPathDistance() {
        HashSet<Node> nodes = new HashSet<>();
        nodes.add(aNode("c"));
        Node nodeA = aNodeWithShortestDistanceFromSource("a", 101.2, new Nodes(nodes)), nodeB = aNodeWithNumberOfShortestDistance("b", 2);
        Node updatedNode = nodeA.updateDistance(nodeB, 101.2);
        assertThat(nodeA.getNumberOfShortestPathFromSource(), is(3));
        assertThat(updatedNode.getPredecessors().getNodes(), hasSize(2));
    }

    @Test
    public void shouldUpdateThePairDependencyForAGivenNodeAndSuccessor() {
        Node<String> a = aNodeWithNumberOfShortestDistancPredecessorsAndPairDependency("a", 2.0, 4, nodes());
        Node<String> b = aNodeWithNumberOfShortestDistancPredecessorsAndPairDependency("b", 1.5, 2, nodes());
        assertThat(b.updatePairDependencies(a).intValue(), is(3));
        assertThat(b.getPairDependency().intValue(), is(3));
    }


    @Test
    public void shouldUpdateTheNodeDistanceForAllTheAdjacentNodes() throws Exception {
        Edges edges = mock(Edges.class);
        Node node = aNodeWithEdgesAndShortestDistanceFromSource(edges, 123.3);
        node.updateTheAdjacentNodeDistance();
        verify(edges).updateShortestDistant(node, 123.3);
    }

    @Test
    public void shouldReturnTheLastPartOFthePathAsNameAndCentralityValue() {
        Document document = mock(Document.class);
        Node<Document> documentNode = new Node<>(document, null, null, null, null, 1.34323, null, false);
        when(document.toString()).thenReturn("/users/someuser/input/path asdf /asdfas/asdf.txt");
        when(document.toString()).thenReturn("/users/someuser/input/path asdf /asdfas/asdf.txt");
        assertThat(documentNode.getNameAndCentrality(), is("asdf.txt 1.34323"));
    }

    @Test
    public void shouldRemoveEdgesWithHighCentralityValue() {
        Node<String> a = aNodeWithCentralityValue("a", 1.3);
        Node<String> b = aNodeWithCentralityValue("b", 12.3);
        Node<String> c = aNodeWithCentralityValue("c", 21.3);
        Node<String> d = aNodeWithCentralityValue("d", 10.3);
        a.createEdge(b, 1.1);
        a.createEdge(c, 1.1);
        b.createEdge(c, 1.1);
        b.createEdge(d, 1.1);
        c.createEdge(d, 1.1);
        assertThat(b.getEdges().getNodes(), containsInAnyOrder(a, c, d));
        assertThat(b.removeEdgesWithHighCentralityValue(12.0).getEdges().getNodes(), containsInAnyOrder(a, d));
    }

}
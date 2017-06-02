package bcentrality;

import bcentrality.factory.NodeFactory;
import org.junit.Test;

import java.util.DoubleSummaryStatistics;
import java.util.HashSet;

import static bcentrality.factory.NodeFactory.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NodeTest {
    @Test
    public void shouldUpdateTheNodeDistanceIfLessThanTheCurrentDistnaceFromSource() throws Exception {
        Node nodeA = aNodeWithShortestDistanceFromSource("a", 101.2), nodeB = aNodeWithNumberOfShortestDistance("b", 2);
        assertThat(nodeA.getNumberOfShortestPathFromSource(), is(0));
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
        Node updatedNode = nodeA.updateDistance(nodeB, 100.2);
        assertThat(nodeA.getNumberOfShortestPathFromSource(), is(2));
        assertThat(updatedNode.getPredecessors().getNodes(), hasSize(2));
    }

    @Test
    public void shouldUpdateThePairDependencyForAGivenNodeAndSuccessor() {
        Node<String> a = aNodeWithNumberOfShortestDistancPredecessorsAndPairDependency("a", 2.0, 4, nodes());
        Node<String> b = aNodeWithNumberOfShortestDistancPredecessorsAndPairDependency("b", 1.5, 2, nodes());
        assertThat(b.updatePairDependencies(a).intValue(),is(3));
        assertThat(b.getPairDependency().intValue(),is(3));
    }

    @Test
    public void shouldUpdateTheNodeDistanceForAllTheAdjacentNodes() throws Exception {
        Edges edges = mock(Edges.class);
        Node node = aNodeWithEdgesAndShortestDistanceFromSource(edges, 123.3);
        node.updateTheAdjacentNodeDistance();
        verify(edges).updateShortestDistant(node, 123.3);
    }

    @Test
    public void shouldMarkTheNodeVisited() {
        Node<String> unvisitedNode = NodeFactory.anUnvisitedNode("a");
        assertThat(unvisitedNode.visited().getIsVisited(), is(true));
    }


}
package csimilarity;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GraphTest {

    @Test
    public void shouldReturnAGraphAfterSparsification() throws Exception {
        Double sparsificationRatio = 0.3d;
        Integer numberOfEdgesToRetain = 1; //Both values are inter-linked

        DocumentNode aDocument = mock(DocumentNode.class);
        DocumentNode prunedADocument = mock(DocumentNode.class);
        when(aDocument.prune(numberOfEdgesToRetain)).thenReturn(prunedADocument);

        DocumentNode bDocument = mock(DocumentNode.class);
        DocumentNode prunedBDocument = mock(DocumentNode.class);
        when(bDocument.prune(numberOfEdgesToRetain)).thenReturn(prunedBDocument);

        List<DocumentNode> documentNodes = Arrays.asList(aDocument, bDocument);

        Graph graph = new Graph(documentNodes);
        Graph sparsedGraph = graph.sparse(sparsificationRatio);

        assertThat(sparsedGraph.documentNodes.size(), is(2));
        assertThat(sparsedGraph.documentNodes, Matchers.containsInAnyOrder(prunedADocument, prunedBDocument));
    }


}
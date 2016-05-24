package csimilarity;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class DocumentNodeTest {

    @Test
    public void shouldReturnTheNodeRetainingOnlyTheSpecifiedNumberOfEdgesWhichHaveHigherWeight() {
        Document aDocument = mock(Document.class);

        DocumentNode aDocumentEdge1 = mock(DocumentNode.class);
        DocumentNode aDocumentEdge2 = mock(DocumentNode.class);
        DocumentNode aDocumentEdge3 = mock(DocumentNode.class);

        DocumentNode documentNode = new DocumentNode(aDocument, asList(new DocumentNodeEdge(aDocumentEdge1, 1.4),
                new DocumentNodeEdge(aDocumentEdge2, 0.4), new DocumentNodeEdge(aDocumentEdge3, 5.4)));
        DocumentNode prunedDocumentNode = documentNode.prune(2);
        List<DocumentNodeEdge> prunedDocumentNodeEdges = prunedDocumentNode.getEdges();

        assertThat(prunedDocumentNodeEdges, hasSize(2));
        assertThat(prunedDocumentNodeEdges.stream().map(DocumentNodeEdge::getEdgeWeigth)
                .collect(Collectors.toList()), Matchers.containsInAnyOrder(1.4, 5.4));

    }

}
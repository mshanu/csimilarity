package csimilarity;

import org.hamcrest.Matchers;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class DocumentWeightsTest {

    @Test
    public void shouldReturnListOfDocumentPair() {
        DocumentWeight documentWeight1 = mock(DocumentWeight.class);
        DocumentWeight documentWeight2 = mock(DocumentWeight.class);
        DocumentWeight documentWeight3 = mock(DocumentWeight.class);
        DocumentWeight documentWeight4 = mock(DocumentWeight.class);
        DocumentWeights documentWeights = new DocumentWeights(asList(documentWeight1, documentWeight2, documentWeight3,
                documentWeight4));
        List<DocumentWeightPair> pairs = documentWeights.getPairs();
        assertThat(pairs, IsCollectionWithSize.hasSize(12));
        assertThat(pairs, Matchers.containsInAnyOrder(
                new DocumentWeightPair(documentWeight1,documentWeight2),
                new DocumentWeightPair(documentWeight1,documentWeight3),
                new DocumentWeightPair(documentWeight1,documentWeight4),
                new DocumentWeightPair(documentWeight2,documentWeight1),
                new DocumentWeightPair(documentWeight2,documentWeight3),
                new DocumentWeightPair(documentWeight2,documentWeight4),
                new DocumentWeightPair(documentWeight3,documentWeight1),
                new DocumentWeightPair(documentWeight3,documentWeight2),
                new DocumentWeightPair(documentWeight3,documentWeight4),
                new DocumentWeightPair(documentWeight4,documentWeight1),
                new DocumentWeightPair(documentWeight4,documentWeight2),
                new DocumentWeightPair(documentWeight4,documentWeight3)
                ));
    }
}
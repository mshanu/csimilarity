package csimilarity;

import org.junit.Test;

import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DocumentWeightPairTest {

    @Test
    public void shouldReturnTheWeightBetweenTwoDocumentWeightNodes() {
        DocumentWeight documentA = mock(DocumentWeight.class);
        DocumentWeight documentB = mock(DocumentWeight.class);
        DocumentWeightPair documentWeightPair = new DocumentWeightPair(documentA, documentB);
        when(documentA.words()).thenReturn(new HashSet<>(asList("this", "that")));
        when(documentB.words()).thenReturn(new HashSet<>(asList("this", "there")));

        when(documentA.getTfIf("this")).thenReturn(1.3);
        when(documentB.getTfIf("this")).thenReturn(1.5);

        when(documentA.getTfIf("that")).thenReturn(2.3);
        when(documentB.getTfIf("that")).thenReturn(0.0);

        when(documentA.getTfIf("there")).thenReturn(0.0);
        when(documentB.getTfIf("there")).thenReturn(3.3);

        assertThat(documentWeightPair.cosineSimilarity(), is(0.20361474157394124)); // 1.3*1.5 + 2.3*0.0 + 0.0*3.3 / sqrt(sum(1.3^2,2.3^2,0.0^2) * sqrt(sum(1.5^2 + 0.00^2 + 3.3^2)
    }

}

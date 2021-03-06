package csimilarity;

import bcentrality.factory.DocumentWeightFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DocumentWeightTest {

    @Test
    public void shouldPopulateTermFrequencyInverseTermFrequence() {
        Document document = mock(Document.class);
        Documents allDocuments = mock(Documents.class);
        when(document.words()).thenReturn(new HashSet<>(asList("this", "that")));
        when(allDocuments.inverseDocumentFrequency("this")).thenReturn(1.56);
        when(allDocuments.inverseDocumentFrequency("that")).thenReturn(2.56);
        when(document.getWordCount("this")).thenReturn(2);
        DocumentWeight documentWeight = new DocumentWeight(document, allDocuments);
        assertThat(documentWeight.getTfIf("this"), is(3.12));

    }

    @Test
    public void shouldReturnTheFingerPrintValueByMultiplyingOneDimensionalVectorWithTFIdVector() {
        HashMap<String, Double> wordTfIf = new HashMap<>();
        wordTfIf.put("hello", 234.00);
        wordTfIf.put("world", 123.00);
        DocumentWeight documentWeight = DocumentWeightFactory.aDocumentWeightWithTfIdfVector(wordTfIf);

    }

}
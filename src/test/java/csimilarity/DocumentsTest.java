package csimilarity;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DocumentsTest {


    @Test
    public void shouldReturnTheInverseDoucmentFrequency() {
        Document document1 = mock(Document.class);
        Document document2 = mock(Document.class);
        Document document3 = mock(Document.class);
        when(document1.contains("thing")).thenReturn(true);
        when(document2.contains("thing")).thenReturn(true);
        when(document3.contains("thing")).thenReturn(false);
        Documents documents = new Documents(asList(document1, document2, document3));
        assertThat(documents.inverseDocumentFrequency("thing"), is(0.17609125905568124)); //log(3/2)
    }
}
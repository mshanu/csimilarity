package lhs;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class HashVectorTest {

    @Test
    public void shouldReturnBackSameFingerPrintBitForDocumentWithSameWords() {
        HashVector hashVector = new HashVector();
        HashMap<String, Double> tfItfVectorForDocument1 = new HashMap<>();
        tfItfVectorForDocument1.put("hello", 123.23);
        tfItfVectorForDocument1.put("world", 347.3);

        HashMap<String, Double> tfItfVectorForDocument2 = new HashMap<>();
        tfItfVectorForDocument2.put("hello", 123.23);
        tfItfVectorForDocument2.put("world", 347.23);

        String fingerPrintForDoc2 = hashVector.multiply(tfItfVectorForDocument2);
        String fingerPrintForDoc1 = hashVector.multiply(tfItfVectorForDocument1);
        assertEquals(fingerPrintForDoc1, fingerPrintForDoc2);
    }

}
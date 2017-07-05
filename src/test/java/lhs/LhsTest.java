package lhs;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class LhsTest {

    @Test
    public void shouldReturnTheFingerPrint() {
        Lhs lhs = new Lhs(5);
        HashMap<String, Double> wordTfIdf1 = new HashMap<>();
        wordTfIdf1.put("Hello", 1234.03);
        wordTfIdf1.put("world", 4354.03);
        wordTfIdf1.put("tfif", 4354.03);

        HashMap<String, Double> wordTfIdf2 = new HashMap<>();
        wordTfIdf2.put("Hello", 1134.03);
        wordTfIdf2.put("world", 4354.03);
        wordTfIdf2.put("tfif", 4357.03);
        assertEquals(lhs.fingerPrint(wordTfIdf1), lhs.fingerPrint(wordTfIdf2));
    }

}
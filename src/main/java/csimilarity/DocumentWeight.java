package csimilarity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DocumentWeight {
    private
    Document document;
    private Map<String, Double> wordTfIf = new HashMap<>();

    public DocumentWeight(Document document, Documents documents) {
        this.document = document;
        Set<String> words = this.document.words();
        for (String word : words) {
            wordTfIf.put(word, documents.inverseDocumentFrequency(word) * document.getWordCount(word));
        }

    }

    public Double getTfIf(String word) {
        return wordTfIf.getOrDefault(word, 0.0);
    }

    public Set<String> words() {
        return wordTfIf.keySet();
    }

    public Document getDocument() {
        return document;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentWeight that = (DocumentWeight) o;

        return document.equals(that.document);

    }

    @Override
    public int hashCode() {
        return document.hashCode();
    }
}

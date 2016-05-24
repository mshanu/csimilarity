package csimilarity;

import java.util.List;

public class Documents {
    List<Document> documentList;

    public Documents(List<Document> documentList) {
        this.documentList = documentList;
    }

    
    public Double inverseDocumentFrequency(String word) {
        Integer documentCount = documentList.size();
        Long documentWhichHasTheWord = documentList.stream().filter(document -> document.contains(word)).count();
        return Math.log10(documentCount.doubleValue() / documentWhichHasTheWord.doubleValue());
    }
}

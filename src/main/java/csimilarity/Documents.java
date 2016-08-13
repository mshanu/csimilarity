package csimilarity;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Documents {
    @Getter
    private List<Document> documentList;

    public Documents(List<Document> documentList) {
        this.documentList = documentList;
    }

//    public Documents(List<Document> documentList) {
//        this.documentList = documentList;
//        documentList.stream().forEach(document -> {
//            Map<String, Long> wordCountMap = document.getWordCountMap();
//            wordCountMap.forEach((s, aLong) -> {
//                if (frequency.containsKey(s)) {
//                    frequency.put(s, frequency.get(s) + 1);
//                } else {
//                    frequency.put(s, 1l);
//                }
//            });
//        });
//    }

    public Double inverseDocumentFrequency(String word) {
        Integer documentCount = documentList.size();
        Long documentWhichHasTheWord = documentList.stream().filter(document -> document.contains(word)).count();
        return Math.log10(documentCount.doubleValue() / documentWhichHasTheWord.doubleValue());
    }
}

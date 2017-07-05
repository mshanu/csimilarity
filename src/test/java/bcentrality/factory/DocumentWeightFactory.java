package bcentrality.factory;

import csimilarity.DocumentWeight;

import java.util.Map;

public class DocumentWeightFactory {
    public static DocumentWeight aDocumentWeightWithTfIdfVector(Map<String, Double> wordTfIf) {
        return Builder.forClass(DocumentWeight.class).with("wordTfIf", wordTfIf).build();
    }
}

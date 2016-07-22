package csimilarity;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
public class DocumentWeights {
    private List<DocumentWeight> documentWeights;

    public List<DocumentWeightPair> getPairs() {
        return documentWeights.stream().flatMap(eachDocumentWeight -> createDocumentPairList(eachDocumentWeight, documentWeights).stream()).collect(toList());
    }

    private List<DocumentWeightPair> createDocumentPairList(DocumentWeight documentWeight, List<DocumentWeight> documentWeights) {
        return documentWeights.stream().filter(documentWeight1 -> !documentWeight1.equals(documentWeight))
                .map(documentWeight2 -> new DocumentWeightPair(documentWeight, documentWeight2)).collect(Collectors.toList());
    }
}

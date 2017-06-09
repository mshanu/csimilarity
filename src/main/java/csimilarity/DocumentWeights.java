package csimilarity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
public class DocumentWeights {
    @Getter
    private List<DocumentWeight> documentWeights;

    public List<DocumentWeightPair> getPairs() {
        return documentWeights.stream().parallel().flatMap(eachDocumentWeight -> createDocumentPairList(eachDocumentWeight, documentWeights).stream()).collect(toList());
    }

    private List<DocumentWeightPair> createDocumentPairList(DocumentWeight documentWeight, List<DocumentWeight> documentWeights) {
        return documentWeights.stream().filter(documentWeight1 -> !documentWeight1.equals(documentWeight))
                .map(documentWeight2 -> new DocumentWeightPair(documentWeight, documentWeight2)).collect(Collectors.toList());
    }
}

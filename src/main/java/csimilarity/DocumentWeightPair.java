package csimilarity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@EqualsAndHashCode
public class DocumentWeightPair {
    private DocumentWeight documentA;
    private DocumentWeight documentB;

    public DocumentWeightPair(DocumentWeight documentA, DocumentWeight documentB) {
        this.documentA = documentA;
        this.documentB = documentB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentWeightPair that = (DocumentWeightPair) o;

        if (documentA.equals(that.documentA) && documentB.equals(that.documentB)) return true;
        if (documentB.equals(that.documentA) && documentA.equals(that.documentB)) return true;
        return false;

    }

    @Override
    public int hashCode() {
        int result = documentA.hashCode();
        result = 31 * result + documentB.hashCode();
        return result;
    }

    public Double cosineSimilarity() {
        Set<String> allWords = new HashSet<>(documentA.words());
        allWords.addAll(documentB.words());
        List<Double> documentAWeightSquares = new ArrayList<>();
        List<Double> documentBWeightSquares = new ArrayList<>();
        List<Double> crossProduct = new ArrayList<>();
        allWords.stream().forEach(word -> {
            Double docAWeight = documentA.getTfIf(word);
            Double docBWeight = documentB.getTfIf(word);
            crossProduct.add(docAWeight * docBWeight);
            documentAWeightSquares.add(docAWeight * docAWeight);
            documentBWeightSquares.add(docBWeight * docBWeight);
        });
        Double crossProductSum = crossProduct.stream().reduce(Double::sum).get();
        Double documentAWeightSquareSum = documentAWeightSquares.stream().reduce(Double::sum).get();
        Double documentBWeightSquareSum = documentBWeightSquares.stream().reduce(Double::sum).get();
        return crossProductSum / (Math.sqrt(documentAWeightSquareSum) * Math.sqrt(documentBWeightSquareSum));
    }

    public static Double cosineSimilarity(DocumentWeight documentA, DocumentWeight documentB) {
        Set<String> allWords = new HashSet<>(documentA.words());
        allWords.addAll(documentB.words());
        List<Double> documentAWeightSquares = new ArrayList<>();
        List<Double> documentBWeightSquares = new ArrayList<>();
        List<Double> crossProduct = new ArrayList<>();
        allWords.stream().forEach(word -> {
            Double docAWeight = documentA.getTfIf(word);
            Double docBWeight = documentB.getTfIf(word);
            crossProduct.add(docAWeight * docBWeight);
            documentAWeightSquares.add(docAWeight * docAWeight);
            documentBWeightSquares.add(docBWeight * docBWeight);
        });
        Double crossProductSum = crossProduct.stream().reduce(Double::sum).get();
        Double documentAWeightSquareSum = documentAWeightSquares.stream().reduce(Double::sum).get();
        Double documentBWeightSquareSum = documentBWeightSquares.stream().reduce(Double::sum).get();
        return crossProductSum / (Math.sqrt(documentAWeightSquareSum) * Math.sqrt(documentBWeightSquareSum));
    }

    @Override
    public String toString() {
        return "From:" + documentA.getDocument().getName() + " To: " + documentB.getDocument().getName() + " cosine:" + cosineSimilarity();
    }

    public Document getFromDocument() {
        return documentA.getDocument();
    }

    public Document getToDocument() {
        return documentB.getDocument();
    }

}

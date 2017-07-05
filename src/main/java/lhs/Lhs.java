package lhs;

import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Lhs {
    private Integer fingerPrintSize;
    private List<HashVector> hashVectorList;


    public Lhs(Integer fingerPrintSize) {
        this.fingerPrintSize = fingerPrintSize;
        hashVectorList = IntStream.range(0, fingerPrintSize).mapToObj(e -> new HashVector()).collect(toList());
    }

    public String fingerPrint(HashMap<String, Double> wordTfIf) {
        return hashVectorList.stream().map(hashVector -> hashVector.multiply(wordTfIf)).reduce(String::concat).get();

    }
}

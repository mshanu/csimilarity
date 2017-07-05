package lhs;

import java.util.HashMap;
import java.util.Random;

public class HashVector {
    private HashMap<String, Integer> randomValues = new HashMap<>();
    private Random random = new Random();

    public Integer multiplicant(String word) {
        if (!randomValues.containsKey(word)) {
            randomValues.put(word, (random.nextInt(1000) - 500));
        }
        return randomValues.get(word);
    }

    public String multiply(HashMap<String, Double> tfItfVector) {
        Double sum = tfItfVector.entrySet().stream().map(entry -> entry.getValue() * multiplicant(entry.getKey())).reduce(Double::sum).get();
        return sum > 0 ? "1" : "0";
    }
}

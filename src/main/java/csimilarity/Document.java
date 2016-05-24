package csimilarity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Document {
    private String name;
    private Map<String, Integer> wordCountMap = new HashMap<>();

    public Document(String name, String text) {
        this.name = name;
        String[] eachWord = text.split("\\s+");
        for (String word : eachWord) {
            String trimmedWord = word.replaceAll("[^\\w]", "").toLowerCase();
            Integer wordCount = this.wordCountMap.getOrDefault(trimmedWord, 0);
            wordCountMap.put(trimmedWord, ++wordCount);
        }
    }

    public Integer getWordCount(String word) {
        return wordCountMap.getOrDefault(word,0);
    }

    public Set<String> words() {
        return wordCountMap.keySet();
    }



    public Boolean contains(String word) {
        return wordCountMap.containsKey(word);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        return name.equals(document.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

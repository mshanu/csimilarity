package csimilarity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.CharArraySet;
import org.tartarus.snowball.ext.PorterStemmer;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode(exclude = "wordCountMap")
@AllArgsConstructor
public class Document {
    private String name;
    private Map<String, Long> wordCountMap = new HashMap<>();

    public Integer getWordCount(String word) {
        return wordCountMap.getOrDefault(word, 0l).intValue();
    }

    public Set<String> words() {
        return wordCountMap.keySet();
    }


    public Boolean contains(String word) {
        return wordCountMap.containsKey(word);
    }

    public static Document createFrom(Path path) {
        try {
            List<String> words = extractWords(path);
            Map<String, Long> collect = words.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            return new Document(path.toString(), collect);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> extractWords(Path path) throws IOException {
        List<String> words = new LinkedList<String>();
//Read the File and store the content in a single String
        String fileContent = new
                String(Files.readAllBytes(path),
                StandardCharsets.UTF_8);
//Tokenize The content
        Tokenizer tokenizer = new StandardTokenizer();
        tokenizer.setReader(new StringReader(fileContent.toLowerCase()));
        StandardFilter standardFilter = new StandardFilter(tokenizer);
        CharArraySet stopSet = CharArraySet.copy(StopAnalyzer.ENGLISH_STOP_WORDS_SET);
        StopFilter stopFilter = new StopFilter(standardFilter,
                stopSet);
        CharTermAttribute charTermAttribute
                = tokenizer.addAttribute(CharTermAttribute.class);
        stopFilter.reset();
        PorterStemmer stemmer = new PorterStemmer();
        while (stopFilter.incrementToken()) {
            String token = charTermAttribute.toString();
            stemmer.setCurrent(token);
            stemmer.stem();
            words.add(stemmer.getCurrent());
        }
        return words;
    }

    @Override
    public String toString() {
        return name.replace("/Users/shanu/Downloads/TestDocuments/", "");
    }
}

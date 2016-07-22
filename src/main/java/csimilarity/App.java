package csimilarity;

import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class App {
    public static void main(String[] args) {

        try (final Stream<Path> pathStream = Files.walk(Paths.get("/Users/shanu/Downloads/TestDocuments"), FileVisitOption.FOLLOW_LINKS)) {
            Documents documents = pathStream.filter(path -> !path.toFile().isDirectory() &&
                    path.toFile().getAbsolutePath().endsWith("txt")).map(Document::createFrom)
                    .collect(collectingAndThen(toList(), Documents::new));
            DocumentWeights documentWeights = documents.getDocumentList().stream()
                    .map(document -> new DocumentWeight(document, documents)).collect(collectingAndThen(toList(), DocumentWeights::new));
            List<DocumentWeightPair> pairs = documentWeights.getPairs();
            pairs.forEach(System.out::println);

        } catch (Exception e) {

        }

    }
}

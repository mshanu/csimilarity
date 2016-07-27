package csimilarity;

import bcentrality.Graph;
import bcentrality.GraphBuilder;

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
        Documents documents = getDocuments();
        DocumentWeights documentWeights = documents.getDocumentList().stream()
                .map(document -> new DocumentWeight(document, documents)).collect(collectingAndThen(toList(), DocumentWeights::new));
        List<DocumentWeightPair> pairs = documentWeights.getPairs();
        GraphBuilder<Document> documentGraphBuilder = new GraphBuilder<>();
        pairs.stream().forEach(pair -> documentGraphBuilder.addEdge(pair.getFromDocument(), pair.getToDocument(), 1-pair.cosineSimilarity()));
        Graph graph = documentGraphBuilder.build();
        Graph sparsedGraph = graph.sparse(0.1);
        sparsedGraph.calculateCentralityValue();

        System.out.println(sparsedGraph.getNodes().stream().filter(node -> node.getCentralityValue() != 1.0).count());
    }

    private static Documents getDocuments() {
        try (final Stream<Path> pathStream = Files.walk(Paths.get("/Users/shanu/Downloads/TestDocuments"), FileVisitOption.FOLLOW_LINKS)) {
            return pathStream.filter(path -> !path.toFile().isDirectory() &&
                    path.toFile().getAbsolutePath().endsWith("txt")).map(Document::createFrom)
                    .collect(collectingAndThen(toList(), Documents::new));


        } catch (Exception e) {

        }
        return null;
    }
}

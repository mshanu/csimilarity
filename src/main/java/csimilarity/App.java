package csimilarity;

import bcentrality.CentralityGraph;
import bcentrality.Graph;
import bcentrality.GraphBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class App {

    public static void main(String[] args) throws IOException {
        Documents documents = getDocuments();
        System.out.println("Document size " + documents.getDocumentList().size());
        DocumentWeights documentWeights = documents.getDocumentList().stream().parallel()
                .map(document -> new DocumentWeight(document, documents)).collect(collectingAndThen(toList(), DocumentWeights::new));
        System.out.println("Creating pairs");
        List<DocumentWeightPair> pairs = documentWeights.getPairs();
        System.out.println("Finished pairs");

        GraphBuilder<Document> documentGraphBuilder = new GraphBuilder<>(new HashMap<>());
        pairs.stream().forEach(pair -> documentGraphBuilder.addEdge(pair.getFromDocument(), pair.getToDocument(), 1 - pair.cosineSimilarity()));
        Graph graph = documentGraphBuilder.build();
        System.out.println("Build complete, Doing sparse");
        Graph sparsedGraph = graph.sparse(0.5);

        System.out.println("Calculating centraliry");
        CentralityGraph centralityGraph = sparsedGraph.calculateCentralityValue();
        List<Graph> graphs = centralityGraph.removeEdgesWithHigherCentrality().getClusters();
        final Integer[] i = {0};
        graphs.forEach(eg -> {
            try {
                BufferedWriter writer = Files.newBufferedWriter(Paths.get("/Users/shanu/Projects/csimilarity/output.dot" + (++i[0])));
                graph.toString(writer);
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException();
            }
        });


    }


    private static Documents getDocuments() {
        try (final Stream<Path> pathStream = Files.walk(Paths.get("/Users/shanu/Downloads/Input"), FileVisitOption.FOLLOW_LINKS)) {
            return pathStream.filter(path -> !path.toFile().isDirectory() && path.toAbsolutePath().toString().length() % 7 == 0 &&
                    path.toFile().getAbsolutePath().endsWith("txt")).map(Document::createFrom).filter(Document::isNotEmpty)
                    .collect(collectingAndThen(toList(), Documents::new));

        } catch (Exception e) {

        }
        return null;
    }

    public static void showUsedMemory() {
        System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
    }
}

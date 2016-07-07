package csimilarity;

import java.util.List;

import static java.util.stream.Collectors.toList;


public class DocumentNode {
    private Document document;
    private List<DocumentNodeEdge> edges;

    public DocumentNode(Document document, List<DocumentNodeEdge> edges) {
        this.document = document;
        this.edges = edges;
    }

    public DocumentNode prune(Integer numberOfEdgesToRetain) {
        List<DocumentNodeEdge> sortedEdges = edges.stream().sorted((o1, o2) -> o2.getEdgeWeight().compareTo(o1.getEdgeWeight())).collect(toList());
        return new DocumentNode(document, sortedEdges.subList(0, numberOfEdgesToRetain));
    }

    public List<DocumentNodeEdge> getEdges() {
        return edges;
    }


}

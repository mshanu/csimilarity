package csimilarity;

public class DocumentNodeEdge {
    private DocumentNode to;
    private Double edgeWeight;
    private Double distance;

    public DocumentNodeEdge(DocumentNode to, Double edgeWeight) {
        this.to = to;
        this.edgeWeight = edgeWeight;
        this.distance = 1 - edgeWeight;
    }

    public Double getEdgeWeight() {
        return edgeWeight;
    }

    public DocumentNode getTo() {
        return to;
    }


}

package csimilarity;

public class DocumentNodeEdge {
    private DocumentNode to;
    private Double edgeWeigth;

    public DocumentNodeEdge(DocumentNode to, Double edgeWeigth) {
        this.to = to;
        this.edgeWeigth = edgeWeigth;
    }

    public Double getEdgeWeigth() {
        return edgeWeigth;
    }
}

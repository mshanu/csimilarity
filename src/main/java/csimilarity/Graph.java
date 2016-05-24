package csimilarity;

import java.util.List;
import java.util.stream.Collectors;

public class Graph {
    List<DocumentNode> documentNodes;

    public Graph(List<DocumentNode> documentNodes) {
        this.documentNodes = documentNodes;
    }

    public Graph sparse(Double value) {
        Integer graphSize = documentNodes.size();
        Integer numberOfEdgesToRetain = new Double(Math.pow(graphSize, value)).intValue();
        return new Graph(documentNodes.stream().map(documentNode -> documentNode.prune(numberOfEdgesToRetain)).collect(Collectors.toList()));
    }




}

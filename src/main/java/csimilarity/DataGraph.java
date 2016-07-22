package csimilarity;

import java.util.List;
import java.util.stream.Collectors;

public class DataGraph {
    List<DocumentNode> documentNodes;

    public DataGraph(List<DocumentNode> documentNodes) {
        this.documentNodes = documentNodes;
    }

    public DataGraph sparse(Double value) {
        Integer graphSize = documentNodes.size();
        Integer numberOfEdgesToRetain = new Double(Math.pow(graphSize, value)).intValue();
        return new DataGraph(documentNodes.stream().map(documentNode -> documentNode.prune(numberOfEdgesToRetain)).collect(Collectors.toList()));
    }



}

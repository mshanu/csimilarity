package bcentrality;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@AllArgsConstructor
public class CentralityGraph {
    private Graph graph;

    public Double standardDeviationOfCentralities() {
        Double meanCentrality = mean();
        Set<Node> nodes = graph.getNodes();
        Double sumOfSquares = nodes.stream().collect(Collectors.summingDouble(node -> Math.pow(node.getCentralityValue() - meanCentrality, 2)));
        return Math.sqrt(sumOfSquares / nodes.size());
    }

    public Double mean() {
        return graph.getNodes().stream().collect(Collectors.averagingDouble(Node::getCentralityValue));
    }


    public CentralityGraph removeEdgesWithHigherCentrality() {
        Set<Node> nodes = graph.getNodes();
        double meanPlusStandardDeviation = mean() + standardDeviationOfCentralities();
        List<Node> nodesWithHigherCentralityValues = nodes.stream().filter(node -> node.getCentralityValue() > meanPlusStandardDeviation).collect(toList());
        Set<Node> trimmedNodes = nodesWithHigherCentralityValues.stream().map(node -> node.removeEdgesWithHighCentralityValue(meanPlusStandardDeviation)).collect(toSet());
        return new CentralityGraph(new Graph(trimmedNodes));
    }

    public List<Graph> getClusters() {
        return graph.clusters();
    }

    public Node getNode(Node node) {
        return new Nodes(graph.getNodes()).getNode(node);
    }
}

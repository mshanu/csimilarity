package bcentrality;

import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
public class CentralityGraph {
    private HashSet<Node> nodes;

    public Double standardDeviationOfCentralities() {
        Double meanCentrality = mean();
        Double sumOfSquares = nodes.stream().collect(Collectors.summingDouble(node -> Math.pow(node.getCentralityValue() - meanCentrality, 2)));
        return Math.sqrt(sumOfSquares / nodes.size());
    }

    public Double mean() {
        return nodes.stream().collect(Collectors.averagingDouble(Node::getCentralityValue));
    }


    public CentralityGraph removeEdgesWithHigherCentrality() {
        double meanPlusStandardDeviation = mean() + standardDeviationOfCentralities();
        List<Node> nodesWithHigherCentralityValues = nodes.stream().filter(node -> node.getCentralityValue() > meanPlusStandardDeviation).collect(toList());
        nodesWithHigherCentralityValues.stream().forEach(node -> node.removeEdgesWithHighCentralityValue(meanPlusStandardDeviation));
        return new CentralityGraph(nodes);
    }

    public Node getNode(Node node) {
        return nodes.stream().filter(node1 -> node1.equals(node)).findAny().get();
    }
}

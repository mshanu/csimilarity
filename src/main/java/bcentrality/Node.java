package bcentrality;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashSet;

@EqualsAndHashCode(of = "dataNode")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Node<T> {
    private T dataNode;
    private Double shortestDistanceFromSource;
    private Integer numberOfShortestPathFromSource = 1;
    private Nodes predecessors = new Nodes(new HashSet<>());
    private Edges edges = new Edges(new ArrayList<>());
    private Double centralityValue = 0.0;
    private Double pairDependency = 0.0;

    public Node(T dataNode) {
        this.dataNode = dataNode;
        this.shortestDistanceFromSource = Double.MAX_VALUE;
    }

    public void initShortDistanceCalculation() {
        this.predecessors = new Nodes();
        this.shortestDistanceFromSource = Double.MAX_VALUE;
        this.pairDependency = 0.0;
    }

    public void createEdge(Node node, Double weight) {
        edges.create(node, weight);
    }

    public void updateTheAdjacentNodeDistance() {
        edges.updateShortestDistant(this, shortestDistanceFromSource);
    }

    public Node updateDistance(Node predecessorNode, Double distanceFromSource) {
        if (new BigDecimal(distanceFromSource, MathContext.DECIMAL32).equals(new BigDecimal(shortestDistanceFromSource, MathContext.DECIMAL32))) {
            predecessors.add(predecessorNode);
            numberOfShortestPathFromSource += predecessorNode.getNumberOfShortestPathFromSource();
        }
        if (distanceFromSource < shortestDistanceFromSource) {
            this.shortestDistanceFromSource = distanceFromSource;
            predecessors = new Nodes();
            predecessors.add(predecessorNode);
            numberOfShortestPathFromSource = predecessorNode.getNumberOfShortestPathFromSource();
        }
        return this;
    }

    public Node prune(Integer numberOfEdgesToRetain) {
        edges = edges.prune(numberOfEdgesToRetain);
        return this;
    }


    //Optimize here
    public void updatePairDependencies() {
        this.predecessors.getNodes().stream().forEach(node -> node.updatePairDependencies(this));
    }

    public void addCentrality() {
        this.centralityValue += this.pairDependency;
    }

    public void makeStartingNode() {
        this.shortestDistanceFromSource = 0.0;
        this.numberOfShortestPathFromSource = 1;
    }


    @Override
    public String toString() {
        if (edges.isEmpty()) {
            return "";
        }
        String template = "\"%s\" -> \"%s\" [label =\"%s\"];\n";
        return edges.getEdges().stream().map(edge -> String.format(template, getNameAndCentrality(), edge.getToNode().getNameAndCentrality(), edge.getWeight())).reduce(String::concat).get();

    }

    String getNameAndCentrality() {
        return dataNode.toString() + " " + centralityValue;
    }


    public Node<T> removeEdgesWithHighCentralityValue(Double meanPlusStandardDeviation) {
        return new Node<>(dataNode, shortestDistanceFromSource, 1, predecessors, edges.pruneWithCentralityValue(meanPlusStandardDeviation), centralityValue, 1.5);
    }

    public Boolean hasHigherCentralityValue(Double centralityValue) {
        return this.centralityValue > centralityValue;
    }


    public Double updatePairDependencies(Node<T> successorNode) {
        this.pairDependency = this.pairDependency + ((numberOfShortestPathFromSource.doubleValue() / successorNode.getNumberOfShortestPathFromSource().doubleValue())
                * (1 + successorNode.getPairDependency()));
        return this.pairDependency;
    }

    public boolean hasEdge(Node to) {
        return edges.hasNode(to);
    }
}

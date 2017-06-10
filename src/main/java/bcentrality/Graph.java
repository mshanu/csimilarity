package bcentrality;

import lombok.Getter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class Graph {
    @Getter
    private List<Node> nodes;


    public Graph(List<Node> nodes) {
        this.nodes = nodes;
    }

    public ArrayDeque<Node> getShortestDistanceForAllNodesFrom(Node node) {
        this.nodes.stream().forEach(Node::initShortDistanceCalculation);
        node.makeStartingNode();
        return dijstraksTraversal(new ArrayList<>(this.nodes), new ArrayDeque<>());
    }

    public Graph calculateCentralityValue() {
        nodes.stream().forEach(sourceNode -> {
            ArrayDeque<Node> nodesWithShortestPaths = getShortestDistanceForAllNodesFrom(sourceNode);
            nodesWithShortestPaths.forEach(node1 -> {
                node1.updatePairDependencies();
                if (!node1.equals(sourceNode)) {
                    node1.addCentrality();
                }
            });
        });
        return new Graph(nodes);
    }

    /*
    Learned: Previous implementation was of keeping another collection of nodes which are covered
    Removing the nodes from the list on each iteration and.
    nodes.stream().min(Comparator.comparing(Node::getShortestDistanceFromSource)).get()
    going with priority que next.
     */
    private ArrayDeque<Node> dijstraksTraversal(List<Node> nodes, ArrayDeque<Node> shortestDistanceCalculatedNodes) {
        if (nodes.isEmpty()) {
            return shortestDistanceCalculatedNodes;
        }
        Node leastDistantNode = nodes.stream().min(Comparator.comparing(Node::getShortestDistanceFromSource)).get();
        nodes.remove(leastDistantNode);
        leastDistantNode.updateTheAdjacentNodeDistance();
        shortestDistanceCalculatedNodes.push(leastDistantNode);
        return dijstraksTraversal(nodes, shortestDistanceCalculatedNodes);
    }

    public Graph sparse(Double value) {
        Integer graphSize = nodes.size();
        Integer numberOfEdgesToRetain = new Double(Math.pow(graphSize, value)).intValue();
        return new Graph(nodes.stream().map(node -> node.prune(numberOfEdgesToRetain)).collect(toList()));
    }


    @Override
    public String toString() {
        return this.nodes.stream().map(Node::toString).reduce(String::concat).get();
    }

    public void toString(BufferedWriter writer) throws IOException {
        writer.write("digraph myGrpah { \n node [fontsize=8,nodesep=4.0,fontcolor=blue]; \n");
        this.nodes.forEach(node -> {
            try {
                node.toString(writer);
            } catch (IOException e) {
                throw new RuntimeException();
            }
        });
        writer.write("}");
    }

    public List<Graph> clusters() {
        return createCluster(new ArrayList<>(), nodes);
    }

    List<Graph> createCluster(List<Graph> graphs, List<Node> nodes) {
        Optional<Node> hasAnyNodeYetToBeVisited = nodes.stream().filter(node -> !node.getIsVisited()).findFirst();
        if (hasAnyNodeYetToBeVisited.isPresent()) {
            Queue<Node> queue = new ArrayDeque<>();
            queue.add(hasAnyNodeYetToBeVisited.get().visited());
            bfs(queue);
            List<Node> visitedNodes = nodes.stream().filter(Node::getIsVisited).collect(toList());
            graphs.add(new Graph(visitedNodes));
            return createCluster(graphs, nodes.stream().filter(node -> !node.getIsVisited()).collect(toList()));
        }
        return graphs;
    }

    public void bfs(Queue<Node> queue) {
        if (!queue.isEmpty()) {
            Node visitedNode = queue.remove();
            Set<Node> edgeNodes = visitedNode.getEdgeNodes();
            edgeNodes.stream().map(Node::visited).forEach(queue::add);
            bfs(queue);
        }
    }


}

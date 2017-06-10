package bcentrality;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

@AllArgsConstructor
@Getter
public class Edge {
    private Node toNode;
    private Double weight;

    public Integer compareTheDistance(Edge edge) {
        return weight.compareTo(edge.weight);
    }

    public void updateShortestDistance(Node parent, double distanceFromSource) {
        toNode.updateDistance(parent, distanceFromSource + weight);
    }

    public Boolean hasHigherCentralityValue(Double centralityValue) {
        return toNode.hasHigherCentralityValue(centralityValue);
    }

    public String getFormatattedWeight() {
        return BigDecimal.valueOf(weight).setScale(4, RoundingMode.HALF_UP).toString();
    }
}

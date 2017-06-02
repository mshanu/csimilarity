package bcentrality;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class CentralityGraphTest {
//    @Test
//    public void shouldReturnTheStandardDeviationOfCentralityGraph() {
//        CentralityGraph centralityGraph = new CentralityGraph(new HashSet<>(asList(new Node<>(1, null, null, null, 2.3,false),
//                new Node<>(1, null, null, null, 4.8,false),
//                new Node<>(1, null, null, null, 5.6,false)
//        )));
//        // 2.3 + 4.8 + 5.8 (mean = 4.3)
//        // std = sqrt( sqr(4.3 - 2.3){4} + sqr(4.8 - 4.3) {0.25} + sqr(5.8 - 4.3) {2.25} / 3)
//        assertThat(centralityGraph.standardDeviationOfCentralities(), is(1.4055445761538676));
//    }

//    @Test
//    public void shouldRemoveTheEdgeWithCentralityMoreThanMeanAndStandardDeviation() {
//        Node<String> nodea = new Node<>("a",null,null,new Edges(new ArrayList<>()),25.6,false);
//        Node<String> nodeb = new Node<>("b",null,null,new Edges(new ArrayList<>()),35.6,false);
//        Node<String> nodec = new Node<>("c",null,null,new Edges(new ArrayList<>()),4.6,false);
//        Node<String> noded = new Node<>("d",null,null,new Edges(new ArrayList<>()),0.6,false);
//        nodea.createEdge(nodeb,1.0);
//        nodea.createEdge(nodec,1.0);
//        nodeb.createEdge(noded,1.0);
//        nodec.createEdge(noded,1.0);
//        CentralityGraph centralityGraph = spy(new CentralityGraph(new HashSet<>(asList(nodea, nodeb, nodec, noded))));
//
//        doReturn(20.0).when(centralityGraph).mean();
//        doReturn(5.0).when(centralityGraph).standardDeviationOfCentralities();
//
//        assertThat(centralityGraph.getNode(nodea).getEdges().size(),is(1));
//
//        CentralityGraph proonedGraph = centralityGraph.removeEdgesWithHigherCentrality();
//        assertThat(proonedGraph.getNode(nodea).getEdges().size(),is(1));
//
//
//    }
}
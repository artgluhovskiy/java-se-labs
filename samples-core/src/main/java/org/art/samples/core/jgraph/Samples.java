package org.art.samples.core.jgraph;

import lombok.extern.log4j.Log4j2;
import org.jgrapht.DirectedGraph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;
import org.junit.jupiter.api.Test;

@Log4j2
public class Samples {

    @Test
    void test1() {
        DirectedGraph<String, DefaultEdge> directedGraph
            = new DefaultDirectedGraph<>(DefaultEdge.class);
        directedGraph.addVertex("v1");
        directedGraph.addVertex("v2");
        directedGraph.addVertex("v3");
        directedGraph.addEdge("v1", "v2");

        DepthFirstIterator depthFirstIterator
            = new DepthFirstIterator<>(directedGraph);

        while (depthFirstIterator.hasNext()) {
            log.info("Vertex: {}", depthFirstIterator.next());
        }

        Graphs.successorListOf(directedGraph, "v1")
            .forEach(successor -> {
                log.info("Successor: {}", successor);
            });
    }
}

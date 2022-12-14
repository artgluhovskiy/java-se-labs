package org.art.samples.core.algorithms.cci.graphs;

import java.util.List;

import org.art.samples.core.algorithms.utils.graphs.trees.TreeNode;
import org.junit.jupiter.api.Test;

import static org.art.samples.core.algorithms.utils.graphs.trees.TreeUtils.bfsSearch;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * "Route Between Nodes (Tree variant)" solution from "Cracking the Coding Interview".
 */
public class RouteBetweenNodes {

    public boolean hasRouteBetweenNodes(TreeNode<String> from, TreeNode<String> to) {
        bfsSearch(from, to);
        if (to.isVisited()) {
            return true;
        }
        return false;
    }

    @Test
    void test0() {
        TreeNode<String> subGraph = new TreeNode<>("d", List.of(new TreeNode<>("f", null)));
        TreeNode<String> graph = new TreeNode<>("a",
            List.of(
                new TreeNode<>("b", null),
                new TreeNode<>("c", List.of(subGraph))
            )
        );
        assertTrue(hasRouteBetweenNodes(graph, subGraph));
    }

    @Test
    void test1() {
        TreeNode<String> graph1 = new TreeNode<>("a",
            List.of(
                new TreeNode<>("b", null),
                new TreeNode<>("c", null)
            )
        );
        TreeNode<String> graph2 = new TreeNode<>("d",
            List.of(
                new TreeNode<>("e", null),
                new TreeNode<>("f", null)
            )
        );
        assertFalse(hasRouteBetweenNodes(graph1, graph2));
    }
}

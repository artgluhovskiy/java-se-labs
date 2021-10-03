package org.art.java_core.algorithms.hackerrank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * "Breadth First Search: Shortest Reach" quiz solution from HackerRank.
 */
public class BreadthFirstSearchShortestReach {

    public static List<Integer> bfs(int n, int m, List<List<Integer>> edges, int s) {

        Map<Integer, List<Integer>> neighbours = new HashMap<>(n);
        Map<Integer, Integer> distances = new HashMap<>(n);

        for (int i = 1; i <= n; i++) {
            neighbours.put(i, new ArrayList<>());
            distances.put(i, 0);
        }

        for (List<Integer> edge : edges) {
            Integer v1 = edge.get(0);
            Integer v2 = edge.get(1);
            neighbours.get(v1).add(v2);
            neighbours.get(v2).add(v1);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);

        Set<Integer> visited = new HashSet<>(n);
        visited.add(s);

        while (!queue.isEmpty()) {
            Integer currentNode = queue.poll();

            List<Integer> currentNodeNeighbours = neighbours.get(currentNode);
            for (Integer neighbourNode : currentNodeNeighbours) {
                if (!visited.contains(neighbourNode)) {
                    distances.put(neighbourNode, distances.get(currentNode) + 6);
                    visited.add(neighbourNode);
                    queue.add(neighbourNode);
                }
            }
        }

        List<Integer> result = new ArrayList<>(n);
        for (int l = 1; l <= n; l++) {
            if (l != s) {
                Integer distance = distances.get(l);
                if (distance == 0) {
                    result.add(-1);
                } else {
                    result.add(distance);
                }
            }
        }
        return result;
    }

    @Test
    void test0() {
        int numOfNodes = 5;
        int numOfEdges = 3;
        int startNode = 1;
        List<List<Integer>> edges = List.of(
                List.of(1, 2), List.of(1, 3), List.of(3, 4)
        );
        List<Integer> actual = bfs(numOfNodes, numOfEdges, edges, startNode);
        List<Integer> expected = List.of(6, 6, 12, -1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test1() {
        int numOfNodes = 5;
        int numOfEdges = 3;
        int startNode = 3;
        List<List<Integer>> edges = List.of(
                List.of(1, 2), List.of(1, 3), List.of(3, 4)
        );
        List<Integer> actual = bfs(numOfNodes, numOfEdges, edges, startNode);
        List<Integer> expected = List.of(6, 12, 6, -1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test2() {
        int numOfNodes = 5;
        int numOfEdges = 3;
        int startNode = 4;
        List<List<Integer>> edges = List.of(
                List.of(1, 2), List.of(1, 3), List.of(3, 4)
        );
        List<Integer> actual = bfs(numOfNodes, numOfEdges, edges, startNode);
        List<Integer> expected = List.of(12, 18, 6, -1);
        Assertions.assertEquals(expected, actual);
    }
}

package org.art.java_core.algorithms.cci.graphs.trees;

import java.util.HashMap;
import java.util.Map;

import org.art.java_core.algorithms.common.graphs.trees.BinaryTreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * "Paths with Sum" solution from "Cracking the Coding Interview".
 * BFS traversing is used.
 */
public class PathsWithSum {

    public int pathsWithSum(BinaryTreeNode<Integer> root, int targetSum) {
        return calculatePathsWithSum(root, targetSum, 0, new HashMap<>());
    }

    private int calculatePathsWithSum(BinaryTreeNode<Integer> root, int targetSum, int runningSum, Map<Integer, Integer> pathCounter) {
        if (root == null) {
            return 0;
        }

        runningSum = runningSum + root.getElem();
        int remainingSum = runningSum - targetSum;

        int pathCount = pathCounter.getOrDefault(remainingSum, 0);

        if (runningSum == targetSum) {
            pathCount++;
        }

        incrementPathCounter(pathCounter, runningSum, 1);
        pathCount += calculatePathsWithSum(root.getRight(), targetSum, runningSum, pathCounter);
        pathCount += calculatePathsWithSum(root.getLeft(), targetSum, runningSum, pathCounter);
        incrementPathCounter(pathCounter, runningSum, -1);

        return pathCount;
    }

    private void incrementPathCounter(Map<Integer, Integer> pathCounter, int runningSum, int delta) {
        int pathCount = pathCounter.getOrDefault(runningSum, 0) + delta;
        if (pathCount == 0) {
            pathCounter.remove(runningSum);
        } else {
            pathCounter.put(runningSum, pathCount);
        }
    }


    @Test
    void test0() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(7,
            new BinaryTreeNode<>(3,
                new BinaryTreeNode<>(1, null, null),
                new BinaryTreeNode<>(5, null, null)
            ),
            new BinaryTreeNode<>(10,
                new BinaryTreeNode<>(9, new BinaryTreeNode<>(8, null, null), null),
                new BinaryTreeNode<>(15, new BinaryTreeNode<>(2, null, null), null)
            )
        );
        int targetSum = 17;
        int expected = 3;
        assertEquals(expected, pathsWithSum(root, targetSum));
    }
}

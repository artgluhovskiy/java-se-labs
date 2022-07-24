package org.art.java_core.algorithms.cci.graphs.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.art.java_core.algorithms.common.trees.BinaryTreeNode;
import org.art.java_core.algorithms.common.trees.TreeNodePrinter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * "List of Depths" solution from "Cracking the Coding Interview".
 */
public class ListsOfDepths {

    public List<List<Integer>> buildListsOfDepths(BinaryTreeNode<Integer> root) {
        Objects.requireNonNull(root);
        List<List<Integer>> depths = new ArrayList<>();
        populateListOfDepths(root, depths, 0);
        return depths;
    }

    private void populateListOfDepths(BinaryTreeNode<Integer> root, List<List<Integer>> depths, int depth) {
        if (root == null) {
            return;
        }
        addToListOfDepths(depth, root.getElem(), depths);
        populateListOfDepths(root.getLeft(), depths, depth + 1);
        populateListOfDepths(root.getRight(), depths, depth + 1);
    }

    private void addToListOfDepths(int depth, Integer val, List<List<Integer>> depths) {
        if (depth < depths.size()) {
            depths.get(depth).add(val);
        } else {
            List<Integer> newDepthList = new ArrayList<>();
            newDepthList.add(val);
            depths.add(newDepthList);
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
                new BinaryTreeNode<>(15, null, null)
            )
        );
        System.out.println(TreeNodePrinter.printTree(root));
        List<List<Integer>> expectedResult = List.of(List.of(7), List.of(3, 10), List.of(1, 5, 9, 15), List.of(8));
        List<List<Integer>> result = buildListsOfDepths(root);
        assertEquals(expectedResult, result);
    }
}

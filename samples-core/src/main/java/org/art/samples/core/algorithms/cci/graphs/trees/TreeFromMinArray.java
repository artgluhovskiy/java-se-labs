package org.art.samples.core.algorithms.cci.graphs.trees;

import org.art.samples.core.algorithms.utils.graphs.trees.BinaryTreeNode;
import org.art.samples.core.algorithms.utils.graphs.trees.TreeNodePrinter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * "Tree From Sorted Array" solution from "Cracking the Coding Interview".
 */
public class TreeFromMinArray {

    public BinaryTreeNode<Integer> buildTree(int[] array) {
        return buildTreeHelper(array, 0, array.length - 1);
    }

    private BinaryTreeNode<Integer> buildTreeHelper(int[] array, int start, int end) {
        if (end < start) {
            return null;
        }
        int mid = start + (end - start) / 2;
        BinaryTreeNode<Integer> leftTree = buildTreeHelper(array, start, mid - 1);
        BinaryTreeNode<Integer> rightTree = buildTreeHelper(array, mid + 1, end);
        return new BinaryTreeNode<>(array[mid], leftTree, rightTree);
    }

    @Test
    void test0() {
        int[] input = {1, 3, 5, 7, 9, 10, 15};
        BinaryTreeNode<Integer> expectedTree = new BinaryTreeNode<>(7,
            new BinaryTreeNode<>(3,
                new BinaryTreeNode<>(1, null, null),
                new BinaryTreeNode<>(5, null, null)
            ),
            new BinaryTreeNode<>(10,
                new BinaryTreeNode<>(9, null, null),
                new BinaryTreeNode<>(15, null, null)
            )
        );
        BinaryTreeNode<Integer> resultTree = buildTree(input);
        System.out.println(TreeNodePrinter.printTree(expectedTree));
        System.out.println(TreeNodePrinter.printTree(resultTree));
        assertEquals(expectedTree, resultTree);
    }

    @Test
    void test1() {
        int[] input = {1};
        BinaryTreeNode<Integer> expectedTree = new BinaryTreeNode<>(1, null, null);
        BinaryTreeNode<Integer> resultTree = buildTree(input);
        assertEquals(expectedTree, resultTree);
    }

    @Test
    void test2() {
        int[] input = {1, 3, 5};
        BinaryTreeNode<Integer> expectedTree = new BinaryTreeNode<>(3,
            new BinaryTreeNode<>(1, null, null),
            new BinaryTreeNode<>(5, null, null)
        );
        BinaryTreeNode<Integer> resultTree = buildTree(input);
        System.out.println(TreeNodePrinter.printTree(expectedTree));
        System.out.println(TreeNodePrinter.printTree(resultTree));
        assertEquals(expectedTree, resultTree);
    }
}

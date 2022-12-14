package main.java.org.art.samples.core.algorithms.cci.graphs.trees;

import java.util.Objects;

import lombok.Getter;
import main.java.org.art.samples.core.algorithms.utils.graphs.trees.BinaryTreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * "Check Balanced" solution from "Cracking the Coding Interview".
 */
public class CheckBalanced {

    public boolean checkBalancedV1(BinaryTreeNode<Integer> root) {
        Objects.requireNonNull(root);
        DepthStatistics statistics = new DepthStatistics();
        return traverseAndCheckIfBalanced(root, 0, statistics);
    }

    private boolean traverseAndCheckIfBalanced(BinaryTreeNode<Integer> root, int depth, DepthStatistics statistics) {
        if (root == null) {
            statistics.updateStatistics(depth);
            return checkStatistics(statistics);
        }
        boolean isBalanced = traverseAndCheckIfBalanced(root.getLeft(), depth + 1, statistics);
        if (!isBalanced) {
            return false;
        } else {
            return traverseAndCheckIfBalanced(root.getRight(), depth + 1, statistics);
        }
    }

    private boolean checkStatistics(DepthStatistics statistics) {
        if (statistics.getMaxDepth() - statistics.getMinDepth() > 1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkBalancedV2(BinaryTreeNode<Integer> root) {
        Objects.requireNonNull(root);
        int height = checkHeight(root);
        return height != Integer.MIN_VALUE;
    }

    private int checkHeight(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return -1;
        }
        int leftHeight = checkHeight(root.getLeft());
        if (leftHeight == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        int rightHeight = checkHeight(root.getRight());
        if (rightHeight == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return Integer.MIN_VALUE;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    @Getter
    private static class DepthStatistics {

        private int minDepth = Integer.MAX_VALUE;

        private int maxDepth = Integer.MIN_VALUE;

        public void updateStatistics(int depth) {
            if (depth < this.minDepth) {
                this.minDepth = depth;
            }
            if (depth > this.maxDepth) {
                this.maxDepth = depth;
            }
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
                new BinaryTreeNode<>(9,
                    new BinaryTreeNode<>(8, null, null), null),
                new BinaryTreeNode<>(15, null, null)
            )
        );
        assertTrue(checkBalancedV1(root));
    }

    @Test
    void test1() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(7,
            new BinaryTreeNode<>(3,
                new BinaryTreeNode<>(1, null, null),
                new BinaryTreeNode<>(5, null, null)
            ),
            new BinaryTreeNode<>(10,
                new BinaryTreeNode<>(9,
                    new BinaryTreeNode<>(8,
                        new BinaryTreeNode<>(16, null, null), null), null),
                new BinaryTreeNode<>(15, null, null)
            )
        );
        assertFalse(checkBalancedV1(root));
    }

    @Test
    void test2() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(7,
            new BinaryTreeNode<>(3,
                new BinaryTreeNode<>(1, null, null),
                new BinaryTreeNode<>(5, null, null)
            ),
            new BinaryTreeNode<>(10,
                new BinaryTreeNode<>(9,
                    new BinaryTreeNode<>(8, null, null), null),
                new BinaryTreeNode<>(15, null, null)
            )
        );
        assertTrue(checkBalancedV2(root));
    }

    @Test
    void test3() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(7,
            new BinaryTreeNode<>(3,
                new BinaryTreeNode<>(1, null, null),
                new BinaryTreeNode<>(5, null, null)
            ),
            new BinaryTreeNode<>(10,
                new BinaryTreeNode<>(9,
                    new BinaryTreeNode<>(8,
                        new BinaryTreeNode<>(16, null, null), null), null),
                new BinaryTreeNode<>(15, null, null)
            )
        );
        assertFalse(checkBalancedV2(root));
    }
}

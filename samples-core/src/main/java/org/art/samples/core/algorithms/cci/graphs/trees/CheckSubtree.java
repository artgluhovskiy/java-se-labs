package main.java.org.art.samples.core.algorithms.cci.graphs.trees;

import main.java.org.art.samples.core.algorithms.utils.graphs.trees.BinaryTreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * "Check Subtree" solution from "Cracking the Coding Interview".
 */
public class CheckSubtree {

    public boolean checkSubtree(BinaryTreeNode<Integer> r1, BinaryTreeNode<Integer> r2) {
        if (r2 == null) {
            return true;
        }
        return subtree(r1, r2);
    }

    private boolean subtree(BinaryTreeNode<Integer> r1, BinaryTreeNode<Integer> r2) {
        if (r1 == null) {
            return false;
        } else if (r1.getElem().equals(r2.getElem()) && matchTrees(r1, r2)) {
            return true;
        } else {
            return subtree(r1.getLeft(), r2) || subtree(r1.getRight(), r2);
        }
    }

    private boolean matchTrees(BinaryTreeNode<Integer> r1, BinaryTreeNode<Integer> r2) {
        if (r1 == null && r2 == null) {
            return true;
        } else if (r1 == null || r2 == null) {
            return false;
        } else if (!r1.getElem().equals(r2.getElem())) {
            return false;
        } else {
            return matchTrees(r1.getRight(), r2.getRight()) && matchTrees(r1.getLeft(), r2.getLeft());
        }
    }

    @Test
    void test0() {
        BinaryTreeNode<Integer> r1 = new BinaryTreeNode<>(7,
            new BinaryTreeNode<>(3,
                new BinaryTreeNode<>(1, null, null),
                new BinaryTreeNode<>(5, null, null)
            ),
            new BinaryTreeNode<>(10,
                new BinaryTreeNode<>(9, null, null),
                new BinaryTreeNode<>(15, null, null)
            )
        );
        BinaryTreeNode<Integer> r2 = new BinaryTreeNode<>(10,
            new BinaryTreeNode<>(9, null, null),
            new BinaryTreeNode<>(15, null, null)
        );
        assertTrue(checkSubtree(r1, r2));
    }

    @Test
    void test2() {
        BinaryTreeNode<Integer> r1 = new BinaryTreeNode<>(7,
            new BinaryTreeNode<>(3,
                new BinaryTreeNode<>(1, null, null),
                new BinaryTreeNode<>(5, null, null)
            ),
            new BinaryTreeNode<>(10,
                new BinaryTreeNode<>(9, null, null),
                new BinaryTreeNode<>(15, null, null)
            )
        );
        BinaryTreeNode<Integer> r2 = new BinaryTreeNode<>(10,
            new BinaryTreeNode<>(9, null, null),
            new BinaryTreeNode<>(16, null, null)
        );
        assertFalse(checkSubtree(r1, r2));
    }
}

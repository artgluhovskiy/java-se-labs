package org.art.java_core.algorithms.cci.graphs.trees;

import org.art.java_core.algorithms.common.graphs.trees.BinaryTreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * "Validate BST" solution from "Cracking the Coding Interview".
 */
public class ValidateBst {

    public boolean validateBst(BinaryTreeNode<Integer> root) {
        return validateBstHelper(root, null, null);
    }

    private boolean validateBstHelper(BinaryTreeNode<Integer> root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if (min != null && root.getElem() < min) {
            return false;
        }
        if (max != null && root.getElem() > max) {
            return false;
        }
        if (!validateBstHelper(root.getLeft(), min, root.getElem()) || !validateBstHelper(root.getRight(), root.getElem(), max)) {
            return false;
        }
        return true;
    }

    @Test
    void test0() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(3, null, null);
        assertTrue(validateBst(root));
    }

    @Test
    void test1() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(3,
            new BinaryTreeNode<>(1, null, null),
            new BinaryTreeNode<>(5, null, null)
        );
        assertTrue(validateBst(root));
    }

    @Test
    void test2() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(3,
            new BinaryTreeNode<>(5, null, null),
            new BinaryTreeNode<>(1, null, null)
        );
        assertFalse(validateBst(root));
    }

    @Test
    void test3() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(7,
            new BinaryTreeNode<>(3,
                new BinaryTreeNode<>(1, null, null),
                new BinaryTreeNode<>(5, null, null)
            ),
            new BinaryTreeNode<>(10,
                new BinaryTreeNode<>(9, null, null),
                new BinaryTreeNode<>(15, null, null)
            )
        );
        assertTrue(validateBst(root));
    }

    @Test
    void test4() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(7,
            new BinaryTreeNode<>(3,
                new BinaryTreeNode<>(1, null, null),
                new BinaryTreeNode<>(5, null, null)
            ),
            new BinaryTreeNode<>(10,
                new BinaryTreeNode<>(9, null, null),
                new BinaryTreeNode<>(15, new BinaryTreeNode<>(8, null, null), null)
            )
        );
        assertFalse(validateBst(root));
    }

    @Test
    void test5() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(5,
            new BinaryTreeNode<>(7, null, null),
            new BinaryTreeNode<>(10, null, null)
        );
        assertFalse(validateBst(root));
    }
}

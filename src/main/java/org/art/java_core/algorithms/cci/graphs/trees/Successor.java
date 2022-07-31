package org.art.java_core.algorithms.cci.graphs.trees;

import java.util.Objects;

import org.art.java_core.algorithms.common.graphs.trees.BinaryTreeNode;
import org.art.java_core.algorithms.common.graphs.trees.BinaryTreeUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * "Successor" solution from "Cracking the Coding Interview".
 */
public class Successor {

    public BinaryTreeNode<Integer> findSuccessor(BinaryTreeNode<Integer> node) {
        Objects.requireNonNull(node);
        return findSuccessorHelper(node);
    }

    private BinaryTreeNode<Integer> findSuccessorHelper(BinaryTreeNode<Integer> node) {
        if (node.getRight() != null) {
            BinaryTreeNode<Integer> currentNode = node.getRight();
            while (currentNode.getLeft() != null) {
                currentNode = currentNode.getLeft();
            }
            return currentNode;
        } else {
            BinaryTreeNode<Integer> currentNode = node;
            BinaryTreeNode<Integer> parent = node.getParent();
            while (parent != null) {
                if (parent.getLeft() == currentNode) {
                    return parent;
                }
                currentNode = parent;
                parent = parent.getParent();
            }
            return parent;
        }
    }

    @Test
    void test0() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(20,
            new BinaryTreeNode<>(10,
                new BinaryTreeNode<>(5, new BinaryTreeNode<>(2, null, null), null),
                new BinaryTreeNode<>(15, null, null)
            ),
            new BinaryTreeNode<>(30,
                new BinaryTreeNode<>(25, null, null),
                new BinaryTreeNode<>(35, null, null)
            )
        );
        BinaryTreeNode<Integer> targetNode = BinaryTreeUtils.search(root, 5);
        BinaryTreeNode<Integer> successor = findSuccessor(targetNode);
        assertSame(10, successor.getElem());
    }

    @Test
    void test1() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(20,
            new BinaryTreeNode<>(10,
                new BinaryTreeNode<>(5, new BinaryTreeNode<>(2, null, null), null),
                new BinaryTreeNode<>(15,
                    new BinaryTreeNode<>(12, null, null),
                    new BinaryTreeNode<>(17, null, null)
                )
            ),
            new BinaryTreeNode<>(30,
                new BinaryTreeNode<>(25, null, null),
                new BinaryTreeNode<>(35, null, null)
            )
        );
        BinaryTreeNode<Integer> targetNode = BinaryTreeUtils.search(root, 10);
        BinaryTreeNode<Integer> successor = findSuccessor(targetNode);
        assertSame(12, successor.getElem());
    }

    @Test
    void test2() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(20,
            new BinaryTreeNode<>(10,
                new BinaryTreeNode<>(5, null, null),
                new BinaryTreeNode<>(15, null, null)
            ),
            new BinaryTreeNode<>(30,
                new BinaryTreeNode<>(25, null, null),
                new BinaryTreeNode<>(35, null, null)
            )
        );
        BinaryTreeNode<Integer> targetNode = BinaryTreeUtils.search(root, 15);
        BinaryTreeNode<Integer> successor = findSuccessor(targetNode);
        assertSame(20, successor.getElem());
    }
}

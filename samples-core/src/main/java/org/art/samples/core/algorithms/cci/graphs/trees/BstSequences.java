package org.art.samples.core.algorithms.cci.graphs.trees;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.art.samples.core.algorithms.utils.graphs.trees.BinaryTreeNode;
import org.junit.jupiter.api.Test;

/**
 * "BST Sequences" solution from "Cracking the Coding Interview".
 */
public class BstSequences {

    public List<Deque<Integer>> bstSequences(BinaryTreeNode<Integer> root) {
        List<Deque<Integer>> sequences = new ArrayList<>();
        if (root == null) {
            sequences.add(new LinkedList<>());
            return sequences;
        }

        Deque<Integer> prefix = new LinkedList<>();
        prefix.add(root.getElem());
        List<Deque<Integer>> rightSequences = bstSequences(root.getRight());
        List<Deque<Integer>> leftSequences = bstSequences(root.getLeft());
        for (Deque<Integer> rightSeq : rightSequences) {
            for (Deque<Integer> leftSeq : leftSequences) {
                sequences.addAll(weave(prefix, rightSeq, leftSeq));
            }
        }

        return sequences;
    }

    private List<Deque<Integer>> weave(Deque<Integer> prefix, Deque<Integer> rightSeq, Deque<Integer> leftSeq) {
        List<Deque<Integer>> sequences = new ArrayList<>();
        if (rightSeq.isEmpty() || leftSeq.isEmpty()) {
            prefix.addAll(rightSeq);
            prefix.addAll(leftSeq);
            sequences.add(prefix);
            return sequences;
        }

        Integer localPrefix = rightSeq.pollFirst();
        Deque<Integer> prefixClone = new LinkedList<>(prefix);
        prefixClone.addLast(localPrefix);
        sequences.addAll(weave(prefixClone, rightSeq, leftSeq));
        rightSeq.addFirst(localPrefix);

        localPrefix = leftSeq.pollFirst();
        prefixClone = new LinkedList<>(prefix);
        prefixClone.addLast(localPrefix);
        sequences.addAll(weave(prefixClone, rightSeq, leftSeq));
        leftSeq.addFirst(localPrefix);

        return sequences;
    }

    @Test
    void test0() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(7,
            new BinaryTreeNode<>(3, null, null),
            new BinaryTreeNode<>(10, null, null)
        );
        List<Deque<Integer>> sequences = bstSequences(root);
        System.out.println(sequences);
    }

    @Test
    void test1() {
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
        List<Deque<Integer>> sequences = bstSequences(root);
        System.out.println(sequences);
    }
}

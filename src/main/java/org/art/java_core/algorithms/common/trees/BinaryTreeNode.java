package org.art.java_core.algorithms.common.trees;

import java.util.Objects;
import java.util.stream.Stream;

import lombok.Getter;

import static java.util.stream.Collectors.toList;

/**
 * Binary Tree node implementation.
 */
@Getter
public class BinaryTreeNode<T> extends TreeNode<T> {

    public BinaryTreeNode<T> left;

    public BinaryTreeNode<T> right;

    public BinaryTreeNode<T> parent;

    public BinaryTreeNode(T elem, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        super(elem, Stream.of(left, right).filter(Objects::nonNull).collect(toList()));
        this.left = left;
        this.right = right;
    }

    public BinaryTreeNode(T elem, BinaryTreeNode<T> left, BinaryTreeNode<T> right, BinaryTreeNode<T> parent) {
        super(elem, Stream.of(left, right).filter(Objects::nonNull).collect(toList()));
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "TreeNode {" +
            " elem = " + getElem() +
            ", left value = " + (left != null ? left.getElem() : "null") +
            ", right value = " + (right != null ? right.getElem() : "null") +
            ", parent value = " + (parent != null ? parent.getElem() : "null") +
            " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BinaryTreeNode<T> that = (BinaryTreeNode<T>) o;
        return getElem() == that.getElem() && Objects.equals(left, that.left) && Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getElem(), left, right, parent);
    }
}

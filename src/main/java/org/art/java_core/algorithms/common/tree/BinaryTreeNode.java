package org.art.java_core.algorithms.common.tree;

/**
 * Binary Tree node implementation.
 */
public class BinaryTreeNode {

    public int value;
    public BinaryTreeNode left;
    public BinaryTreeNode right;
    public BinaryTreeNode parent;

    public BinaryTreeNode(int value, BinaryTreeNode left, BinaryTreeNode right, BinaryTreeNode parent) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "TreeNode {" +
                " value = " + value +
                ", left value = " + (left != null ? left.value : "null") +
                ", right value = " + (right != null ? right.value : "null") +
                ", parent value = " + (parent != null ? parent.value : "null") +
                " }";
    }
}

package org.art.java_core.algorithms.common.graphs.trees;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeUtils {

    /**
     * Recursively walks through the binary tree and puts the values
     * into a list data structure in a sorted order.
     *
     * @param list the list to put the values
     * @param root the root of the binary tree
     * @return the list of tree values (in a sorted order)
     */
    public static List<Integer> inOrderTreeWalk(BinaryTreeNode<Integer> root, List<Integer> list) {
        if (root != null) {
            inOrderTreeWalk(root.left, list);
            list.add(root.getElem());
            inOrderTreeWalk(root.right, list);
        }
        return list;
    }

    /**
     * Searches the value in the tree and return the
     * reference to the tree node with such value.
     */
    public static <T extends Comparable<T>> BinaryTreeNode<T> search(BinaryTreeNode<T> root, T elem) {
        if (root == null || root.getElem().compareTo(elem) == 0) {
            return root;
        }
        if (elem.compareTo(root.getElem()) < 0) {
            return search(root.left, elem);
        } else {
            return search(root.right, elem);
        }
    }

    /**
     * Inserts the node into an appropriate position in the tree.
     *
     * @param root the tree to insert the node
     * @param node the node to insert
     */
    public static <T extends Comparable<T>> void insertNode(BinaryTreeNode<T> root, BinaryTreeNode<T> node) {
        BinaryTreeNode<T> y = null;
        BinaryTreeNode<T> x = root;
        if (x == null) {
            root = node;
            return;
        }
        while (x != null) {
            y = x;
            if (node.getElem().compareTo(x.getElem()) < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        node.parent = y;
        if (node.getElem().compareTo(y.getElem()) < 0) {
            y.left = node;
        } else {
            y.right = node;
        }
    }

    /**
     * Deletes the node from the tree.
     *
     * @param root the root of the tree
     * @param target the services node to delete
     */
    public static void deleteNode(BinaryTreeNode root, BinaryTreeNode target) {
        if (target.left == null) {
            transplant(root, target, target.right);
        } else if (target.right == null) {
            transplant(root, target, target.left);
        } else {
            BinaryTreeNode minNode = minimum(target.right);
            if (minNode.parent != target) {
                transplant(root, minNode, minNode.right);
                minNode.right = target.right;
                minNode.right.parent = minNode;
            }
            transplant(root, target, minNode);
            minNode.left = target.left;
            minNode.left.parent = minNode;
        }
    }

    private static void transplant(BinaryTreeNode root, BinaryTreeNode target, BinaryTreeNode replacement) {
        if (target.parent == null) {
            root = replacement;
        } else if (target.parent.right == target) {
            target.parent.right = replacement;
        } else if (target.parent.left == target) {
            target.parent.left = replacement;
        }
        if (replacement != null) {
            replacement.parent = target.parent;
        }
    }

    private static BinaryTreeNode minimum(BinaryTreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    private static void leftRotate(BinaryTreeNode root, BinaryTreeNode nodeToRotate) {
        BinaryTreeNode y = nodeToRotate.right;
        nodeToRotate.right = y.left;
        if (y.left != null) {
            y.left.parent = nodeToRotate;
        }
        y.parent = nodeToRotate.parent;
        if (nodeToRotate.parent == null) {
            root = y;
        } else if (nodeToRotate == nodeToRotate.parent.left) {
            nodeToRotate.parent.left = y;
        } else {
            y.left = nodeToRotate;
        }
        y.left = nodeToRotate;
        nodeToRotate.parent = y;
    }

    public static void main(String[] args) {

        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(6, null, null);
        BinaryTreeNode<Integer> node2 = new BinaryTreeNode<>(5, null, null);
        BinaryTreeNode<Integer> node3 = new BinaryTreeNode<>(7, null, null);
        BinaryTreeNode<Integer> node4 = new BinaryTreeNode<>(2, null, null);
        BinaryTreeNode<Integer> node5 = new BinaryTreeNode<>(5, null, null);
        BinaryTreeNode<Integer> node6 = new BinaryTreeNode<>(8, null, null);

        //Insertion test
        List<Integer> list0 = new ArrayList<>();
        insertNode(root, node2);
        insertNode(root, node3);
        insertNode(root, node4);
        insertNode(root, node5);
        insertNode(root, node6);

        //Tree walk test
        List<Integer> list1 = new ArrayList<>();
        inOrderTreeWalk(root, list1);
        System.out.println(list1);

        //Tree search test
        BinaryTreeNode<Integer> targetNode = search(root, 8);
        System.out.println(targetNode);

        //Delete test
        List<Integer> list2 = new ArrayList<>();
        deleteNode(root, node4);
        inOrderTreeWalk(root, list2);
        System.out.println(list2);
    }
}

package main.java.org.art.samples.core.algorithms.cci.graphs.trees;

import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * "Random Node" solution from "Cracking the Coding Interview".
 */
public class RandomNode {

    @Test
    void test0() {
        RandomTree tree = new RandomTree();
        tree.insertInOrder(7);
        tree.insertInOrder(3);
        tree.insertInOrder(10);
        tree.insertInOrder(1);
        tree.insertInOrder(5);
        tree.insertInOrder(9);
        tree.insertInOrder(15);
        System.out.println(tree.getRandomNode());
        System.out.println(tree.getRandomNode());
        System.out.println(tree.getRandomNode());
        System.out.println(tree.getRandomNode());
        System.out.println(tree.getRandomNode());
        System.out.println(tree.getRandomNode());
        System.out.println(tree.getRandomNode());
    }
}

class RandomTree {

    final Random rnd = new Random();

    RandomTreeNode root;

    public void insertInOrder(int value) {
        if (root == null) {
            root = new RandomTreeNode(value);
        } else {
            root.insertInOrder(value);
        }
    }

    public RandomTreeNode getRandomNode() {
        if (root == null) {
            return null;
        }
        int i = rnd.nextInt(size());
        System.out.println("i: " + i);
        return root.getIthNode(i);
    }

    public int size() {
        return root == null ? 0 : root.size;
    }
}

class RandomTreeNode {

    int value;

    RandomTreeNode left;

    RandomTreeNode right;

    int size;

    public RandomTreeNode(int value) {
        this.value = value;
        this.size = 1;
    }

    public void insertInOrder(int newValue) {
        if (newValue < value) {
            if (left == null) {
                left = new RandomTreeNode(newValue);
            } else {
                left.insertInOrder(newValue);
            }
        } else {
            if (right == null) {
                right = new RandomTreeNode(newValue);
            } else {
                right.insertInOrder(newValue);
            }
        }
        size++;
    }

    public RandomTreeNode getIthNode(int i) {
        int leftSize = left == null ? 0 : left.size;
        if(i < leftSize) {
            return left.getIthNode(i);
        } else if (i == leftSize) {
            return this;
        } else {
            return right.getIthNode(i - (leftSize + 1));
        }
    }

    @Override
    public String toString() {
        return "RandomTreeNode{" +
            "value=" + value +
            '}';
    }
}

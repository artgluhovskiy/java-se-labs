package org.art.java_core.algorithms.common.tree;

import java.util.List;

public class TreeNode<T> {

    private final T name;

    private List<TreeNode<T>> adjacentNodes;

    private boolean visited;

    public TreeNode(T value, List<TreeNode<T>> adjacentNodes) {
        this.name = value;
        this.adjacentNodes = adjacentNodes;
    }

    public T getName() {
        return name;
    }

    public List<TreeNode<T>> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(List<TreeNode<T>> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}

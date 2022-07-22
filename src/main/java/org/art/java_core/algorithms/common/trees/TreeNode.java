package org.art.java_core.algorithms.common.trees;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreeNode<T> {

    private final T elem;

    private List<TreeNode<T>> adjacentNodes;

    private boolean visited;

    public TreeNode(T elem, List<TreeNode<T>> adjacentNodes) {
        this.elem = elem;
        this.adjacentNodes = adjacentNodes;
    }
}

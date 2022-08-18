package org.art.java_core.algorithms.utils.graphs.trees;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;

@UtilityClass
public class TreeUtils {

    public static <T> void bfs(TreeNode<T> root) {
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.poll();
            if (!node.isVisited()) {
                node.setVisited(true);
                List<TreeNode<T>> adjacentNodes = node.getAdjacentNodes();
                if (CollectionUtils.isNotEmpty(adjacentNodes)) {
                    for (TreeNode<T> adjNode : adjacentNodes) {
                        if (!adjNode.isVisited()) {
                            queue.add(adjNode);
                        }
                    }
                }
            }
        }
    }

    public static <T> boolean bfsSearch(TreeNode<T> root, TreeNode<T> target) {
        if (root == target) {
            return true;
        }
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.poll();
            if (!node.isVisited()) {
                node.setVisited(true);
                if (node == target) {
                    return true;
                }
                List<TreeNode<T>> adjacentNodes = node.getAdjacentNodes();
                if (CollectionUtils.isNotEmpty(adjacentNodes)) {
                    for (TreeNode<T> adjNode : adjacentNodes) {
                        if (!adjNode.isVisited()) {
                            queue.add(adjNode);
                        }
                    }
                }
            }
        }
        return false;
    }
}

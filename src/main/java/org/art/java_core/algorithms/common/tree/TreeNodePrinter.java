package org.art.java_core.algorithms.common.tree;

import java.util.Iterator;
import java.util.List;

import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class TreeNodePrinter {

    /**
     * Prints tree (to string) in the following way:
     * z
     * ├── c
     * │   ├── a
     * │   └── b
     * ├── d
     * ├── e
     * │   └── t
     * └── f
     */
    public static <T> String printTree(TreeNode<T> root) {
        StringBuilder sb = new StringBuilder();
        print(root, StringUtils.EMPTY, StringUtils.EMPTY, sb);
        return sb.toString();
    }

    private static <T> void print(TreeNode<T> root, String prefix, String childrenPrefix, StringBuilder sb) {
        sb.append(prefix);
        String name = root.isVisited() ? root.getName().toString().toUpperCase() : root.getName().toString().toLowerCase();
        sb.append(name);
        sb.append('\n');
        List<TreeNode<T>> adjacentNodes = root.getAdjacentNodes();
        if (CollectionUtils.isNotEmpty(adjacentNodes)) {
            Iterator<TreeNode<T>> nodeIterator = adjacentNodes.iterator();
            while (nodeIterator.hasNext()) {
                TreeNode<T> adjNode = nodeIterator.next();
                if (nodeIterator.hasNext()) {
                    print(adjNode, childrenPrefix + "├── ", childrenPrefix + "│   ", sb);
                } else {
                    print(adjNode, childrenPrefix + "└── ", childrenPrefix + "    ", sb);
                }
            }
        }
    }
}

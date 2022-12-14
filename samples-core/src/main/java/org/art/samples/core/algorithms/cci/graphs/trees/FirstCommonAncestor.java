package org.art.samples.core.algorithms.cci.graphs.trees;

import lombok.Getter;
import org.art.samples.core.algorithms.utils.graphs.trees.BinaryTreeNode;
import org.art.samples.core.algorithms.utils.graphs.trees.BinaryTreeUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * "First Common Ancestor" solution from "Cracking the Coding Interview".
 */
public class FirstCommonAncestor {

    /**
     * Version 1: Considering the nodes in the tree have the links to the parent nodes.
     */
    public BinaryTreeNode<Integer> firstCommonAncestorV1(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> first, BinaryTreeNode<Integer> second) {
        if (!covers(root, first) || !covers(root, second)) {
            return null;
        } else if (covers(first, second)) {
            return first;
        } else if (covers(second, first)) {
            return second;
        }

        BinaryTreeNode<Integer> sibling = getSibling(first);
        BinaryTreeNode<Integer> parent = first.getParent();
        while (!covers(sibling, second)) {
            sibling = getSibling(parent);
            parent = parent.getParent();
        }
        return parent;
    }

    private BinaryTreeNode<Integer> getSibling(BinaryTreeNode<Integer> node) {
        if (node == null || node.getParent() == null) {
            return null;

        }
        BinaryTreeNode<Integer> parent = node.getParent();
        return node == parent.getLeft() ? parent.getRight() : parent.getLeft();
    }

    private boolean covers(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> target) {
        if (root == null) {
            return false;
        } else if (root == target) {
            return true;
        }
        return covers(root.getLeft(), target) || covers(root.getRight(), target);
    }

    /**
     * Version 2: Considering the nodes in the tree don't have the links to the parent nodes (version contains 2 bugs: 1 - when
     * one of the nodes is not in the tree; 2 - when first (second) node is a child of the second (first) node).
     */
    public BinaryTreeNode<Integer> firstCommonAncestorV2(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> first, BinaryTreeNode<Integer> second) {
        if (root == null) {
            return null;
        }

        BinaryTreeNode<Integer> x = firstCommonAncestorV2(root.getLeft(), first, second);
        if (x != null && x != first && x != second) {
            return x;
        }

        BinaryTreeNode<Integer> y = firstCommonAncestorV2(root.getRight(), first, second);
        if (y != null && y != first && y != second) {
            return y;
        }

        if (x != null && y != null && x != y) {
            return root;
        } else if (root == first || root == second) {
            return root;
        } else {
            return x != null ? x : y;
        }
    }

    /**
     * Version 3: Considering the nodes in the tree don't have the links to the parent nodes (version 2 with the fixed bugs).
     */
    public BinaryTreeNode<Integer> firstCommonAncestorV3(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> first, BinaryTreeNode<Integer> second) {
        Result result = firstCommonAncestorHelper(root, first, second);
        if (result.isAncestor()) {
            return result.getNode();
        }
        return null;
    }

    private Result firstCommonAncestorHelper(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> first, BinaryTreeNode<Integer> second) {
        if (root == null) {
            return new Result(null, false);
        }

        Result x = firstCommonAncestorHelper(root.getLeft(), first, second);
        if (x.isAncestor()) {
            return x;
        }

        Result y = firstCommonAncestorHelper(root.getRight(), first, second);
        if (y.isAncestor()) {
            return y;
        }

        if (x.getNode() != null && y.getNode() != null && x.getNode() != y.getNode()) {
            return new Result(root, true);
        } else if (root == first || root == second) {
            //Fixing the second bug
            boolean isAnc = x.getNode() != null || y.getNode() != null;
            return new Result(root, isAnc);
        } else {
            return new Result(x.getNode() != null ? x.getNode() : y.getNode(), false);
        }
    }

    @Getter
    private class Result {

        private final BinaryTreeNode<Integer> node;

        private final boolean isAncestor;

        public Result(BinaryTreeNode<Integer> node, boolean isAncestor) {
            this.node = node;
            this.isAncestor = isAncestor;
        }
    }

    @Test
    void test0() {
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
        BinaryTreeNode<Integer> first = BinaryTreeUtils.search(root, 2);
        BinaryTreeNode<Integer> second = BinaryTreeUtils.search(root, 35);
        BinaryTreeNode<Integer> expectCommonAncestor = BinaryTreeUtils.search(root, 20);
        BinaryTreeNode<Integer> resultCommonAncestor = firstCommonAncestorV1(root, first, second);
        assertSame(expectCommonAncestor, resultCommonAncestor);
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
        BinaryTreeNode<Integer> first = BinaryTreeUtils.search(root, 2);
        BinaryTreeNode<Integer> second = BinaryTreeUtils.search(root, 17);
        BinaryTreeNode<Integer> expectCommonAncestor = BinaryTreeUtils.search(root, 10);
        BinaryTreeNode<Integer> resultCommonAncestor = firstCommonAncestorV1(root, first, second);
        assertSame(expectCommonAncestor, resultCommonAncestor);
    }

    @Test
    void test2() {
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
        BinaryTreeNode<Integer> first = BinaryTreeUtils.search(root, 25);
        BinaryTreeNode<Integer> second = BinaryTreeUtils.search(root, 30);
        BinaryTreeNode<Integer> expectCommonAncestor = BinaryTreeUtils.search(root, 30);
        BinaryTreeNode<Integer> resultCommonAncestor = firstCommonAncestorV1(root, first, second);
        assertSame(expectCommonAncestor, resultCommonAncestor);
    }

    @Test
    void test3() {
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
        BinaryTreeNode<Integer> first = BinaryTreeUtils.search(root, 2);
        BinaryTreeNode<Integer> second = BinaryTreeUtils.search(root, 35);
        BinaryTreeNode<Integer> expectCommonAncestor = BinaryTreeUtils.search(root, 20);
        BinaryTreeNode<Integer> resultCommonAncestor = firstCommonAncestorV2(root, first, second);
        assertSame(expectCommonAncestor, resultCommonAncestor);
    }

    @Test
    void test4() {
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
        BinaryTreeNode<Integer> first = BinaryTreeUtils.search(root, 2);
        BinaryTreeNode<Integer> second = BinaryTreeUtils.search(root, 17);
        BinaryTreeNode<Integer> expectCommonAncestor = BinaryTreeUtils.search(root, 10);
        BinaryTreeNode<Integer> resultCommonAncestor = firstCommonAncestorV2(root, first, second);
        assertSame(expectCommonAncestor, resultCommonAncestor);
    }

    @Test
    void test5() {
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
        BinaryTreeNode<Integer> first = BinaryTreeUtils.search(root, 25);
        BinaryTreeNode<Integer> second = BinaryTreeUtils.search(root, 30);
        BinaryTreeNode<Integer> expectCommonAncestor = BinaryTreeUtils.search(root, 30);
        BinaryTreeNode<Integer> resultCommonAncestor = firstCommonAncestorV2(root, first, second);
        assertSame(expectCommonAncestor, resultCommonAncestor);
    }

    @Test
    void test6() {
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
        BinaryTreeNode<Integer> first = BinaryTreeUtils.search(root, 2);
        BinaryTreeNode<Integer> second = BinaryTreeUtils.search(root, 35);
        BinaryTreeNode<Integer> expectCommonAncestor = BinaryTreeUtils.search(root, 20);
        BinaryTreeNode<Integer> resultCommonAncestor = firstCommonAncestorV3(root, first, second);
        assertSame(expectCommonAncestor, resultCommonAncestor);
    }

    @Test
    void test7() {
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
        BinaryTreeNode<Integer> first = BinaryTreeUtils.search(root, 2);
        BinaryTreeNode<Integer> second = BinaryTreeUtils.search(root, 17);
        BinaryTreeNode<Integer> expectCommonAncestor = BinaryTreeUtils.search(root, 10);
        BinaryTreeNode<Integer> resultCommonAncestor = firstCommonAncestorV3(root, first, second);
        assertSame(expectCommonAncestor, resultCommonAncestor);
    }

    @Test
    void test8() {
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
        BinaryTreeNode<Integer> first = BinaryTreeUtils.search(root, 25);
        BinaryTreeNode<Integer> second = BinaryTreeUtils.search(root, 30);
        BinaryTreeNode<Integer> expectCommonAncestor = BinaryTreeUtils.search(root, 30);
        BinaryTreeNode<Integer> resultCommonAncestor = firstCommonAncestorV3(root, first, second);
        assertSame(expectCommonAncestor, resultCommonAncestor);
    }
}

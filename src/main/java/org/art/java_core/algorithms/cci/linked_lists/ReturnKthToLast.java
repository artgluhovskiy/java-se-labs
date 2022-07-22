package org.art.java_core.algorithms.cci.linked_lists;

import org.art.java_core.algorithms.common.lists.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * "Return Kth To Last" solution from "Cracking the Coding Interview".
 * "2-pointers approach" is used - version 1.
 * "recursive approach" is used - version 2.
 */
public class ReturnKthToLast {

    public int returnKthToLastV1(int k, ListNode<Integer> head) {
        if (head == null) {
            throw new IllegalArgumentException("head cannot be null");
        }
        ListNode<Integer> basePointer = head;
        ListNode<Integer> aheadPointer = head;
        for (int i = 0; i < k; i++) {
            if (aheadPointer == null) {
                throw new IllegalStateException("List size is not enough to find the k-th element to last");
            }
            aheadPointer = aheadPointer.next;
        }

        while (aheadPointer != null) {
            basePointer = basePointer.next;
            aheadPointer = aheadPointer.next;
        }

        return basePointer.val;
    }

    public int returnKthToLastV2(int k, ListNode<Integer> head) {
        if (head == null) {
            throw new IllegalArgumentException("head cannot be null");
        }
        NodeFinderContainer container = new NodeFinderContainer(0);
        findKthToLast(k, head, container);
        return container.foundNode.val;
    }

    private void findKthToLast(int k, ListNode<Integer> head, NodeFinderContainer container) {
        if (head == null) {
            return;
        }
        findKthToLast(k, head.next, container);
        container.inc();
        if (container.counter == k) {
            container.foundNode = head;
        }
    }

    static class NodeFinderContainer {

        private ListNode<Integer> foundNode;

        private int counter;

        public NodeFinderContainer(int counter) {
            this.counter = counter;
        }

        public void inc() {
            this.counter++;
        }
    }

    @Test
    void test0() {
        ListNode<Integer> inputList = ListNode.of(2);
        int result = returnKthToLastV1(1, inputList);
        assertEquals(2, result);
    }

    @Test
    void test1() {
        ListNode<Integer> inputList = ListNode.of(2, 3);
        int result = returnKthToLastV1(2, inputList);
        assertEquals(2, result);
    }

    @Test
    void test2() {
        ListNode<Integer> inputList = ListNode.of(2, 3, 4, 8, 3);
        int result = returnKthToLastV1(3, inputList);
        assertEquals(4, result);
    }

    @Test
    void test3() {
        ListNode<Integer> inputList = ListNode.of(2);
        int result = returnKthToLastV2(1, inputList);
        assertEquals(2, result);
    }

    @Test
    void test4() {
        ListNode<Integer> inputList = ListNode.of(2, 3);
        int result = returnKthToLastV2(2, inputList);
        assertEquals(2, result);
    }

    @Test
    void test5() {
        ListNode<Integer> inputList = ListNode.of(2, 3, 4, 8, 3);
        int result = returnKthToLastV2(3, inputList);
        assertEquals(4, result);
    }
}

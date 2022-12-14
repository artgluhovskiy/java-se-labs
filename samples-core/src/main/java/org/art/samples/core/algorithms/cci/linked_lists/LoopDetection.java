package org.art.samples.core.algorithms.cci.linked_lists;

import org.art.samples.core.algorithms.utils.lists.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * "Loop Detection" solution from "Cracking the Coding Interview".
 * "Fast Runner / Slow Runner approach" is used to find the collision (meeting) point.
 */
public class LoopDetection {

    public ListNode<Integer> detectLoop(ListNode<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("list cannot be null");
        }
        if (list.next == null) {
            return null;
        }
        if (list.next == list) {
            return list;
        }
        ListNode<Integer> collisionPointer = findCollisionNode(list);
        if (collisionPointer == null || collisionPointer.next == null) {
            return null;
        }
        ListNode<Integer> helperPointer = list;
        while (helperPointer != collisionPointer) {
            helperPointer = helperPointer.next;
            collisionPointer = collisionPointer.next;
        }
        return helperPointer;
    }

    private ListNode<Integer> findCollisionNode(ListNode<Integer> list) {
        ListNode<Integer> oneStepPointer = list;
        ListNode<Integer> twoStepPointer = list;
        while (twoStepPointer != null && twoStepPointer.next != null) {
            oneStepPointer = oneStepPointer.next;
            twoStepPointer = twoStepPointer.next.next;
            if (oneStepPointer == twoStepPointer) {
                return oneStepPointer;
            }
        }
        return null;
    }

    @Test
    void test0() {
        ListNode<Integer> n1 = new ListNode<>(2, null);
        ListNode<Integer> n2 = new ListNode<>(6, null);
        ListNode<Integer> n3 = new ListNode<>(8, null);
        ListNode<Integer> n4 = new ListNode<>(7, null);
        ListNode<Integer> n5 = new ListNode<>(9, null);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n3;
        ListNode<Integer> loopNode = detectLoop(n1);
        assertSame(n3, loopNode);
    }

    @Test
    void test1() {
        ListNode<Integer> list = ListNode.of(1, 3, 7, 5, 6);
        ListNode<Integer> loopNode = detectLoop(list);
        assertNull(loopNode);
    }
}

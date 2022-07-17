package org.art.java_core.algorithms.cci.linked_lists;

import org.art.java_core.algorithms.common.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * "Intersection in Singly Linked Lists" solution from "Cracking the Coding Interview".
 * "2-pointers approach" is used.
 */
public class Intersection {

    public ListNode<Integer> findIntersection(ListNode<Integer> list1, ListNode<Integer> list2) {
        if (list1 == null || list2 == null) {
            throw new IllegalArgumentException("lists cannot be null");
        }
        ListNode<Integer> result = findIntersectionHelper(list1, list2);
        if (result != null) {
            return result;
        } else {
            return findIntersectionHelper(list2, list1);
        }
    }

    private ListNode<Integer> findIntersectionHelper(ListNode<Integer> list1, ListNode<Integer> list2) {
        if (list1 == list2) {
            return list1;
        }
        ListNode<Integer> oneStepPointer = list1;
        ListNode<Integer> twoStepPointer = list2;
        while (oneStepPointer.next != null && twoStepPointer.next != null) {
            oneStepPointer = oneStepPointer.next;
            twoStepPointer = twoStepPointer.next;
            if (oneStepPointer == twoStepPointer) {
                return oneStepPointer;
            }
            twoStepPointer = twoStepPointer.next;
            if (twoStepPointer == null) {
                return null;
            }
            if (oneStepPointer == twoStepPointer) {
                return oneStepPointer;
            }
        }
        return null;
    }

    @Test
    void test0() {
        ListNode<Integer> intersectingNode = new ListNode<>(1, new ListNode<>(3, new ListNode<>(9, null)));
        ListNode<Integer> list1 = new ListNode<>(4, new ListNode<>(2, new ListNode<>(7, intersectingNode)));
        ListNode<Integer> list2 = new ListNode<>(5, new ListNode<>(10, intersectingNode));
        ListNode<Integer> intersectionResult = findIntersection(list1, list2);
        assertSame(intersectingNode, intersectionResult);
    }

    @Test
    void test2() {
        ListNode<Integer> list1 = new ListNode<>(4, new ListNode<>(2, new ListNode<>(7, null)));
        ListNode<Integer> list2 = new ListNode<>(5, new ListNode<>(10, null));
        ListNode<Integer> intersectionResult = findIntersection(list1, list2);
        assertNull(intersectionResult);
    }

    @Test
    void test3() {
        ListNode<Integer> list = new ListNode<>(4, new ListNode<>(2, new ListNode<>(7, null)));
        ListNode<Integer> intersectionResult = findIntersection(list, list);
        assertSame(list, intersectionResult);
    }
}

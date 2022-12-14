package org.art.samples.core.algorithms.cci.linked_lists;

import org.art.samples.core.algorithms.utils.lists.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * "Intersection in Singly Linked Lists" solution from "Cracking the Coding Interview".
 */
public class Intersection {

    public ListNode<Integer> findIntersection(ListNode<Integer> head1, ListNode<Integer> head2) {
        if (head1 == null || head2 == null) {
            throw new IllegalArgumentException("lists cannot be null");
        }

        ListNode<Integer> current1 = head1;
        int length1 = 0;
        while (current1 != null) {
            length1++;
            current1 = current1.next;
        }

        ListNode<Integer> current2 = head2;
        int length2 = 0;
        while (current2 != null) {
            length2++;
            current2 = current2.next;
        }

        if (current1 != current2) {
            return null;
        }

        ListNode<Integer> longList;
        ListNode<Integer> shortList;
        if (length1 > length2) {
            longList = head1;
            shortList = head2;
        } else {
            longList = head2;
            shortList = head1;
        }

        for (int i = 0; i < Math.abs(length1 - length2); i++) {
            longList = longList.next;
        }

        while (longList != shortList) {
            longList = longList.next;
            shortList = shortList.next;
        }

        return longList;
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
    void test1() {
        ListNode<Integer> list1 = new ListNode<>(4, new ListNode<>(2, new ListNode<>(7, null)));
        ListNode<Integer> list2 = new ListNode<>(5, new ListNode<>(10, null));
        ListNode<Integer> intersectionResult = findIntersection(list1, list2);
        assertNull(intersectionResult);
    }

    @Test
    void test2() {
        ListNode<Integer> list = new ListNode<>(4, new ListNode<>(2, new ListNode<>(7, null)));
        ListNode<Integer> intersectionResult = findIntersection(list, list);
        assertSame(list, intersectionResult);
    }
}

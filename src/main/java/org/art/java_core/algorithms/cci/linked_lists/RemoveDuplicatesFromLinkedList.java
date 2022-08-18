package org.art.java_core.algorithms.cci.linked_lists;


import org.art.java_core.algorithms.utils.lists.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * "Remove Duplicated From Linked List" solution from "Cracking the Coding Interview".
 * "3-pointers approach" is used. No additional buffer should be used.
 */
public class RemoveDuplicatesFromLinkedList {

    public ListNode<Integer> removeDuplicates(ListNode<Integer> list) {
        if (list == null || list.next == null) {
            return list;
        }

        ListNode<Integer> basePointer = list;
        ListNode<Integer> helperPointer = basePointer;
        ListNode<Integer> findingPointer = helperPointer.next;

        do {
            while (helperPointer.next != null) {
                if (basePointer.val.equals(findingPointer.val)) {
                    helperPointer.next = findingPointer.next;
                    findingPointer = findingPointer.next;
                } else {
                    helperPointer = helperPointer.next;
                    findingPointer = findingPointer.next;
                }
            }
            basePointer = basePointer.next;
            helperPointer = basePointer;
            findingPointer = helperPointer.next;
        } while (basePointer.next != null);

        return list;
    }

    @Test
    void test0() {
        ListNode<Integer> input = ListNode.of(3);
        ListNode<Integer> result = removeDuplicates(input);
        ListNode<Integer> expected = ListNode.of(3);
        assertEquals(expected, result);
    }

    @Test
    void test1() {
        ListNode<Integer> input = ListNode.of(3, 5);
        ListNode<Integer> result = removeDuplicates(input);
        ListNode<Integer> expected = ListNode.of(3, 5);
        assertEquals(expected, result);
    }

    @Test
    void test2() {
        ListNode<Integer> input = ListNode.of(3, 5, 3);
        ListNode<Integer> result = removeDuplicates(input);
        ListNode<Integer> expected = ListNode.of(3, 5);
        assertEquals(expected, result);
    }

    @Test
    void test3() {
        ListNode<Integer> input = ListNode.of(3, 5, 6, 2, 5, 8);
        ListNode<Integer> result = removeDuplicates(input);
        ListNode<Integer> expected = ListNode.of(3, 5, 6, 2, 8);
        assertEquals(expected, result);
    }
}

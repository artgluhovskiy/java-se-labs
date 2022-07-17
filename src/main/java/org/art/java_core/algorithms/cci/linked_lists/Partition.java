package org.art.java_core.algorithms.cci.linked_lists;

import org.art.java_core.algorithms.common.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * "Partition" solution from "Cracking the Coding Interview".
 * N-pointers approach is used.
 */
public class Partition {

    public ListNode<Integer> partition(int target, ListNode<Integer> list) {
        ListNode<Integer> leftPartTail = null;
        ListNode<Integer> leftPartHelperPointer = null;
        ListNode<Integer> rightPartTail = null;
        ListNode<Integer> rightPartHelperPointer = null;
        ListNode<Integer> currentPointer = list;

        while (currentPointer != null) {
            if (currentPointer.val < target) {
                if (leftPartTail == null) {
                    leftPartTail = currentPointer;
                    leftPartHelperPointer = currentPointer;
                } else {
                    leftPartHelperPointer.next = currentPointer;
                    leftPartHelperPointer = leftPartHelperPointer.next;
                }
            } else {
                if (rightPartTail == null) {
                    rightPartTail = currentPointer;
                    rightPartHelperPointer = currentPointer;
                } else {
                    rightPartHelperPointer.next = currentPointer;
                    rightPartHelperPointer = rightPartHelperPointer.next;
                }
            }
            currentPointer = currentPointer.next;
        }

        if (leftPartHelperPointer == null) {
            return rightPartTail;
        }
        if (rightPartHelperPointer == null) {
            return leftPartTail;
        }
        leftPartHelperPointer.next = rightPartTail;
        rightPartHelperPointer.next = null;
        return leftPartTail;
    }

    @Test
    void test0() {
        ListNode<Integer> inputList = ListNode.of(3, 5, 8, 5, 10, 2, 1);
        ListNode<Integer> resultList = partition(5, inputList);
        ListNode<Integer> expected = ListNode.of(3, 2, 1, 5, 8, 5, 10);
        assertEquals(expected, resultList);
    }

    @Test
    void test1() {
        ListNode<Integer> inputList = ListNode.of(5, 8, 5, 10);
        ListNode<Integer> resultList = partition(5, inputList);
        ListNode<Integer> expected = ListNode.of(5, 8, 5, 10);
        assertEquals(expected, resultList);
    }

    @Test
    void test2() {
        ListNode<Integer> inputList = ListNode.of(3, 2, 1);
        ListNode<Integer> resultList = partition(5, inputList);
        ListNode<Integer> expected = ListNode.of(3, 2, 1);
        assertEquals(expected, resultList);
    }
}

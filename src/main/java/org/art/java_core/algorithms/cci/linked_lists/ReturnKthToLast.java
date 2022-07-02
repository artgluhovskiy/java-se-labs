package org.art.java_core.algorithms.cci.linked_lists;

import org.art.java_core.algorithms.common.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * "Return Kth To Last" solution from "Cracking the Coding Interview".
 * "2-pointers approach" is used.
 */
public class ReturnKthToLast {

    public int returnKthToLast(int k, ListNode head) {
        if (head == null) {
            throw new IllegalArgumentException("head cannot be null");
        }
        ListNode basePointer = head;
        ListNode aheadPointer = head;
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

    @Test
    void test0() {
        ListNode inputList = ListNode.of(2);
        int result = returnKthToLast(1, inputList);
        assertEquals(2, result);
    }

    @Test
    void test1() {
        ListNode inputList = ListNode.of(2, 3);
        int result = returnKthToLast(2, inputList);
        assertEquals(2, result);
    }

    @Test
    void test2() {
        ListNode inputList = ListNode.of(2, 3, 4, 8, 3);
        int result = returnKthToLast(3, inputList);
        assertEquals(4, result);
    }
}

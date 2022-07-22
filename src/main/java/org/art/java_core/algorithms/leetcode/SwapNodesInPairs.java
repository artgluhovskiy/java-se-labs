package org.art.java_core.algorithms.leetcode;

import org.art.java_core.algorithms.common.lists.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * "Swap Nodes in Pairs" quiz solution from LeetCode.
 */
public class SwapNodesInPairs {

    public ListNode<Integer> swapPairs(ListNode<Integer> head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode<Integer> ptr1 = head;
        ListNode<Integer> ptr2 = head.next;
        while (ptr2 != null) {
            ptr1.val = ptr1.val + ptr2.val;
            ptr2.val = ptr1.val - ptr2.val;
            ptr1.val = ptr1.val - ptr2.val;
            ptr1 = ptr1.next.next;
            if (ptr1 == null || ptr1.next == null) {
                return head;
            }
            ptr2 = ptr2.next.next;
        }
        return head;
    }

    @Test
    void test0() {
        ListNode<Integer> input = ListNode.of(1, 2, 3, 4);
        ListNode<Integer> expected = ListNode.of(2, 1, 4, 3);
        ListNode<Integer> result = swapPairs(input);
        assertEquals(expected, result);
    }
}

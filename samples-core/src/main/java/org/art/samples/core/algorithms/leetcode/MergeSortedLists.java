package main.java.org.art.samples.core.algorithms.leetcode;

import org.art.samples.core.algorithms.utils.lists.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * "Merge Two Sorted Lists" quiz solution from LeetCode.
 */
public class MergeSortedLists {

    public ListNode<Integer> mergeTwoLists(ListNode<Integer> l1, ListNode<Integer> l2) {
        ListNode<Integer> dummy = new ListNode<>(0);
        ListNode<Integer> l3 = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                l3.next = l1;
                l1 = l1.next;
            } else {
                l3.next = l2;
                l2 = l2.next;
            }
            l3 = l3.next;
        }
        if (l1 == null) {
            l3.next = l2;
        }
        if (l2 == null) {
            l3.next = l1;
        }
        return dummy.next;
    }

    @Test
    void test0() {
        ListNode<Integer> l1 = ListNode.of(1, 2, 4);
        ListNode<Integer> l2 = ListNode.of(1, 3, 4);
        ListNode<Integer> result = ListNode.of(1, 1, 2, 3, 4, 4);
        assertEquals(result, mergeTwoLists(l1, l2));
    }

    @Test
    void test1() {
        ListNode<Integer> l1 = ListNode.of(1, 2, 4);
        ListNode<Integer> l2 = ListNode.of(1, 3, 4, 6);
        ListNode<Integer> result = ListNode.of(1, 1, 2, 3, 4, 4, 6);
        assertEquals(result, mergeTwoLists(l1, l2));
    }
}



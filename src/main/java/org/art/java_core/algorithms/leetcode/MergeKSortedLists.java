package org.art.java_core.algorithms.leetcode;

import org.art.java_core.algorithms.utils.lists.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * "Merge K Sorted Lists" quiz solution from LeetCode.
 */
public class MergeKSortedLists {

    public ListNode<Integer> mergeKLists(ListNode<Integer>[] lists) {
        if (lists.length == 1) {
            return lists[0];
        } else if (lists.length == 0) {
            return null;
        }
        return mergeKLists0(lists, 0, lists.length - 1);
    }

    private ListNode<Integer> mergeKLists0(ListNode<Integer>[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        } else if (end - start == 1) {
            return merge2Lists(lists[start], lists[end]);
        } else {
            int mid = (end + start) / 2;
            ListNode<Integer> l1 = mergeKLists0(lists, start, mid);
            ListNode<Integer> l2 = mergeKLists0(lists, mid + 1, end);
            return merge2Lists(l1, l2);
        }
    }

    private ListNode<Integer> merge2Lists(ListNode<Integer> l1, ListNode<Integer> l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode<Integer> dummy = new ListNode<>(0);
        ListNode<Integer> current;
        if (l1.val <= l2.val) {
            dummy.next = l1;
            current = l1;
            l1 = l1.next;
        } else {
            dummy.next = l2;
            current = l2;
            l2 = l2.next;
        }
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        if (l1 == null) {
            current.next = l2;
        } else {
            current.next = l1;
        }
        return dummy.next;
    }

    @Test
    void test0() {
        ListNode<Integer> l1 = ListNode.of(1, 4, 5);
        ListNode<Integer> l2 = ListNode.of(1, 3, 4);
        ListNode<Integer> l3 = ListNode.of(2, 6);
        ListNode<Integer>[] input = new ListNode[]{l1, l2, l3};
        ListNode<Integer> result = mergeKLists(input);
        ListNode<Integer> expected = ListNode.of(1, 1, 2, 3, 4, 4, 5, 6);
        assertEquals(expected, result);
    }

    @Test
    void test1() {
        ListNode<Integer> l1 = ListNode.of(-10, -9, -9, -3, -1, -1, 0);
        ListNode<Integer> l2 = ListNode.of(-5);
        ListNode<Integer> l3 = ListNode.of(4);
        ListNode<Integer> l4 = ListNode.of(-8);
        ListNode<Integer> l5 = null;
        ListNode<Integer> l6 = ListNode.of(-9, -6, -5, -4, -2, 2, 3);
        ListNode<Integer> l7 = ListNode.of(-3, -3, -2, -1, 0);
        ListNode<Integer>[] input = new ListNode[]{l1, l2, l3, l4, l5, l6, l7};
        ListNode<Integer> result = mergeKLists(input);
        ListNode<Integer> expected = ListNode.of(-10, -9, -9, -9, -8, -6, -5, -5, -4, -3, -3, -3, -2, -2, -1, -1, -1, 0, 0, 2, 3, 4);
        assertEquals(expected, result);
    }
}

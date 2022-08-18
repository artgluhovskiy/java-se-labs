package org.art.java_core.algorithms.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.art.java_core.algorithms.utils.lists.ListNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * "Add Two Numbers" quiz solution from LeetCode.
 */
@Slf4j
public class AddTwoNumbers {

    public ListNode<Integer> addTwoNumbers(ListNode<Integer> l1, ListNode<Integer> l2) {
        ListNode<Integer> root = new ListNode<>(0);
        ListNode<Integer> currentNode = root;
        int sum;
        int carry = 0;

        while (l1 != null && l2 != null) {
            sum = l1.val + l2.val + carry;
            carry = sum > 9 ? 1 : 0;
            ListNode<Integer> newNode = new ListNode<>(sum % 10);
            currentNode.next = newNode;
            currentNode = newNode;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            sum = l1.val + carry;
            carry = sum > 9 ? 1 : 0;
            ListNode<Integer> newNode = new ListNode<>(sum % 10);
            currentNode.next = newNode;
            currentNode = newNode;
            l1 = l1.next;
        }

        while (l2 != null) {
            sum = l2.val + carry;
            carry = sum > 9 ? 1 : 0;
            ListNode<Integer> newNode = new ListNode<>(sum % 10);
            currentNode.next = newNode;
            currentNode = newNode;
            l2 = l2.next;
        }

        if (carry > 0) {
            ListNode<Integer> newNode = new ListNode<>(carry);
            currentNode.next = newNode;
        }

        return root.next;
    }

    @Test
    @DisplayName("342 + 465 = 807")
    void test1() {
        ListNode<Integer> l1 = ListNode.of(2, 4, 3);
        log.info("l1 linked list: " + l1);

        ListNode<Integer> l2 = ListNode.of(5, 6, 4);
        log.info("l2 linked list: " + l2);

        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode<Integer> result = addTwoNumbers.addTwoNumbers(l1, l2);

        assertEquals(ListNode.of(7, 0, 8), result);
    }

    @Test
    @DisplayName("99 + 1 = 100")
    void test2() {
        ListNode<Integer> l1 = ListNode.of(9, 9);
        log.info("l1 linked list: " + l1);

        ListNode<Integer> l2 = ListNode.of(1);
        log.info("l2 linked list: " + l2);

        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode<Integer> result = addTwoNumbers.addTwoNumbers(l1, l2);

        assertEquals(ListNode.of(0, 0, 1), result);
    }
}

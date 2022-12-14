package org.art.samples.core.algorithms.cci.linked_lists;

import org.art.samples.core.algorithms.utils.lists.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * "Palindrome" solution from "Cracking the Coding Interview".
 * "2-pointers approach" is used to find middle node.
 * "3-pointers approach" is used to reverse the linked list.
 */
public class IsPalindrome {

    public boolean isPalindrome(ListNode<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("list cannot be null");
        }
        if (list.next == null) {
            return true;
        }
        ListNode<Integer> middle = defineMiddle(list);
        ListNode<Integer> reversedHeadPart = reverseList(middle);
        while (reversedHeadPart != null) {
            if (!list.val.equals(reversedHeadPart.val)) {
                return false;
            }
            list = list.next;
            reversedHeadPart = reversedHeadPart.next;
        }
        return true;
    }

    private ListNode<Integer> defineMiddle(ListNode<Integer> list) {
        ListNode<Integer> oneStepPointer = list;
        ListNode<Integer> twoStepPointer = list.next;
        while (twoStepPointer.next != null) {
            oneStepPointer = oneStepPointer.next;
            twoStepPointer = twoStepPointer.next;
            if (twoStepPointer.next == null) {
                break;
            }
            twoStepPointer = twoStepPointer.next;
        }
        return oneStepPointer.next;
    }

    private ListNode<Integer> reverseList(ListNode<Integer> list) {
        ListNode<Integer> pointer1 = list;
        if (pointer1.next == null) {
            return pointer1;
        }
        ListNode<Integer> pointer2 = list.next;
        if (pointer2.next == null) {
            pointer2.next = pointer1;
            pointer1.next = null;
            return pointer2;
        }
        ListNode<Integer> pointer3 = list.next.next;
        pointer1.next = null;
        while (pointer3 != null) {
            pointer2.next = pointer1;
            pointer1 = pointer2;
            pointer2 = pointer3;
            pointer3 = pointer3.next;
        }
        pointer2.next = pointer1;
        return pointer2;
    }

    @Test
    void test0() {
        ListNode<Integer> input = ListNode.of(1, 2, 3, 3, 2, 1);
        boolean result = isPalindrome(input);
        assertTrue(result);
    }

    @Test
    void test1() {
        ListNode<Integer> input = ListNode.of(1, 2, 3, 4, 2, 1);
        boolean result = isPalindrome(input);
        assertFalse(result);
    }

    @Test
    void test2() {
        ListNode<Integer> input = ListNode.of(1, 2, 3, 4, 3, 2, 1);
        boolean result = isPalindrome(input);
        assertTrue(result);
    }
}

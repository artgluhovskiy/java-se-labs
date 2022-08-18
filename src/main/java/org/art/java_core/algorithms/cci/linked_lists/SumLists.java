package org.art.java_core.algorithms.cci.linked_lists;

import org.art.java_core.algorithms.utils.lists.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * "Sum Lists" solution from "Cracking the Coding Interview".
 */
public class SumLists {

    public ListNode<Integer> sumLists(ListNode<Integer> list1, ListNode<Integer> list2) {
        if (list1 == null || list2 == null) {
            throw new IllegalArgumentException("lists cannot be null");
        }
        int num1 = readNumberFromList(list1);
        int num2 = readNumberFromList(list2);
        int sum = num1 + num2;
        return writeNumberAsList(sum);
    }

    private int readNumberFromList(ListNode<Integer> list) {
        int num = 0;
        ListNode<Integer> pointer = list;
        int multiplier = 1;
        while (pointer != null) {
            num = num + pointer.val * multiplier;
            pointer = pointer.next;
            multiplier *= 10;
        }
        return num;
    }

    private ListNode<Integer> writeNumberAsList(int num) {
        ListNode<Integer> currentPointer = new ListNode<>(0, null);    //dummy node
        ListNode<Integer> result = currentPointer;
        int value = num;
        while (value != 0) {
            int remainder = value % 10;
            currentPointer.next = new ListNode<>(remainder, null);
            currentPointer = currentPointer.next;
            value = (value - remainder) / 10;
        }
        return result.next;
    }

    @Test
    void test0() {
        ListNode<Integer> list1 = ListNode.of(7, 1, 6);
        ListNode<Integer> list2 = ListNode.of(5, 9, 2);
        ListNode<Integer> result = sumLists(list1, list2);
        ListNode<Integer> expected = ListNode.of(2, 1, 9);
        assertEquals(expected, result);
    }
}

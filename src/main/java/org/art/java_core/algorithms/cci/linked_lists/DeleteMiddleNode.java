package org.art.java_core.algorithms.cci.linked_lists;

import org.art.java_core.algorithms.common.lists.ListNode;
import org.art.java_core.algorithms.common.lists.ListNodeUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * "Delete Middle Node" solution from "Cracking the Coding Interview".
 * Mutable variant.
 */
public class DeleteMiddleNode {

    void deleteMiddleNode(ListNode<Integer> target) {
        ListNode<Integer> currentPointer = target;
        ListNode<Integer> aheadPointer = target.next;    //no need for null check here according to the problem definition
        do {
            currentPointer.val = aheadPointer.val;
            aheadPointer = aheadPointer.next;
            currentPointer = currentPointer.next;
        } while (aheadPointer.next != null);

        currentPointer.val = aheadPointer.val;
        currentPointer.next = null;
    }

    @Test
    void test0() {
        ListNode<Integer> inputList = ListNode.of(1, 2, 3, 4, 5, 6, 7);
        ListNode<Integer> middleElement = ListNodeUtils.getKthElement(4, inputList);
        deleteMiddleNode(middleElement);
        ListNode<Integer> expected = ListNode.of(1, 2, 3, 5, 6, 7);
        assertEquals(expected, inputList);
    }
}

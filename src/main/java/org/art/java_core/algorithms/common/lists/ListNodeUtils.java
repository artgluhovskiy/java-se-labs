package org.art.java_core.algorithms.common.lists;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ListNodeUtils {

    public static <T> ListNode<T> getKthElement(int k, ListNode<T> head) {
        if (head == null) {
            throw new IllegalArgumentException("head cannot be null");
        }
        if (k == 0) {
            throw new IllegalArgumentException("k cannot be zero");
        }
        if (k == 1) {
            return head;
        }
        ListNode<T> result = head;
        for (int i = 0; i < k - 1; i++) {
            if (result.next == null) {
                throw new IllegalStateException("Cannot get the k-th element of the head. The head size is not enough");
            }
            result = result.next;
        }
        return result;
    }

    public static <T> int getLength(ListNode<T> head) {
        if (head == null) {
            throw new IllegalArgumentException("head cannot be null");
        }
        int counter = 0;
        ListNode<T> current = head;
        while (current != null) {
            counter++;
            current = current.next;
        }
        return counter;
    }
}

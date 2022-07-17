package org.art.java_core.algorithms.common;

/**
 * Helper class for coding problems related to the Singly Linked Lists.
 */
public class ListNode<T> {

    public T val;

    public ListNode<T> next;

    public ListNode(T x) {
        this.val = x;
    }

    public ListNode(T val, ListNode<T> next) {
        this.val = val;
        this.next = next;
    }

    public static <T> ListNode<T> of(T... values) {
        if (values == null || values.length == 0) {
            return null;
        }
        ListNode<T> list = new ListNode<>(values[0]);
        ListNode<T> resList = list;
        for (int i = 1; i < values.length; i++) {
            list.next = new ListNode<>(values[i]);
            list = list.next;
        }
        return resList;
    }

    public static <T> ListNode<T> getKthElement(int k, ListNode<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("list cannot be null");
        }
        if (k == 0) {
            throw new IllegalArgumentException("k cannot be zero");
        }
        if (k == 1) {
            return list;
        }
        ListNode<T> result = list;
        for (int i = 0; i < k - 1; i++) {
            if (result.next == null) {
                throw new IllegalStateException("Cannot get the k-th element of the list. The list size is not enough");
            }
            result = result.next;
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode<T> node = this;
        sb.append(node.val);
        while (node.next != null) {
            node = node.next;
            sb.append("->");
            sb.append(node.val);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (that == null) {
            return false;
        }
        if (!(that instanceof ListNode)) {
            return false;
        }
        ListNode<T> ptr1 = this;
        ListNode<T> ptr2 = (ListNode<T>) that;
        while (ptr1 != null && ptr2 != null) {
            if (ptr1.val != ptr2.val) {
                return false;
            }
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        return ptr1 == null && ptr2 == null;
    }
}

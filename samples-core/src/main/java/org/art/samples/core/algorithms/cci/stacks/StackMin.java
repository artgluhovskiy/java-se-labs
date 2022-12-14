package org.art.samples.core.algorithms.cci.stacks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * "Stack Min" solution from "Cracking the Coding Interview".
 */
public class StackMin {

    @Test
    void test0() {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(1);
        stack.push(5);
        assertSame(1, stack.min());
    }

    @Test
    void test1() {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        stack.push(2);
        stack.push(1);
        stack.push(5);
        assertSame(-1, stack.min());
    }

    @Test
    void test2() {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        stack.push(2);
        stack.push(-4);
        stack.push(5);
        assertSame(-4, stack.min());
        assertSame(5, stack.pop());
        assertSame(-4, stack.min());
        assertSame(-4, stack.pop());
        assertSame(-1, stack.min());
        assertSame(2, stack.pop());
        assertSame(-1, stack.min());
        stack.push(5);
        stack.push(-6);
        stack.push(4);
        assertSame(-6, stack.min());
    }
}

class Stack<T extends Comparable<T>> {

    private Node<T> top;

    void push(T elem) {
        if (elem == null) {
            throw new NullPointerException("elem cannot be null");
        }
        Node<T> newNode;
        if (top == null) {
            newNode = new Node<>(elem, null);
            newNode.min = newNode;
        } else {
            newNode = new Node<>(elem, top);
            Node<T> minNode;
            if (elem.compareTo(top.min.elem) < 0) {
                minNode = newNode;
            } else {
                minNode = top.min;
            }
            newNode.min = minNode;
        }
        top = newNode;
    }

    T pop() {
        if (top == null) {
            throw new IllegalStateException("Stack is empty");
        }
        Node<T> elem = top;
        top = top.next;
        return elem.elem;
    }

    T min() {
        if (top == null) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.min.elem;
    }

    private static class Node<T> {

        public Node(T elem, Node<T> next) {
            this.elem = elem;
            this.next = next;
        }

        T elem;

        Node<T> next;

        Node<T> min;
    }
}

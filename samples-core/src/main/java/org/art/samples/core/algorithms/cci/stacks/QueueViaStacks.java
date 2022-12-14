package org.art.samples.core.algorithms.cci.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * "Queue via Stacks" solution from "Cracking the Coding Interview".
 */
public class QueueViaStacks {

    @Test
    void test0() {
        MyQueue queue = new MyQueue(10);
        queue.enqueue(1);
        queue.enqueue(3);
        queue.enqueue(6);
        assertSame(1, queue.dequeue());
        queue.enqueue(2);
        assertSame(3, queue.dequeue());
        assertSame(6, queue.dequeue());
        assertSame(2, queue.dequeue());
        assertThrows(IllegalStateException.class, queue::dequeue);
    }
}

class MyQueue {

    private final Deque<Integer> stack1;

    private final Deque<Integer> stack2;

    private Deque<Integer> current;

    private final int capacity;

    private boolean dequeueMode;

    public MyQueue(int capacity) {
        this.stack1 = new ArrayDeque<>(capacity);
        this.stack2 = new ArrayDeque<>(capacity);
        this.current = stack1;
        this.capacity = capacity;
        this.dequeueMode = false;
    }

    public void enqueue(Integer val) {
        if (current.size() == capacity) {
            throw new IllegalStateException("Queue is full");
        }
        if (dequeueMode) {
            drenchCurrent();
            current = defineNonCurrentStack();
            dequeueMode = false;
        }
        current.push(val);
    }

    public Integer dequeue() {
        if (current.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        if (!dequeueMode) {
            drenchCurrent();
            current = defineNonCurrentStack();
            dequeueMode = true;
        }
        return current.pop();
    }

    private void drenchCurrent() {
        Deque<Integer> nonCurrentStack = defineNonCurrentStack();
        while (!current.isEmpty()) {
            Integer val = current.pop();
            nonCurrentStack.push(val);
        }
    }

    private Deque<Integer> defineNonCurrentStack() {
        if (current == stack1) {
            return stack2;
        } else {
            return stack1;
        }
    }
}

package main.java.org.art.samples.core.algorithms.cci.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * "Sort Stack" solution from "Cracking the Coding Interview".
 */
public class SortStack {

    void sort(Deque<Integer> stack) {
        Deque<Integer> bufferStack = new ArrayDeque<>(stack.size());
        while (!stack.isEmpty()) {
            Integer tmp = stack.pop();
            while (!bufferStack.isEmpty() && bufferStack.peek() > tmp) {
                stack.push(bufferStack.pop());
            }
            bufferStack.push(tmp);
        }

        while (!bufferStack.isEmpty()) {
            stack.push(bufferStack.pop());
        }
    }

    @Test
    void test() {
        Deque<Integer> stack = new ArrayDeque<>(5);
        stack.push(1);
        stack.push(4);
        stack.push(2);
        stack.push(3);
        stack.push(6);
        sort(stack);
        assertSame(1, stack.pop());
        assertSame(2, stack.pop());
        assertSame(3, stack.pop());
        assertSame(4, stack.pop());
        assertSame(6, stack.pop());
    }
}

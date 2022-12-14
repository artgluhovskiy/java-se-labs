package org.art.samples.core.algorithms.cci.stacks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * "Three In One Stack Implementation" solution from "Cracking the Coding Interview".
 */
public class ThreeStacksInOneArray<T> {

    private static final int INITIAL_CAPACITY = 4;

    private static final int UNDEFINED_INDEX = -1;

    private static final int OFFSET = 3;

    private static final int CAPACITY_MULTIPLIER = 2;

    private Object[] elementData;

    public ThreeStacksInOneArray() {
        elementData = new Object[INITIAL_CAPACITY];
    }

    private final IndexHolder top1Index = new IndexHolder();

    private final IndexHolder top2Index = new IndexHolder();

    private final IndexHolder top3Index = new IndexHolder();

    public void push1(T element) {
        push0(element, top1Index, 0);
    }

    public void push2(T element) {
        push0(element, top2Index, 1);
    }

    public void push3(T element) {
        push0(element, top3Index, 2);
    }

    private void push0(T element, IndexHolder topIndex, int initialTopIndex) {
        if (topIndex.getValue() == UNDEFINED_INDEX) {
            topIndex.setInitial(initialTopIndex);
            elementData[initialTopIndex] = element;
        } else {
            topIndex.increase();
            if (topIndex.getValue() > elementData.length - 1) {
                increaseCapacity();
            }
            elementData[topIndex.getValue()] = element;
        }
    }

    public T pop1() {
        return pop0(top1Index);
    }

    public T pop2() {
        return pop0(top2Index);
    }

    public T pop3() {
        return pop0(top3Index);
    }

    private T pop0(IndexHolder topIndex) {
        if (topIndex.getValue() == UNDEFINED_INDEX) {
            throw new IllegalStateException("Stack 1 is empty");
        }
        @SuppressWarnings("unchecked") T element = (T) elementData[topIndex.getValue()];
        topIndex.decrease();
        return element;
    }

    public T peek1() {
        return peek0(top1Index);
    }

    public T peek2() {
        return peek0(top2Index);
    }

    public T peek3() {
        return peek0(top3Index);
    }

    private T peek0(IndexHolder topIndex) {
        if (topIndex.getValue() == UNDEFINED_INDEX) {
            throw new IllegalStateException("Stack 1 is empty");
        }
        @SuppressWarnings("unchecked") T element = (T) elementData[topIndex.getValue()];
        return element;
    }

    public boolean isEmpty1() {
        return isEmpty0(top1Index);
    }

    public boolean isEmpty2() {
        return isEmpty0(top2Index);
    }

    public boolean isEmpty3() {
        return isEmpty0(top3Index);
    }

    private boolean isEmpty0(IndexHolder topIndex) {
        if (topIndex.getValue() == UNDEFINED_INDEX) {
            return true;
        } else {
            return false;
        }
    }

    private void increaseCapacity() {
        Object[] increasedElementData = new Object[elementData.length * CAPACITY_MULTIPLIER];
        System.arraycopy(elementData, 0, increasedElementData, 0, elementData.length);
        elementData = increasedElementData;
    }

    private static class IndexHolder {

        private int index = UNDEFINED_INDEX;

        void setInitial(int initialIndex) {
            index = initialIndex;
        }

        void increase() {
            if (index == UNDEFINED_INDEX) {
                throw new IllegalStateException("Initial index value should be set");
            }
            index += OFFSET;
        }

        void decrease() {
            if (index == UNDEFINED_INDEX) {
                throw new IllegalStateException("Index is already undefined");
            }
            index -= OFFSET;
            if (index < 0) {
                index = UNDEFINED_INDEX;
            }
        }

        int getValue() {
            return index;
        }
    }

    @Test
    void test() {
        ThreeStacksInOneArray<Integer> stacks = new ThreeStacksInOneArray<>();
        stacks.push1(1);
        stacks.push1(4);
        stacks.push1(7);
        stacks.push2(2);
        stacks.push2(5);
        stacks.push2(8);
        stacks.push3(3);
        stacks.push3(6);
        stacks.push3(9);
        assertSame(7, stacks.pop1());
        assertSame(8, stacks.pop2());
        assertSame(9, stacks.pop3());
        assertSame(4, stacks.peek1());
        assertSame(5, stacks.peek2());
        assertSame(6, stacks.peek3());
        assertSame(4, stacks.pop1());
        assertSame(5, stacks.pop2());
        assertSame(6, stacks.pop3());
        assertFalse(stacks.isEmpty1());
        assertFalse(stacks.isEmpty2());
        assertFalse(stacks.isEmpty3());
        assertSame(1, stacks.pop1());
        assertSame(2, stacks.pop2());
        assertSame(3, stacks.pop3());
        assertTrue(stacks.isEmpty1());
        assertTrue(stacks.isEmpty2());
        assertTrue(stacks.isEmpty3());
    }
}

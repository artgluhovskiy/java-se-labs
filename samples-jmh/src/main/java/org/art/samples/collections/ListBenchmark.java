package org.art.samples.collections;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@Fork(value = 2)
@Warmup(iterations = 1, time = 5)
@Measurement(iterations = 2, time = 5)
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ListBenchmark {

    private static final int SIZE = 3000;

    @State(Scope.Thread)
    public static class GlobalState {

        ArrayList<String> arrayList;

        ArrayDeque<String> arrayDeque;

        LinkedList<String> linkedList;

        String elemToAdd;

        @Setup(Level.Invocation)
        public void setupState() {
            arrayList = new ArrayList<>();
            arrayDeque = new ArrayDeque<>();
            linkedList = new LinkedList<>();

            for (int i = 0; i < SIZE; i++) {
                String val = UUID.randomUUID().toString();
                arrayList.add(val);
                arrayDeque.add(val);
                linkedList.add(val);
            }

            elemToAdd = UUID.randomUUID().toString();
        }

        @TearDown(value = Level.Invocation)
        public void tearDown() {
            arrayList = null;
            arrayDeque = null;
            linkedList = null;
            elemToAdd = null;
        }
    }

    @Benchmark
    public void add_ArrayList(GlobalState globalState, Blackhole blackhole) {
        boolean result = globalState.arrayList.add(globalState.elemToAdd);
        blackhole.consume(result);
    }

    @Benchmark
    public void add_ArrayDeque(GlobalState globalState, Blackhole blackhole) {
        boolean result = globalState.arrayDeque.add(globalState.elemToAdd);
        blackhole.consume(result);
    }

    @Benchmark
    public void add_LinkedList(GlobalState globalState, Blackhole blackhole) {
        boolean result = globalState.linkedList.add(globalState.elemToAdd);
        blackhole.consume(result);
    }

    @Benchmark
    public void addFirst_ArrayList(GlobalState globalState) {
        globalState.arrayList.add(0, globalState.elemToAdd);
    }

    @Benchmark
    public void addFirst_ArrayDeque(GlobalState globalState, Blackhole blackhole) {
        boolean result = globalState.arrayDeque.offerFirst(globalState.elemToAdd);
        blackhole.consume(result);
    }

    @Benchmark
    public void addFirst_LinkedList(GlobalState globalState, Blackhole blackhole) {
        boolean result = globalState.linkedList.offerFirst(globalState.elemToAdd);
        blackhole.consume(result);
    }

    @Benchmark
    public void addLast_ArrayList(GlobalState globalState) {
        globalState.arrayList.add(SIZE - 1, globalState.elemToAdd);
    }

    @Benchmark
    public void addLast_ArrayDeque(GlobalState globalState, Blackhole blackhole) {
        boolean result = globalState.arrayDeque.offerLast(globalState.elemToAdd);
        blackhole.consume(result);
    }

    @Benchmark
    public void addLast_LinkedList(GlobalState globalState, Blackhole blackhole) {
        boolean result = globalState.linkedList.offerLast(globalState.elemToAdd);
        blackhole.consume(result);
    }

    @Benchmark
    public void setInTheMiddle_ArrayList(GlobalState globalState, Blackhole blackhole) {
        String result = globalState.arrayList.set(SIZE / 2, globalState.elemToAdd);
        blackhole.consume(result);
    }

    @Benchmark
    public void setInTheMiddle_LinkedList(GlobalState globalState, Blackhole blackhole) {
        String result = globalState.linkedList.set(SIZE / 2, globalState.elemToAdd);
        blackhole.consume(result);
    }

    @Benchmark
    public void getFirst_ArrayList(GlobalState globalState, Blackhole blackhole) {
        String result = globalState.arrayList.get(0);
        blackhole.consume(result);
    }

    @Benchmark
    public void getFirst_ArrayDeque(GlobalState globalState, Blackhole blackhole) {
        String result = globalState.arrayDeque.getFirst();
        blackhole.consume(result);
    }

    @Benchmark
    public void getFirst_LinkedList(GlobalState globalState, Blackhole blackhole) {
        String result = globalState.linkedList.getFirst();
        blackhole.consume(result);
    }

    @Benchmark
    public void getAndRemoveFirst_ArrayList(GlobalState globalState, Blackhole blackhole) {
        String result = globalState.arrayList.remove(0);
        blackhole.consume(result);
    }

    @Benchmark
    public void getAndRemoveFirst_ArrayDeque(GlobalState globalState, Blackhole blackhole) {
        String result = globalState.arrayDeque.pollFirst();
        blackhole.consume(result);
    }

    @Benchmark
    public void getAndRemoveFirst_LinkedList(GlobalState globalState, Blackhole blackhole) {
        String result = globalState.linkedList.pollFirst();
        blackhole.consume(result);
    }

    @Benchmark
    public void getAndRemoveLast_ArrayList(GlobalState globalState, Blackhole blackhole) {
        String result = globalState.arrayList.remove(SIZE - 1);
        blackhole.consume(result);
    }

    @Benchmark
    public void getAndRemoveLast_ArrayDeque(GlobalState globalState, Blackhole blackhole) {
        String result = globalState.arrayDeque.pollLast();
        blackhole.consume(result);
    }

    @Benchmark
    public void getAndRemoveLast_LinkedList(GlobalState globalState, Blackhole blackhole) {
        String result = globalState.linkedList.pollLast();
        blackhole.consume(result);
    }

}

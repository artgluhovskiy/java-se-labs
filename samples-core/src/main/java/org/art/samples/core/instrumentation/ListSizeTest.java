package org.art.samples.core.instrumentation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;

import org.openjdk.jol.info.GraphLayout;

public class ListSizeTest {

    public static final int SIZE = 3000;

    public static void main(String[] args) {

        ArrayList<Integer> arrayList = new ArrayList<>(SIZE);
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>(SIZE);
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < SIZE; i++) {
            arrayList.add(i);
            arrayDeque.add(i);
            linkedList.add(i);
        }

        // Result: 60040 B
        System.out.println("ArrayList size ------------");
        System.out.println(GraphLayout.parseInstance(arrayList).toFootprint());

        // Result: 60048 B
        System.out.println("ArrayDeque size ------------");
        System.out.println(GraphLayout.parseInstance(arrayDeque).toFootprint());

        // Result: 120032 B
        System.out.println("LinkedList size ------------");
        System.out.println(GraphLayout.parseInstance(linkedList).toFootprint());
    }

}

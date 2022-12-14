package main.java.org.art.samples.core.collections;

import java.util.function.Predicate;

/**
 * Simple custom predicate implementation.
 */
public class CustomPredicate implements Predicate<Integer> {

    public boolean test(Integer i) {
        return i <= 5;
    }
}

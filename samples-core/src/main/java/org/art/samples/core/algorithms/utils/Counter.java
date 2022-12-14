package main.java.org.art.samples.core.algorithms.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Counter {

    private int count;

    public void inc() {
        count++;
    }

    public void dec() {
        count--;
    }
}

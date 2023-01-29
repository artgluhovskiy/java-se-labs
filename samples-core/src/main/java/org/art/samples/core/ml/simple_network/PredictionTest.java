package org.art.samples.core.ml.simple_network;

import java.util.ArrayList;
import java.util.List;

public class PredictionTest {

    public static void main(String[] args) {
        Network network500 = new Network(500, 2.0);
        Network network1000 = new Network(1_000, 2.0);

        // Training
        // Data: value 1 - height, value 2 - weight
        // Output: 1 - male, 0 - female
        List<List<Integer>> data = new ArrayList<>();
        data.add(List.of(115, 66));
        data.add(List.of(175, 78));
        data.add(List.of(205, 72));
        data.add(List.of(120, 67));
        List<Double> answers = List.of(0.0, 1.0, 1.0, 0.0);

        network500.train(data, answers);
        network1000.train(data, answers);

        // Prediction
        System.out.printf("female', 130, 66: network500: %.10f | network1000: %.10f%n", network500.predict(130, 66), network1000.predict(130, 66));
        System.out.printf("  male, 167, 73: network500: %.10f | network1000: %.10f%n", network500.predict(167, 73), network1000.predict(167, 73));
        System.out.printf("female, 105, 67: network500: %.10f | network1000: %.10f%n", network500.predict(105, 67), network1000.predict(105, 67));
        System.out.printf("female, 120, 72: network500: %.10f | network1000: %.10f%n", network500.predict(120, 72), network1000.predict(120, 72));
        System.out.printf("  male, 143, 67: network500: %.10f | network1000: %.10f%n", network500.predict(143, 67), network1000.predict(143, 67));
    }
}

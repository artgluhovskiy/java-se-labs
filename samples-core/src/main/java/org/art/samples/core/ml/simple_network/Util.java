package org.art.samples.core.ml.simple_network;

import java.util.List;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Util {

    public static double sigmoid(double in) {
        return 1 / (1 + Math.exp(-in));
    }

    public static double meanSquareLoss(List<Double> correctAnswers, List<Double> predictedAnswers) {
        double sumSquare = 0;
        for (int i = 0; i < correctAnswers.size(); i++) {
            double error = correctAnswers.get(i) - predictedAnswers.get(i);
            sumSquare += (error * error);
        }
        return sumSquare / (correctAnswers.size());
    }
}

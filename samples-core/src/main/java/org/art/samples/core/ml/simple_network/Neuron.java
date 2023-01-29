package org.art.samples.core.ml.simple_network;

import java.util.concurrent.ThreadLocalRandom;

public class Neuron {

    private static final ThreadLocalRandom RND = ThreadLocalRandom.current();

    private double oldBias = RND.nextDouble(-1, 1);

    private double oldWeight1 = RND.nextDouble(-1, 1);

    private double oldWeight2 = RND.nextDouble(-1, 1);

    private double bias = RND.nextDouble(-1, 1);

    private double weight1 = RND.nextDouble(-1, 1);

    private double weight2 = RND.nextDouble(-1, 1);

    public double compute(double input1, double input2) {
        double preActivation = (this.weight1 * input1) + (this.weight2 * input2) + this.bias;
        return Util.sigmoid(preActivation);
    }

    public void mutate(Double learnFactor) {
        int propertyToChange = RND.nextInt(0, 3);
        double changeFactor = (learnFactor == null) ? RND.nextDouble(-1, 1) : (learnFactor * RND.nextDouble(-1, 1));
        if (propertyToChange == 0) {
            this.bias += changeFactor;
        } else if (propertyToChange == 1) {
            this.weight1 += changeFactor;
        } else {
            this.weight2 += changeFactor;
        }
    }

    public void forget() {
        bias = oldBias;
        weight1 = oldWeight1;
        weight2 = oldWeight2;
    }

    public void remember() {
        oldBias = bias;
        oldWeight1 = weight1;
        oldWeight2 = weight2;
    }
}

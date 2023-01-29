package org.art.samples.core.ml.simple_network;

import java.util.ArrayList;
import java.util.List;

public class Network {

    private final int epochs;

    private final Double learnFactor;

    private final List<Neuron> neurons = List.of(
        new Neuron(), new Neuron(), new Neuron(),           // input nodes
        new Neuron(), new Neuron(),                         // hidden nodes
        new Neuron()                                        // output node
    );

    public Network(int epochs, Double learnFactor) {
        this.epochs = epochs;
        this.learnFactor = learnFactor;
    }

    public double predict(Integer input1, Integer input2) {
        return neurons.get(5).compute(
            neurons.get(4).compute(
                neurons.get(2).compute(input1, input2),
                neurons.get(1).compute(input1, input2)
            ),
            neurons.get(3).compute(
                neurons.get(1).compute(input1, input2),
                neurons.get(0).compute(input1, input2)
            )
        );
    }

    public void train(List<List<Integer>> data, List<Double> answers) {
        Double bestEpochLoss = null;
        for (int epoch = 0; epoch < epochs; epoch++) {
            // adapt neuron
            Neuron epochNeuron = neurons.get(epoch % neurons.size());
            epochNeuron.mutate(this.learnFactor);

            List<Double> predictions = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                predictions.add(i, predict(data.get(i).get(0), data.get(i).get(1)));
            }
            double thisEpochLoss = Util.meanSquareLoss(answers, predictions);

            if (epoch % 10 == 0) {
                System.out.printf("Epoch: %s | bestEpochLoss: %.15f | thisEpochLoss: %.15f%n",
                    epoch, bestEpochLoss, thisEpochLoss);
            }

            if (bestEpochLoss == null) {
                bestEpochLoss = thisEpochLoss;
                epochNeuron.remember();
            } else {
                if (thisEpochLoss < bestEpochLoss) {
                    bestEpochLoss = thisEpochLoss;
                    epochNeuron.remember();
                } else {
                    epochNeuron.forget();
                }
            }
        }

    }
}

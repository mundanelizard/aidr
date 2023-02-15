package org.example;

import java.util.Arrays;
import static org.example.matrix.*;

public class SVM {
    final private double LEARNING_RATE = 0.001;
    final private double LAMBDA_PARAM = 0.01;
    final private double N_ITERATIONS = 1000;
    private double[] weights;
    private double bias;

    public void train(int[][] dataset, int[] labels) {
        int nFeatures = dataset[0].length;

        weights = new double[nFeatures];
        Arrays.fill(weights, 0);
        bias = 0;

        for(int i = 0; i < N_ITERATIONS; i++) {
            _train(dataset, labels);
        }
    }

    private void _train(int[][] dataset, int[] labels) {
        if (weights == null) {
            throw new IllegalCallerException("weights are to be set before call this method.");
        }

        for(int j = 0; j < dataset.length; j++) {
            int[] feature = dataset[j];

            boolean condition = labels[j] * (dot(feature, weights) - bias) >= 1;

            if (condition) {
                weights = minus(weights, mult(LEARNING_RATE, mult(2 * LAMBDA_PARAM, weights)));
            } else {
                weights = minus(weights, mult(LEARNING_RATE, minus(mult(2 * LAMBDA_PARAM, weights), mult(labels[j], feature))));
                bias -= LEARNING_RATE * labels[j];
            }
        }
    }

    public int predict(int[] feature) {
        return sign((int) (dot(feature, weights) - bias));
    }

}

package org.example;

import java.util.Arrays;
import static org.example.matrix.*;

public class SVM {
    final private double LEARNING_RATE = 0.001;
    final private double LAMBDA_PARAM = 0.01;
    final private double N_ITERATIONS = 1000;
    private double[] weights;
    /**
     * This is the line constant (usually denoted as k or c), but I decided to call it bias.
     */
    private double bias;

    /**
     * Trains the model using the feature and the labels.
     * @param dataset an array of features
     * @param labels an array of labels
     */
    public void train(int[][] dataset, int[] labels) {
        int nFeatures = dataset[0].length;

        // initialising the weights to zero
        weights = new double[nFeatures];
        Arrays.fill(weights, 0);

        // initialising the line constant to zero
        bias = 0;

        for(int i = 0; i < N_ITERATIONS; i++) {
            _train(dataset, labels);
        }
    }

    /**
     * Performs the fit and the drawing of the hyperplane.
     * @param dataset an array of features
     * @param labels an array of labels
     */
    private void _train(int[][] dataset, int[] labels) {
        if (weights == null) {
            throw new IllegalCallerException("weights are to be set before call this method.");
        }

        for(int j = 0; j < dataset.length; j++) {
            int[] feature = dataset[j];

            boolean condition = labels[j] * (dot(feature, weights) - bias) >= 1;

            if (condition) {
                // updating using the formula => w - (lr * 2 * lp * w)
                weights = minus(weights, mult(LEARNING_RATE, mult(2 * LAMBDA_PARAM, weights)));
            } else {
                // updating using the formula => w - (lr * (2 * lp * w - xi.yi))
                weights = minus(weights, mult(LEARNING_RATE, minus(mult(2 * LAMBDA_PARAM, weights), mult(labels[j], feature))));
                // updating using the formula => c - lr * yi
                bias -= LEARNING_RATE * labels[j];
            }
        }
    }

    /**
     * Predicts if a feature is on the left or right side on the hyperplane.
     * @param feature data feature
     * @return -1 or 1
     */
    public int predict(int[] feature) {
        return sign((int) (dot(feature, weights) - bias));
    }

}

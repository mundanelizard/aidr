package org.example;

import java.util.Arrays;

public class KNN implements Classifier {
    private int[][] dataset;
    private int[] label;

    @Override
    public int predict(int[] test) {
        if (dataset == null || dataset.length == 0) {
            throw new IllegalStateException("Call KNN.train with dataset before calling KNN.predict.");
        }

        var distance = calculateDistance(test);
        return getSortedClosest(distance);
    }


    private int getSortedClosest(double[] distance) {
        double closestDistance = Double.MAX_VALUE;
        var closestIndex = 0;

        for (int i = 0; i < distance.length; i++) {
            if (distance[i] > closestDistance)
                continue;

            closestDistance = distance[i];
            closestIndex = i;
        }

        return label[closestIndex];
    }

    private double[] calculateDistance(int[] test) {
        double[] distance = new double[dataset.length];

        for (int i = 0; i < dataset.length; i++) {

            int[] squaredDiffVector = new int[test.length];

            for (int j = 0; j < test.length; j++) {
                var diff = dataset[i][j] - test[j];
                squaredDiffVector[j] = diff * diff;
            }

            distance[i] = Math.sqrt(sum(squaredDiffVector));
        }

        return distance;
    }

    private int sum(int[] data) {
        return Arrays.stream(data).reduce(0, Integer::sum);
    }

    @Override
    public void train(int[][] dataset, int[] label) {
        this.dataset = dataset;
        this.label = label;
    }
}

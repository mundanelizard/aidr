package org.example;

import static org.example.matrix.sum;

public class KNN implements Classifier {
    private int[][] dataset;
    private int[] label;

    /**
     * Predicts the digit using the features.
     * @param features an array of integer values.
     * @return a value between 0 and 9.
     */
    @Override
    public int predict(int[] features) {
        if (dataset == null || dataset.length == 0) {
            throw new IllegalStateException("Call KNN.train with dataset before calling KNN.predict.");
        }

        var distance = calculateDistance(features);
        return getSortedClosest(distance);
    }


    /**
     * Sorts the distance based on the closest and return the indexes.
     * @param distance the distance between the testFeature and train data
     * @return the indices of the closest distances
     */
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

    /**
     * Sorts the distance based on the closest and return the indexes.
     * @param features an array of integers
     * @return an array of the distances between features and train set.
     */
    private double[] calculateDistance(int[] features) {
        double[] distance = new double[dataset.length];

        for (int i = 0; i < dataset.length; i++) {

            int[] squaredDiffVector = new int[features.length];

            for (int j = 0; j < features.length; j++) {
                var diff = dataset[i][j] - features[j];
                squaredDiffVector[j] = diff * diff;
            }

            distance[i] = Math.sqrt(sum(squaredDiffVector));
        }

        return distance;
    }

    /**
     * Simulates training by setting the dataset and labels.
     * @param dataset an array of features
     * @param label labels for each feature
     */
    @Override
    public void train(int[][] dataset, int[] label) {
        this.dataset = dataset;
        this.label = label;
    }
}

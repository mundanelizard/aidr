package org.example;

public interface Classifier {
    /**
     * Trains the model based on the given dataset and labels.
     * @param dataset an array of features
     * @param label labels for each feature
     */
    void train(int[][] dataset, int [] label);

    /**
     * Predicts the number based on the features
     * @param test features which is an array of integer
     * @return a predicted number
     */
    int predict(int[] test);
}

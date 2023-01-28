package org.example;

public interface Classifier {
    boolean VERBOSE = true;
    // well you're up all night -- and your neighbours don't know.

    void train(int[][] dataset, int [] label);
    int predict(int[] test);
}

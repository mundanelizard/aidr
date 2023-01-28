package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        var knn = new KNN();
        var doubleFoldAccuracy = calculateDoubleFoldAccuracy(knn);
        System.out.printf("Double Fold Accuracy: %.6f%%\n", doubleFoldAccuracy);
        var singleAccuracy = calculateSingleAccuracy(knn);
        System.out.printf("Single Fold Accuracy: %.6f%%\n", singleAccuracy);
    }

    public static double calculateSingleAccuracy(Classifier classifier) throws Exception {
        var digits = Reader.readInput("train.csv");

        var features = digits.features();
        var labels = digits.labels();

        var output = test(labels, features, classifier);

        return (output[1] / (double) output[0]) * 100;
    }


    public static double calculateDoubleFoldAccuracy(Classifier classifier) throws Exception {
        var digits = Reader.readInput("test.csv");

        var features = digits.features();
        var labels = digits.labels();

        classifier.train(features, labels);

        var output = test(labels, features, classifier);
        var total = output[0];
        var correct = output[1];

        digits = Reader.readInput("train.csv");

        features = digits.features();
        labels = digits.labels();

        output = test(labels, features, classifier);

        total += output[0];
        correct += output[1];

        return (correct / (double) total) * 100;
    }

    static int[] test(int[] labels, int[][] features, Classifier classifier) {
        var total = labels.length;
        var correct = 0;

        for (var index = 0; index < features.length; index++) {
            var expected = labels[index];
            var predicted = classifier.predict(features[index]);

            if (expected == predicted) {
                correct += 1;
            }
        }

        return new int[] {total, correct};
    }
}
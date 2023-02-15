package org.example;

public class Main {
    static final String TRAIN = "train.csv";
    static final String TEST = "test.csv";

    public static void main(String[] args) throws Exception {
        var knn = new KNN();
        train(knn);
        System.out.println("Euclidean Distance");
        System.out.printf("Double Fold Accuracy: %.6f%%\n", calculateDoubleFoldAccuracy(knn));
        System.out.printf("Accuracy: %.6f%%\n", calculateSingleAccuracy(knn));

        var mcsvm = new MCSVM();
        train(mcsvm);
        System.out.println("\n\nSupport Vector Machines ");
        System.out.printf("Double Fold Accuracy: %.6f%%\n", calculateDoubleFoldAccuracy(mcsvm));
        System.out.printf("Accuracy: %.6f%%\n", calculateSingleAccuracy(mcsvm));
    }

    public static void train(Classifier classifier) throws Exception {
        var digits = Reader.readInput(TRAIN);

        var features = digits.features();
        var labels = digits.labels();

        classifier.train(features, labels);
    }

    public static double calculateSingleAccuracy(Classifier classifier) throws Exception {
        var digits = Reader.readInput(TEST);

        var features = digits.features();
        var labels = digits.labels();

        var output = test(labels, features, classifier);

        return (output[1] / (double) output[0]) * 100;
    }


    public static double calculateDoubleFoldAccuracy(Classifier classifier) throws Exception {
        var train = Reader.readInput(TRAIN);
        var output = test(train.labels(), train.features(), classifier);
        var total = output[0];
        var correct = output[1];

        var test = Reader.readInput(TEST);
        output = test(test.labels(), test.features(), classifier);
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
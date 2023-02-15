package org.example;
import static org.example.matrix.keep;

public class MCSVM implements Classifier {
    /**
     * Stores all the SVM classifiers need for digits recognition.
     */
    private final SVM[] svms = new SVM[9];

    /**
     * Trains the support vector machines on the dataset and the labels.
     * @param dataset an array of features
     * @param labels labels for each feature
     */
    @Override
    public void train(int[][] dataset, int[] labels) {
        for(int i = 0; i < svms.length; i++) {
            svms[i] = new SVM();
            svms[i].train(dataset, keep(labels, i));
        }
    }

    /**
     * Predicts the digit using the features.
     * @param features an array of integer values.
     * @return a value between 0 and 9.
     */
    @Override
    public int predict(int[] features) {
        for (int i = 0; i < svms.length; i++) {
            var prediction = svms[i].predict(features);
            if (prediction > 0) {
                return i;
            }
        }

        return 9;
    }
}

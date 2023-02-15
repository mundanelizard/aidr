package org.example;
import static org.example.matrix.keep;

public class MCSVM implements Classifier {
    SVM[] svms = new SVM[9];
    @Override
    public void train(int[][] dataset, int[] labels) {
        for(int i = 0; i < svms.length; i++) {
            svms[i] = new SVM();
            svms[i].train(dataset, keep(labels, i));
        }
    }

    @Override
    public int predict(int[] test) {
        for (int i = 0; i < svms.length; i++) {
            var prediction = svms[i].predict(test);
            if (prediction > 0) {
                return i;
            }
        }

        return 9;
    }
}

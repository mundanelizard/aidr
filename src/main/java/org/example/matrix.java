package org.example;

public class matrix {
    static double dot(int[] x, double[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("Expected x and y to be of equal length to perform dot product");
        }

        double sum = 0;

        for (int i = 0; i < x.length; i++) {
            sum += x[i] * y[i];
        }

        return sum;
    }

    static double[] mult(double x, double[] y) {
        var result = new double[y.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = y[i] * x;
        }

        return result;
    }

    static double[] mult(double x, int[] y) {
        var result = new double[y.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = y[i] * x;
        }

        return result;
    }

    static double[] minus(double[] x, double[] y) {
            if (x.length != y.length) {
                throw new IllegalArgumentException("Expected x and y to be of equal length to perform dot product");
            }

        var result = new double[x.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = x[i] - y[i];
        }

        return result;
    }

    static public int sign(int n) {
        if (n <= 0)
            return -1;
        return 1;
    }

    static public boolean[] equals(int[] x, int[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("Expected x and y to be of equal length to perform dot product");
        }

        var results = new boolean[x.length];

        for (int i = 0; i < results.length; i++) {
            results[i] = x[i] == y[i];
        }

        return results;
    }

    static int[] keep(int[] x, int n) {
        int[] results = new int[x.length];

        for (int i = 0; i < results.length; i++) {
            results[i] = x[i] == n ? 1 : -1;
        }

        return results;
    }
}

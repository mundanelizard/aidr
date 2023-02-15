package org.example;

import java.util.Arrays;

/**
 * This is a matrix and vector helper class.
 */
public class matrix {
    /**
     * Perform matrix dot product on the input vector x and y.
     * @param x first vector
     * @param y left vector
     * @return a scalar value.
     */
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

    /**
     * Multiplies a vector y with scalar x.
     * @param x a scalar
     * @param y a vector
     * @return a new vector with the result.
     */
    static double[] mult(double x, double[] y) {
        var result = new double[y.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = y[i] * x;
        }

        return result;
    }

    /**
     * Multiplies a vector y with scalar x.
     * @param x a scalar
     * @param y a vector
     * @return a new vector with the result.
     */
    static double[] mult(double x, int[] y) {
        var result = new double[y.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = y[i] * x;
        }

        return result;
    }

    /**
     * Subtracts vector y from vector x.
     * @param x first vector
     * @param y second vector
     * @return a new array with the result.
     */
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

    /**
     * Converts an integer to -1 or 1 depending on its value.
     * 1 if the integer is greater than zero and -1 otherwise.
     * @param n an integer
     * @return -1 or 1.
     */
    static public int sign(int n) {
        if (n <= 0)
            return -1;
        return 1;
    }

    /**
     * Compares the value of both arrays and returns true for matches and false otherwise.
     * @param x first array to compare
     * @param y second array to compare
     * @return a boolean array of the results.
     */
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

    /**
     * It sets the integer that matches n to 1, and the ones that doesn't match to -1.
     * @param x A vector or a 1D array contains integers to match.
     * @param n An integer to be matched in the vector.
     * @return an array of integers with values -1 or 1.
     */
    static int[] keep(int[] x, int n) {
        int[] results = new int[x.length];

        for (int i = 0; i < results.length; i++) {
            results[i] = x[i] == n ? 1 : -1;
        }

        return results;
    }

    /**
     * Sums the values of an array.
     * @param data an array of integers
     * @return a sum
     */
    static int sum(int[] data) {
        return Arrays.stream(data).reduce(0, Integer::sum);
    }
}

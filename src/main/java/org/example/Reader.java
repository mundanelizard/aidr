package org.example;

import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Reader {
    /**
     * Reads and parses inputs from a file.
     * @param name file name
     * @return a features, labels pair.
     * @throws Exception if file doesn't exist.
     */
    public static Input readInput(String name) throws Exception {
        String[] lines = Files.readString(Paths.get(name)).trim().split("\n");

        int[][] features = new int[lines.length][lines[0].split(",").length - 1];
        int[] labels = new int[lines.length];

        for (int i = 0; i < lines.length; i++) {
            var line = lines[i];
            var rawFeatures = line.split(",");
            features[i] = convertStringArrayToIntArray(rawFeatures, rawFeatures.length - 1);
            var rawLabel = rawFeatures[rawFeatures.length - 1];
            labels[i] = Integer.parseInt(rawLabel.trim());
        }

        return new Input(features, labels);
    }

    /**
     * Converts strings array to int array.
     * @param rawFeatures string array of features.
     * @param length expected length of features.
     * @return a new array with the features
     * @throws Exception if the length doesn't match the feature length
     */
    static int[] convertStringArrayToIntArray(String[] rawFeatures, int length) throws Exception {
        if (length > rawFeatures.length) {
            throw new Exception("Invalid argument length.");
        }

        int[] features = new int[length];

        for (var i = 0; i < length; i++) {
            features[i] = Integer.parseInt(rawFeatures[i]);
        }

        return features;
    }

    record Input(int[][] features, int[] labels) {}
}

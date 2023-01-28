package org.example;

import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Reader {
    public static Input readInput(String name) throws Exception {
        String[] lines = Files.readString(Paths.get(name)).trim().split("\n");

        int[][] features = new int[lines.length][lines[0].split(",").length - 1];
        int[] labels = new int[lines.length];

        for (int i = 0; i < lines.length; i++) {
            var line = lines[i];
            var rawFeatures = line.split(",");
            var f = convertStringArrayToIntArray(rawFeatures, rawFeatures.length - 1);
            features[i] = f;
            var rawLabel = rawFeatures[rawFeatures.length - 1];
            labels[i] = Integer.parseInt(rawLabel.trim());
        }

        return new Input(features, labels);
    }

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

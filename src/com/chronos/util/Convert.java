package com.chronos.util;

import java.util.ArrayList;
import java.util.List;

public class Convert {
    public static float[] matrixToArray(List<float[][]> input) {
        int length = input.size();
        int rows = input.get(0).length;
        int cols = input.get(0)[0].length;

        float[] vector = new float[length * rows * cols];

        int i = 0;
        for (float[][] floats : input) {
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    vector[i] = floats[r][c];
                    i++;
                }
            }
        }

        return vector;
    }

    public static List<float[][]> arrayToMatrix(float[] input, int length, int rows, int cols) {
        List<float[][]> out = new ArrayList<>();

        int i = 0;
        for (int l = 0; l < length; l++) {
            float[][] matrix = new float[rows][cols];
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    matrix[r][c] = input[i];
                    i++;
                }
            }
            out.add(matrix);
        }

        return out;
    }

    public static float[] intArrayToFloatArray(int[] input) {
        float[] vector = new float[input.length];
        for (int i = 0; i < input.length; i++) {
            vector[i] = (float)input[i];
        }
        return vector;
    }
}

package com.ubs.sudoku.converter;

import java.util.Arrays;

/**
 * This class tranforms the matrix of strings
 * (read from the input file) to an int matrix,
 * replacing the empty space with 0 and leaving
 * the rest of numbers as they were
 */
public class SudokuConverter {

    private final String SPACES_REGEX = "\\s{2,}";
    private final String EMPTY_REGEX = "";

    public int[][] toIntMatrix(String[][] matrix) {
        return Arrays.stream(matrix)
                .map(this::toIntArray)
                .toArray(int[][]::new);
    }

    private int[] toIntArray(String[] matrix) {
        return Arrays.stream(matrix)
                .map(s -> s.trim().replaceAll(SPACES_REGEX, EMPTY_REGEX))
                .mapToInt(this::toInt)
                .toArray();
    }

    private int toInt(String s) {
        return s.isEmpty() ? 0 : Integer.parseInt(s);
    }
}

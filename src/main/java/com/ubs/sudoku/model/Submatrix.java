package com.ubs.sudoku.model;

import java.util.ArrayList;

public class Submatrix {

    private final ArrayList<Integer> submatrix;

    public Submatrix (int sudokuSize) {
        submatrix = new ArrayList<>(sudokuSize);
    }

    public ArrayList<Integer> getSubmatrix() {
        return submatrix;
    }
}

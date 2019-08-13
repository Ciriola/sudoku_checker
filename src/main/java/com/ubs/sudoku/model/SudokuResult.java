package com.ubs.sudoku.model;

public enum SudokuResult {
    VALID(0),
    INVALID(1);

    private final int code;

    SudokuResult(int code) {
     this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}

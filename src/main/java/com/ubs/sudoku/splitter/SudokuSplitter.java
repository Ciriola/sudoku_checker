package com.ubs.sudoku.splitter;

import com.ubs.sudoku.model.Submatrix;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is creating all the submatrices
 * of a sudoku of a specific size
 * (e.g. 9x9 matrix -> 9 submatrix of size 9)
 *
 *  a a a | b b b | c c c
 *  a a a | b b b | c c c
 *  a a a | b b b | c c c
 *  ---------------------
 *  d d d | e e e | f f f
 *  d d d | e e e | f f f
 *  d d d | e e e | f f f
 *  ---------------------
 *  g g g | h h h | i i i
 *  g g g | h h h | i i i
 *  g g g | h h h | i i i
 *
 */
public class SudokuSplitter {

    private final int sudokuSize;
    private final int blockSize;
    private final int[][] matrix;

    public SudokuSplitter(int[][] matrix) {
        this.sudokuSize = matrix.length;
        this.blockSize = (int) Math.sqrt(sudokuSize);
        this.matrix = matrix;
    }

    public List<Submatrix> createSubmatrices() {

        List<Submatrix> submatrices = new ArrayList<>(sudokuSize);
        if(sudokuSize == 0) {
            return submatrices;
        }
        for(int i = 0; i <= sudokuSize - blockSize; i = i + blockSize) {
            for(int j = 0; j <= sudokuSize - blockSize; j = j + blockSize) {
                submatrices.add(createSubmatrix(matrix, i, j));
            }
        }
        return submatrices;
    }

    private Submatrix createSubmatrix(int[][] matrix, int row, int col) {

        Submatrix submatrix = new Submatrix(sudokuSize);

        for(int i = row; i < blockSize + row; i++) {
            for(int j = col; j < blockSize + col; j++) {
                submatrix.getSubmatrix().add(matrix[i][j]);
            }
        }

        return submatrix;
    }
}

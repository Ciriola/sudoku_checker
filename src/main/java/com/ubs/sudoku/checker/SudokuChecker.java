package com.ubs.sudoku.checker;

import com.ubs.sudoku.exception.InvalidSudokuException;
import com.ubs.sudoku.model.Submatrix;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuChecker {

  private final int sudokuSize;

  private final int[][] matrix;
  private final List<Submatrix> submatrices;

  public SudokuChecker(int[][] matrix, List<Submatrix> submatrices) {
    this.matrix = matrix;
    this.sudokuSize = matrix.length;
    this.submatrices = submatrices;
  }

  public void validateSudoku() throws InvalidSudokuException {
    validateRows();
    validateColumns();
    validateSubmatrices();
  }

  private void validateRows() throws InvalidSudokuException {
    for (int i = 0; i < sudokuSize; i++) {
      if (!validateDimension(matrix[i])) {
        throw new InvalidSudokuException(
            "The row at the index " + i + " is not valid: " + Arrays.toString(matrix[i]));
      }
    }
  }

  private void validateColumns() throws InvalidSudokuException {
    for (int i = 0; i < sudokuSize; i++) {
      int[] column = extractColumn(i);
      if (!validateDimension(column)) {
        throw new InvalidSudokuException(
            "The column at the index " + i + " is not valid: " + Arrays.toString(column));
      }
    }
  }

  private void validateSubmatrices() throws InvalidSudokuException {
    for (int i = 0; i < sudokuSize; i++) {
      int[] submatrix = extractSubmatrix(i);
      if (!validateDimension(submatrix)) {
        throw new InvalidSudokuException(
            "The submatrix " + i + " is not valid: " + Arrays.toString(submatrix));
      }
    }
  }

  private int[] extractColumn(int colIndex) {
    return Arrays.stream(matrix).mapToInt(row -> row[colIndex]).toArray();
  }

  private int[] extractSubmatrix(int submatrixIndex) {
    return submatrices.get(submatrixIndex).getSubmatrix().stream().mapToInt(i -> i).toArray();
  }

  /**
   * Dimension is generic, it could be a row array,
   * a column array, or a submatrix array
   *
   * @param dim
   * @return
   */
  private boolean validateDimension(int[] dim) {
    Set<Integer> set = new HashSet<>();
    return Arrays.stream(dim)
        .filter(n -> n != 0)
        .allMatch(n -> acceptedValue(n) && isNotDuplicated(set, n));
  }

  /**
   * E.g. for the 9x9 sudoku, just numbers from 1 to 9 are accepted, for the 16x16, just numbers
   * from 1 to 16 and so on ...
   *
   * @param value is the input number to check
   * @return true or false is the input is valid
   */
  private boolean acceptedValue(int value) {
    return value >= 1 && value <= matrix.length;
  }

  private boolean isNotDuplicated(Set<Integer> set, int value) {
    return set.add(value);
  }
}

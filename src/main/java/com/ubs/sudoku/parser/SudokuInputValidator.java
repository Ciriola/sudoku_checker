package com.ubs.sudoku.parser;

import com.ubs.sudoku.exception.InvalidInputException;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SudokuInputValidator {

  private static final String SPACES_REGEX = "\\s{1,}";
  private static final int MIN_SUDOKU_SIZE = 9;

  private final String[][] matrix;

  private String validNumbersRegex = "[1-9]";

  public SudokuInputValidator(String[][] matrix) {
    this.matrix = matrix;
  }

  /**
   * This method checks if we are working with a "square matrix" (at least 9x9) whose dimension is a
   * perfect square and whose rows and columns have exactly the same number of elements
   */
  public void validateMatrix() throws InvalidInputException {
    if (matrix.length < MIN_SUDOKU_SIZE) {
      throw new InvalidInputException("the min sudoku accepted is the one 9x9");
    }
    if (!isPerfectSquare(matrix)) {
      throw new InvalidInputException("the sudoku is not a 'square' sudoku");
    }

    validNumbersRegex =
        matrix.length > MIN_SUDOKU_SIZE
            ? validNumbersRegex.concat(generateRegularExpression(matrix.length))
            : validNumbersRegex;

    if (hasInvalidChars(matrix)) {
      throw new InvalidInputException("the sudoku contains some chars that are not recognized");
    }
  }

  private boolean isPerfectSquare(String[][] matrix) {
    double sq = Math.sqrt(matrix.length);
    return ((sq - Math.floor(sq)) == 0) && haveRowAndColSameLength(matrix);
  }

  private boolean haveRowAndColSameLength(String[][] matrix) {
    return Arrays.stream(matrix).allMatch(e -> e.length == matrix.length);
  }

  private boolean hasInvalidChars(String[][] matrix) {
    return Arrays.stream(matrix).flatMap(Arrays::stream).anyMatch(this::isInvalidValue);
  }

  private boolean isInvalidValue(String value) {
    return !value.isEmpty() && !value.matches(SPACES_REGEX) && !value.matches(validNumbersRegex);
  }

  private String generateRegularExpression(int matrixLength) {
    return IntStream.rangeClosed(10, matrixLength)
        .collect(StringBuilder::new, (sb, i) -> sb.append("|").append(i), StringBuilder::append)
        .toString();
  }
}

package com.ubs.sudoku.checker;

import com.ubs.sudoku.exception.InvalidSudokuException;
import com.ubs.sudoku.model.Submatrix;
import com.ubs.sudoku.splitter.SudokuSplitter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SudokuCheckerTest {

  @Test
  void sudokuValidatedWhenDefault() {
    int[][] matrix = {
      {9, 0, 4, 0, 6, 0, 7, 0, 1},
      {0, 2, 0, 4, 0, 3, 0, 8, 0},
      {8, 0, 0, 0, 0, 0, 0, 0, 4},
      {0, 0, 1, 8, 4, 9, 6, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 3, 2, 5, 7, 9, 0, 0},
      {4, 0, 0, 0, 0, 0, 0, 0, 7},
      {0, 8, 0, 6, 0, 4, 0, 5, 0},
      {5, 0, 6, 0, 8, 0, 2, 0, 3}
    };

    SudokuSplitter sudokuSplitter = new SudokuSplitter(matrix);
    List<Submatrix> submatrices = sudokuSplitter.createSubmatrices();

    SudokuChecker sudokuChecker = new SudokuChecker(matrix, submatrices);

    assertAll(sudokuChecker::validateSudoku);
  }

  @Test
  void sudoku16x16validatedWhenDefault() {
    int[][] matrix = {
            {9, 0, 4, 0, 6, 0, 7, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 2, 0, 0, 0, 3, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0},
            {8, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 4, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 2, 5, 7, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {4, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0},
            {0, 8, 0, 6, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {5, 0, 6, 0, 8, 0, 2, 0, 3, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 15,0, 0, 0, 0, 0, 0, 0,16, 0, 0, 0},
            {0, 0, 0, 0, 0,11, 0, 0, 0, 0, 0, 0,12, 0, 0, 0},
            {0, 0, 0, 0, 0,0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 13,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 14, 0, 0,0, 0, 0, 0},
            {0, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}

    };

    SudokuSplitter sudokuSplitter = new SudokuSplitter(matrix);
    List<Submatrix> submatrices = sudokuSplitter.createSubmatrices();

    SudokuChecker sudokuChecker = new SudokuChecker(matrix, submatrices);

    assertAll(sudokuChecker::validateSudoku);
  }

  @Test
  void throwsInvalidSudokuWhenRowHasDuplication() {
    int[][] matrix = {
      {9, 9, 4, 0, 6, 0, 7, 0, 1},
      {0, 2, 0, 4, 0, 3, 0, 8, 0},
      {8, 0, 0, 0, 0, 0, 0, 0, 4},
      {0, 0, 1, 8, 4, 9, 6, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 3, 2, 5, 7, 9, 0, 0},
      {4, 0, 0, 0, 0, 0, 0, 0, 7},
      {0, 8, 0, 6, 0, 4, 0, 5, 0},
      {5, 0, 6, 0, 8, 0, 2, 0, 3}
    };

    SudokuSplitter sudokuSplitter = new SudokuSplitter(matrix);
    List<Submatrix> submatrices = sudokuSplitter.createSubmatrices();

    SudokuChecker sudokuChecker = new SudokuChecker(matrix, submatrices);

    assertThrows(InvalidSudokuException.class, sudokuChecker::validateSudoku);
  }

  @Test
  void throwsInvalidSudokuWhenColumnHasDuplication() {
    int[][] matrix = {
            {9, 0, 4, 0, 6, 0, 7, 0, 1},
            {0, 2, 0, 4, 0, 3, 0, 8, 0},
            {8, 0, 0, 0, 0, 0, 0, 0, 4},
            {0, 0, 1, 8, 4, 9, 6, 0, 0},
            {8, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 2, 5, 7, 9, 0, 0},
            {4, 0, 0, 0, 0, 0, 0, 0, 7},
            {0, 8, 0, 6, 0, 4, 0, 5, 0},
            {5, 0, 6, 0, 8, 0, 2, 0, 3}
    };

    SudokuSplitter sudokuSplitter = new SudokuSplitter(matrix);
    List<Submatrix> submatrices = sudokuSplitter.createSubmatrices();

    SudokuChecker sudokuChecker = new SudokuChecker(matrix, submatrices);

    assertThrows(InvalidSudokuException.class, sudokuChecker::validateSudoku);
  }

  @Test
  void throwsInvalidSudokuWhenSubmatrixHasDuplication() {
    int[][] matrix = {
            {9, 0, 4, 0, 6, 0, 7, 0, 1},
            {0, 2, 9, 4, 0, 3, 0, 8, 0},
            {8, 0, 0, 0, 0, 0, 0, 0, 4},
            {0, 0, 1, 8, 4, 9, 6, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 2, 5, 7, 9, 0, 0},
            {4, 0, 0, 0, 0, 0, 0, 0, 7},
            {0, 8, 0, 6, 0, 4, 0, 5, 0},
            {5, 0, 6, 0, 8, 0, 2, 0, 3}
    };

    SudokuSplitter sudokuSplitter = new SudokuSplitter(matrix);
    List<Submatrix> submatrices = sudokuSplitter.createSubmatrices();

    SudokuChecker sudokuChecker = new SudokuChecker(matrix, submatrices);

    assertThrows(InvalidSudokuException.class, sudokuChecker::validateSudoku);
  }

  @Test
  void throwsInvalidSudokuWhenNegativeNumber() {
    int[][] matrix = {
            {-9, 0, 4, 0, 6, 0, 7, 0, 1},
            {0, 2, 9, 4, 0, 3, 0, 8, 0},
            {8, 0, 0, 0, 0, 0, 0, 0, 4},
            {0, 0, 1, 8, 4, 9, 6, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 2, 5, 7, 9, 0, 0},
            {4, 0, 0, 0, 0, 0, 0, 0, 7},
            {0, 8, 0, 6, 0, 4, 0, 5, 0},
            {5, 0, 6, 0, 8, 0, 2, 0, 3}
    };

    SudokuSplitter sudokuSplitter = new SudokuSplitter(matrix);
    List<Submatrix> submatrices = sudokuSplitter.createSubmatrices();

    SudokuChecker sudokuChecker = new SudokuChecker(matrix, submatrices);

    assertThrows(InvalidSudokuException.class, sudokuChecker::validateSudoku);
  }

  @Test
  void throwsInvalidSudokuWhenHigherNumberThanSudokuSize() {
    int[][] matrix = {
            {16, 0, 4, 0, 6, 0, 7, 0, 1},
            {0, 2, 9, 4, 0, 3, 0, 8, 0},
            {8, 0, 0, 0, 0, 0, 0, 0, 4},
            {0, 0, 1, 8, 4, 9, 6, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 2, 5, 7, 9, 0, 0},
            {4, 0, 0, 0, 0, 0, 0, 0, 7},
            {0, 8, 0, 6, 0, 4, 0, 5, 0},
            {5, 0, 6, 0, 8, 0, 2, 0, 3}
    };

    SudokuSplitter sudokuSplitter = new SudokuSplitter(matrix);
    List<Submatrix> submatrices = sudokuSplitter.createSubmatrices();

    SudokuChecker sudokuChecker = new SudokuChecker(matrix, submatrices);

    assertThrows(InvalidSudokuException.class, sudokuChecker::validateSudoku);
  }
}

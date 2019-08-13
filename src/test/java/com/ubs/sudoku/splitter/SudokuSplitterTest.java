package com.ubs.sudoku.splitter;

import com.ubs.sudoku.model.Submatrix;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SudokuSplitterTest {

  @Test
  void createSubmatricesWhenDefault() {

    int[][] matrix = new int[][]{
            {1,2,3,4,5,6,7,8,9},
            {1,2,3,4,5,6,7,8,9},
            {1,2,3,4,5,6,7,8,9},
            {1,2,3,4,5,6,7,8,9},
            {1,2,3,4,5,6,7,8,9},
            {1,2,3,4,5,6,7,8,9},
            {1,2,3,4,5,6,7,8,9},
            {1,2,3,4,5,6,7,8,9},
            {1,2,3,4,5,6,7,8,9},
    };

    SudokuSplitter sudokuSplitter = new SudokuSplitter(matrix);

    List<Submatrix> submatrices = sudokuSplitter.createSubmatrices();

    ArrayList<Integer> expectedSubmatrix =
            new ArrayList<>(Arrays.asList(1, 2, 3, 1, 2, 3, 1, 2, 3));

    assertNotNull(submatrices);
    assertEquals(9, submatrices.size());
    assertEquals(9, submatrices.get(0).getSubmatrix().size());

    assertEquals(expectedSubmatrix, submatrices.get(0).getSubmatrix());

  }

  @Test
  void createSubmatricesWhenEmptyMatrix() {

    int[][] matrix = new int[][]{};

    final SudokuSplitter sudokuSplitter = new SudokuSplitter(matrix);

    List<Submatrix> submatrices = sudokuSplitter.createSubmatrices();

    assertNotNull(submatrices);
    assertTrue(submatrices.isEmpty());
  }
}
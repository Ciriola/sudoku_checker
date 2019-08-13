package com.ubs.sudoku.converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuConverterTest {

  @Test
  void toIntMatrixWhenDefault() {
    String[][] matrix = new String[][]{
            {"1","2","3","4","5","6","7","8","9"},
            {"1","2","3","4","5","6","7","8","9"},
            {"1","2","3","4","5","6","7","8","9"},
            {"1","2","3","4","5","6","7","8","9"},
            {"1","2","3","4","5","6","7","8","9"},
            {"1","2","3","4","5","6","7","8","9"},
            {"1","2","3","4","5","6","7","8","9"},
            {"1","2","3","4","5","6","7","8","9"},
            {"1","2","3","4","5","6","7","8","9"}
    };

    SudokuConverter sudokuConverter = new SudokuConverter();
    int[][] sudoku = sudokuConverter.toIntMatrix(matrix);

    assertNotNull(sudoku);
    assertEquals(9, sudoku.length);

    for (int[] row : sudoku) {
      assertArrayEquals(new int[]{1,2,3,4,5,6,7,8,9}, row);

      int zeroIndexColumn = row[0];
      assertEquals(1, zeroIndexColumn);
    }
  }

  @Test
  void toIntMatrixWhenMatrixIsPartiallyFilled() {
    String[][] matrix = new String[][]{
            {"1","","3","","5","","7","",""},
            {"","","3","","","6","7","8","9"},
            {"","","","","","","","",""},
            {"1","","3","","5","","7","",""},
            {"","","3","","","6","7","8","9"},
            {"","","","","","","","",""},
            {"1","","3","","5","","7","",""},
            {"","","3","","","6","7","8","9"},
            {"","","","","","","","",""}
    };

    SudokuConverter sudokuConverter = new SudokuConverter();
    int[][] sudoku = sudokuConverter.toIntMatrix(matrix);

    assertNotNull(sudoku);
    assertEquals(9, sudoku.length);

    int[] firstRow = new int[]{1,0,3,0,5,0,7,0,0};
    assertArrayEquals(firstRow, sudoku[0]);

    for (int[] row : sudoku) {
      int zeroIndexColumn = row[1];
      assertEquals(0, zeroIndexColumn);
    }
  }

  @Test
  void toIntMatrixWhenMatrixIsPartiallyFilledAndHasMultipleSpaces() {
    String[][] matrix = new String[][]{
            {" ","","  ","   ","","     "," ","",""},
            {" ","","  ","   ","","     "," ","",""},
            {" ","","  ","   ","","     "," ","",""},
            {" ","","  ","   ","","     "," ","",""},
            {" ","","  ","   ","","     "," ","",""},
            {" ","","  ","   ","","     "," ","",""},
            {" ","","  ","   ","","     "," ","",""},
            {" ","","  ","   ","","     "," ","",""},
            {" ","","  ","   ","","     "," ","",""}
    };

    SudokuConverter sudokuConverter = new SudokuConverter();
    int[][] sudoku = sudokuConverter.toIntMatrix(matrix);

    assertNotNull(sudoku);
    assertEquals(9, sudoku.length);

    for (int[] row : sudoku) {
      assertArrayEquals(new int[]{0,0,0,0,0,0,0,0,0}, row);

      int zeroIndexColumn = row[0];
      assertEquals(0, zeroIndexColumn);
    }
  }
}
package com.ubs.sudoku.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class SudokuParser {

  private static final String DELIMITER = ",";
  private static final int NO_LIMIT = -1;

  /**
   * This method reads the matrix from the input file 'puzzleName.txt'
   *
   * @return a matrix of string
   * @throws IOException
   */
  public String[][] read(Path path) throws IOException {
      try (Stream<String> stream = getLines(path)) {
          return stream
                .map(s -> s.split(DELIMITER, NO_LIMIT))
                .toArray(String[][]::new);
      }
  }

  Stream<String> getLines(Path path) throws IOException {
      return Files.lines(path);
  }
}

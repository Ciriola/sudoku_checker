package com.ubs.sudoku.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class SudokuParserTest {

  @Spy
  @InjectMocks
  private SudokuParser sudokuParser;

  @Mock
  Path path;

  @Test
  void readLinesWhenDefault() throws IOException {
    List<String> matrixLines = new ArrayList<>();

    for(int i = 0; i<9; i++) {
      matrixLines.add("1,2,3,4,5,6,7,8,9");
    }

    Stream<String> lines = matrixLines.stream();

    doReturn(lines).when(sudokuParser).getLines(any(Path.class));
    String[][] matrix = sudokuParser.read(path);

    assertNotNull(matrix);
    assertEquals(9, matrix.length);

    for (String[] row : matrix) {
      assertEquals(9, row.length);
      assertArrayEquals(new String[]{"1","2","3","4","5","6","7","8","9"}, row);

      String zeroIndexColumn = row[0];
      assertEquals("1", zeroIndexColumn);
    }
  }

  @Test
  void readLinesWhenJustOneLine() throws IOException {
    String firstLine = "1,2,3,4,5,6,7,8,9";

    Stream<String> lines = Stream.of(firstLine);

    doReturn(lines).when(sudokuParser).getLines(any(Path.class));
    String[][] matrix = sudokuParser.read(path);

    assertNotNull(matrix);
    assertEquals(1, matrix.length);
    assertEquals(9, matrix[0].length);

    assertArrayEquals(new String[]{"1","2","3","4","5","6","7","8","9"}, matrix[0]);

    assertEquals(1, Integer.parseInt(matrix[0][0]));
    assertEquals(2, Integer.parseInt(matrix[0][1]));
  }

  @Test
  void readLinesWhenEmptyLine() throws IOException {
    String emptyLine = "";

    Stream<String> lines = Stream.of(emptyLine);

    doReturn(lines).when(sudokuParser).getLines(any(Path.class));
    String[][] matrix = sudokuParser.read(path);

    assertNotNull(matrix);
    assertEquals(1, matrix.length);
    assertEquals(1, matrix[0].length);
    assertEquals("", matrix[0][0]);
  }

  @Test
  void readFileWhenNoContent() throws IOException {

    Stream<String> lines = Stream.of();

    doReturn(lines).when(sudokuParser).getLines(any(Path.class));
    String[][] matrix = sudokuParser.read(path);

    assertNotNull(matrix);
    assertEquals(0, matrix.length);
  }

  @Test
  void getFileLinesWhenDefault() throws IOException {
    File file = File.createTempFile("temp", ".tmp");

    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
    bw.write("test");
    bw.close();

    Path path = file.toPath();

    Stream<String> lines = sudokuParser.getLines(path);

    String actualFileContent = lines.collect(Collectors.joining());

    assertEquals("test", actualFileContent);

    file.deleteOnExit();
  }
}
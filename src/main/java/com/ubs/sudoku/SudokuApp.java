package com.ubs.sudoku;

import com.ubs.sudoku.checker.SudokuChecker;
import com.ubs.sudoku.converter.SudokuConverter;
import com.ubs.sudoku.exception.InvalidInputException;
import com.ubs.sudoku.exception.InvalidSudokuException;
import com.ubs.sudoku.model.Submatrix;
import com.ubs.sudoku.model.SudokuResult;
import com.ubs.sudoku.parser.SudokuInputValidator;
import com.ubs.sudoku.parser.SudokuParser;
import com.ubs.sudoku.splitter.SudokuSplitter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SudokuApp {

  private static final String DEFAULT_FILENAME = "puzzleName.txt";
  private static final String DELIMITER_FOR_JAR = "!";
  private static final String JAR_SUFFIX = "jar";

  public static void main(String[] args) {

    if (args.length > 1) {
      throw new IllegalArgumentException(
          "Be sure to launch the app with the right parameters: 'validate.bat <inputfile.txt>'");
    }

    String filename = args.length == 0 ? DEFAULT_FILENAME : args[0];

    String[][] matrix = null;
    SudokuParser sudokuParser = new SudokuParser();

    try {
      Path path = getPath(args);
      matrix = sudokuParser.read(path);
    } catch (IOException e) {
      System.out.println("Error reading the input file : " + filename + ". Check its name and/or the path");
      System.exit(1);
    } catch (URISyntaxException e) {
      System.out.println("The string related to the resource : " + filename + " can't be parsed as a URI reference");
      System.exit(1);
    }

    SudokuInputValidator sudokuInputValidator = new SudokuInputValidator(matrix);

    try {
      sudokuInputValidator.validateMatrix();
    } catch (InvalidInputException e) {
      System.out.println("Error: " + e.getMessage());
      System.exit(1);
    }

    SudokuConverter sudokuTransformer = new SudokuConverter();

    int[][] sudoku = sudokuTransformer.toIntMatrix(matrix);

    SudokuSplitter sudokuSplitter = new SudokuSplitter(sudoku);
    List<Submatrix> submatrices = sudokuSplitter.createSubmatrices();

    SudokuChecker checker = new SudokuChecker(sudoku, submatrices);

    try {
      System.out.println("\n********************");
      System.out.println("*  SUDOKU CHECKER  *");
      System.out.println("********************");

      checker.validateSudoku();

      System.out.println("\n -> Result: " + SudokuResult.VALID.getCode() + "(" + SudokuResult.VALID.toString() + ")");
    } catch (InvalidSudokuException e) {
      System.out.println("\n -> Result: " + SudokuResult.INVALID.getCode() + "(" + SudokuResult.INVALID.toString() + ") " + ". Error: " + e.getMessage());
      System.exit(1);
    }
  }

  private static Path getPath(String[] args) throws URISyntaxException {
    return args.length == 0 ? getPathFromClassLoader() : Paths.get(args[0]);
  }

  /**
   * This methods allows you to execute the program from the IDE or with the default resource file
   * "puzzleName.txt" through command line without specifying the name of the input file to analyse
   * (0 arguments) In any case the use is discouraged, please from command line try to use the
   * syntax:
   *
   * <p>validate.bat <inputfile.txt>
   *
   * This method exists to give the opportunity to use the IDE (debug or other purposes)
   * without changing the code
   *
   * @return the Path to be used in case : - you want to execute the program into the IDE - you want
   *     to execute the program without specifying any argument (e.g. puzzleNameX.txt), but getting
   *     the default puzzleName.txt file present inside the jar
   * @throws URISyntaxException
   */
  private static Path getPathFromClassLoader() throws URISyntaxException {
    URL url = SudokuApp.class.getClassLoader().getResource(DEFAULT_FILENAME);
    URI uri = Objects.requireNonNull(url).toURI();

    if (uri.toString().split(DELIMITER_FOR_JAR)[0].endsWith(JAR_SUFFIX)) {
      try {
        FileSystems.newFileSystem(uri, Collections.emptyMap());
        System.out.println("\nNo parameters specified -> using the default puzzleName.txt\n\n");
      } catch (IOException e) {
        System.out.println("Please specify through the command line the following syntax : 'validate.bat <inputfile.txt>'");
        System.exit(1);
      }
    }
    return Paths.get(uri);
  }
}

#SUDOKU 

###Description

This app basically represents a sudoku validator/checker.
My approach was mainly focusing on the correctness and extensibility of this game.
My aim was to create a validator capable of validate also bigger 
sudoku than the classical dimension of 9x9. 
I didn't perform performance tests, but I've done (in my tests) some simple examples 
of 16x16 and 25x25 sudoku, without seeing a reduction of performances.  

###Usage

The application can be launched in three different ways (without changing anything in the code)
 
 1) through a terminal (following the specifications), specifying the .bat file and the input file:

        validate.bat puzzleName.txt 
        
 2) through the IDE, just launching the main class SudokuApp (debugging purposes etc...)

 3) through a terminal, specifying just the .bat file, the default "puzzleName.txt" present 
    inside the jar will be called by the respective classloader
    
###Build

To build the application, from the root of the project where the pom is present just do:

mvn clean install 

###Testing/Reporting

I've tested all the classes reaching an overall coverage of 100% with jacoco
I've just excluded in the coverage analysis the main class and the two
classes/enum defined in the 'com.ubs.sudoku.model' package

To launch the tests and produce the reports you should launch:

mvn test

Once that is done, inside the target folder will be created another folder
called "jacoco-report". Inside that folder there is an index.html page
that is showing the report, with the coverage of branch/lines

###Doubts/Assumptions

####Formatting

On the description of the exercise there was a confusion about the file format to use
(csv or txt). But asking to who has sent the exercise, the reply was to consider txt files
So, let's see what should be in my context the representation of a sudoku of this form:

|9| |4| |6| |7| |1|
| |2| |4| |3| |8| |
|8| | | | | | | |4|
| | |1|8|4|9|6| | |
| | | | | | | | | |
| | |3|2|5|7|9| | |
|4| | | | | | | |7|
| |8| |6| |4| |5| |
|5| |6| |8| |2| |3|

Wanted and so adopted representation (with , delimiter) :

9,,4,,6,,7,,1
,2,,4,,3,,8,
8,,,,,,,,4
,,1,8,4,9,6,,
,,,,,,,,
,,3,2,5,7,9,,
4,,,,,,,,7
,8,,6,,4,,5,
5,,6,,8,,2,,3

Also spaces 1 or more ar accepted. So the previous one could be equivalent to :

9,   ,4,,6,,7,,1
,2,,4,  ,3,,8,
8,, ,, ,,,,4
,,1,8,4,9,6,,
,, ,, , ,   ,,
,,3,2,5,7,9,,
4,,,,,,,,7
,8,,6,,4,,5,
5,,6,,8,,2,,3

###Algorithm 

Basically the check happens in three phases 

Phase 1: Parse and validation of the input (external file) to a matrix of strings

Phase 2: Transforming the matrix of string in matrix of int, substituting 0 to eventual spaces

Phase 3: check the dimensions (rows, columns, submatrices) to see if the sudoku is valid 

###Logging 

Is not a good practice use the stdout, but in this exercise the logging was not required






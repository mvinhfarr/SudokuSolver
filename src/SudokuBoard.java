/*
* SudokuBoard creates a 2d array representing an unsolved sudoku board
* Converts board into exact cover to solve with algorithmX
* Also allows you to solve with brute force algorithm
*
* Currently only supports creation from a file source
* 
* Myles Vinh Farr
* 12 June 2020
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class SudokuBoard {
    private String boardFile; //unsolved read file found in dir "data"
    private String outputFile; //solved board write to dir "results"

    private int[][] board; //unsolved sudoku board as 2d array -> 0 = unknown
    private int size; //num rows or cols
    private int base;
    
    private int[][] ecArr;
    /*
    * sudoku board converted into a exact cover
    * rows: possibilities; cols: constraints
    * for a 9x9:
        * 729 rows -> 81 squares can be any of 9 possibilities
        * 324 cols -> 81 row-col, 81 row-num, 81 col-num, & 81 box-num constraints
    *
    * 4 sets of constraints for a sudoku board:
        * row-col: each square (r1c1, r1c2... r2c1, r2c2...) must have only one number
        * row-num: each row (r1-r9) must have nums 1-9 once and only once (r1#1, r1#2... r2#1, r2#2)
        * col-num: same as row-num but for columns
        * box-num: each 3x3 sub square must have nums 1-9 once and only once (b1#1, b1#2...)
    */

    private int[][] solvedBoard; //solved sudoku board

    //RIGHT NOW: only can create SudokuBoard object from file source
    //FUTURE: create SudokuBoard from SudoGUI class
    public SudokuBoard(String boardFile, String outputFile) {
        this.boardFile = boardFile;
        this.outputFile = outputFile;
    }

    //initialize board[][] from a file in dir "data"
    //first line must indicate size as total num rows
    //delimiter = ","
    //will handle spaces or zeros as unfilled values
    public void generateBoardFromFile() throws FileNotFoundException {
        Scanner input = new Scanner(new File("data/" + boardFile));

        size = input.nextInt();
        base = (int) Math.sqrt(size);
        board = new int[size][size];
        solvedBoard = new int[size][size];
        input.nextLine();

        for (int i = 0; i < size; i++) {
            String[] tempRow = input.nextLine().split(",", size);

            for (int j = 0; j < size; j++) {
                try {
                    int val = Integer.parseInt(tempRow[j].trim(), 10);

                    if (val >= 0 && val <= size) {
                        board[i][j] = val;
                    } else {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    if(tempRow[j].isBlank()) {
                        board[i][j] = 0;
                    } else if(base == 4 || base == 5) {
                        int val = Integer.parseInt(tempRow[j].trim(), size+1);
                        if(val >= 0 && val <= size) board[i][j] = val;
                        else throw new NumberFormatException("value out of boards at: " + i + ", " + j);
                    } else {
                        //System.out.printf("wrong format at [%d][%d]%n", i, j);
                        throw new NumberFormatException("value unmappable at: " + i + ", " + j);
                    }
                }
            }
        }
    }

    //print the solved sudoku board to a file in dir "results"
    //prints using PrintStream (TO CHANGE) and separates with space
    //calls on static int2dArrPrint
    public void printSudokuBoard(int[][] arr, boolean toFile) throws FileNotFoundException {
        PrintStream out;
        if(toFile) out = new PrintStream(new File("results/" + outputFile));
        else out = new PrintStream(System.out);

        for(int[] row: arr) {
            for(int c: row) {
                out.print(c + " ");
            }
            out.println();
        }
        out.println();
    }
    
    //fill in the constraints each row fills in the exact cover
    //each row satisfies 4 constraints, 1 from each set
    private void fillECRow(int row, int col, int num) {
        int sizeSq = size*size;
                
        int rowIndex = row*sizeSq + col*size + num;
        int rowCol = 0*sizeSq + row*size + col;
        int rowNum = 1*sizeSq + row*size + num;
        int colNum = 2*sizeSq + col*size + num;
        int boxNum = 3*sizeSq + (row/base*base + col/base)*size + num;

        ecArr[rowIndex][rowCol] = 1;
        ecArr[rowIndex][rowNum] = 1;
        ecArr[rowIndex][colNum] = 1;
        ecArr[rowIndex][boxNum] = 1;
    }

    //convert sudoku board into exact cover
    private void initializeExactCover() {
        ecArr = new int[(int) Math.pow(size, 3)][size*size * 4];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(board[i][j] == 0) { //if square in board is unfilled, fill all possibilities
                    for(int temp = 0; temp < size; temp++) {
                        fillECRow(i, j, temp);
                    }
                } else { //else only need to fill in one possibility
                    fillECRow(i, j, board[i][j]-1);
                }
            }
        }
    }
    
    //solve sudoku board by converting into exact cover and running algorithm x
    public void solveWithAlgorithmX() {
        initializeExactCover();

        ExactCover sudokuEC = new ExactCover(ecArr);
        sudokuEC.algorithmX();
        int[] solvedECRowIndex = sudokuEC.getSolutionRows();
        
        for(int i: solvedECRowIndex) {
            int tempRow = i / (size*size);
            int tempCol = (i % (size*size)) /size;
            int tempNum = (i % size) + 1;

            solvedBoard[tempRow][tempCol] = tempNum;
        }
    }

    //solve sudoku board using simple brute force algorithm
    public void solveWithBruteForce() {
        BruteForceSolver bfs = new BruteForceSolver(board);
        bfs.solve();
        solvedBoard = bfs.getSolved();
    }

    public int[][] getBoard() {
        return board;
    }

    public int[][] getEcArr() {
        return  ecArr;
    }

    public int[][] getSolvedBoard() {
        return solvedBoard;
    }
}
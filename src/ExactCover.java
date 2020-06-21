/*
* ExactCover allows you to solve an exact cover using Donald Knuth's Algorithm X
* Allows you to initialize the exact cover from an existing 2d array or from a file source
*
* Myles Vinh Farr
* 10 June 2020
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExactCover {
    private int[][] arr; //exact cover as an immutable 2d array

    private ArrayList<Integer> partial_solution; //list representing indexes of rows in solution

    private int[][] solution; //subset of arr[][] that exactly fits constraints

    //create an ExactCover object from an existing 2d arr
    public ExactCover(int[][] arr) {
        this.arr = arr;
        this.partial_solution = new ArrayList<>();
    }

    //create an ExactCover object from a file source
    //first line must be #rows and #cols
    //ec must be matrix of comma separated 1s and 0s
    public ExactCover(String ecFile) throws FileNotFoundException {
        Scanner input = new Scanner(new File(ecFile));

        int numRows = input.nextInt();
        int numCols = input.nextInt();
        input.nextLine();

        this.arr = new int[numRows][numCols];

        for(int row = 0; row < numRows; row++) {
            String[] tempRow = input.nextLine().split(",", numCols);
            for(int col = 0; col < tempRow.length; col++) {
                try {
                    int val = Integer.parseInt(tempRow[col]);
                    if(val == 0 || val == 1) {
                        arr[row][col] = val;
                    } else {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    System.out.printf("wrong format at [%d][%d]%n", row, col);
                }
            }
        }

        this.partial_solution = new ArrayList<>();
    }

    //returns index of the col with the fewest 1s from subset {rows[], cols[]} of arr[][]
    private int smallCol(ArrayList<Integer> rows, ArrayList<Integer> cols) {
        int[] temp = new int[cols.size()]; //sum along select cols[] for select rows[] from arr

        for(int row : rows) { //iterate through select rows[]
            for (int j = 0; j < cols.size(); j++) { //iterate by index to match length of temp and cols
                temp[j] += arr[row][cols.get(j)]; //sum along column
            }
        }

        int smallest = temp[0];
        int smallIndex = 0;

        for(int i = 1; i < temp.length; i++) {
            if(temp[i] < smallest) {
                smallest = temp[i];
                smallIndex = i;
            }
        }

        return cols.get(smallIndex); //return the index of the col in cols[] with fewest 1s
    }

    //returns indexes of rows with 1s in col c
    //acts on select rows[] of arr
    private ArrayList<Integer> col1Index(int c, ArrayList<Integer> rows) {
        ArrayList<Integer> col1Index = new ArrayList<>();

        for(int r: rows) {
            if(arr[r][c] == 1) col1Index.add(r);
        }

        return col1Index;
    }

    //returns indexes of cols with 1s in row r
    //acts on select cols[] of arr
    private ArrayList<Integer> row1Index(int r, ArrayList<Integer> cols) {
        ArrayList<Integer> row1Index = new ArrayList<>();

        for(int c: cols) {
            if(arr[r][c] == 1) row1Index.add(c);
        }

        return row1Index;
    }

    //recursive algorithmX
    //instead of reducing the size of the exact cover, I track the active rows and cols in the branch
    private boolean algorithmX(ArrayList<Integer> rows, ArrayList<Integer> cols) {
        //check if df is empty
        if(rows.size() == 0 || cols.size() == 0) return true;

        //find col with fewest 1s
        int smallCol = smallCol(rows, cols);

        //index of rows with a 1 in smallCol
        ArrayList<Integer> smallCol1Index = col1Index(smallCol, rows);

        //loop will try a branch (row from smallCol1Index)
        //recursively calls itself to test the branch
        //if a branch is valid it will run to completion -> matrix must end empty
        for(int branch : smallCol1Index) {
            //index of cols in current row that have a 1
            ArrayList<Integer> levelRow1Index = row1Index(branch, cols);

            //new set of rows and cols
            ArrayList<Integer> branchRows = new ArrayList<>(rows);
            ArrayList<Integer> branchCols = new ArrayList<>(cols);

            //drop rows
            ArrayList<Integer> levelSharedCol1Index = new ArrayList<>();
            for(int col: levelRow1Index) {
                ArrayList<Integer> tempCol1Index = col1Index(col, rows);
                levelSharedCol1Index.addAll(tempCol1Index);
            }
            branchRows.removeAll(levelSharedCol1Index);

            //drop cols
            branchCols.removeAll(levelRow1Index);

            //recursify
            if(algorithmX(branchRows, branchCols)) {
                this.partial_solution.add(branch);
                return true;
            }
        }

        return false; //if there are no rows with a 1 in smallCol, return false
    }

    //initiate algorithmX with full set of rows and cols
    public void algorithmX() {
        ArrayList<Integer> rows = IntStream.rangeClosed(0, arr.length-1).boxed()
                                            .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Integer> cols = IntStream.rangeClosed(0, arr[0].length-1).boxed()
                                            .collect(Collectors.toCollection(ArrayList::new));

        this.algorithmX(rows, cols);
    }

    //convert rows in solution into the valid solution matrix
    public void postProcess() {
        solution = new int[partial_solution.size()][arr[0].length];

        for(int i = 0; i < solution.length; i++) {
            System.arraycopy(arr[partial_solution.get(i)], 0, solution[i], 0, arr[0].length);
        }
    }

    public int[][] getArr() {
        return arr;
    }

    public int[] getSolutionRows() {
        return partial_solution.stream().mapToInt(i -> i).toArray();
    }

    public int[][] getSolution() {
        return solution;
    }
}


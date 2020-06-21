public class BruteForceSolver {
    private int[][] board;
    private final int SIZE;
    private final int BASE;

    private int[][] solved;

    private boolean valid;
    private int lastVal;
    private int row;
    private int col;

    public BruteForceSolver(int [][] board) {
        this.board = board;
        this.SIZE = board.length;
        this.BASE = (int) Math.sqrt(SIZE);

        this.solved = SudokuBoard.int2dArrDeepCopy(board);

        this.valid = true; //if solved[][] is currently a valid sudoku board
        this.lastVal = 0; //value in last square
        this.row = 0; //current row
        this.col = 0; //current col

        solve();
    }

    private void solve() {
        while(row < SIZE) {
            while(col < SIZE) {
                if(board[row][col] == 0) { //current square unknown
                    int temp;
                    if(valid) { //current state valid
                        temp = fillSquare(1); //fill new square
                    } else { //current state invalid
                        temp = fillSquare(lastVal +1); //refill square
                    }

                    if(temp == -1) { //no valid possibilities
                        valid = false;
                        if(col == 0) { //if last square is in previous row
                            lastVal = solved[row-1][SIZE-1];
                            solved[row][col] = 0;
                            col = SIZE-1;
                            row--;
                        } else { //last square in current row
                            lastVal = solved[row][col-1];
                            solved[row][col] = 0;
                            col--;
                        }
                    } else { //valid possibility
                        valid = true;
                        solved[row][col] = temp;
                        lastVal = temp;
                        col++;
                    }
                } else { //current square known
                    if(!valid) {
                        if(col == 0) {
                            lastVal = solved[row - 1][SIZE-1];
                            col = SIZE-1;
                            row--;
                        } else {
                            lastVal = solved[row][col-1];
                            col--;
                        }
                    } else {
                        col++;
                    }
                }
            }

            col = 0; //reset col
            row++; //and move to next row
        }
    }

    //returns true if arr[] contains i
    private static boolean checkArr(int[] arr, int val) {
        for(int i:arr) {
            if(i == val) {
                return true;
            }
        }
        return false;
    }

    //returns true if current row in solved contains val
    private boolean checkRow(int val) {
        return checkArr(solved[row], val);
    }

    //returns true if current col in solved contains val
    private boolean checkColumn(int val) {
        int[] tempCol = new int[SIZE];

        for(int i = 0; i < SIZE; i++) {
            tempCol[i] = solved[i][col];
        }

        return checkArr(tempCol, val);
    }

    //returns true if current subSquare that current row & col are in contains val
    private boolean checkSquare(int val) {
        int[] subSquare = new int[SIZE];

        int startRow = row/BASE*BASE;
        int startCol = col/BASE*BASE;

        int n = 0;
        for(int i = 0; i < BASE; i++) {
            for(int j = 0; j < BASE; j++) {
                subSquare[n] = solved[startRow + i][startCol + j];
                n++;
            }
        }

        return checkArr(subSquare, val);
    }

    //fill current square (row, col) with a possibility
    //checks startPoint < i < SIZE
    private int fillSquare(int startPoint) {
        for(int i = startPoint; i <= SIZE; i++) {
            if(!checkRow(i))
                if(!checkColumn(i))
                    if(!checkSquare(i))
                        return i; //if no conflicts
        }
        return -1; //return -1 if no there are no valid possibilities
    }

    public int[][] getSolved() {
        return solved;
    }
}

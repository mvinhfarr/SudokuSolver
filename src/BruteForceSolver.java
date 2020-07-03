public class BruteForceSolver {
    private int[][] board;
    private final int size;
    private final int base;

    private int[][] solved;

    private boolean valid;
    private int lastVal;
    private int row;
    private int col;

    public BruteForceSolver(int [][] board) {
        this.board = board;
        this.size = board.length;
        this.base = (int) Math.sqrt(size);

        this.solved = int2dArrDeepCopy(board);

        this.valid = true; //if solved[][] is currently a valid sudoku board
        this.lastVal = 0; //value in last square
        this.row = 0; //current row
        this.col = 0; //current col
    }

    public void solve() {
        while(row < size) {
            while(col < size) {
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
                            lastVal = solved[row-1][size -1];
                            solved[row][col] = 0;
                            col = size -1;
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
                            lastVal = solved[row - 1][size -1];
                            col = size -1;
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

    //fill current square (row, col) with a possibility
    //checks startPoint < i < size
    private int fillSquare(int startPoint) {
        for(int i = startPoint; i <= size; i++) {
            if(!checkRow(solved, row, i))
                if(!checkColumn(solved, col, i))
                    if(!checkSquare(solved, row, col, i))
                        return i; //if no conflicts
        }
        return -1; //return -1 if no there are no valid possibilities
    }

    public int[][] getSolved() {
        return solved;
    }

    //creates a deep copy of a 2d int array (not used)
    public static int[][] int2dArrDeepCopy(int[][] a) {
        int[][] copy = new int[a.length][];
        for(int i = 0; i < a.length; i++) {
            int tempLength = a[i].length;
            copy[i] = new int[tempLength];
            System.arraycopy(a[i], 0, copy[i], 0, tempLength);
        }
        return copy;
    }

    //returns true if current row in solved contains val
    public static boolean checkRow(int[][] board, int row, int val) {
        return checkArr(board[row], val);
    }

    //returns true if current col in solved contains val
    public static boolean checkColumn(int[][] board, int col, int val) {
        int[] tempCol = new int[board.length];

        for(int i = 0; i < board.length; i++) {
            tempCol[i] = board[i][col];
        }

        return checkArr(tempCol, val);
    }

    //returns true if current subSquare that current row & col are in contains val
    public static boolean checkSquare(int[][] board, int row, int col, int val) {
        int base = (int) Math.sqrt(board.length);

        int[] subSquare = new int[board.length];

        int startRow = row/ base * base;
        int startCol = col/ base * base;

        int n = 0;
        for(int i = 0; i < base; i++) {
            for(int j = 0; j < base; j++) {
                subSquare[n] = board[startRow + i][startCol + j];
                n++;
            }
        }

        return checkArr(subSquare, val);
    }
}

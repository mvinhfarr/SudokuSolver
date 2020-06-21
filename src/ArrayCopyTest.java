import java.io.PrintStream;

public class ArrayCopyTest {
    public static void main(String[] args) {
        int[][] board = new int[9][9];

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                board[i][j] = i+j;
            }
        }

        long cloneStartTime = System.nanoTime();

        int[][] cloneCopy = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            cloneCopy[i] = board[i].clone();
        }

        long cloneEndTime = System.nanoTime();

        long acStartTime = System.nanoTime();

        int [][] arraycopyCopy = new int[board.length][];
        for(int i = 0; i < board.length; i++) {
            int[] aMatrix = board[i];
            int aLength = aMatrix.length;
            arraycopyCopy[i] = new int[aLength];
            System.arraycopy(aMatrix, 0, arraycopyCopy[i], 0, aLength);
        }

        long acEndTime = System.nanoTime();

        long modStartTime = System.nanoTime();

        int [][] modCopy = new int[board.length][];
        for(int i = 0; i < board.length; i++) {
            int tempLength = board[i].length;
            modCopy[i] = new int[tempLength];
            System.arraycopy(board[i], 0, modCopy[i], 0, tempLength
            );
        }

        long modEndTime = System.nanoTime();

        long cloneElapsedTime = cloneEndTime-cloneStartTime;
        long acElapsedTime = acEndTime-acStartTime;
        long modElapsedTime = modEndTime-modStartTime;

        SudokuBoard.int2dArrPrint(new PrintStream(System.out), cloneCopy);
        System.out.println("Solve time in nanoseconds  : " + cloneElapsedTime);
        System.out.println("Solve time in milliseconds : " + cloneElapsedTime/1000000);

        SudokuBoard.int2dArrPrint(new PrintStream(System.out), arraycopyCopy);
        System.out.println("Solve time in nanoseconds  : " + acElapsedTime);
        System.out.println("Solve time in milliseconds : " + acElapsedTime/1000000);

        SudokuBoard.int2dArrPrint(new PrintStream(System.out), modCopy);
        System.out.println("Solve time in nanoseconds  : " + modElapsedTime);
        System.out.println("Solve time in milliseconds : " + modElapsedTime/1000000);
    }
}
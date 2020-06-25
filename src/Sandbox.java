import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;


public class Sandbox {
    public static void main(String[] args) throws FileNotFoundException{
        //demoExactCover();

        String easyBoard = "testBoardEasy.txt";
        String easySolved = "easySolved.txt";
        //demoSudokuSolver(easyBoard, easySolved, false, false);

        String medBoard = "testBoardMed.txt";
        String medSolved = "medSolved.txt";
        //demoSudokuSolver(medBoard, medSolved, false, false);

        String hardBoard = "testBoardHard.txt";
        String hardSolved = "hardSolved.txt";
        //demoSudokuSolver(hardBoard, hardSolved, false, false);

        String veryHardBoard = "veryHard.txt";
        String veryHardSolved = "veryHardSolved.txt";
        //demoSudokuSolver(veryHardBoard, veryHardSolved, true, false);

        String fourBoard = "fourXfour.txt";
        String fourSolved = "fourXfourSolved.txt";
        //demoSudokuSolver(fourBoard, fourSolved, true, false);

        String fourManySolutionsBoard = "fourManySolutions.txt";
        String fourManySolutionsSolved = "fourManySolutionsSolved.txt";
        //demoSudokuSolver(fourManySolutionsBoard, fourManySolutionsSolved, true, false);

        String sixteenBoard = "sixteenXsixteen.txt";
        String sixteenSolved = "sixteenSolved.txt";
        //demoSudokuSolver(sixteenBoard, sixteenSolved, true, false);

        String sixteen2Board = "sixteen2.txt";
        String sixteen2Solved = "sixteen2Solved.txt";
        //demoSudokuSolver(sixteen2Board, sixteen2Solved, true, false);
    }


    public static void demoSudokuSolver(String boardFile, String outputFile, boolean print, boolean save) throws FileNotFoundException {
        System.out.println(boardFile);

        SudokuBoard sb = new SudokuBoard(boardFile, outputFile);

        long startTime1 = System.nanoTime();

        sb.solveWithAlgorithmX();

        long endTime1 = System.nanoTime();
        long elapsedTime1 = endTime1-startTime1;

        if(save) sb.printSudokuBoard(sb.getSolvedBoard(), true);

        if(print) {
            sb.printSudokuBoard(sb.getSolvedBoard(), false);
        }

        System.out.println("Algorithm X solve time in nanoseconds  : " + elapsedTime1);
        System.out.println("Algorithm X solve time in milliseconds : " + elapsedTime1/1000000);
        System.out.println();


        long startTime2 = System.nanoTime();

        sb.solveWithBruteForce();

        long endTime2 = System.nanoTime();
        long elapsedTime2 = endTime2-startTime2;

        if(print) {
            sb.printSudokuBoard(sb.getSolvedBoard(), false);
        }

        System.out.println("Brute Force solve time in nanoseconds  : " + elapsedTime2);
        System.out.println("Brute Force solve time in milliseconds : " + elapsedTime2/1000000);
        System.out.println();
    }

    public static void demoExactCover() throws FileNotFoundException {
        String ec_file = "data/simple_ec.txt";

        long startTime = System.nanoTime();

        ExactCover simpleEC = new ExactCover(ec_file);

        simpleEC.algorithmX();
        simpleEC.postProcess();

        long endTime = System.nanoTime();
        long elapsedTime = endTime-startTime;

        System.out.println(ec_file);

        System.out.println("Unsolved Exact Cover:");
        ArrayCopyTest.int2dArrPrint(new PrintStream(System.out), simpleEC.getArr());

        System.out.println("Solution rows:");
        System.out.println(Arrays.toString(simpleEC.getSolutionRows()));
        System.out.println();

        System.out.println("Reverse ordered, solved Exact Cover:");
        ArrayCopyTest.int2dArrPrint(new PrintStream(System.out), simpleEC.getSolution());

        System.out.println("Solve time in nanoseconds  : " + elapsedTime);
        System.out.println("Solve time in milliseconds : " + elapsedTime/1000000);
        System.out.println("\n");
    }
}

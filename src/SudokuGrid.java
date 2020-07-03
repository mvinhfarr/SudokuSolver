import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.util.ArrayList;

public class SudokuGrid extends JPanel{
    private final JTextField[][] grid;

    private SudokuSolver sudo;
    //private int[][] sudoBoard;

    public final int size;
    public final int base;

    private int numBase;

    private final JPanel[][] subGridPanels;

    public SudokuGrid(int size) {
        super();

        this.grid = new JTextField[size][size];

        //not inplemented yet
        this.sudo = new SudokuSolver(this.grid);
        //this.sudoBoard = new int[size][size];

        this.size = size;
        this.base = (int) Math.sqrt(size);

        //initiate the board not in base = size + 1 (0 for unfilled)
        this.numBase = size+1;

        //JPanel Array to hold each base X base subgrid
        this.subGridPanels = new JPanel[base][base];

        setLayout(new GridLayout(base, base));

        initTFGrid();
        initPanelGrid();
    }

    private void initTFGrid() {
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                SquareTF square = new SquareTF(this, i, j);

                grid[i][j] = square;
            }
        }
    }

    private void initPanelGrid() {
        for(int i = 0; i < base; i++) {
            for(int j = 0; j < base; j++) {
                JPanel subGrid = new JPanel(new GridLayout(base, base));
                subGrid.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

                subGridPanels[i][j] = subGrid;
                add(subGrid);
            }
        }

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                subGridPanels[i/base][j/base].add(grid[i][j]);
            }
        }
    }

    public void moveCursor(SquareTF sq, char dir) {
        if(dir == 'N') {
            if(sq.row == 0)
                grid[size-1][sq.col].requestFocus();
            else
                grid[sq.row-1][sq.col].requestFocus();
        } else if(dir == 'S') {
            if(sq.row == size - 1)
                grid[0][sq.col].requestFocus();
            else
                grid[sq.row+1][sq.col].requestFocus();
        } else if(dir == 'W') {
            if(sq.col == 0)
                grid[sq.row][size-1].requestFocus();
            else
                grid[sq.row][sq.col-1].requestFocus();
        } else if(dir == 'E') {
            if(sq.col == size - 1)
                grid[sq.row][0].requestFocus();
            else
                grid[sq.row][sq.col+1].requestFocus();
        }
    }

    public void setNumBase(boolean b) {
        numBase = b ? 10 : size+1;
    }
    public int getNumBase() {
        return numBase;
    }

    public void solveSudo() throws BadLocationException {
        updateSolver();
        sudo.solveWithAlgorithmX();

        int[][] solvedSudo = sudo.getSolvedBoard();

        for(int i = 0; i < size; i ++) {
            for(int j = 0; j < size; j++) {
                String s = Integer.toString(solvedSudo[i][j], numBase);
                grid[i][j].getDocument().insertString(0, s, null);
            }
        }
    }

    public void validate() {
        updateSolver();

        ArrayList<int[]> badCells = sudo.validate();

        for(int[] cell: badCells) {
            grid[cell[0]][cell[1]].setBackground(new Color(255, 0, 0));
        }
    }

    private void updateSolver() {
        int[][] board = new int[size][size];
        //board = sudo.getBoard();
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                String s = grid[i][j].getText();
                try {
                    int val = Integer.parseInt(s, numBase);
                    board[i][j] = val;
                } catch (NumberFormatException e) {
                    if(s == null) {
                        board[i][j] = 0;
                    }
                }
            }
        }

        sudo.setBoard(board);
    }
}


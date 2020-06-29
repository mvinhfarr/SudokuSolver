import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.KeyEvent;

public class SudokuGrid extends JPanel{
    private JTextField[][] grid;

    private int[][] sudoBoard;

    public final int size;
    public final int base;

    private JPanel[][] subGridPanels;

    public SudokuGrid(int size) throws BadLocationException {
        super();

        this.grid = new JTextField[size][size];
        this.sudoBoard = new int[size][size];
        this.size = size;
        this.base = (int) Math.sqrt(size);

        this.subGridPanels = new JPanel[base][base];

        setLayout(new GridLayout(base, base));

        initTFGrid();
        initPanGrid();


    }

    private void initTFGrid() throws BadLocationException {
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JTextField square = new SquareTF(i, j, size);
                square.addKeyListener(new SquareKeyListener(this));
                grid[i][j] = square;
            }
        }
    }

    private void initPanGrid() {
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

    public void solveSudo() {
        SudokuSolver sudo = new SudokuSolver(grid);
        sudo.solveWithAlgorithmX();

        int[][] solvedSudo = sudo.getSolvedBoard();

        for(int i = 0; i < size; i ++) {
            for(int j = 0; j < size; j++) {
                grid[i][j].setText("" + solvedSudo[i][j]);
            }
        }
    }

    public void moveCursor(SquareTF sq, int keyCode) {
        if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
            if(sq.row == 0)
                grid[size-1][sq.col].requestFocus();
            else
                grid[sq.row-1][sq.col].requestFocus();
        } else if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
            if(sq.row == size - 1)
                grid[0][sq.col].requestFocus();
            else
                grid[sq.row+1][sq.col].requestFocus();
        } else if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
            if(sq.col == 0)
                grid[sq.row][size-1].requestFocus();
            else
                grid[sq.row][sq.col-1].requestFocus();
        } else if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
            if(sq.row == size - 1)
                grid[sq.row][0].requestFocus();
            else
                grid[sq.row][sq.col+1].requestFocus();
        }
    }
}

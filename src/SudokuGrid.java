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

    private boolean baseTen;

    private JPanel[][] subGridPanels;

    public SudokuGrid(int size) throws BadLocationException {
        super();

        this.grid = new JTextField[size][size];

        this.sudoBoard = new int[size][size];

        this.size = size;
        this.base = (int) Math.sqrt(size);

        this.baseTen = true;

        this.subGridPanels = new JPanel[base][base];

        setLayout(new GridLayout(base, base));

        initTFGrid();
        initPanelGrid();
    }

    private void initTFGrid() throws BadLocationException {
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                SquareTF square = new SquareTF(i, j, size);
                square.addKeyListener(new SquareKeyListener(this, square));

                AbstractDocument doc = (AbstractDocument) square.getDocument();
                doc.setDocumentFilter(new SquareDocFilter(this));
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
        } else if(dir == 'S') {
            if(sq.col == size - 1)
                grid[sq.row][0].requestFocus();
            else
                grid[sq.row][sq.col+1].requestFocus();
        }
    }

    public boolean getBaseTen() {
        return baseTen;
    }

    public void setBaseTen(boolean bool) {
        baseTen = bool;
    }
}

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import java.awt.*;

public class SudokuGrid extends JPanel{
    private JTextField[][] grid;

    public final int size;
    public final int base;

    private JPanel[][] subGridPanels;
    //private JPanel boardPanel;

    public SudokuGrid(int size) {
        this.grid = new JTextField[size][size];
        this.size = size;
        this.base = (int) Math.sqrt(size);

        this.subGridPanels = new JPanel[base][base];

        setLayout(new GridLayout(base, base));

        initTFGrid();
        initPanGrid();
    }

    private void initTFGrid() {
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JTextField square = new SquareTF(i, j);
                //addKeyListener(new SquareKeyListener());
                ((AbstractDocument) square.getDocument()).setDocumentFilter(new SquareDocFilter(size));

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

}

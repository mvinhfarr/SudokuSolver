import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SudokuGrid extends JPanel{
    private JTextField[][] grid;

    private int size;
    private int base;

    private JPanel[][] subGridPanels;
    //private JPanel boardPanel;

    public SudokuGrid(int size) {
        this.grid = new JTextField[size][size];
        this.size = size;
        this.base = (int) Math.sqrt(size);

        initGrid();

        this.subGridPanels = new JPanel[base][base];

        setLayout(new GridLayout(base, base));

        for(int i = 0; i < base; i++) {
            for(int j = 0; j < base; j++) {
                JPanel subGrid = new JPanel(new GridLayout(base, base));
                subGrid.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

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

    private void initGrid() {
        Border squareBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Dimension squareDim = new Dimension(30, 30);
        Font squareFont = new Font("Verdana", Font.PLAIN, 20);

        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JTextField square = new JTextField();

                square.setPreferredSize(squareDim);
                square.setBorder(squareBorder);
                square.setHorizontalAlignment(JTextField.CENTER);
                square.setFont(squareFont);

                grid[i][j] = square;
            }
        }
    }

}

import javax.swing.*;
import java.awt.*;

public class SudokuFrame extends JFrame{
    //private SudokuGrid grid;

    public SudokuFrame() {
        super("Sudoku Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);

        Container c = getContentPane();

        //JPanel container = new JPanel(new BoxLayout())

        SudokuGrid grid = new SudokuGrid(16);
        ButtonPanel buttons = new ButtonPanel(grid);

        c.add(grid, BorderLayout.CENTER);
        c.add(buttons, BorderLayout.SOUTH);

        pack();
    }

    public static void main(String[] args) {
        SudokuFrame sf = new SudokuFrame();
        sf.setVisible(true);
    }
}

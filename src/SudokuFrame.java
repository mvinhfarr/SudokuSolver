import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;

public class SudokuFrame {
    private JFrame f;
    //private SudokuGrid grid;

    public SudokuFrame() throws BadLocationException {
        this.f = new JFrame("Sudoku Solver");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600, 600);

        Container c = f.getContentPane();

        //JPanel container = new JPanel(new BoxLayout())

        SudokuGrid grid = new SudokuGrid(9);
        ButtonPanel buttons = new ButtonPanel(grid);

        c.add(grid, BorderLayout.CENTER);
        c.add(buttons, BorderLayout.SOUTH);

        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) throws BadLocationException {
        SudokuFrame sf = new SudokuFrame();
    }
}

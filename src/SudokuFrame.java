import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;

public class SudokuFrame {
    private JFrame f;
    private SudokuGrid grid;

    public SudokuFrame() throws BadLocationException {
        this.f = new JFrame("Sudoku Solver");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600, 600);

        Container c = f.getContentPane();

        this.grid = new SudokuGrid(9);

        c.add(grid);

        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) throws BadLocationException {
        SudokuFrame sf = new SudokuFrame();
    }
}

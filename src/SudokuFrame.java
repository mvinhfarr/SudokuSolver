import javax.swing.*;
import java.awt.*;

public class SudokuFrame {
    private JPanel p;

    private int size = 3;

    public SudokuFrame() {
        JFrame f = new JFrame("Sudoku Solver");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600, 600);
        //f.pack();
        f.setVisible(true);

        Container c = f.getContentPane();


        p = new JPanel(new GridLayout(size,size));
        p.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        // drawGrid();

        c.add(p);



    }

    private void drawGrid() {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                JPanel subGrid = new JPanel(new GridLayout(size, size));
                subGrid.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
                p.add(subGrid);
            }
        }
    }

    //private JTextField square() {

    //}

}

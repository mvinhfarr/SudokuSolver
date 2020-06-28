import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {
    private SudokuGrid grid;
    private JButton initBoard, validate, restart, solve;

    public ButtonPanel(SudokuGrid grid) {
        super(new BorderLayout());
        setSize(150, 150);

        this.grid = grid;

        this.initBoard = new JButton("Set Board");
        this.validate = new JButton("Validate");
        this.restart = new JButton("Restart");
        this.solve = new JButton("Solve");

        initBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        validate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        add(initBoard, BorderLayout.NORTH);
        add(restart, BorderLayout.WEST);
        add(validate, BorderLayout.EAST);
        add(solve, BorderLayout.SOUTH);
    }
}

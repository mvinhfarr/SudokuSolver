import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {
    private SudokuGrid grid;
    private JButton initBoard, validate, restart, solve;

    public ButtonPanel(SudokuGrid grid) {
        super(new GridLayout(2,2,4,4));

        Dimension buttonDim = new Dimension(40, 40);

        setSize(buttonDim.width*2, buttonDim.height*2);

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
                grid.solveSudo();
            }
        });


        initBoard.setPreferredSize(buttonDim);
        restart.setPreferredSize(buttonDim);
        validate.setPreferredSize(buttonDim);
        solve.setPreferredSize(buttonDim);

        add(initBoard);
        add(restart);
        add(validate);
        add(solve);
    }
}

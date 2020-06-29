import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class ButtonPanel extends JPanel {
    private SudokuGrid grid;
    private JButton restart, solve;
    private JButton validate;
    private JCheckBox baseTen;

    public ButtonPanel(SudokuGrid grid) {
        super(new GridLayout(2,2,4,4));

        Dimension buttonDim = new Dimension(40, 40);

        setSize(buttonDim.width*2, buttonDim.height*2);

        this.grid = grid;

        this.restart = new JButton("Restart");
        this.solve = new JButton("Solve");
        this.validate = new JButton("Validate");
        this.baseTen = new JCheckBox("Base Ten", true);

        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        solve.addActionListener(e -> grid.solveSudo());

        validate.addActionListener(e -> {

        });

        baseTen.addItemListener(e -> grid.setBaseTen(e.getStateChange() == ItemEvent.SELECTED));


        restart.setPreferredSize(buttonDim);
        solve.setPreferredSize(buttonDim);
        validate.setPreferredSize(buttonDim);

        add(restart);
        add(solve);
        add(validate);

        add(baseTen);
    }
}

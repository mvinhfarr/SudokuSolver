import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class ButtonPanel extends JPanel {
    public ButtonPanel(SudokuGrid grid) {
        super(new GridLayout(2,2,4,4));

        Dimension buttonDim = new Dimension(40, 40);

        setSize(buttonDim.width*2, buttonDim.height*2);

        JButton restart = new JButton("Restart");
        JButton solve = new JButton("Solve");
        JButton validate = new JButton("Validate");
        JCheckBox baseTen = new JCheckBox("Base Ten", grid.getBaseTen());

        restart.addActionListener(e -> {

        });

        solve.addActionListener(
                e -> grid.solveSudo()
        );

        validate.addActionListener(e -> {

        });

        baseTen.addItemListener(
                e -> grid.setBaseTen(e.getStateChange() == ItemEvent.SELECTED)
        );


        restart.setPreferredSize(buttonDim);
        solve.setPreferredSize(buttonDim);
        validate.setPreferredSize(buttonDim);

        add(restart);
        add(solve);
        add(validate);

        add(baseTen);
    }
}

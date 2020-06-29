import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SquareTF extends JTextField {
    public final int row, col;

    private final int size;

    private boolean inFocus;

    private int val;
    private boolean isValid;
    private boolean isFinal;

    private AbstractDocument doc;

    public SquareTF(int row, int col, int size) {
        super(); //why does it work even if i dont have this

        this.row = row;
        this.col = col;

        this.size = size;

        this.val = 0;
        this.isValid = true;
        this.isFinal = false;

        //highlight square yellow when in focus
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setBackground(new Color(250, 225, 100));
                inFocus = true;
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBackground(Color.WHITE);
                inFocus = false;
            }
        });

        setEditable(true);

        setFormat();
    }

    private void setFormat() {
        Border squareBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Dimension squareDim = new Dimension(75, 75);
        Font squareFont = new Font("Monospace", Font.PLAIN, squareDim.height-10);
        Caret blank = new DefaultCaret() {
            @Override
            public void paint(Graphics g) {
            }

            @Override
            public boolean isVisible() {
                return false;
            }

            @Override
            public boolean isSelectionVisible() {
                return false;
            }
        };

        setPreferredSize(squareDim);
        setBorder(squareBorder);
        setHorizontalAlignment(JTextField.CENTER);
        setFont(squareFont);
        setCaret(blank);
    }

    public void setVal(int val) {
        this.val = val;
        //set valid
    }

    public void setFinal(boolean isFinal) {
        //check if val == correct in solved board
            this.isFinal = isFinal;
    }

    public int getVal() {
        return val;
    }

    public boolean getValid() {
        return isValid;
    }

    public boolean getFinal() {
        return isFinal;
    }
}

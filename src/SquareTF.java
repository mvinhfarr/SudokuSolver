import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SquareTF extends JTextField {
    private final SudokuGrid grid;

    public final int row, col;

    //not in use
    private boolean inFocus;
    private boolean isValid;
    private boolean isFinal;

    public SquareTF(SudokuGrid grid, int row, int col) {
        super(); //why does it work even if i dont have this

        this.grid = grid;

        this.row = row;
        this.col = col;

        this.isValid = true;
        this.isFinal = false;

        //highlight square yellow when in focus
        //caret always at back
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setBackground(new Color(250, 225, 100));
                setCaretPosition(getDocument().getLength());
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBackground(Color.WHITE);
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setCaretPosition(getDocument().getLength());
                System.out.println(getCaretPosition());
            }
        });

        //move with arrow keys
        addKeyListener(new SquareKeyListener(this));
        //set restrictions on input
        ((AbstractDocument) getDocument()).setDocumentFilter(new SquareDocFilter(this));

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

    public SudokuGrid getParentGrid() {
        return grid;
    }



    public void setFinal(boolean isFinal) {
        //check if val == correct in solved board
        this.isFinal = isFinal;
    }

    public boolean getValid() {
        return isValid;
    }

    public boolean getFinal() {
        return isFinal;
    }
}

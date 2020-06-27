import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;

public class SquareTF extends JTextField {
    public final int row, col;

    private final int size;

    private int val;
    private boolean isValid;
    private boolean isFinal;

    private AbstractDocument doc;

    public SquareTF(int row, int col, int size) throws BadLocationException {
        super(); //why does it work even if i dont have this

        this.row = row;
        this.col = col;

        this.size = size;

        this.val = 0;
        this.isValid = true;
        this.isFinal = false;

        this.doc = (AbstractDocument) getDocument();
        doc.setDocumentFilter(new SquareDocFilter(size));

        setEditable(true);

        setFormat();
    }

    public SquareTF(int row, int col, int size, int val) {
        this.row = row;
        this.col = col;

        this.size = size;

        this.val = val;
        this.isValid = true;
        this.isFinal = true;

        setEditable(false);
        setFormat();
    }

    private void setFormat() {
        Border squareBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Dimension squareDim = new Dimension(75, 75);
        Font squareFont = new Font("Monospace", Font.PLAIN, squareDim.height-10);

        setPreferredSize(squareDim);
        setBorder(squareBorder);
        setHorizontalAlignment(JTextField.CENTER);
        setFont(squareFont);
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

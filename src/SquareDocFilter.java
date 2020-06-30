import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.event.KeyEvent;

public class SquareDocFilter extends DocumentFilter {
    private SudokuGrid grid;
    private int size;

    public SquareDocFilter(SudokuGrid grid) {
        super();
        this.grid = grid;
        this.size = grid.size;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        int currentLen = fb.getDocument().getLength();
        String newVal = testEntry("", string);

        if(newVal == null) return;

        remove(fb, 0, currentLen);
        super.insertString(fb, 0, newVal, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        int currentLen = fb.getDocument().getLength();
        String currentStr = fb.getDocument().getText(0, currentLen);
        String newVal = testEntry(currentStr, text);

        if(newVal == null) return;

        remove(fb, 0, currentLen);
        super.replace(fb, 0, length, newVal, attrs);
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, fb.getDocument().getLength(), length);
    }

    private String testEntry(String current, String newStr) {
        String s;
        int base;

        if(grid.getBaseTen()) { //squares read only in base ten
            s = current;
            s += newStr;
            base = 10;
        } else { //squares read in base = size
            s = newStr;
            s.toUpperCase();
            base = size+1; //because we reserve 0 as a blank value
        };

        try {
            int val = Integer.parseInt(s, base);

            if(val > 0 && val <= size) return s;
            else throw new NumberFormatException();
        } catch (NumberFormatException e) {
            //include check for base 16 & 25
            System.out.println(s + " " + base);
            return null;
        }
    }
}

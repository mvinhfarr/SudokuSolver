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
        if(string == null) return;

        String newVal = testEntry(fb.getDocument().getText(0, fb.getDocument().getLength()), string);
        remove(fb, offset, 0);
        super.insertString(fb, offset, newVal, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if(text == null) return;

        String newVal = testEntry(fb.getDocument().getText(0, fb.getDocument().getLength()), text);
        remove(fb, offset, length);
        super.replace(fb, offset, length, newVal, attrs);
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        //System.out.println("remove");
        super.remove(fb, fb.getDocument().getLength() - 1, fb.getDocument().getLength());
    }

    private String testEntry(String current, String newStr) {
        String s;
        int base;

        if(grid.getBaseTen()) {
            s = current;
            s += newStr;
            base = 10;
        } else {
            s = newStr;
            base = size+1;
        };

        try {
            int val = Integer.parseInt(s, base);

            if(val > 0 && val <= size) return s;
            else throw new NumberFormatException();
        } catch (NumberFormatException e) {
            //include check for base 16 & 25
            return "";
        }
    }
}

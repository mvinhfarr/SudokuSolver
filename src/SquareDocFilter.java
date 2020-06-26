import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class SquareDocFilter extends DocumentFilter {
    private final int validRange;

    public SquareDocFilter(int validRange) {
        super();
        this.validRange = validRange;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        System.out.println("hi");
        if(fb.getDocument().getLength() + string.length() == 1) {
            try {
                int val = Integer.parseInt(string);
                super.insertString(fb, offset, string, attr);
            } catch (NumberFormatException e) {
                System.out.println(string);
            }
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        super.replace(fb, offset, length, text, attrs);
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        //super.remove(fb, offset, length);
    }

    private boolean testEntry(String s) {
        try {
            int val = Integer.parseInt(s, 10);
            if(val >= 0 && val <= validRange) return true;
            else throw new NumberFormatException();
        } catch (NumberFormatException e) {
            //include check for base 16 & 25
            return false;
        }
    }
}

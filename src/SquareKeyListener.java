import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SquareKeyListener implements KeyListener {
    private final SudokuGrid grid;

    public SquareKeyListener(SquareTF sq) {
        this.grid = sq.getParentGrid();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //handle arrow keys
        if(e.getExtendedKeyCode() == KeyEvent.VK_UP) {
            grid.moveCursor((SquareTF) e.getSource(), 'N');
        } else if(e.getExtendedKeyCode() == KeyEvent.VK_DOWN) {
            grid.moveCursor((SquareTF) e.getSource(), 'S');
        } else if(e.getExtendedKeyCode() == KeyEvent.VK_LEFT) {
            grid.moveCursor((SquareTF) e.getSource(), 'W');
        } else if(e.getExtendedKeyCode() == KeyEvent.VK_RIGHT) {
            grid.moveCursor((SquareTF) e.getSource(), 'E');
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println("key released");
    }
}

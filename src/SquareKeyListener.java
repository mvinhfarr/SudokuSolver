import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class SquareKeyListener implements KeyListener {
    private SudokuGrid grid;
    private SquareTF sq;
    private int size;

    public SquareKeyListener(SudokuGrid grid, SquareTF sq) {
        this.grid = grid;
        this.sq = sq;
        this.size = grid.size;
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

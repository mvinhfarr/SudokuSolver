import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class SquareKeyListener implements KeyListener {
    private SudokuGrid grid;
    private int size;

    public SquareKeyListener(SudokuGrid grid) {
        this.grid = grid;
        this.size = grid.size;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //handle arrowkeys
        int[] set = new int[]{KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT,
            KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D};
        for(int c: set) {
            if(e.getExtendedKeyCode() == c) {
                grid.moveCursor((SquareTF) e.getSource(), e.getExtendedKeyCode());
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println("key released");
    }
}

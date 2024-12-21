package Controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter {
    public boolean leftPressed = false;  // Track left arrow key
    public boolean rightPressed = false; // Track right arrow key
    public boolean shootPressed = false; // Track spacebar key

    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println("Key Pressed: " + e.getKeyCode());
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> leftPressed = true;
            case KeyEvent.VK_RIGHT -> rightPressed = true;
            case KeyEvent.VK_SPACE -> shootPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // System.out.println("Key Pressed: " + e.getKeyCode());
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> leftPressed = false;
            case KeyEvent.VK_RIGHT -> rightPressed = false;
            case KeyEvent.VK_SPACE -> shootPressed = false;
        }
    }
}
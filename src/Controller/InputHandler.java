package Controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter {
    public boolean leftPressed = false;  // Track if the left arrow is pressed
    public boolean rightPressed = false; // Track if the right arrow is pressed

    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println("Key Pressed: " + e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // System.out.println("Key Released: " + e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }
}
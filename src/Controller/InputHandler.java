package Controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter {
    public boolean leftPressed = false;  // Track if the left arrow is pressed
    public boolean rightPressed = false; // Track if the right arrow is pressed

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> leftPressed = true; // Left arrow key
            case KeyEvent.VK_RIGHT -> rightPressed = true; // Right arrow key
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> leftPressed = false;
            case KeyEvent.VK_RIGHT -> rightPressed = false;
        }
    }
}
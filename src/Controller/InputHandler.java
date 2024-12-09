package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    private final GameController controller;

    public InputHandler(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> controller.getGameState().getPlayer().moveLeft();
            case KeyEvent.VK_RIGHT -> controller.getGameState().getPlayer().moveRight();
            case KeyEvent.VK_SPACE -> controller.getGameState().getPlayer().shoot();
            case KeyEvent.VK_ESCAPE -> controller.stopGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}

package View;

import Model.GameState;

import javax.swing.*;
import java.awt.*;

public class GameRenderer extends JFrame {
    public GameRenderer(GameState gameState) {
        setTitle("Space Invaders");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void render(GameState gameState) {
        // Redraw the game
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Example rendering logic (replace with actual game logic)
        // GameState gameState = ...; // Access current game state
        // g.setColor(Color.BLUE);
        // g.fillRect(gameState.getPlayer().getX(), gameState.getPlayer().getY(), 50, 10); // Render player
    }
}

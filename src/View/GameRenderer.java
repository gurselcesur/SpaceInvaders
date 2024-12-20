package View;

import Controller.InputHandler;
import Model.GameState;
import Model.Player;

import javax.swing.*;
import java.awt.*;


public class GameRenderer extends JPanel {
    private GameState gameState; // Reference to the game state
    private InputHandler inputHandler; // Input handler for player controls
    private Player player;

    public GameRenderer(GameState gameState, InputHandler inputHandler) {
        this.gameState = gameState;
        this.inputHandler = inputHandler;
        this.player = gameState.getPlayer(); // Initialize player after gameState is assigned
        System.out.println("GameRenderer object created");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Use Graphics2D for advanced rendering
        Graphics2D g2 = (Graphics2D) g;

        // Draw the game background
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Draw the player
        player.draw(g2);
    }
}
package View;

import Controller.InputHandler;
import Model.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GameRenderer extends JPanel {
    private GameState gameState; // Reference to the game state
    private InputHandler inputHandler; // Input handler for player controls
    private Player player;
    private FloatControl volumeControl; // Control for adjusting volume
    private BufferedImage backgroundImage; // Background image


    public GameRenderer(GameState gameState, InputHandler inputHandler) {
        this.gameState = gameState;
        this.inputHandler = inputHandler;
        this.player = gameState.getPlayer(); // Initialize player after gameState is assigned
        gameState.setPlayer(player);


        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("resources/img/Background.jpeg"));
        } catch (IOException e) {
            System.err.println("Background image not found: " + e.getMessage());
        }

        // Add key listener to capture inputs
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(inputHandler);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Use Graphics2D for advanced rendering
        Graphics2D g2 = (Graphics2D) g;

        // Draw the game background
        drawBackground(g2);

        // Draw the player
        drawPlayer(g2);

        // Draw bullets
        drawBullets(g2);

        // Draw enemies
        drawEnemies(g2);
    }

    /**
     * Draws the background.
     */
    private void drawBackground(Graphics2D g2) {
        if (backgroundImage != null) {
            g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        } else {
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    /**
     * Draws the player.
     */
    private void drawPlayer(Graphics2D g2) {
        player.draw(g2);
    }

    /**
     * Draws all bullets in the game.
     */
    private void drawBullets(Graphics2D g2) {
        List<Bullet> bullets = gameState.getBullets();
        for (Bullet bullet : bullets) {
            bullet.draw(g2);
        }
    }

    /**
     * Draws all enemies in the game.
     */
    private void drawEnemies(Graphics2D g2) {
        List<Enemy> enemies = gameState.getEnemies();
        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                enemy.draw(g2);
            }
        }
    }

    /**
     * Updates the game state and repaints the screen.
     */
    public void updateGame() {
        // Update game logic
        gameState.update();

        // Repaint the screen
        repaint();

        // Debugging
        System.out.println("GameRenderer repainted. Player position: (" + player.getX() + ", " + player.getY() + ")");
    }
}
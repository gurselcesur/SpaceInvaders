package View;

import Model.GameState;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private JButton pauseButton; // Button to pause the game
    private GameState gameState; // Reference to the game state
    private GameRenderer gameRenderer; // The game renderer

    public GameView(String username, GameState gameState, GameRenderer gameRenderer) {
        this.gameState = gameState;
        this.gameRenderer = gameRenderer;

        // Frame setup
        setTitle("Space Invaders");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centers the window on the screen
        setResizable(false);
        setLayout(new BorderLayout()); // Use BorderLayout for better layout management

        // Add the top panel with username and pause button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.DARK_GRAY);

        JLabel usernameLabel = new JLabel("Player: " + username);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        usernameLabel.setForeground(Color.WHITE);

        pauseButton = new JButton("Pause");
        pauseButton.setFont(new Font("Arial", Font.BOLD, 16));
        pauseButton.setBackground(Color.LIGHT_GRAY);
        pauseButton.setFocusPainted(false);

        topPanel.add(usernameLabel);
        topPanel.add(Box.createHorizontalGlue()); // Spacer to push pause button to the right
        topPanel.add(pauseButton);
        add(topPanel, BorderLayout.NORTH);

        // Add GameRenderer as the central game panel
        add(gameRenderer, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }

    public JButton getPauseButton() {
        return pauseButton;
    }
}
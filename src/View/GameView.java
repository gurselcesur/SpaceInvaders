package View;

import Model.GameState;
import Utils.SoundManager;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private JButton pauseButton; // Button to pause the game
    private JLabel scoreLabel; // Label to display the player's score
    private JLabel healthLabel; // Label to display the player's health
    private GameState gameState; // Reference to the game state
    private GameRenderer gameRenderer; // The game renderer
    private MainMenu mainMenu; // Reference to MainMenu
    private SoundManager soundManager; // SoundManager instance for audio management

    public GameView(String username, GameState gameState, GameRenderer gameRenderer, MainMenu mainMenu) {
        this.gameState = gameState;
        this.gameRenderer = gameRenderer;
        this.mainMenu = mainMenu;
        soundManager = SoundManager.getInstance();

        // Frame setup
        setTitle("Space Invaders");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centers the window on the screen
        setResizable(false);
        setLayout(new BorderLayout()); // Use BorderLayout for better layout management

        // Add the top panel with username, score, health, and pause button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.DARK_GRAY);

        JLabel usernameLabel = new JLabel("Player: " + username);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        usernameLabel.setForeground(Color.WHITE);

        scoreLabel = new JLabel("Score: " + gameState.getScore());
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        scoreLabel.setForeground(Color.WHITE);

        healthLabel = new JLabel("Health: " + gameState.getPlayer().getHealth());
        healthLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        healthLabel.setForeground(Color.WHITE);

        pauseButton = new JButton("Pause");
        pauseButton.setFont(new Font("Arial", Font.BOLD, 16));
        pauseButton.setBackground(Color.LIGHT_GRAY);
        pauseButton.setFocusPainted(false);

        topPanel.add(usernameLabel);
        topPanel.add(Box.createHorizontalStrut(20)); // Spacer between username and score
        topPanel.add(scoreLabel);
        topPanel.add(Box.createHorizontalStrut(20)); // Spacer between score and health
        topPanel.add(healthLabel);
        topPanel.add(Box.createHorizontalGlue()); // Spacer to push pause button to the right
        topPanel.add(pauseButton);
        add(topPanel, BorderLayout.NORTH);

        // Add GameRenderer as the central game panel
        add(gameRenderer, BorderLayout.CENTER);
        // Ensure GameRenderer gains focus
        SwingUtilities.invokeLater(gameRenderer::requestFocusInWindow);

        soundManager.playBackgroundMusic("resources/sound/Unorganic Asteroid Beat.wav");
        // Start threads to update the score and health dynamically
        startScoreUpdater();
        startHealthUpdater();

        // Make the frame visible
        setVisible(true);
    }

    public JButton getPauseButton() {
        return pauseButton;
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }

    private void startScoreUpdater() {
        Timer timer = new Timer(100, e -> updateScore()); // Update score every 100 milliseconds
        timer.start();
    }

    public void updateScore() {
        scoreLabel.setText("Score: " + gameState.getScore());
    }

    private void startHealthUpdater() {
        Timer timer = new Timer(100, e -> updateHealth()); // Update health every 100 milliseconds
        timer.start();
    }

    public void updateHealth() {
        healthLabel.setText("Health: " + gameState.getPlayer().getHealth());
    }
}

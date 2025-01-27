package View;

import Model.GameState;
import Utils.SoundManager;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private JButton pauseButton; // Button to pause the game
    private JLabel scoreLabel; // Label to display the player's score
    private JLabel healthLabel; // Label to display the player's health
    private JLabel stageLabel; // Label to display the game stage
    private GameState gameState; // Reference to the game state
    private GameRenderer gameRenderer; // The game renderer
    private MainMenu mainMenu; // Reference to MainMenu
    private SoundManager soundManager; // SoundManager instance for audio management
    private JSlider soundControlSlider; // Slider to control sound volume
    private boolean isGameViewOn = false;

    public GameView(String username, GameState gameState, GameRenderer gameRenderer, MainMenu mainMenu) {
        this.isGameViewOn = true;
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

        // Add the top panel with username, score, health, pause button, and sound control
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.BLACK); // Transparent background

        JLabel usernameLabel = new JLabel("Player: " + username);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        usernameLabel.setForeground(Color.WHITE);

        scoreLabel = new JLabel("Score: " + gameState.getScore());
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        scoreLabel.setForeground(Color.WHITE);

        healthLabel = new JLabel("Health: " + gameState.getPlayer().getHealth());
        healthLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        healthLabel.setForeground(Color.WHITE);

        stageLabel = new JLabel("Stage = " + gameState.getStageNumber());
        stageLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        stageLabel.setForeground(Color.WHITE);

        pauseButton = new JButton("Pause");
        pauseButton.setFont(new Font("Arial", Font.BOLD, 16));
        pauseButton.setBackground(Color.LIGHT_GRAY);
        pauseButton.setFocusPainted(false);

        // Sound control slider
        soundControlSlider = new JSlider(0, 100, 50); // Min: 0, Max: 100, Default: 50
        soundControlSlider.setPreferredSize(new Dimension(150, 20));
        soundControlSlider.setOpaque(true);
        soundControlSlider.setBackground(Color.DARK_GRAY); // Example background color
        soundControlSlider.setForeground(Color.WHITE);     // Example foreground color for ticks and labels
        soundControlSlider.addChangeListener(e -> adjustSoundVolume(soundControlSlider.getValue()));

        JLabel soundLabel = new JLabel("Volume");
        soundLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        soundLabel.setForeground(Color.WHITE);

        topPanel.add(usernameLabel);
        topPanel.add(Box.createHorizontalStrut(20)); // Spacer between username and score
        topPanel.add(scoreLabel);
        topPanel.add(Box.createHorizontalStrut(20)); // Spacer between score and health
        topPanel.add(healthLabel);
        topPanel.add(Box.createHorizontalStrut(20)); // Spacer between score and health
        topPanel.add(stageLabel);
        topPanel.add(Box.createHorizontalStrut(20)); // Spacer between health and volume
        topPanel.add(soundLabel);
        topPanel.add(soundControlSlider);
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
        startStageUpdater();

        // Make the frame visible
        setVisible(true);
    }

    public boolean isGameViewOn() {
        return isGameViewOn;
    }

    public void setGameViewOn(boolean gameViewOn) {
        isGameViewOn = gameViewOn;
    }

    public JButton getPauseButton() {
        return pauseButton;
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }

    /**
     * Adjusts the sound volume based on the slider value.
     *
     * @param volume The volume level (0 to 100).
     */
    private void adjustSoundVolume(int volume) {
        float adjustedVolume = volume / 100.0f; // Convert to 0.0 to 1.0 range
        soundManager.setVolume(adjustedVolume); // Assuming SoundManager has a setVolume method

        SwingUtilities.invokeLater(() -> gameRenderer.requestFocusInWindow());
    }

    private void startScoreUpdater() {
        Timer timer = new Timer(100, e -> updateScore()); // Update score every 100 milliseconds
        timer.start();
    }


    private void startStageUpdater() {
        Timer timer = new Timer(100, e -> updateStage()); // Update health every 100 milliseconds
        timer.start();
    }

    public void updateScore() {
        scoreLabel.setText("Score: " + gameState.getScore());
    }

    public void updateStage(){ stageLabel.setText("Stage: " + gameState.getStageNumber()); }

    private void startHealthUpdater() {
        Timer timer = new Timer(100, e -> updateHealth()); // Update health every 100 milliseconds
        timer.start();
    }

    public void updateHealth() {
        healthLabel.setText("Health: " + gameState.getPlayer().getHealth());
    }
}
package View;

import Utils.SoundManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JFrame {
    private JTextField usernameField;    // Text field for username input
    private JButton startGameButton;    // Start Game button
    private JButton showScoreboardButton; // Show Scoreboard button
    private final SoundManager soundManager; // SoundManager instance
    private JSlider soundControlSlider; // Slider to control sound volume

    private Image backgroundImage;  // Field to store the background image


    public MainMenu() {
        soundManager = SoundManager.getInstance();

        // Frame setup
        setTitle("Space Invaders");
        setSize(700, 500);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("resources/img/mainMenu.png"));
        } catch (IOException e) {
            System.err.println("Background image not found: " + e.getMessage());
        }

        // Create a custom JPanel to handle background image
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);  // Call to the super method to paint components
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        panel.setLayout(null);  // Use absolute positioning for components
        setContentPane(panel);  // Set the custom panel as the content pane

        // Welcome user label
        JLabel welcomeLabel = new JLabel("Welcome to Space Invaders!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 32));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setBounds(50, 45, 600, 50); // Daha geniş bir alan ver
        add(welcomeLabel);

        // Username label
        JLabel usernameLabel = new JLabel("Enter your username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(200, 150, 200, 30);
        add(usernameLabel);

        // Username text field
        usernameField = new JTextField();
        usernameField.setBounds(400, 150, 150, 30);
        add(usernameField);

        // Start Game button
        startGameButton = new JButton("Start Game");
        startGameButton.setFont(new Font("Arial", Font.BOLD, 16));
        startGameButton.setBounds(250, 250, 200, 40);
        add(startGameButton);

        // Show Scoreboard button
        showScoreboardButton = new JButton("Show Scoreboard");
        showScoreboardButton.setFont(new Font("Arial", Font.BOLD, 16));
        showScoreboardButton.setBounds(250, 320, 200, 40);
        add(showScoreboardButton);

        // Sound control slider
        soundControlSlider = new JSlider(0, 100, 50); // Min: 0, Max: 100, Default: 50
        soundControlSlider.setBounds(350, 400, 150, 30); // Set position and size
        soundControlSlider.setPaintTicks(true); // Enable tick marks
        soundControlSlider.setPaintLabels(true); // Enable labels
        soundControlSlider.addChangeListener(e -> adjustSoundVolume(soundControlSlider.getValue()));
        add(soundControlSlider);

        // Volume label
        JLabel soundLabel = new JLabel("Volume:");
        soundLabel.setForeground(Color.WHITE);
        soundLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        soundLabel.setBounds(250, 400, 100, 30); // Position the label
        add(soundLabel);

        // Play background music
        soundManager.playBackgroundMusic("resources/sound/SpaceWeed319.wav"); // Load background music
        soundManager.loop(); // Start looping background music

        // Make the frame visible
        setVisible(true);
    }

    // Getter methods
    public JTextField getUsernameField() {
        return usernameField;
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }

    public JButton getStartGameButton() {
        return startGameButton;
    }

    public JButton getShowScoreboardButton() {
        return showScoreboardButton;
    }

    private void adjustSoundVolume(int volume) {
        float adjustedVolume = volume / 100.0f; // Convert to 0.0 to 1.0 range
        soundManager.setVolume(adjustedVolume); // Assuming SoundManager has a setVolume method
    }
}

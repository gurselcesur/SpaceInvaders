package View;

import Utils.SoundManager;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    private JTextField usernameField;    // Text field for username input
    private JButton startGameButton;    // Start Game button
    private JButton showScoreboardButton; // Show Scoreboard button
    private JButton lowerSoundButton;    // Lower volume button
    private JButton higherSoundButton;   // Increase volume button
    private final SoundManager soundManager; // SoundManager instance

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

        // Welcome user label
        JLabel welcomeLabel = new JLabel("Welcome to Space Invaders!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setBounds(150, 50, 400, 50);
        add(welcomeLabel);

        // Username label
        JLabel usernameLabel = new JLabel("Enter your username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
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

        // Lower sound button
        lowerSoundButton = new JButton("Lower Sound");
        lowerSoundButton.setFont(new Font("Arial", Font.BOLD, 14));
        lowerSoundButton.setBounds(150, 400, 150, 40);
        add(lowerSoundButton);

        // Higher sound button
        higherSoundButton = new JButton("Higher Sound");
        higherSoundButton.setFont(new Font("Arial", Font.BOLD, 14));
        higherSoundButton.setBounds(400, 400, 150, 40);
        add(higherSoundButton);

        // Attach button listeners for volume control
        lowerSoundButton.addActionListener(e -> soundManager.decreaseVolume());
        higherSoundButton.addActionListener(e -> soundManager.increaseVolume());

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
    public JButton getLowerSoundButton(){
        return lowerSoundButton;
    }
    public JButton getHigherSoundButton(){
        return higherSoundButton;
    }
}

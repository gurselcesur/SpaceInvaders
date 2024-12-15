package View;

import javax.swing.*;
import java.awt.*;

public class GameRenderer extends JFrame {
    private JButton pauseButton; // Button to pause the game

    public GameRenderer(String username) {
        // Frame setup
        setTitle("Space Invaders - Game Screen");
        setSize(800, 600);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Display username
        JLabel usernameLabel = new JLabel("Player: " + username);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        usernameLabel.setBounds(10, 10, 200, 30);
        add(usernameLabel);

        // Add Pause button
        pauseButton = new JButton("Pause");
        pauseButton.setFont(new Font("Arial", Font.BOLD, 16));
        pauseButton.setBounds(650, 10, 100, 40);
        add(pauseButton);

        // Placeholder for game canvas (to be implemented later)
        JLabel gamePlaceholder = new JLabel("Game Screen Placeholder");
        gamePlaceholder.setFont(new Font("Arial", Font.PLAIN, 24));
        gamePlaceholder.setHorizontalAlignment(SwingConstants.CENTER);
        gamePlaceholder.setBounds(100, 100, 600, 400);
        add(gamePlaceholder);

        // Make the frame visible
        setVisible(true);
    }

    public JButton getPauseButton() {
        return pauseButton;
    }
}